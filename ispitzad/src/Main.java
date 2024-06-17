import java.util.Stack;

public class Main {
    private static void compress(Stack<Integer> s)
    {
        int value,pe, count;
        Stack<Integer> sr=new Stack();
        while(!sr.isEmpty())
        {
            value =s.peek();
            count =0;
            while(!sr.empty()&& value ==s.peek())
            {
                count++;
                s.pop();
            }
            sr.push(count);
            sr.push(value);
        }
        while(!sr.isEmpty())
            s.push(sr.pop());
    }

    public static void main(String[] args) {
        Stack<Integer> s=new Stack();
        for(int i=0;i<4;i++)
            for (int j=0;j<i+1;j++)
            {
                s.add(i);
            }
        compress(s);
        while(!s.isEmpty())
        {
            System.out.println(s.peek()+","+s.pop());
        }
    }
}