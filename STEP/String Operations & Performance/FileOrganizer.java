import java.util.*;

public class FileOrganizer {
    static String getExtension(String file){
        int idx=file.lastIndexOf('.');
        return(idx==-1)?"":file.substring(idx+1).toLowerCase();
    }

    static String getCategory(String ext){
        if(ext.equals("txt")||ext.equals("doc"))return"Document";
        if(ext.equals("jpg")||ext.equals("png"))return"Image";
        if(ext.equals("mp3")||ext.equals("wav"))return"Audio";
        if(ext.equals("mp4")||ext.equals("mkv"))return"Video";
        return"Unknown";
    }

    static String rename(String file,String category,int idx){
        String ext=getExtension(file);
        return category+"_"+idx+"."+ext;
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of files: ");
        int n=sc.nextInt();
        sc.nextLine();
        String[]files=new String[n];
        for(int i=0;i<n;i++){
            System.out.print("Enter file "+(i+1)+": ");
            files[i]=sc.nextLine();
        }
        System.out.printf("%-20s %-15s %-25s\n","Original","Category","New Name");
        int doc=0,img=0,aud=0,vid=0,unk=0;
        for(int i=0;i<n;i++){
            String ext=getExtension(files[i]);
            String cat=getCategory(ext);
            switch(cat){
                case"Document":doc++;break;
                case"Image":img++;break;
                case"Audio":aud++;break;
                case"Video":vid++;break;
                default:unk++;
            }
            System.out.printf("%-20s %-15s %-25s\n",files[i],cat,rename(files[i],cat,i+1));
        }
        System.out.println("\nCategory Counts:");
        System.out.println("Documents: "+doc);
        System.out.println("Images: "+img);
        System.out.println("Audio: "+aud);
        System.out.println("Video: "+vid);
        System.out.println("Unknown: "+unk);
    }
}

