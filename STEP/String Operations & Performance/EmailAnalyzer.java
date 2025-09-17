import java.util.*;

public class EmailAnalyzer {
    static boolean isValid(String email) {
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        return at > 0 && dot > at && email.indexOf('@') == email.lastIndexOf('@');
    }
    static void analyzeEmails(String[] emails) {
        System.out.printf("%-25s %-15s %-15s %-15s %-15s %-10s\n","Email","Username","Domain","Domain Name","Extension","Valid");
        for (String email : emails) {
            if (!isValid(email)) {
                System.out.printf("%-25s %-15s %-15s %-15s %-15s %-10s\n",email,"-","-","-","-","No");
                continue;
            }
            int at = email.indexOf('@');
            int dot = email.lastIndexOf('.');
            String user = email.substring(0, at);
            String domain = email.substring(at + 1);
            String domainName = email.substring(at + 1, dot);
            String ext = email.substring(dot + 1);
            System.out.printf("%-25s %-15s %-15s %-15s %-15s %-10s\n",email,user,domain,domainName,ext,"Yes");
        }
    }
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter number of emails: ");
            int n = sc.nextInt();
            sc.nextLine();
            String[] emails = new String[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter email "+(i+1)+": ");
                emails[i] = sc.nextLine();
            }
            analyzeEmails(emails);
        }
    }
}

