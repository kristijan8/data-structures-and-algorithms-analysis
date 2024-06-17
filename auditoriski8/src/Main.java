class BNode<E> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;
    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info){
        this.info = info;
        this.left = null;
        this.right = null;
    }
    public BNode(E info,BNode<E> left,BNode<E> right){
        this.info = info;
        this.left = left;
        this.right = right;
    }
}
class BTree <E>{
    public BNode<E> root;

    public BTree(){
        root = null;
    }
    public BTree(E info){
        root = new BNode<E>(info);

    }
    public BNode<E> addChild(E info,BNode<E> parentNode,int where){
        BNode<E> tmp = new BNode<E>(info);
        if(where == BNode.LEFT && parentNode.left == null){
            parentNode.left = tmp;
        }
        else if(where == BNode.RIGHT && parentNode.right == null){
            parentNode.right = tmp;
        }
        else{
            return null;
        }

        return tmp;
    }


    public void inOrder(BNode<E> r){ // prvo levo dete, root, desno dete
        if(r != null){
            inOrder(r.left);
            System.out.print(r.info + ", ");
            inOrder(r.right);

        }
    }
    public void preOrder(BNode<E> r){ // prvo go izminuvame root, potoa decata
        if(r != null){
            System.out.print(r.info + ", ");
            inOrder(r.left);
            inOrder(r.right);

        }
    }
    public int numLeaves(BNode<E> r){
//        if(r.left == null && r.right == null){ // prv nacin
//            return 1;
//
//        }
//        if(r.left != null && r.right != null){
//            return numLeaves(r.left) + numLeaves(r.right);
//        }
//        else if(r.left != null){
//            return numLeaves(r.left);
//        }
//        else{
//            return numLeaves(r.right);
//        }
//
        if(r == null){
            return 0;
        }
        if(r.left == null && r.right == null){
            return 1 + numLeaves(r.left) + numLeaves(r.right);
        }
        else{
            return numLeaves(r.left) + numLeaves(r.right);
        }

    }
}


public class Main {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree(10);
        //tree.root.left = (int) = new BNode<Integer>(5); // vtor nacin
        tree.addChild(5, tree.root, 1);
        tree.addChild(6, tree.root, 2);

        tree.inOrder(tree.root);


    }
}