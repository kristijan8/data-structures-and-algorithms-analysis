public class Node<E> {
        protected E data;
        protected Node<E> next;
        protected Node<E> prev;
        public Node(E data,Node<E> next,Node <E> prev)
        {
            this.data=data;
            this.prev=prev;
            this.next=next;
        }
        public Node() {
            data=null;
            next=null;
            prev=null;
        }
}
