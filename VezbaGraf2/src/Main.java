import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class GNode<E extends Comparable<E>>
{
    public int num;
    public E info;
    public LinkedList<GNode<E>> list;

    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list=new LinkedList<>();
    }
    public void addNeighbour(GNode<E> node)
    {
        if (!list.contains(node))
            list.add(node);
    }
    public void deleteNeighbour(GNode<E> node)
    {
        if (list.contains(node))
            list.remove(node);
    }
    public boolean hasNeighbour(GNode<E> node)
    {
        return list.contains(node);
    }
}
class Graph<E extends Comparable<E>>
{
    public GNode<E>[] graph;
    public int n;

    public Graph( int n, E infos[]) {
        this.n = n;
        graph=new GNode[n];
        for (int i=0;i<n;i++)
            graph[i]=new GNode<>(i,infos[i]);
    }
    public boolean neighbours(int x,int y)
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
    public void dfs(int start)
    {
        boolean niza[]=new boolean[n];
        for (int i=0;i<n;i++)
            niza[i]=false;
        dfsitteracija(start,niza);
        System.out.println();
    }

    private void dfsitteracija(int start, boolean[] niza) {
        niza[start]=true;
        System.out.print(graph[start].info.toString()+" ");
        for (int i=0;i<graph[start].list.size();i++)
        {
            if(!niza[graph[start].list.get(i).num])
                dfsitteracija(graph[start].list.get(i).num,niza);
        }
    }
    public void bfs(int start)
    {
        ArrayList<Integer> sled=new ArrayList<>();
        sled.add(start);
        boolean niza[]=new boolean[n];
        for (int i=0;i<n;i++)
            niza[i]=false;
        niza[0]=true;
        while (sled.size()!=0)
        {
            int moment=sled.remove(0);
            System.out.print(moment+" ");
            for (int i=0;i<graph[moment].list.size();i++)
            {
                if(!niza[graph[moment].list.get(i).num])
                {
                    sled.add(graph[moment].list.get(i).num);
                    niza[graph[moment].list.get(i).num]=true;
                }
            }
        }
        System.out.println();
    }


}

public class Main {
    public static void main(String[] args)
    {
        Integer niza[]={0,1,2,3,4,5,6};
        Graph<Integer> graph = new Graph(7,niza);
        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1,5);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(5,6);
        graph.bfs(0);

    }
}