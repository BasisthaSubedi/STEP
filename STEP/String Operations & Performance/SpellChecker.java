import java.util.*;

public class SpellChecker {
    static String[] dictionary = {"hello","world","java","programming","spell","checker","example"};

    static String[] splitSentence(String sentence){
        ArrayList<String> words=new ArrayList<>();
        int start=0;
        for(int i=0;i<=sentence.length();i++){
            if(i==sentence.length()||sentence.charAt(i)==' '||sentence.charAt(i)=='.'||sentence.charAt(i)==','||sentence.charAt(i)=='!'||sentence.charAt(i)=='?'){
                if(i>start)words.add(sentence.substring(start,i));
                start=i+1;
            }
        }
        return words.toArray(new String[0]);
    }

    static int stringDistance(String w1,String w2){
        int[][]dp=new int[w1.length()+1][w2.length()+1];
        for(int i=0;i<=w1.length();i++){
            for(int j=0;j<=w2.length();j++){
                if(i==0)dp[i][j]=j;
                else if(j==0)dp[i][j]=i;
                else dp[i][j]=w1.charAt(i-1)==w2.charAt(j-1)?dp[i-1][j-1]:1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
            }
        }
        return dp[w1.length()][w2.length()];
    }

    static String getClosestWord(String word){
        String suggestion=word;
        int minDist=Integer.MAX_VALUE;
        for(String dictWord:dictionary){
            int dist=stringDistance(word.toLowerCase(),dictWord.toLowerCase());
            if(dist<minDist){
                minDist=dist;
                suggestion=dictWord;
            }
        }
        return minDist<=2?suggestion:"-";
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence=sc.nextLine();
        String[]words=splitSentence(sentence);
        System.out.printf("%-15s %-15s %-10s %-10s\n","Word","Suggestion","Distance","Status");
        for(String word:words){
            String suggestion=getClosestWord(word);
            int dist=suggestion.equals("-")?-1:stringDistance(word.toLowerCase(),suggestion.toLowerCase());
            String status=suggestion.equals(word)?"Correct":"Misspelled";
            System.out.printf("%-15s %-15s %-10d %-10s\n",word,suggestion,dist,status);
        }
    }
}
