public class Main {
    static class Node<E>{
        public E data;
        public int data2;
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
    static class SLinkedList<E> {
        private Node<E> head;

        public SLinkedList() {
            head = null;
        }

        public Node<E> getHead() {
            return head;
        }

        public void insertFirst(E e) {
            Node<E> first = new Node<E>(e, head);
            head = first;
        }

        public void insertLast(E e) {
            if (head == null) {
                this.insertFirst(e);
                return;
            }
            Node<E> t = head;
            while (t.next != null) {
                t = t.next;
            }
            Node<E> last = new Node<E>(e, null);
            t.next = last;
        }
        public void brisiPosleden()
        {
            if(head==null)
                return;
            Node<E> dvizi=head;
            while(dvizi.next.next!=null)
            {
                dvizi=dvizi.next;
            }
            dvizi.next=null;
        }
        public void printList() {
            Node<E> t = this.head;
            while (t.next != null) {
                System.out.print(t.data + "-> ");
                t = t.next;
            }
            System.out.print(t.data + "\n");
        }

        public void deleteFirst() {
            if (head != null)
                head = head.next;
        }

        public void prevrti() {
            if (head != null) {
                Node<E> current = head;
                Node<E> prev = null;
                Node<E> next;
                while (current != null) {
                    next = current.next;
                    current.next = prev;
                    prev = current;
                    current = next;
                }
                head = prev;
            }

        }

        public void expand(int factor) {
            if (factor <= 0) {
                head = null;
            } else {
                Node<Integer> current = (Node<Integer>) head;
                while (current != null) {
                    current.data = current.data / factor;
                    for (int i = 1; i < factor; i++) {
                        current.next = new Node(current.data, current.next);
                        current = current.next;
                    }
                    current = current.next;
                }
            }

        }

        public int prebroi() {
            if (head == null)
                return 0;
            Node<E> dvizi = head;
            int i = 0;
            while (dvizi != null) {
                dvizi = dvizi.next;
                i++;
            }
            return i;
        }
        public void brisiSled(Node<E> jazol)
        {
            if(jazol.next==null)
                return;
            jazol.next=jazol.next.next;
        }

        public void func() {
            Node<E> dvizi = head;
            while (dvizi.next != null) {
                int b = 0;
                E k = dvizi.data;
                Node<E> dvizi2 = dvizi;
                while (dvizi2.next != null) {
                    if (dvizi2.next.data == k) {
                        brisiSled(dvizi2);
                        b++;
                    }
                    dvizi2 = dvizi2.next;
                }
                /*if(dvizi2.data==k)
                {
                    brisiPosleden();
                    b++;
                }

                /*if (dvizi2.next.data == k) {
                    dvizi2.next = dvizi2.next.next;
                    b++;
                }*/
                dvizi.data2 = b;
                dvizi = dvizi.next;
                }
            }
            void print2()
            {
                Node<E> t = this.head;
                while (t.next != null) {
                    System.out.print(t.data +","+t.data2+ "-> ");
                    t = t.next;
                }
                System.out.print(t.data + "," + t.data2 + "\n");
            }
        }

    public static void main(String[] args) {
        SLinkedList lista=new SLinkedList();
        lista.insertLast(1);
        lista.insertLast(2);
        lista.insertLast(3);
        lista.insertLast(3);
        lista.insertLast(4);
        lista.insertLast(3);
        lista.insertLast(6);
        lista.insertLast(2);
        lista.insertLast(3);
        lista.insertLast(7);
        lista.printList();
        lista.func();
        lista.printList();
        lista.print2();


    }
}