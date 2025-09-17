import java.util.*;

public class TextCompressor {
    static char[] uniqueChars;
    static String[] codes;

    static void countFrequency(String text){
        ArrayList<Character>chars=new ArrayList<>();
        ArrayList<Integer>freqs=new ArrayList<>();
        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            int idx=chars.indexOf(c);
            if(idx==-1){chars.add(c);freqs.add(1);}
            else freqs.set(idx,freqs.get(idx)+1);
        }
        uniqueChars=new char[chars.size()];
        codes=new String[chars.size()];
        for(int i=0;i<chars.size();i++)uniqueChars[i]=chars.get(i);
        for(int i=0;i<uniqueChars.length;i++)codes[i]=Integer.toString(i,36);
    }

    static String compress(String text){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<text.length();i++){
            char c=text.charAt(i);
            for(int j=0;j<uniqueChars.length;j++){
                if(uniqueChars[j]==c){
                    sb.append(codes[j]).append(" ");
                    break;
                }
            }
        }
        return sb.toString().trim();
    }

    static String decompress(String compressed){
        String[]parts=compressed.split(" ");
        StringBuilder sb=new StringBuilder();
        for(String code:parts){
            for(int j=0;j<codes.length;j++){
                if(codes[j].equals(code)){
                    sb.append(uniqueChars[j]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter text: ");
        String text=sc.nextLine();
        countFrequency(text);
        String compressed=compress(text);
        String decompressed=decompress(compressed);
        System.out.println("Original: "+text);
        System.out.println("Compressed: "+compressed);
        System.out.println("Decompressed: "+decompressed);
        System.out.println("Compression Ratio: "+(100-(compressed.length()*100.0/text.length()))+"%");
    }
}
