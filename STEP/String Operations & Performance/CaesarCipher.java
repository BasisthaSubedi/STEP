
import java.util.*;

public class CaesarCipher {
    static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') sb.append((char)((c - 'A' + shift) % 26 + 'A'));
            else if (c >= 'a' && c <= 'z') sb.append((char)((c - 'a' + shift) % 26 + 'a'));
            else sb.append(c);
        }
        return sb.toString();
    }
    static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift % 26);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift: ");
        int shift = sc.nextInt();
        String enc = encrypt(text, shift);
        String dec = decrypt(enc, shift);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        System.out.println("Valid: " + text.equals(dec));
    }
}
