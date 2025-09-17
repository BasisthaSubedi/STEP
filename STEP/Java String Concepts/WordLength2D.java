import java.util.*;

public class WordLength2D {
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
    static String[][] getWordLength2D(String[] words) {
        String[][] res = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            res[i][0] = words[i];
            res[i][1] = String.valueOf(getLength(words[i]));
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] words = manualSplit(s);
        String[][] table = getWordLength2D(words);
        System.out.println("Word\tLength");
        for (String[] row : table)
            System.out.println(row[0] + "\t" + Integer.parseInt(row[1]));
    }
}
