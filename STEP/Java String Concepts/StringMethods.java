import java.util.Scanner;

public class StringMethods {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your full name: ");
        String fullName = sc.nextLine();
        System.out.print("Enter your favorite programming language: ");
        String lang = sc.nextLine();
        System.out.print("Write a sentence about your programming experience: ");
        String sentence = sc.nextLine();
        String[] parts = fullName.split(" ");
        String first = parts[0];
        String last = parts.length > 1 ? parts[1] : "";
        int count = sentence.replace(" ", "").length();
        String upperLang = lang.toUpperCase();
        System.out.println("\n=== SUMMARY ===");
        System.out.println("First Name: " + first);
        System.out.println("Last Name: " + last);
        System.out.println("Favorite Language: " + upperLang);
        System.out.println("Characters in Sentence: " + count);
        sc.close();
    }
}
