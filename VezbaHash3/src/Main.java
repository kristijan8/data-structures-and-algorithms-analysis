import java.util.Scanner;
import java.util.SortedMap;

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
        return (key.toString().toLowerCase().charAt(0)-'a')% htable.length;
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
class adresa
{
    public String ime,prezime,mail,drzava;

    public adresa(String ime, String prezime, String mail, String drzava) {
        this.ime = ime;
        this.prezime = prezime;
        this.mail = mail;
        this.drzava = drzava;
    }
}

public class Main {
    static  Integer f(String s1,String s2)
    {
        int n=Integer.parseInt(s2);
        int m=s2.split("@")[0].charAt(0)-'a';
        return n*26+m;
    }
    public static void main(String[] args) {
        System.out.println("Kolku elektronski adresi kje vnesuvas");
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();
        SLLHT<String,adresa> map1=new SLLHT<>(26);
        for (int i=0;i<n;i++)
        {
            String st1=scanner.nextLine();
            String st[]=st1.split(" ");
            adresa a=new adresa(st[0],st[1],st[2],st[3]);
            map1.insert(st[2],a);
        }
        System.out.println("Vnesi elektronska adresa");
        String add=scanner.nextLine();
        System.out.println("Vnesi ja novata adresa");
        String nova=scanner.nextLine();
        SLLNode<Map<String ,adresa>> pom=map1.find(add);
        if (pom!=null)
        {
            System.out.println(pom.info.value.ime+" "+pom.info.value.prezime+" uspesno ja smenivte svojata adresa");
            adresa novaa=new adresa(pom.info.value.ime,pom.info.value.prezime,nova,pom.info.value.drzava);
            map1.delete(add);
            map1.insert(nova,novaa);
        }
        SLLNode<Map<String ,adresa>> pom2=map1.find(nova);
        System.out.println(pom2.info.value.mail);
    }
}