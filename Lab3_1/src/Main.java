class BNode<E> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;
    static int LEFT = 1;
    static int RIGHT = 2;

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

class BTree<E> {
    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public BNode<E> addChild(E info, BNode<E> node, int where) {
        BNode<E> tmp = new BNode<E>(info);
        if (where == BNode.LEFT) {
            if (node.left != null) {
                return null;
            }
            node.left = tmp;
        } else {
            if (node.right != null) {
                return null;
            }
            node.right = tmp;
        }
        return tmp;
    }

    public void inorderR(BNode<E> r) {
        if (r != null) {
            inorderR(r.left);
            System.out.print(r.info + ", ");
            inorderR(r.right);
        }
    }

    public int numLeaves(BNode<E> r) {
        if (r.left == null && r.right == null) {
            return 1;
        }

        if (r.left != null && r.right != null) {
            return numLeaves(r.left) + numLeaves(r.right);
        } else if (r.left != null) {
            return numLeaves(r.left);
        } else {
            return numLeaves(r.right);
        }
    }

    public int numLeaves1(BNode<E> r) {
        if (r == null) {
            return 0;
        }

        if (r.left == null && r.right == null) {
            return 1 + numLeaves1(r.left) + numLeaves1(r.right);
        } else {
            return numLeaves1(r.left) + numLeaves1(r.right);
        }
    }
    public int maxHeight(BNode<E> root)
    {
        if (root==null)
            return 0;
        if(root.left==null && root.right==null)
            return 0;
        else
            return 1+Math.max(maxHeight(root.left),maxHeight(root.right));
    }
}



public class Main {
    public static void main(String[] args) {
        BTree<Integer> tree = new BTree(10);
        // tree.root.left = new BNode(5);
        tree.addChild(5, tree.root, 1);
        tree.addChild(6, tree.root, 2);
        tree.addChild(4, tree.root.left, 1);
        tree.inorderR(tree.root);
        System.out.println("\n"+tree.maxHeight(tree.root));
    }
}