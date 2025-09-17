import java.util.*;

public class BMI {
    static String getStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal";
        else if (bmi < 29.9) return "Overweight";
        else return "Obese";
    }
    static String[][] computeBMI(double[][] arr) {
        String[][] res = new String[arr.length][4];
        for (int i = 0; i < arr.length; i++) {
            double weight = arr[i][0];
            double height = arr[i][1] / 100;
            double bmi = weight / (height * height);
            res[i][0] = String.valueOf(arr[i][0]);
            res[i][1] = String.valueOf(arr[i][1]);
            res[i][2] = String.format("%.2f", bmi);
            res[i][3] = getStatus(bmi);
        }
        return res;
    }
    static void display(String[][] arr) {
        System.out.println("Height(cm)\tWeight(kg)\tBMI\tStatus");
        for (String[] row : arr)
            System.out.println(row[1] + "\t\t" + row[0] + "\t\t" + row[2] + "\t" + row[3]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] data = new double[10][2];
        for (int i = 0; i < 10; i++) {
            System.out.print("Enter weight (kg) for person " + (i + 1) + ": ");
            data[i][0] = sc.nextDouble();
            System.out.print("Enter height (cm) for person " + (i + 1) + ": ");
            data[i][1] = sc.nextDouble();
        }
        String[][] res = computeBMI(data);
        display(res);
    }
}
