import java.util.*;

public class NestedLoopFrequency {
    static String[][] getFrequency(String s) {
        char[] arr = s.toCharArray();
        int[] freq = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            freq[i] = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    freq[i]++;
                    arr[j] = '\0';
                }
            }
        }
        int unique = 0;
        for (int i = 0; i < arr.length; i++) if (arr[i] != '\0') unique++;
        String[][] res = new String[unique][2];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '\0') {
                res[idx][0] = String.valueOf(arr[i]);
                res[idx][1] = String.valueOf(freq[i]);
                idx++;
            }
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
