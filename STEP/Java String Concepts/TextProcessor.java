import java.util.*;

public class TextProcessor {
    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words)
            sb.append(Character.toUpperCase(word.charAt(0)))
              .append(word.substring(1).toLowerCase())
              .append(" ");
        return sb.toString().trim();
    }
    public static void analyzeText(String text) {
        String[] sentences = text.split("[.!?]");
        String[] words = text.split("\\s+");
        int charCount = text.replace(" ", "").length();
        String longest = "";
        Map<Character, Integer> freq = new HashMap<>();
        for (String word : words) if (word.length() > longest.length()) longest = word;
        for (char c : text.replaceAll("\\s+", "").toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        char mostCommon = text.charAt(0);
        for (char c : freq.keySet())
            if (freq.get(c) > freq.get(mostCommon)) mostCommon = c;
        System.out.println("Characters: " + charCount);
        System.out.println("Words: " + words.length);
        System.out.println("Sentences: " + sentences.length);
        System.out.println("Longest Word: " + longest);
        System.out.println("Most Common Char: " + mostCommon);
    }
    public static String[] getWordsSorted(String text) {
        String cleaned = text.replaceAll("[^a-zA-Z ]", "");
        String[] words = cleaned.split("\\s+");
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph: ");
        String input = sc.nextLine();
        String cleaned = cleanInput(input);
        System.out.println("\nCleaned Text: " + cleaned);
        analyzeText(cleaned);
        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("\nSorted Words: " + Arrays.toString(sortedWords));
        System.out.print("\nEnter a word to search: ");
        String search = sc.next();
        boolean found = Arrays.stream(sortedWords).anyMatch(w -> w.equalsIgnoreCase(search));
        System.out.println(found ? "Word found!" : "Word not found!");
        sc.close();
    }
}
