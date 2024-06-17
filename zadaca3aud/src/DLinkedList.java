public class DLinkedList<E> extends Node<E> {
        private Node<E> head;
        private Node<E> tail;
        public DLinkedList()
        {
            head=tail=null;
        }
        public Node<E> getHead()
        {
            return head;
        }
        public Node<E> getTail()
        {
            return tail;
        }
        public void insertFirst(E e)
        {
            Node<E> first=new Node<E>(e,head,null);
            if(head==null) {
                head = tail = first;
                return;
            }
            head=first;
        }
        public void insertLast(E e)
        {
            if(head==null)
            {
                this.insertFirst(e);
                return;
            }
            Node<E> last=new Node(e,null,tail);
            tail.next=last;
            tail=last;
        }
        public void printList()
        {
            Node<E> t=this.head;
            while (t.next!=null) {
                System.out.print(t.data + " <-> ");
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
    }
