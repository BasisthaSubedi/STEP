import java.util.*;

public class UniqueCharFrequency {
    static char[] getUniqueChars(String s) {
        char[] temp = new char[s.length()];
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean unique = true;
            for (int j = 0; j < i; j++)
                if (s.charAt(i) == s.charAt(j)) { unique = false; break; }
            if (unique) temp[idx++] = s.charAt(i);
        }
        return Arrays.copyOf(temp, idx);
    }
    static String[][] getFrequency(String s) {
        char[] unique = getUniqueChars(s);
        String[][] res = new String[unique.length][2];
        for (int i = 0; i < unique.length; i++) {
            int count = 0;
            for (int j = 0; j < s.length(); j++)
                if (s.charAt(j) == unique[i]) count++;
            res[i][0] = String.valueOf(unique[i]);
            res[i][1] = String.valueOf(count);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[][] freq = getFrequency(s);
        System.out.println("Character\tFrequency");
        for (String[] row : freq)
            System.out.println(row[0] + "\t\t" + row[1]);
    }
}
