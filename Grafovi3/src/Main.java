import javax.xml.xpath.XPathNodes;
import java.util.LinkedList;

class GNode<E>
{
    public int num;
    public E info;
    public LinkedList<GNode<E>> list;
    public GNode(int num,E info)
    {
        this.num=num;
        this.info=info;
        list=new LinkedList<>();
    }
    public void addNeighbour(GNode<E> node)
    {
        if(!list.contains(node))
            list.add(node);
    }
    public void deleteNeighbour(GNode<E> node)
    {
        if(list.contains(node))
            list.remove(node);
    }
    public boolean hasNeighbour(GNode<E> node)
    {
        return list.contains(node);
    }
}
class Graph<E>
{
    public int n;
    public GNode<E> graph[];
    public Graph(int n,E[] infos)
    {
        this.n=n;
        graph=new GNode[n];
        for (int i=0;i<n;i++)
        {
            graph[i]=new GNode<>(i,infos[i]);
        }
    }
    boolean neightbours(int x,int y)
    {
        return graph[x].hasNeighbour(graph[y]);
    }
    public void addEdge(int x,int y)
    {
        graph[x].addNeighbour(graph[y]);
    }
    public void deleteEdge(int x,int y)
    {
        graph[x].deleteNeighbour(graph[y]);
    }
    public void dfs(int poseteni[],int start)
    {
        poseteni[start]=1;
        GNode<E> pom=graph[start];
        for (int i=0;i<pom.list.size();i++)
        {
            if (poseteni[pom.list.get(i).num]==0)
            {
                dfs(poseteni,pom.list.get(i).num);
            }
        }
    }
}



public class Main {
    public static void main(String[] args)
    {

    }
}