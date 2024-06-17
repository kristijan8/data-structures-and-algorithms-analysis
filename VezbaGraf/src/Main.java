import java.util.ArrayList;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph=new Graph<>(4);
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,0);
        graph.printMatrix();
        System.out.println(graph.func(1));

    }
}