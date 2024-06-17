public class Main {
    public static void main(String[] args) {
        int niza[]={50,20,5,2,1};
        System.out.println(min_pari_gr(niza,10));
    }
    private static int min_pari_gr(int []niza,int suma)
    {
        int i=0,br=0,pom;
        while (i < niza.length && suma > 0)
        {
            pom=suma/niza[i];
            suma-=pom*niza[i];
            br+=pom;
            i++;
        }
        return br;
    }
}