import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();
        System.out.println("\n=== STRING MANIPULATION ===");
        String trimmed = input.trim();
        System.out.println("Trimmed: " + trimmed);
        String underscored = trimmed.replace(" ", "_");
        System.out.println("Replaced Spaces: " + underscored);
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("Removed Digits: " + noDigits);
        String[] words = trimmed.split("\\s+");
        System.out.println("Words: " + Arrays.toString(words));
        String joined = String.join(" | ", words);
        System.out.println("Joined Words: " + joined);
        System.out.println("No Punctuation: " + removePunctuation(trimmed));
        System.out.println("Capitalized: " + capitalizeWords(trimmed));
        System.out.println("Reversed: " + reverseWordOrder(trimmed));
        countWordFrequency(trimmed);
        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase()).append(" ");
        }
        return result.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        System.out.println("\nWord Frequency:");
        for (String w : map.keySet()) {
            System.out.println(w + ": " + map.get(w));
        }
    }
}
