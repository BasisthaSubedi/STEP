package Encapsulation;

public class Problem2 {
    static class AccessModifierDemo {
        private int privateField;
        String defaultField;
        protected double protectedField;
        public boolean publicField;

        public AccessModifierDemo(int p, String d, double pr, boolean pub) {
            privateField = p; defaultField = d; protectedField = pr; publicField = pub;
        }
        private void privateMethod() { System.out.println("Private method"); }
        void defaultMethod() { System.out.println("Default method"); }
        protected void protectedMethod() { System.out.println("Protected method"); }
        public void publicMethod() { System.out.println("Public method"); }
    }

    // Simulating another package (like com.company.main)
    static class PackageTestMain {
        public static void test() {
            AccessModifierDemo demo = new AccessModifierDemo(1,"X",1.1,true);
            // demo.privateField;  // ❌
            // demo.defaultField;  // ❌
            // demo.protectedField; // ❌ (not subclass here)
            System.out.println(demo.publicField); // ✅
            demo.publicMethod(); // ✅
        }
    }

    // Simulating subclass in another package
    static class ExtendedDemo extends AccessModifierDemo {
        public ExtendedDemo() { super(2,"Y",2.2,false); }
        public void testInheritedAccess() {
            // privateField ❌
            // defaultField ❌
            protectedField = 3.3;  // ✅
            publicField = true;    // ✅
            protectedMethod();     // ✅
            publicMethod();        // ✅
        }
    }

    public static void main(String[] args) {
        PackageTestMain.test();
        ExtendedDemo child = new ExtendedDemo();
        child.testInheritedAccess();
    }
}
