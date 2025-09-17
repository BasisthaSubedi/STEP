public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");
        long start, end;
        start = System.nanoTime();
        concatenateWithString(10000);
        end = System.nanoTime();
        System.out.println("String time: " + (end - start) + " ns");
        start = System.nanoTime();
        concatenateWithStringBuilder(10000);
        end = System.nanoTime();
        System.out.println("StringBuilder time: " + (end - start) + " ns");
        start = System.nanoTime();
        concatenateWithStringBuffer(10000);
        end = System.nanoTime();
        System.out.println("StringBuffer time: " + (end - start) + " ns");
        demonstrateStringBuilderMethods();
        compareStringComparisonMethods();
    }

    public static void concatenateWithString(int times) {
        String s = "";
        for (int i = 0; i < times; i++) {
            s += "a";
        }
    }

    public static void concatenateWithStringBuilder(int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append("a");
        }
    }

    public static void concatenateWithStringBuffer(int times) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sb.append("a");
        }
    }

    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Java");
        sb.append(" Programming");
        sb.insert(5, "Language ");
        sb.replace(0, 4, "JAVA");
        sb.delete(5, 13);
        sb.reverse();
        System.out.println("StringBuilder Result: " + sb.toString());
    }

    public static void compareStringComparisonMethods() {
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");
        System.out.println("s1 == s2: " + (s1 == s2));
        System.out.println("s1 == s3: " + (s1 == s3));
        System.out.println("s1.equals(s3): " + s1.equals(s3));
    }
}
