import java.util.*;

public class FindReplace {
    static int[] findOccurrences(String text, String find) {
        ArrayList<Integer> pos = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            pos.add(index);
            index = text.indexOf(find, index + find.length());
        }
        return pos.stream().mapToInt(i -> i).toArray();
    }

    static String manualReplace(String text, String find, String replace) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length();) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                sb.append(replace);
                i += find.length();
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    static boolean compareWithBuiltIn(String text, String find, String replace) {
        return text.replace(find, replace).equals(manualReplace(text, find, replace));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter substring to find: ");
        String find = sc.nextLine();
        System.out.print("Enter substring to replace: ");
        String replace = sc.nextLine();
        String result = manualReplace(text, find, replace);
        System.out.println("Manual Result: " + result);
        System.out.println("Built-in Result: " + text.replace(find, replace));
        System.out.println("Match: " + compareWithBuiltIn(text, find, replace));
    }
}
