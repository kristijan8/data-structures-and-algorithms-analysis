import javax.sound.midi.Soundbank;
import java.util.Scanner;

class Map<K extends Comparable<K>, E> {

    public K key;
    public E mak,ang;

    public Map(K key, E mak,E ang) {
        this.key = key;
        this.mak=mak;
        this.ang=ang;
    }
}

class SLLNode<E> {

    protected E info;
    protected SLLNode<E> next;

    public SLLNode(E info, SLLNode<E> next) {
        this.info= info;
        this.next = next;
    }
}

class SLLHT<K extends Comparable<K>, E> {
    private SLLNode<Map<K, E>>[] htable;

    public SLLHT(int m) {
        htable = (SLLNode<Map<K, E>>[]) new SLLNode[m];
        for (int i = 0; i < m; i++) {
            htable[i] = null;
        }
    }

    private int hash(K key) {
        return (key.toString().toLowerCase().charAt(0)-'a')%26;
    }

    public SLLNode<Map<K, E>> find(K look) {
        int h = hash(look);
        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (look.equals(node.info.key)) {
                return node;
            }
        }
        return null;
    }

    public void insert(K key, E mak, E ang) {
        Map<K, E> entry = new Map(key,mak,ang);
        int h = hash(key);
        for (SLLNode<Map<K, E>> node = htable[h]; node != null; node = node.next) {
            if (key.equals(node.info.key)) {
                node.info = entry;
                return;
            }
        }
        htable[h] = new SLLNode<Map<K, E>>(entry, htable[h]);
    }

    public void delete(K key) {
        int h = hash(key);
        for (SLLNode<Map<K, E>> pred = null, node = htable[h]; node != null; pred = node, node = node.next) {
            if (key.equals(node.info.key)) {
                if (pred == null) {
                    htable[h] = node.next;
                } else {
                    pred.next = node.next;
                }
                return;
            }
        }
    }
}



public class Main {
    static void prevedi(String tekst,SLLHT<String,String> mapa)
    {
        String niza[]=tekst.split(" ");
        for (String zbor: niza)
        {
            SLLNode<Map<String,String>> pom=mapa.find(zbor);
            if(pom==null)
            {
                System.out.print("?");
            }
            else
                System.out.print(pom.info.ang);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Vnesi kolku parovi kje ima recnikot");
        int n=sc.nextInt();
        sc.nextLine();
        System.out.println("Vnesi gi pratovite");
        SLLHT<String,String> mapa=new SLLHT<>(26);
        for (int i=0;i<n;i++)
        {
            String par=sc.nextLine();
            par=par.toLowerCase();
            mapa.insert(par.split(" ")[0],par.split(" ")[0],par.split(" ")[1]);
        }
        System.out.println("Vnesi tekst");
        String tekst=sc.nextLine();
        prevedi(tekst.toLowerCase(),mapa);
    }
}