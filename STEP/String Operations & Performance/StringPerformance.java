import java.util.*;

public class StringPerformance {
    static long concatString(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) s += "x";
        return System.currentTimeMillis() - start;
    }
    static long concatBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append("x");
        return System.currentTimeMillis() - start;
    }
    static long concatBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) sb.append("x");
        return System.currentTimeMillis() - start;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int n = sc.nextInt();
        long t1 = concatString(n);
        long t2 = concatBuilder(n);
        long t3 = concatBuffer(n);
        System.out.printf("%-15s %-20s\n","Method","Time(ms)");
        System.out.printf("%-15s %-20d\n","String",t1);
        System.out.printf("%-15s %-20d\n","StringBuilder",t2);
        System.out.printf("%-15s %-20d\n","StringBuffer",t3);
    }
}
