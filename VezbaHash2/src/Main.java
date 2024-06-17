import java.util.Scanner;

class Map<K extends Comparable<K>,E>
{
    public E value;
    public K key;

    public Map(E value, K key) {
        this.value = value;
        this.key = key;
    }
}
class SLLNode<E>
{
    public E info;
    public SLLNode<E> next;

    public SLLNode(E info, SLLNode<E> next) {
        this.info = info;
        this.next = next;
    }
}
class SLLHT<K extends Comparable<K>,E>
{
    public SLLNode<Map<K,E>> htable[];
    SLLHT(int m)
    {
        htable=new SLLNode[m];
        for (int i=0;i<m;i++)
            htable[i]=null;
    }
    public int hash(K key)
    {
        return (key.toString().toLowerCase().charAt(0)-'0')% htable.length;
    }
    public SLLNode<Map<K,E>> find(K look)
    {
        int h=hash(look);
        for (SLLNode<Map<K,E>> node=htable[h]; node!=null; node=node.next)
        {
            if (look.equals(node.info.key))
                return node;
        }
        return null;
    }
    public void delete(K key)
    {
        int h=hash(key);
        for (SLLNode<Map<K,E>> node=htable[h],pred=null;
             node!=null; pred=node,node=node.next)
        {
            if(key.equals(node.info.key))
            {
                if (pred==null)
                    htable[h]=htable[h].next;
                else
                    pred.next=node.next;
                return;
            }
        }
    }
    public void insert(K key,E val)
    {
        int h=hash(key);
        for (SLLNode<Map<K,E>> node=htable[h]; node!=null; node=node.next)
        {
            if (key.equals(node.info.key))
            {
                node.info.value=val;
                return;
            }
        }
        Map<K,E> entry=new Map<>(val,key);
        htable[h]=new SLLNode<>(entry,htable[h]);
    }
}
class gragjanin{
    public String ime,prezime,embg;
    public gragjanin(String ime, String prezime, String embg) {
        this.ime = ime;
        this.prezime = prezime;
        this.embg = embg;
    }
}
class prekrsok{
    public String embg;
    public int brojPrekrsoci,iznosZaPlakjanje;
    public prekrsok(String embg, int brojPrekrsoci, int iznosZaPlakjanje) {
        this.embg = embg;
        this.brojPrekrsoci = brojPrekrsoci;
        this.iznosZaPlakjanje = iznosZaPlakjanje;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Vnesi kolku gragjani ima vo bazata na podatoci");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        System.out.println("Vnesi gi gragjanite");
        SLLHT<String,gragjanin> map1=new SLLHT<>(10);
        for (int i=0;i<n;i++)
        {
            String gr=sc.nextLine();
            String gr1[]=gr.split(" ");
            gragjanin g=new gragjanin(gr1[0],gr1[1],gr1[2]);
            map1.insert(gr1[2],g);
        }
        System.out.println("Vnesi kolku gragjani ima so prekrsoci");
        int m=sc.nextInt();
        sc.nextLine();
        SLLHT<String,prekrsok> map2=new SLLHT<>(10);
        System.out.println("Vnesi gi prekrsocite");
        for (int i=0;i<m;i++)
        {
            String gr=sc.nextLine();
            String gr1[]=gr.split(" ");
            prekrsok p=new prekrsok(gr1[0],Integer.parseInt(gr1[1]),Integer.parseInt(gr1[2]));
            map2.insert(gr1[0],p);
        }
        System.out.println("vnesi eden maticen broj");
        String embg=sc.nextLine();
        SLLNode<Map<String,gragjanin>> pom=map1.find(embg);
        if (pom!=null)
        {
            SLLNode<Map<String,prekrsok>> pom1=map2.find(embg);
            if (pom1!=null)
                System.out.println(pom.info.value.ime+" "+pom.info.value.prezime+" ima za plakjanje: "+pom1.info.value.iznosZaPlakjanje);
            else
                System.out.println(pom.info.value.ime+" "+pom.info.value.prezime+" nema za plakjanje");
        }
        else
            System.out.println("Nema gragjanin so takov maticen broj");
    }
}