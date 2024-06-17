import java.util.Scanner;

public class Main {

    static boolean funkc(String a,String b)
    {

        a=a.replace(" ","");
        b=b.replace(" ","");
        if(a.length()!=b.length())
            return false;
        a=a.toLowerCase();
        b=b.toLowerCase();
        char a1[]=a.toCharArray();
        for(int i=0;i<a1.length-1;i++)
        {
            for(int j=i+1;j<a1.length;j++)
            {
                if((int)a1[i]<(int)a1[j])
                {
                    char pom=a1[i];
                    a1[i]=a1[j];
                    a1[j]=pom;
                }
            }
        }
        char b1[]=b.toCharArray();
        for(int i=0;i<b1.length-1;i++)
        {
            for(int j=i+1;j<b1.length;j++)
            {
                if((int)b1[i]<(int)b1[j])
                {
                    char pom=b1[i];
                    b1[i]=b1[j];
                    b1[j]=pom;
                }
            }
        }
        for(int i=0;i<b1.length;i++)
        {
            if(a1[i]!=b1[i])
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String a;
        Scanner scanner = new Scanner(System.in);
        a=scanner.nextLine();
        String b;
        b=scanner.nextLine();
        System.out.println(funkc(a,b));

    }
}