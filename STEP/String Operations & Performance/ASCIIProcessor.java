import java.util.*;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        System.out.println("\n=== ASCII PROCESSING ===");
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            System.out.println("Char: " + ch + " | ASCII: " + (int) ch + " | Type: " + classifyCharacter(ch));
            if (Character.isLetter(ch)) {
                System.out.println("Toggle Case: " + toggleCase(ch) + " | ASCII: " + (int) toggleCase(ch));
            }
        }
        System.out.print("\nEnter shift for Caesar Cipher: ");
        int shift = sc.nextInt();
        System.out.println("Ciphered Text: " + caesarCipher(input, shift));
        System.out.println("\nASCII Table from 65 to 90:");
        displayASCIITable(65, 90);
        sc.close();
    }

    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        if (Character.isLowerCase(ch)) return "Lowercase Letter";
        if (Character.isDigit(ch)) return "Digit";
        return "Special Character";
    }

    public static char toggleCase(char ch) {
        return Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch);
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                sb.append((char) ((ch - base + shift) % 26 + base));
            } else sb.append(ch);
        }
        return sb.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }
}
