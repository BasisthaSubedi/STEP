import java.util.*;

public class TextCalculator {
    static boolean valid(String exp){
        for(char c:exp.toCharArray())if(!Character.isDigit(c)&&"+-*/() ".indexOf(c)==-1)return false;
        return true;
    }

    static int evaluate(String exp){
        Stack<Integer>nums=new Stack<>();
        Stack<Character>ops=new Stack<>();
        for(int i=0;i<exp.length();){
            char c=exp.charAt(i);
            if(Character.isDigit(c)){
                int j=i;
                while(j<exp.length()&&Character.isDigit(exp.charAt(j)))j++;
                nums.push(Integer.parseInt(exp.substring(i,j)));
                i=j;
            }else if(c=='('){
                ops.push(c);i++;
            }else if(c==')'){
                while(ops.peek()!='(')calc(nums,ops);
                ops.pop();i++;
            }else if("+-*/".indexOf(c)!=-1){
                while(!ops.isEmpty()&&prec(ops.peek())>=prec(c))calc(nums,ops);
                ops.push(c);i++;
            }else i++;
        }
        while(!ops.isEmpty())calc(nums,ops);
        return nums.pop();
    }

    static void calc(Stack<Integer>nums,Stack<Character>ops){
        int b=nums.pop(),a=nums.pop();
        char op=ops.pop();
        if(op=='+')nums.push(a+b);
        else if(op=='-')nums.push(a-b);
        else if(op=='*')nums.push(a*b);
        else nums.push(a/b);
    }

    static int prec(char op){
        return(op=='+'||op=='-')?1:2;
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter expression: ");
        String exp=sc.nextLine();
        if(!valid(exp))System.out.println("Invalid expression");
        else System.out.println("Result: "+evaluate(exp));
    }
}

