public class Main {
    static class Node<E>{
        public E data;
        protected Node<E> next;
        public Node(E data,Node<E> next)
        {
            this.data=data;
            this.next=next;
        }
        public Node() {
            data=null;
            next=null;
        }
    }
    static class SLinkedList<E>
    {
        private Node<E> head;
        public SLinkedList()
        {
            head=null;
        }
        public Node<E> getHead()
        {
            return head;
        }
        public void insertFirst(E e)
        {
            Node<E> first=new Node<E>(e,head);
            head=first;
        }
        public void insertLast(E e)
        {
            if(head==null)
            {
                this.insertFirst(e);
                return;
            }
            Node<E> t=head;
            while(t.next!=null)
            {
                t=t.next;
            }
            Node<E> last=new Node<E>(e,null);
            t.next=last;
        }
        public void printList()
        {
            Node<E> t=this.head;
            while (t.next!=null) {
                System.out.print(t.data + "-> ");
                t = t.next;
            }
            System.out.print(t.data+"\n");
        }
        public void deleteFirst()
        {
            if(head!=null)
                head=head.next;
        }
        public void prevrti()
        {
            if(head!=null)
            {
                Node<E> current=head;
                Node<E> prev=null;
                Node<E> next;
                while(current != null)
                {
                    next=current.next;
                    current.next=prev;
                    prev=current;
                    current=next;
                }
                head=prev;
            }
        }
        public void expand(int factor)
        {
            if(factor<=0)
            {
                head=null;
            }
            else
            {
                Node<Integer> current=(Node<Integer>) head;
                while(current!=null)
                {
                    current.data= current.data/factor;
                    for(int i=1;i<factor;i++)
                    {
                        current.next =new Node( current.data,current.next);
                        current=current.next;
                    }
                    current=current.next;
                }
            }

        }
    }
    public static void main(String[] args) {
        SLinkedList<Integer> list=new SLinkedList();
        list.insertLast(40);
        list.insertLast(60);
        list.insertLast(40);
        list.printList();
        list.expand(5);
        list.printList();

    }
}