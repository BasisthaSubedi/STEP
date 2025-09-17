package Encapsulation;

// HospitalManagement.java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * HospitalManagement.java
 * Single-file implementation for Assignment Problem 1.
 */
public class HospitalManagement {

    // ---------------------------
    // Immutable MedicalRecord
    // ---------------------------
    public static final class MedicalRecord {
        private final String recordId;
        private final String patientDNA;
        private final String[] allergies;
        private final String[] medicalHistory;
        private final LocalDate birthDate;
        private final String bloodType;

        // Simple HIPAA-like validation example
        private static void validate(String recordId, String patientDNA, LocalDate birthDate, String bloodType) {
            if (recordId == null || recordId.isBlank()) throw new IllegalArgumentException("recordId required");
            if (patientDNA == null || patientDNA.length() < 8) throw new IllegalArgumentException("Invalid DNA");
            if (birthDate == null || birthDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Invalid birthDate");
            if (bloodType == null || bloodType.isBlank()) throw new IllegalArgumentException("bloodType required");
        }

        public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory,
                             LocalDate birthDate, String bloodType) {
            validate(recordId, patientDNA, birthDate, bloodType);
            this.recordId = recordId;
            this.patientDNA = patientDNA;
            this.allergies = allergies == null ? new String[0] : Arrays.copyOf(allergies, allergies.length);
            this.medicalHistory = medicalHistory == null ? new String[0] : Arrays.copyOf(medicalHistory, medicalHistory.length);
            this.birthDate = birthDate;
            this.bloodType = bloodType;
        }

        public String getRecordId() { return recordId; }
        public String getPatientDNA() { return patientDNA; }
        public LocalDate getBirthDate() { return birthDate; }
        public String getBloodType() { return bloodType; }

        // Defensive copies for arrays
        public String[] getAllergies() { return Arrays.copyOf(allergies, allergies.length); }
        public String[] getMedicalHistory() { return Arrays.copyOf(medicalHistory, medicalHistory.length); }

        // final to prevent overriding for safety
        public final boolean isAllergicTo(String substance) {
            if (substance == null) return false;
            for (String s : allergies) if (substance.equalsIgnoreCase(s)) return true;
            return false;
        }

        @Override
        public String toString() {
            return "MedicalRecord{" +
                    "recordId='" + recordId + '\'' +
                    ", birthDate=" + birthDate +
                    ", bloodType='" + bloodType + '\'' +
                    ", allergies=" + Arrays.toString(allergies) +
                    ", history=" + Arrays.toString(medicalHistory) +
                    '}';
        }
    }

    // ---------------------------
    // Patient class with privacy
    // ---------------------------
    public static class Patient {
        private final String patientId;
        private final MedicalRecord medicalRecord; // PHI - immutable inner object
        private String currentName;
        private String emergencyContact;
        private String insuranceInfo;
        private int roomNumber;
        private String attendingPhysician;

        // Constructor for emergency admission: minimal data, generate temp id
        public Patient(String currentName) {
            this.patientId = "TEMP-" + UUID.randomUUID().toString();
            this.medicalRecord = null; // unknown at emergency
            this.currentName = Objects.requireNonNullElse(currentName, "Unknown");
            this.emergencyContact = null;
            this.insuranceInfo = null;
            this.roomNumber = -1;
            this.attendingPhysician = null;
            // In a real system, log audit, mark as temporary record
        }

        // Standard admission (full info)
        public Patient(String patientId, MedicalRecord medicalRecord,
                       String currentName, String emergencyContact, String insuranceInfo,
                       int roomNumber, String attendingPhysician) {
            this.patientId = Objects.requireNonNull(patientId);
            this.medicalRecord = medicalRecord; // medicalRecord already validated when created
            this.currentName = currentName;
            this.emergencyContact = emergencyContact;
            this.insuranceInfo = insuranceInfo;
            this.roomNumber = roomNumber;
            this.attendingPhysician = attendingPhysician;
            validatePrivacyRules();
        }

        // Transfer admission: imports existing record, generates new patient wrapper
        public Patient(MedicalRecord existingRecord, String currentName, int roomNumber) {
            this.patientId = "TR-" + UUID.randomUUID().toString();
            this.medicalRecord = existingRecord;
            this.currentName = currentName;
            this.emergencyContact = null;
            this.insuranceInfo = null;
            this.roomNumber = roomNumber;
            this.attendingPhysician = null;
            validatePrivacyRules();
        }

        // Package-private method: basic info for staff in same package (hospital staff)
        String getBasicInfo() {
            return "Patient[ID=" + patientId + ", name=" + currentName + ", room=" + roomNumber +
                    ", attending=" + attendingPhysician + "]";
        }

        // Public: only non-sensitive public info
        public String getPublicInfo() {
            return "PatientPublic{name='" + currentName + "', room=" + roomNumber + "}";
        }

        // Getters for allowed data with controlled access
        public String getPatientId() { return patientId; }

        // Provide access to medical record only to authorized staff via HospitalSystem.validateStaffAccess
        MedicalRecord getMedicalRecordForInternalUse() { return medicalRecord; } // package-private to restrict casual access

        // Validated setters for modifiable personal data
        public void setCurrentName(String currentName) {
            if (currentName == null || currentName.isBlank()) throw new IllegalArgumentException("Invalid name");
            this.currentName = currentName;
        }
        public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
        public void setInsuranceInfo(String insuranceInfo) { this.insuranceInfo = insuranceInfo; }
        public void setRoomNumber(int roomNumber) {
            if (roomNumber < -1) throw new IllegalArgumentException("Invalid room");
            this.roomNumber = roomNumber;
        }
        public void setAttendingPhysician(String attendingPhysician) { this.attendingPhysician = attendingPhysician; }

        private void validatePrivacyRules() {
            // Example validation: if medical record exists, patientId must not be TEMP
            if (medicalRecord != null && patientId.startsWith("TEMP-")) {
                throw new IllegalStateException("Transfer/standard record cannot have temporary ID");
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Patient{patientId=").append(patientId);
            sb.append(", name=").append(currentName);
            sb.append(", room=").append(roomNumber);
            // Include an audit note about whether medical record is present but do not print PHI
            sb.append(", hasMedicalRecord=").append(medicalRecord != null);
            sb.append(", attending=").append(attendingPhysician);
            sb.append("}");
            return sb.toString();
        }
    }

    // ---------------------------
    // Medical Staff classes
    // ---------------------------
    public static class Doctor {
        private final String licenseNumber;
        private final String specialty;
        private final Set<String> certifications;

        public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
            this.licenseNumber = Objects.requireNonNull(licenseNumber);
            this.specialty = specialty;
            this.certifications = certifications == null ? new HashSet<>() : new HashSet<>(certifications);
        }

        public String getLicenseNumber() { return licenseNumber; }
        public String getSpecialty() { return specialty; }
        public Set<String> getCertifications() { return Collections.unmodifiableSet(certifications); }

        @Override public String toString() { return "Doctor[" + licenseNumber + "," + specialty + "]"; }
    }

    public static class Nurse {
        private final String nurseId;
        private final String shift;
        private final List<String> qualifications;

        public Nurse(String nurseId, String shift, List<String> qualifications) {
            this.nurseId = Objects.requireNonNull(nurseId);
            this.shift = shift;
            this.qualifications = qualifications == null ? new ArrayList<>() : new ArrayList<>(qualifications);
        }

        public String getNurseId() { return nurseId; }
        public String getShift() { return shift; }
        public List<String> getQualifications() { return Collections.unmodifiableList(qualifications); }

        @Override public String toString() { return "Nurse[" + nurseId + "," + shift + "]"; }
    }

    public static class Administrator {
        private final String adminId;
        private final List<String> accessPermissions;

        public Administrator(String adminId, List<String> accessPermissions) {
            this.adminId = Objects.requireNonNull(adminId);
            this.accessPermissions = accessPermissions == null ? new ArrayList<>() : new ArrayList<>(accessPermissions);
        }

        public String getAdminId() { return adminId; }
        public List<String> getAccessPermissions() { return Collections.unmodifiableList(accessPermissions); }

        @Override public String toString() { return "Administrator[" + adminId + "]"; }
    }

    // ---------------------------
    // HospitalSystem with access control
    // ---------------------------
    public static class HospitalSystem {
        // policies and privacy rules
        public static final int MAX_ROOM_NUMBER = 1000;
        public static final String HOSPITAL_NAME = "OpenAI General (Demo)";
        private static final Set<String> ADMIN_PRIVILEGES = Set.of("VIEW_ALL", "MANAGE_STAFF");

        // patient registry: patientId -> Patient
        private final Map<String, Patient> patientRegistry = new HashMap<>();

        // Admit a patient (staff can be Doctor/Nurse/Administrator)
        public boolean admitPatient(Patient patient, Object staff) {
            if (patient == null) throw new IllegalArgumentException("patient required");
            if (!validateStaffAcceptance(staff)) return false;
            patientRegistry.put(patient.getPatientId(), patient);
            // small audit print
            System.out.println("Admitted: " + patient.getPatientId() + " by " + staff);
            return true;
        }

        // Package-private: internal operation to fetch patient (used by staff after access validation)
        Patient getPatientInternal(String patientId) { return patientRegistry.get(patientId); }

        // Check whether staff is allowed to admit or access registry
        private boolean validateStaffAcceptance(Object staff) {
            return (staff instanceof Doctor) || (staff instanceof Nurse) || (staff instanceof Administrator);
        }

        // Validate detailed staff access to a patient's PHI (medical record)
        private boolean validateStaffAccess(Object staff, Patient patient) {
            if (patient == null) return false;
            if (staff instanceof Administrator) {
                Administrator admin = (Administrator) staff;
                // check admin permissions
                return admin.getAccessPermissions().contains("VIEW_ALL");
            } else if (staff instanceof Doctor) {
                Doctor doc = (Doctor) staff;
                // Doctors can view medical record if they are the attending physician or by specialty match
                if (patient.attendingPhysician != null && patient.attendingPhysician.equals(doc.getLicenseNumber()))
                    return true;
                // Otherwise allow if specialty is not null (simplified)
                return doc.getSpecialty() != null;
            } else if (staff instanceof Nurse) {
                Nurse nurse = (Nurse) staff;
                // Nurses can view limited PHI for their shift/assigned wards (simplified)
                return nurse.getQualifications().size() > 0;
            }
            return false;
        }

        // Public method to request medical record access (returns the medical record if allowed)
        public Optional<MedicalRecord> requestMedicalRecord(Object staff, String patientId) {
            Patient patient = patientRegistry.get(patientId);
            if (patient == null) return Optional.empty();
            if (validateStaffAccess(staff, patient)) {
                // Note: we return the immutable MedicalRecord (safe by design)
                return Optional.ofNullable(patient.getMedicalRecordForInternalUse());
            } else {
                System.out.println("Access denied for staff: " + staff);
                return Optional.empty();
            }
        }

        // Package-private internal maintenance method (example)
        void internalRoomReassignment(String patientId, int newRoom) {
            Patient p = patientRegistry.get(patientId);
            if (p != null) p.setRoomNumber(newRoom);
        }

        @Override
        public String toString() {
            return "HospitalSystem[" + HOSPITAL_NAME + ", patients=" + patientRegistry.size() + "]";
        }
    }

    // ---------------------------
    // Demo
    // ---------------------------
    public static void main(String[] args) {
        System.out.println("=== HospitalManagement Demo ===");
        HospitalSystem hs = new HospitalSystem();

        // Create immutable medical record
        MedicalRecord rec = new MedicalRecord(
                "MR-1001",
                "AACTGCTA", // dummy DNA string (must be >= 8 chars per validator)
                new String[]{"Penicillin"},
                new String[]{"Appendectomy 2019"},
                LocalDate.of(1990, 5, 20),
                "O+"
        );

        // Standard patient
        Patient p1 = new Patient("P-1001", rec, "John Doe", "Jane:555-0100", "InsureCo-AB", 101, "LIC-DR-001");

        // Emergency (minimal) patient
        Patient p2 = new Patient("Unknown Patient");

        // Staff
        Doctor dr = new Doctor("LIC-DR-001", "Cardiology", Set.of("ACLS"));
        Nurse nurse = new Nurse("N-200", "Night", List.of("RN", "BLS"));
        Administrator admin = new Administrator("ADM1", List.of("VIEW_ALL"));

        // Admit patients (role checked)
        hs.admitPatient(p1, dr);
        hs.admitPatient(p2, nurse);

        // Request medical record: doctor should get access (license matches attending)
        Optional<MedicalRecord> drView = hs.requestMedicalRecord(dr, p1.getPatientId());
        System.out.println("Doctor view of p1 record: " + drView);

        // Nurse request
        Optional<MedicalRecord> nurseView = hs.requestMedicalRecord(nurse, p1.getPatientId());
        System.out.println("Nurse view of p1 record: " + nurseView);

        // Admin request (VIEW_ALL)
        Optional<MedicalRecord> adminView = hs.requestMedicalRecord(admin, p1.getPatientId());
        System.out.println("Admin view of p1 record: " + adminView);

        // Audit print
        System.out.println("Patient public info: " + p1.getPublicInfo());
        System.out.println("Patient basic info (staff view): " + p1.getBasicInfo());
        System.out.println("System state: " + hs);
    }
}
