import java.util.*;

public class TextCase {
    static char toUpper(char c) {
        return (c >= 'a' && c <= 'z') ? (char)(c - 32) : c;
    }
    static char toLower(char c) {
        return (c >= 'A' && c <= 'Z') ? (char)(c + 32) : c;
    }
    static String upperCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(toUpper(c));
        return sb.toString();
    }
    static String lowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) sb.append(toLower(c));
        return sb.toString();
    }
    static String titleCase(String s) {
        StringBuilder sb = new StringBuilder();
        boolean space = true;
        for (char c : s.toCharArray()) {
            if (c == ' ') { sb.append(c); space = true; }
            else if (space) { sb.append(toUpper(c)); space = false; }
            else sb.append(toLower(c));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.printf("%-20s %-20s %-20s %-20s\n","Original","Upper","Lower","Title");
        System.out.printf("%-20s %-20s %-20s %-20s\n",text,upperCase(text),lowerCase(text),titleCase(text));
    }
}

