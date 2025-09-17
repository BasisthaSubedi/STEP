import java.util.*;

public class VowelConsonantCount {
    static boolean isVowel(char c) {
        if (c >= 'A' && c <= 'Z') c = (char)(c + 32);
        return c=='a'||c=='e'||c=='i'||c=='o'||c=='u';
    }
    static int[] countVC(String s) {
        int v = 0, c = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')) {
                if (isVowel(ch)) v++; else c++;
            }
        }
        return new int[]{v,c};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int[] res = countVC(s);
        System.out.println("Vowels: " + res[0]);
        System.out.println("Consonants: " + res[1]);
    }
}
