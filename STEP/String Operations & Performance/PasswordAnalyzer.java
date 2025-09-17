import java.util.*;

public class PasswordAnalyzer {
    static int analyze(String pwd){
        int upper=0,lower=0,digit=0,special=0;
        for(int i=0;i<pwd.length();i++){
            char c=pwd.charAt(i);
            if(c>=65&&c<=90)upper++;
            else if(c>=97&&c<=122)lower++;
            else if(c>=48&&c<=57)digit++;
            else special++;
        }
        int score=0;
        score+=(pwd.length()>8?(pwd.length()-8)*2:0);
        if(upper>0)score+=10;
        if(lower>0)score+=10;
        if(digit>0)score+=10;
        if(special>0)score+=10;
        if(pwd.toLowerCase().contains("123")||pwd.toLowerCase().contains("abc")||pwd.toLowerCase().contains("qwerty"))score-=10;
        return Math.max(score,0);
    }

    static String strengthLevel(int score){
        if(score<=20)return"Weak";
        else if(score<=50)return"Medium";
        return"Strong";
    }

    static String generate(int length){
        String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower="abcdefghijklmnopqrstuvwxyz";
        String digits="0123456789";
        String special="!@#$%^&*";
        String all=upper+lower+digits+special;
        Random r=new Random();
        StringBuilder sb=new StringBuilder();
        sb.append(upper.charAt(r.nextInt(upper.length())));
        sb.append(lower.charAt(r.nextInt(lower.length())));
        sb.append(digits.charAt(r.nextInt(digits.length())));
        sb.append(special.charAt(r.nextInt(special.length())));
        for(int i=4;i<length;i++)sb.append(all.charAt(r.nextInt(all.length())));
        List<Character>chars=new ArrayList<>();
        for(char c:sb.toString().toCharArray())chars.add(c);
        Collections.shuffle(chars);
        sb.setLength(0);
        for(char c:chars)sb.append(c);
        return sb.toString();
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of passwords: ");
        int n=sc.nextInt();
        sc.nextLine();
        String[]pwds=new String[n];
        for(int i=0;i<n;i++){
            System.out.print("Enter password "+(i+1)+": ");
            pwds[i]=sc.nextLine();
        }
        System.out.printf("%-20s %-10s %-10s\n","Password","Score","Strength");
        for(String p:pwds){
            int s=analyze(p);
            System.out.printf("%-20s %-10d %-10s\n",p,s,strengthLevel(s));
        }
        System.out.print("Generate strong password of length: ");
        int len=sc.nextInt();
        System.out.println("Generated Password: "+generate(len));
    }
}

