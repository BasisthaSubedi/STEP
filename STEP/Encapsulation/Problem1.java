public class Problem1 {
    static class AccessModifierDemo {
        private int privateField;
        String defaultField;         // package-private
        protected double protectedField;
        public boolean publicField;

        public AccessModifierDemo(int privateField, String defaultField,
                                  double protectedField, boolean publicField) {
            this.privateField = privateField;
            this.defaultField = defaultField;
            this.protectedField = protectedField;
            this.publicField = publicField;
        }

        private void privateMethod() { System.out.println("Private method called"); }
        void defaultMethod() { System.out.println("Default method called"); }
        protected void protectedMethod() { System.out.println("Protected method called"); }
        public void publicMethod() { System.out.println("Public method called"); }

        public void testInternalAccess() {
            System.out.println("--- Internal Access ---");
            System.out.println("privateField = " + privateField);
            System.out.println("defaultField = " + defaultField);
            System.out.println("protectedField = " + protectedField);
            System.out.println("publicField = " + publicField);
            privateMethod();
            defaultMethod();
            protectedMethod();
            publicMethod();
        }
    }

    static class SamePackageTest {
        public static void testAccess() {
            AccessModifierDemo demo = new AccessModifierDemo(1, "World", 5.5, false);
            // System.out.println(demo.privateField);   // ❌ Not accessible
            System.out.println(demo.defaultField);      // ✅ Works
            System.out.println(demo.protectedField);    // ✅ Works
            System.out.println(demo.publicField);       // ✅ Works
            demo.defaultMethod();    // ✅ Works
            demo.protectedMethod();  // ✅ Works
            demo.publicMethod();     // ✅ Works
        }
    }

    public static void main(String[] args) {
        AccessModifierDemo demo = new AccessModifierDemo(10, "Hello", 99.9, true);
        demo.testInternalAccess();
        SamePackageTest.testAccess();
    }
}
