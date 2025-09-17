package Encapsulation;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Problem4 {
    static class EmployeeBean implements Serializable {
        private String employeeId, firstName, lastName, department;
        private double salary;
        private Date hireDate;
        private boolean isActive;

        public EmployeeBean() {}
        public EmployeeBean(String id,String f,String l,double s,String d,Date h,boolean a) {
            employeeId=id; firstName=f; lastName=l; setSalary(s); department=d; hireDate=h; isActive=a;
        }

        public String getEmployeeId(){return employeeId;}
        public void setEmployeeId(String id){employeeId=id;}
        public String getFirstName(){return firstName;}
        public void setFirstName(String f){firstName=f;}
        public String getLastName(){return lastName;}
        public void setLastName(String l){lastName=l;}
        public double getSalary(){return salary;}
        public void setSalary(double s){if(s>=0)salary=s;else throw new IllegalArgumentException();}
        public String getDepartment(){return department;}
        public void setDepartment(String d){department=d;}
        public Date getHireDate(){return hireDate;}
        public void setHireDate(Date h){hireDate=h;}
        public boolean isActive(){return isActive;}
        public void setActive(boolean a){isActive=a;}

        // Computed
        public String getFullName(){return firstName+" "+lastName;}
        public int getYearsOfService(){return new Date().getYear()-hireDate.getYear();}
        public String getFormattedSalary(){return String.format("₹%.2f",salary);}
        public void setFullName(String full){String[]p=full.split(" ");if(p.length>=2){firstName=p[0];lastName=p[1];}}

        @Override public String toString(){return employeeId+" "+getFullName()+" "+getFormattedSalary();}
        @Override public boolean equals(Object o){if(!(o instanceof EmployeeBean))return false;return Objects.equals(employeeId,((EmployeeBean)o).employeeId);}
        @Override public int hashCode(){return Objects.hash(employeeId);}
    }

    public static void main(String[] args) {
        EmployeeBean emp=new EmployeeBean("E1","Alice","Smith",50000,"IT",new Date(),true);
        System.out.println(emp);
        emp.setFullName("Alice Johnson");
        System.out.println("Updated: "+emp.getFullName()+" Salary="+emp.getFormattedSalary());
    }
}
