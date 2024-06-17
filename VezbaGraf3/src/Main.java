import java.util.LinkedList;

class edge
{
    public int from,to;
    public float wight;

    public edge(int from, int to, float wight) {
        this.from = from;
        this.to = to;
        this.wight = wight;
    }
}
class GNeighbor<E>
{
    public GNode<E> node;
    public int weight;
    GNeighbor(GNode<E> node, int weight) {
        this.node = node;
        this.weight = weight;
    }
    public GNeighbor(GNode<E> node) {
        this.node = node;
        this.weight = 0;
    }
}
class GNode<E>
{
    public int num;
    public E info;
    public LinkedList<GNeighbor<E>> list;
    public GNode(int num,E info)
    {
        this.num=num;
        this.info=info;
        list=new LinkedList<>();
    }
    public boolean hasNeighbour(GNode<E> o)
    {
        GNeighbor<E> pom=new GNeighbor<E>(o,0);
        return list.contains(pom);
    }
    public void addNEighbour(GNode<E> o,int wight)
    {
        GNeighbor<E> pom=new GNeighbor<>(o,wight);
        list.add(pom);
    }
    public void deleteNeighbour(GNode<E> o)
    {
        GNeighbor<E> pom = new GNeighbor<E>(o, 0);
        if (list.contains(pom)) {
            list.remove(pom);
        }
    }
    public void updateNeighborWeight(GNode<E> o, int weight)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).node.equals(o))
            {
                list.get(i).weight = weight;
            }
        }
    }
}
class Graph<E>
{
    public int n;
    public GNode<E> graph[];
    Graph(int n,E infos[])
    {
        this.n=n;
        graph=new GNode[n];
        for (int i=0;i<n;i++)
            graph[i]=new GNode<>(i,infos[i]);
    }
    Graph(int n)
    {
        this.n=n;
        graph=new GNode[n];
        for (int i=0;i<n;i++)
            graph[i]=new GNode<>(i,null);
    }
    public boolean neighbors(int x,int y)
    {
        return graph[x].hasNeighbour(graph[y]);
    }
    public void addEdge(int x,int y,int weight)
    {
        if (graph[x].hasNeighbour(graph[y]))
            graph[x].updateNeighborWeight(graph[y],weight);
        else
            graph[x].addNEighbour(graph[y],weight);
    }
    public void deleteEdge(int x,int y)
    {
        graph[x].deleteNeighbour(graph[y]);
    }
    public float[] dijkstra(int start)
    {
        boolean fin[]=new boolean[n];
        float dist[]=new float[n];
        for (int i=0;i<n;i++)
        {
            fin[i]=false;
            dist[i]=-1;
        }
        fin[start]=true;
        dist[start]=0;
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<graph[start].list.size();j++)
            {
                int pom=graph[start].list.get(j).node.num;
                if (fin[pom]==false)
                {
                    if (dist[pom]==-1)
                    {
                        dist[pom]=dist[start]+graph[start].list.get(j).weight;
                    }
                    else if (dist[graph[start].list.get(j).node.num] >
                            dist[start] + graph[start].list.get(j).weight)
                    {
                        dist[graph[start].list.get(j).node.num] = dist[start]
                                + graph[start].list.get(j).weight;
                    }
                }
            }
            float minCena=Float.MAX_VALUE;
            for (int k=0;k<n;k++)
            {
                if (fin[k]==false && dist[k]!=-1)
                {
                    if (minCena>dist[k])
                    {
                        minCena=dist[k];
                        start=k;
                    }
                }
            }
            fin[start]=true;
        }
        return dist;
    }

}


public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}