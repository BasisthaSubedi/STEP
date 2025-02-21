   import java.util.Scanner;
    public class Problem11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double total = 0.0;

        while (true) {
            System.out.print("Enter a number (negative or 0 to stop): ");
            double number = scanner.nextDouble();
            if (number <= 0) break;
            total += number;
        }

        System.out.println("Total sum: " + total);
        scanner.close();
    }
}
