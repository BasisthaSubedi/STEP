import java.util.*;

public class CSVAnalyzer {
    static String[][] parseCSV(String data){
        ArrayList<String[]>rows=new ArrayList<>();
        int start=0;ArrayList<String>row=new ArrayList<>();
        for(int i=0;i<=data.length();i++){
            if(i==data.length()||data.charAt(i)==','||data.charAt(i)=='\n'){
                row.add(data.substring(start,i).trim());
                start=i+1;
                if(i==data.length()||data.charAt(i)=='\n'){rows.add(row.toArray(new String[0]));row.clear();}
            }
        }
        return rows.toArray(new String[0][]);
    }

    static void analyze(String[][]data){
        System.out.printf("%-15s %-15s %-15s\n","Name","Age","Score");
        for(String[]r:data){
            System.out.printf("%-15s %-15s %-15s\n",r[0],r[1],r[2]);
        }
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter CSV data:");
        String input="",line;
        while(!(line=sc.nextLine()).isEmpty())input+=line+"\n";
        String[][]data=parseCSV(input);
        analyze(data);
    }
}

