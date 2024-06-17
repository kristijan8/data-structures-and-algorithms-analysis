import java.util.LinkedList;
import java.util.Scanner;
enum tip{kreditna,debitna};
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
class korisnik{
    LinkedList<String> karticki;
    public String ime,prezime,transakcija;
    korisnik(String ime,String przime,String transkacija,LinkedList<String> karticki)
    {
        this.ime=ime;
        this.prezime=przime;
        this.transakcija=transkacija;
        this.karticki=new LinkedList<>();
        this.karticki=karticki;
    }
}
class karticka
{
    public String iban;
    public int sredstva;
    public String tipp;
    public karticka(String iban, int sredstva, String tipp) {
        this.iban = iban;
        this.sredstva = sredstva;
        this.tipp = tipp;
    }
}



public class Main {
    public static void main(String[] args)
    {
        System.out.println("Vnesi kolku korisnici ima:");
        int n;
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        scanner.nextLine();
        SLLHT<String,korisnik> base=new SLLHT<>(10);
        System.out.println("Vnesi gi korisnicite: ");
        for (int i=0;i<n;i++)
        {
            String str=scanner.nextLine();
            LinkedList<String> str1=new LinkedList<>();
            String []str2=str.split(" ");
            for (int j=3;j<str2.length;j++)
                str1.add(str2[i]);
            korisnik k=new korisnik(str2[0],str2[1],str2[2],str1);
            base.insert(str2[2],k);
        }
        System.out.println("Vnesi kolku karticki kje ima:");
        int m=0;
        m=scanner.nextInt();
        scanner.nextLine();
        SLLHT<String,karticka> map=new SLLHT<>(10);
        System.out.println("Vnesi gi kartickite");
        for (int i=0;i<m;i++)
        {
            String str=scanner.nextLine();
            String []str1=str.split(" ");
            karticka k=new karticka(str1[0],Integer.parseInt(str1[1]),str1[2]);
        }
        int deb=0,kred=0;
        System.out.println("Vnesi smetka: ");
        String smetka=scanner.nextLine();
        SLLNode<Map<String,korisnik>> pom=base.find(smetka);
        if (pom!=null)
        {
            kred=deb=0;
            korisnik pom1=pom.info.value;
            for (int i=0;i<pom1.karticki.size();i++)
            {
                SLLNode<Map<String,karticka>> pom2=map.find(pom1.karticki.get(i));
                if (pom2.info.value.tipp.equals("kreditna"))
                    kred++;
               else
                    deb++;
            }
            System.out.println(pom1.ime+" "+pom1.prezime+" "+" ima "+kred+" kreditni i "+deb+" debitni karticki");
        }
        else
        {
            System.out.println("Nema takov korisnik");
        }
    }
}