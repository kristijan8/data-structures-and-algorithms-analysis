
class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;
    BNode (E info)
    {
        this.info=info;
        left=right=null;
    }
    BNode (E info,BNode<E> right,BNode<E> left)
    {
        this.info=info;
        this.left=left;
        this.right=right;
    }
}
class BSTree<E extends Comparable<E>> {
    public BNode<E> root;
    BSTree ()
    {
        root=null;
    }
    BSTree(E info)
    {
        root=new BNode<E>(info);
    }
    public BNode<E> insert(E info,BNode<E> r)
    {
        if(r==null)
        {
            return new BNode<E>(info);
        }
        int comp=info.compareTo(r.info);
        if (comp < 0)
        {
            r.left=insert(info,r.left);
        }
        else if(comp>0)
        {
            r.right=insert(info,r.right);
        }
        return r;

    }
    public boolean contains(E info,BNode<E> r)
    {
        if(r==null)
            return false;
        int comp=info.compareTo(r.info);
        if(comp<0)
            return contains(info,r.left);
        else if(comp>0)
            return contains(info,r.right);
        else
            return true;
    }
    public BNode<E> findMin(BNode<E> r)
    {
        if(r==null)
            return null;
        if(r.left==null)
            return r;
        else
            return findMin(r.left);
    }
    public BNode<E> remove(E info,BNode<E> r)
    {
        if(r==null)
            return r;
        int comp=info.compareTo(r.info);
        if(comp<0)
            r.right = remove(info,r.left);
        else if(comp>0)
            r.left = remove(info,r.right);
        else
        {
            if(r.left!=null && r.right!=null)
            {
                r.info=findMin(r.right).info;
                r.right=remove(r.info,r.right);
            }
            else
            {
                if (r.left!=null)
                    return r.left;
                else
                    return r.right;
            }
        }
        return r;
    }
    public void inorderR(BNode<E> r)
    {
        if(r!=null)
        {
            inorderR(r.left);
            System.out.print(r.info+" ");
            inorderR(r.right);
        }
    }
    public boolean check(BNode<E> r1,BNode<E> r2)
    {
        if(r1==null&&r2==null)
            return true;
        if(r1!=null && r2!=null)
        {
            return (r1.info.compareTo(r2.info)==0) && check(r1.right,r2.right) && check(r1.left,r2.left);
        }
        return false;
    }
    public boolean checkLeaves(BNode<E> r1,BNode<E> r2)
    {
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

}

public class Main {
    static int binarySearch(int arr[], int x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree(10);
        tree.insert(4, tree.root);
        tree.insert(6, tree.root);
        tree.insert(8, tree.root);
        tree.insert(2, tree.root);
//        tree.inorderR(tree.root);
        BSTree<Integer> tree1 = new BSTree(12);
        tree1.insert(4, tree1.root);
        tree1.insert(6, tree1.root);
        tree1.insert(8, tree1.root);
        tree1.insert(2, tree1.root);

        System.out.println(tree.check(tree.root, tree1.root));
        System.out.println(tree.checkLeaves(tree.root, tree1.root));

        BSTree<Integer> tree2 = new BSTree();
        int[] array = {1, 2, 4, 5, 6, 8, 9};
        System.out.println(binarySearch(array,2));
//        tree2.root = makeFromArray(array, 0, 6);
        tree2.inorderR(tree2.root);
    }
}
