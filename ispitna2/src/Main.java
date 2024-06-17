import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> r=new LinkedList<Integer>();
        for(int i=0;i<4;i++)
        {
            r.add(i);
        }
        Queue<Integer> rr=mirrorMe(r);
        if(rr.isEmpty())
        {
            System.out.println("prazen e redot");
        }
        while(!rr.isEmpty())
        {
            System.out.print(rr.remove()+",");
        }
    }
    private static Queue<Integer> mirrorMe(Queue<Integer> r)
    {
        if(r.isEmpty())
            return r;
        if(r.size()%2==1||r==null)
        {
            throw new IllegalArgumentException("Queeafsasmnbdjkashdkjbas");
        }
        int size=r.size();
        Queue<Integer> rr=new LinkedList<>();
        Stack<Integer> m=new Stack<>();
        int i=0;
        while(i<size/2)
        {
            m.push(r.peek());
            rr.add(r.remove());
            i++;
        }
        while(!m.isEmpty())
            rr.add(m.pop());
        while(i<r.size())
        {
            m.push(r.peek());
            rr.add(r.remove());
            i++;
        }
        while(!m.isEmpty())
            rr.add((m.pop()));
        return rr;
    }
}