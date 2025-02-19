public class Problem6 {
        public static void main(String[] args) {
        
            double originalFee = 125000;        
            double discountPercentage = 10;
            double discountAmount = (originalFee * discountPercentage) / 100;
            double finalAmount = originalFee - discountAmount;
            
            System.out.println("The discount amount is INR "+discountAmount+"and final discounted fee is INR "+finalAmount);

        }
    }
