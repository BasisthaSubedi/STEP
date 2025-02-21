    import java.util.Scanner;
    public class Problem4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a natural number: ");
        int n = scanner.nextInt();

        if (n > 0) {
            int sum = n * (n + 1) / 2;
            System.out.println("The sum of natural numbers is: " + sum);
        } else {
            System.out.println("The number is not a natural number.");
        }
        scanner.close();
    }
}