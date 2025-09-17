import java.util.*;

public class TextFormatter {
    static String[] splitWords(String text) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i <= text.length(); i++) {
            if (i == text.length() || text.charAt(i) == ' ') {
                if (i > start) words.add(text.substring(start, i));
                start = i + 1;
            }
        }
        return words.toArray(new String[0]);
    }

    static String justify(String[] words, int width) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < words.length) {
            int j = i, lineLen = 0;
            while (j < words.length && lineLen + words[j].length() + (j - i) <= width) {
                lineLen += words[j].length();
                j++;
            }
            int spaces = width - lineLen;
            int gaps = Math.max(1, j - i - 1);
            for (int k = i; k < j; k++) {
                sb.append(words[k]);
                if (k < j - 1) {
                    int add = spaces / gaps + ((k - i) < (spaces % gaps) ? 1 : 0);
                    for (int s = 0; s < add; s++) sb.append(' ');
                }
            }
            sb.append("\n");
            i = j;
        }
        return sb.toString();
    }

    static String centerAlign(String[] words, int width) {
        StringBuilder sb = new StringBuilder();
        String line = String.join(" ", words);
        int pad = (width - line.length()) / 2;
        sb.append(" ".repeat(Math.max(0, pad))).append(line);
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter line width: ");
        int width = sc.nextInt();
        String[] words = splitWords(text);
        System.out.println("\nJustified Text:\n" + justify(words, width));
        System.out.println("Center Aligned:\n" + centerAlign(words, width));
    }
}
