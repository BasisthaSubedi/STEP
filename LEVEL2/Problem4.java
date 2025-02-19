    import java.util.Scanner;
    public class Problem4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter distance in feet: ");
        double distanceInFeet = sc.nextDouble();
        
        double distanceInYards = distanceInFeet / 3;
        double distanceInMiles = distanceInFeet / (1760 * 3);
        
        System.out.println("The distance in yards is " + distanceInYards + " and in miles is " + distanceInMiles);
        
        sc.close();
    }
}
