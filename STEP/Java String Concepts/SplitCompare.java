import java.util.*;

public class SplitCompare {
    static int getLength(String s) {
        int c = 0;
        try {
            for (;; c++) s.charAt(c);
        } catch (Exception e) {
            return c;
        }
    }
    static String[] manualSplit(String s) {
        int n = getLength(s), w = 1, j = 0;
        for (int i = 0; i < n; i++) if (s.charAt(i) == ' ') w++;
        String[] arr = new String[w];
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                arr[j++] = s.substring(start, i);
                start = i + 1;
            }
        }
        arr[j] = s.substring(start);
        return arr;
    }
    static boolean compareArrays(String[] a, String[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) if (!a[i].equals(b[i])) return false;
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] a1 = manualSplit(s);
        String[] a2 = s.split(" ");
        System.out.println(compareArrays(a1, a2));
    }
}
