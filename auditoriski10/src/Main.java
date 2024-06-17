import java.sql.SQLOutput;

class TBnode<E extends Comparable<E>>{
    public E info;
    public TBnode<E> left;
    public TBnode<E> right;
    char ltag,rtag;
    public TBnode(E info)
    {
        this.info=info;
        ltag=rtag='-';
    }
}
class TBtree<E extends Comparable<E>>
{
    public TBnode<E> lead;
    TBtree()
    {
        lead=new TBnode<>(null);
        lead.left=lead.right=null;
        lead.rtag='+';
        lead.ltag='-';
    }
    public void makeRoot(E info)
    {
        TBnode<E> root=new TBnode<>(info);
        root.left=root.right=lead;
        lead.left=root;
        lead.ltag='+';
    }
    public void insert(E info,TBnode<E> r)
    {
        if(r.left==r)
        {
            makeRoot(info);
            return;
        }
        TBnode<E> n=new TBnode<>(info);
        TBnode<E> pom=r.left;
        while(pom!=lead)
        {
            int cmp=info.compareTo(pom.info);
            if(cmp<0)
            {
                if(pom.ltag=='-')
                {
                    n.left=pom.left;
                    pom.left=n;
                    pom.ltag='+';
                    n.right=pom;
                    break;
                }
                else
                {
                    pom=pom.left;
                }
            }

            else if(cmp>0)
            {
                if(pom.rtag=='-')
                {
                    n.right=pom.right;
                    pom.right=n;
                    pom.ltag='+';
                    n.left=pom;
                    break;
                }
                else
                {
                    pom=pom.right;
                }
            }
        }
    }
    public TBnode<E> predInorder(TBnode<E> node)
    {
        if(node.ltag=='-')
        {
            return node.left;
        }
        TBnode<E> pom=node.left;
        while(pom.rtag=='+')
        {
            pom=pom.right;
        }
        return pom;
    }
    public TBnode<E> sledInorder(TBnode<E> node)
    {
        if(node.rtag=='-')
        {
            return node.right;
        }
        TBnode<E> pom=node.right;
        while(pom.ltag=='+')
        {
            pom=pom.left;
        }
        return pom;
    }
    public void inorder()
    {
        if(lead.left==lead)
        {
            return;
        }
        TBnode<E> pom=sledInorder(lead);//najlev jazol
        while(pom!= lead)
        {
            System.out.print(pom.info+ ",");
            pom=sledInorder(pom);
        }

    }




}


public class Main {
    public static void main(String[] args) {
        TBtree<Integer> tree=new TBtree<>();
        tree.insert(5,tree.lead);
        tree.insert(4,tree.lead);

    }
}