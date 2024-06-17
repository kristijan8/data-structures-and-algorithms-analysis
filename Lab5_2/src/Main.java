import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

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
    public void func1()
    {
        boolean poseteni[]=new boolean[n];
        for (int i=0;i<n;i++)
            poseteni[i]=false;
        ArrayList<E> pateka=new ArrayList<>();
        func(0,poseteni,pateka);
        System.out.println(pateka.toString());
        for (int i=0;i<n;i++)
        {
            if(poseteni[i]==false)
            {
                System.out.println("Ne se izmainati site sobi");
                return;
            }
        }
        System.out.println("Izminati se site sobi");
    }
    private void func(int poz,boolean poseteni[],ArrayList<E> pateka)
    {
        if(!pateka.contains(graph[poz]))
            pateka.add(graph[poz].info);
        poseteni[poz]=true;
        for (int i=0;i<graph[poz].list.size();i++)
        {
            int pom=graph[poz].list.get(i).num;
            if (poseteni[pom]==false)
            {
                func(pom,poseteni,pateka);
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Integer niza[]={0,1,2,3,4,5};
        Graph<Integer> graph=new Graph<>(6,niza);
        graph.addEdge(0, 1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(0,5);

        Graph<Integer> graph1=new Graph<>(6,niza);
        graph1.addEdge(0, 1);
        graph1.addEdge(2,3);
        graph1.addEdge(1,3);
        graph1.addEdge(0,5);

        graph.func1();
        graph1.func1();
    }
}