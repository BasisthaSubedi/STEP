import java.util.*;

public class CharFrequency {
    static String[][] getFrequency(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) freq[s.charAt(i)]++;
        int unique = 0;
        for (int i = 0; i < 256; i++) if (freq[i] > 0) unique++;
        String[][] res = new String[unique][2];
        int idx = 0;
        for (int i = 0; i < 256; i++)
            if (freq[i] > 0) {
                res[idx][0] = String.valueOf((char) i);
                res[idx][1] = String.valueOf(freq[i]);
                idx++;
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
