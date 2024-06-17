import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    static void vnesiPolinom(ArrayList al){
        Scanner input=new Scanner(System.in);
        do{
            al.add(input.nextInt());
            al.add(input.nextInt());
            if((int)al.get(al.size()-2) == 0)
                break;
            System.out.println("Vnesi 0 za kraj");
            if(input.nextInt()==0)
                break;
        }while(true);

    }
    static ArrayList soberi(ArrayList<Integer> l1,ArrayList<Integer> l2)
    {
        ArrayList<Integer> rez=new ArrayList();
        int i=0,j=0;
        while (i<l1.size() && j<l2.size())
        {
            if(l1.get(i)==l2.get(j))
            {
                rez.add(l1.get(i));
                rez.add(l1.get(i+1)+l2.get(j+1));
                i+=2;
                j+=2;
            }
            if(l1.get(i)>l2.get(j))
            {
                rez.add(l1.get(i));
                rez.add(l2.get(i+1));
                i=i+2;
            }
            if(l1.get(i)<l2.get(j))
            {
                rez.add(l2.get(j));
                rez.add(l2.get(j+1));
                j=j+2;
            }
        }
        while(i<l1.size())
        {
            rez.add(l1.get(i));
            rez.add(l1.get(i+1));
            i+=2;
        }
        while(j<l2.size())
        {
            rez.add(l2.get(j));
            rez.add(l2.get(j+1));
            j+=2;
        }
        return rez;
    }
    public static void main(String[] args) {
        ArrayList<Integer> prv=new ArrayList<Integer>();
        ArrayList<Integer> vtor=new ArrayList<Integer>();
        ArrayList<Integer> r=new ArrayList<Integer>();
        vnesiPolinom(prv);
        vnesiPolinom(vtor);
        r=soberi(prv,vtor);
        for(int i=0;i<r.size();i+=2)
        {
            System.out.print("("+r.get(i)+","+r.get(i+1)+")");
        }
    }
}