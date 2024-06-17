class Tochka{
    public double x;
    public double y;
    Tochka(double xx,double yy)
    {
        x=xx;
        y=yy;
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println(neSeNapagjaat());

    }
    private static int neSeNapagjaat()
    {
        int br=0;
        int x1,y1,x2,y2;
        for(x1=0;x1<8;x1++)
        {
            for (y1=0;y1<8;y1++)
            {
                for(x2=0;x2<8;x2++)
                {
                    for (y2=0;y2<8;y2++)
                    {
                        if(!daliSeNapagjaat(x1,y1,x2,y2))
                        {

                            br++;
                        }
                    }
                }
            }
        }
        return br;
    }
    private static boolean daliSeNapagjaat(int x1,int y1,int x2,int y2)
    {
        return (x1==x2 || y1==y2 || (Math.abs(x1-x2)==Math.abs(y1-y2)));

    }
}