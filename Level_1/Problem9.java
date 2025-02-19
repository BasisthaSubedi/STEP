public class Problem9 {
    
    public static void calculateDiscount(double fee, double discountPercent) {
        
        double discount = (fee * discountPercent) / 100;
        double finalFee = fee - discount;
        
        // Display the results
        System.out.printf("The discount amount is INR %.2f and final discounted fee is INR %.2f%n", discount, finalFee);
    }
}
