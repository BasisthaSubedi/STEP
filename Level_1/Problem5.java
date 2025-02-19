public class Problem5 {
    public static void main(String[] args) {
        int totalPens = 14;
        int students = 3;
        
        int pensPerStudent = totalPens / students;
        int remainingPens = totalPens % students;
        
        System.out.println("The pen per student is "+pensPerStudent+" and the remaining pens not distributed is "+remainingPens);
    }
}