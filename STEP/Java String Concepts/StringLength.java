import java.util.*;

public class StringLength {
    static int getLength(String s) {
        int c = 0;
        try {
            for (;; c++) s.charAt(c);
        } catch (Exception e) {
            return c;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int len1 = getLength(s);
        int len2 = s.length();
        System.out.println(len1);
        System.out.println(len2);
    }
}
