class Travel{
    public static void main(String[] args) {
    String name = "Basistha Subedi";
    String From= "Nepal", Via= "Hyderabad",Final ="Chennai";
    double Distance_FromtoVia= 156.6 ;
    int TimeTaken1 = 4*60+4 ;
    Double Distance_ViatoFinal=211.8;
    int TimeTaken2=4*60+25;
    double Totaldistance= Distance_FromtoVia + Distance_ViatoFinal;
    int Totaltime= TimeTaken1+TimeTaken2;
    System.out.println("Total Distance\n="+Totaldistance+"\nTotal Time\n="+Totaltime);
}
}