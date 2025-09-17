import java.util.*;

public class UniqueCharacters {
    static int getLength(String s) {
        int c = 0;
        try { for (;; c++) s.charAt(c); } catch (Exception e) { return c; }
    }
    static char[] getUniqueChars(String s) {
        int n = getLength(s), idx = 0;
        char[] temp = new char[n];
        for (int i = 0; i < n; i++) {
            boolean unique = true;
            for (int j = 0; j < i; j++)
                if (s.charAt(i) == s.charAt(j)) { unique = false; break; }
            if (unique) temp[idx++] = s.charAt(i);
        }
        return Arrays.copyOf(temp, idx);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] res = getUniqueChars(s);
        System.out.println("Unique Characters: " + Arrays.toString(res));
    }
}
