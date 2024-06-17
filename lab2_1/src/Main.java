import java.util.Stack;
public class Main {
    static void func(Stack<Integer> m)
    {
        Stack<Integer> m1=new Stack<Integer>();
        Stack<Integer> m2=new Stack<Integer>();
        if(m.isEmpty())
            return;
        m1.push(m.pop());
        while(!m.isEmpty())
        {
            while(m.peek()<m1.peek())
            {
                m2.push(m1.pop());
                if(m1.isEmpty())
                    break;
            }
            m2.push(m.pop());
            while(!m1.isEmpty())
                m2.push(m1.pop());
            while(!m2.isEmpty())
                m1.push(m2.pop());
        }
        while(!m1.isEmpty())
            m.push(m1.pop());
    }
    public static void main(String[] args) {
        Stack<Integer> m=new Stack<>();
        m.push(2);
        m.push(5);
        m.push(6);
        m.push(1);
        m.push(4);
        m.push(3);
        m.push(9);
        m.push(7);
        m.push(8);
        func(m);
        while(!m.isEmpty())
        {
            System.out.print(m.pop()+" ");
        }
    }
}