 import java.util.Scanner;
    public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter three numbers: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();

        if (num1 < num2 && num1 < num3) {
            System.out.println("The first number is the smallest.");
        } else {
            System.out.println("The first number is not the smallest.");
        }
        scanner.close();
    }
}

