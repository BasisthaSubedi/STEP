import java.util.Scanner;
public class Weight {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);


    System.out.print("Enter weight in pounds: ");
    double weightPounds = scanner.nextDouble();
       double weightKg = weightPounds / 2.2;


       System.out.println("The weight of the person in pounds is " + weightPounds + ", and in kilograms is " + weightKg + ".");
       scanner.close();
   }
}
