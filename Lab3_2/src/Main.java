import javax.sound.midi.Soundbank;
import java.util.ArrayList;

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BSTree<E extends Comparable<E>> {
    public BNode<E> root;

    public BSTree() {
        root = null;
    }

    public BSTree(E info) {
        root = new BNode<E>(info);
    }

    public BNode<E> insert(E info, BNode<E> r) {
        if (r == null) {
            return new BNode(info);
        }

        int comp = info.compareTo(r.info);

        if (comp < 0) {
            r.left = insert(info, r.left);
        } else if (comp > 0) {
            r.right = insert(info, r.right);
        }

        return r;
    }


    public BNode<E> contains(E info, BNode<E> r) {
        if (r == null) {
            return null;
        }
        int comp = info.compareTo(r.info);
        if (comp < 0) {
            return contains(info, r.left);
        } else if (comp > 0) {
            return contains(info, r.right);
        } else {
            return r;
        }
    }

    public BNode<E> remove(E info, BNode<E> r) {
        if (r == null) {
            return r;
        }

        int comp = info.compareTo(r.info);

        if (comp < 0) {
            r.left = remove(info, r.left);
        } else if (comp > 0) {
            r.right = remove(info, r.right);
        } else { // jazolot shto treba da se brishe
            if (r.left != null && r.right != null) {
                r.info = findMin(r.right).info;
                r.right = remove(r.info, r.right);
            } else {
                if (r.left != null) {
                    return r.left;
                } else {
                    return r.right;
                }
            }
        }

        return r;
    }

    private BNode<E> findMin(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.left == null) {
            return r;
        } else {
            return findMin(r.left);
        }
    }
    private BNode<E> findMax(BNode<E> r) {
        if (r == null) {
            return null;
        } else if (r.right == null) {
            return r;
        } else {
            return findMax(r.left);
        }
    }

    public void inorderR(BNode<E> r) {
        if (r != null) {
            inorderR(r.left);
            System.out.print(r.info + ", ");
            inorderR(r.right);
        }
    }

    public boolean check(BNode<E> r1, BNode<E> r2) {
        if (r1 == null && r2 == null) {
            return true;
        }

        if (r1 != null && r2 != null) {
            return (r1.info.compareTo(r2.info) == 0) && check(r1.left, r2.left)
                    && check(r1.right, r2.right);
        }

        return false;
    }

    public boolean checkLeaves(BNode<E> r1, BNode<E> r2) {
        if (r1.left == null && r1.right == null && r2.left == null && r2.right == null) {
            return r1.info.compareTo(r2.info) == 0;
        } else if (r1.left != null && r1.right != null && r2.left != null && r2.right != null) {
            return checkLeaves(r1.left, r2.left) && checkLeaves(r1.right, r2.right);
        } else if (r1.left != null && r1.right == null && r2.left != null && r2.right == null) {
            return checkLeaves(r1.left, r2.left);
        } else if (r1.left == null && r1.right != null && r2.left == null && r2.right != null) {
            return checkLeaves(r1.right, r2.right);
        } else {
            return false;
        }
    }
    public BNode<E> insert(E x, BNode<E> r, E parent, char which)
    {
        if (r==null)W
            return null;
        int comp=parent.compareTo(r.info);
        if (comp>0)
            return insert(x,r.right,parent,which);
        else if (comp<0)
            return insert(x,r.left,parent,which);
        else
        {
            BNode<E> nov=new BNode<>(x);
            if(which=='l')
            {
                nov.left=r.left;
                r.left=nov;
            }
            else
            {
                nov.right=r.right;
                r.right=nov;
            }
            return nov;
        }

    }
    public boolean checkBST(BNode<E> root){
        if(root == null){
            return true;
        }
        if(root.left!=null && (Integer)findMax(root.left).info>(Integer)root.info){
            return false;
        }
        if(root.right!=null && (Integer)findMin(root.right).info < (Integer)root.info){
            return false;
        }
        if(checkBST(root.left)!= true || checkBST(root.right)!=true){
            return false;
        }
        return true;
    }



}



public class Main {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree(10);
        tree.insert(4, tree.root);
        tree.insert(6, tree.root);
        tree.insert(8, tree.root);
        tree.insert(2, tree.root);
        tree.inorderR(tree.root);
        System.out.println();

        BSTree<Integer> tree1 = new BSTree(12);
        tree1.insert(4, tree1.root);
        tree1.insert(6, tree1.root);
        tree1.insert(8, tree1.root);
        tree1.insert(2, tree1.root);
        tree.inorderR(tree1.root);


    }
}