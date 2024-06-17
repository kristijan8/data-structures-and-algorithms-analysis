import java.util.LinkedList;

class Edge {
    public int from, to;
    public float weight;
    public Edge(int from, int to, float weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
class GNeighbor<E> {
    public GNode<E> node;
    public int weight;
    public GNeighbor(GNode<E> node, int weight) {
        this.node = node;
        this.weight = weight;
    }
    public GNeighbor(GNode<E> node) {
        this.node = node;
        this.weight = 0;
    }
}
class GNode<E> {
    public int num;
    public E info;
    public LinkedList<GNeighbor<E>> list;
    public GNode(int num, E info) {
        this.num = num;
        this.info = info;
        list = new LinkedList<GNeighbor<E>>();
    }
    public boolean hasNeighbor(GNode<E> o) {
        GNeighbor<E> pom = new GNeighbor<E>(o, 0);
        return list.contains(pom);
    }
    public void addNeighbor(GNode<E> o, int weight) {
        GNeighbor<E> pom = new GNeighbor<E>(o, weight);
        list.add(pom);
    }
    public void deleteNeighbor(GNode<E> o) {
        GNeighbor<E> pom = new GNeighbor<E>(o, 0);
        if (list.contains(pom)) {
            list.remove(pom);
        }
    }
    public void updateNeighborWeight(GNode<E> o, int weight) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).node.equals(o)) {
                list.get(i).weight = weight;
            }
        }
    }
}
class Graph<E> {
    int n;
    GNode<E> graph[];

    public Graph(int n, E[] infos) {
        this.n = n;
        graph = (GNode<E>[]) new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode(i, infos[i]);
        }
    }

    public Graph(int n) {
        this.n = n;
        graph = (GNode<E>[]) new GNode[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new GNode<E>(i, null);
        }
    }

    boolean neighbors(int x, int y) {
        return graph[x].hasNeighbor(graph[y]);
    }

    void addEdge(int x, int y, int tezina) {
        if (graph[x].hasNeighbor(graph[y])) {
            graph[x].updateNeighborWeight(graph[y], tezina);
        } else {
            graph[x].addNeighbor(graph[y], tezina);
        }
    }

    void deleteEdge(int x, int y) {
        graph[x].deleteNeighbor(graph[y]);
    }
    public Edge[] kruskal()
    {
        int roditel[]=new int[n];
        for (int i=0;i<n;i++)
            roditel[i]=i;
        Edge edges[]=getAllEdges();
        sort(edges);
        Edge fin[]=new Edge[n-1];
        int dodadeni = 0;
        int i = 0;
        while (dodadeni<n-1)
        {
            Edge e=edges[i++];
            if (roditel[e.from]!=roditel[e.to])
            {
                fin[dodadeni++] = e;
                promeni(e.from, e.to, roditel);
            }
        }
        return fin;
    }

    private void promeni(int from, int to, int[] roditel) {
        int pomal,pogolem;
        if (from<to)
        {
            pomal=roditel[from];
            pogolem=roditel[to];
        }
        else
        {
            pomal=roditel[to];
            pogolem=roditel[from];
        }
        for (int i = 0; i < roditel.length; i++)
        {
            if (roditel[i]==pogolem)
            {
                roditel[i]=pomal;
            }
        }
    }

    private Edge[] getAllEdges() {
        int vk=0,ind=0;
        for (int i=0;i<n;i++)
        {
            vk+=graph[i].list.size();
        }
        Edge[] edges = new Edge[vk];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<graph[i].list.size();j++)
            {
                int god=graph[i].num;
                int gdo=graph[i].list.get(j).node.num;
                int weight=graph[i].list.get(j).weight;
                edges[ind++]=new Edge(god,gdo,weight);
            }
        }
        return edges;
    }
    private void sort(Edge[] edges)
    {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].weight > edges[j].weight) {
                    Edge pom = edges[i];
                    edges[i] = edges[j];
                    edges[j] = pom;
                }
            }
        }
    }

}
    public class Main {
    public static void main(String[] args) {
        Integer infos[]={0,1,2,3,4};
        Graph<Integer> graph=new Graph<>(5,infos);
        graph.addEdge(1,0,20);
        graph.addEdge(0,1,20);

        graph.addEdge(1,2,20);
        graph.addEdge(2,1,20);
        graph.addEdge(1,4,40);
        graph.addEdge(4,1,40);
        graph.addEdge(0,3,50);
        graph.addEdge(3,0,50);
        graph.addEdge(2,3,30);
        graph.addEdge(3,2,30);
        graph.addEdge(3,4,70);
        graph.addEdge(4,3,70);
        Edge edges[]=graph.kruskal();
        int count=0;
        for (int i = 0; i < edges.length; i++) {
            count+=edges[i].weight;
        }
        System.out.println(count);
    }
}