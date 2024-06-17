

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class GraphInfo<E extends Comparable<E>> {
    E info;

    public GraphInfo(E info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Key value pair: " + info.toString();
    }
}

class Graph<E extends Comparable<E>> {

    public int mtx[][];
    public GraphInfo<E> infos[];
    public int n;

    public Graph(int n) {
        this.n = n;
        infos = new GraphInfo[n];
        mtx = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mtx[i][j] = 0;
            }
        }

    }

    void addEdge(int x, int y) {
        mtx[x][y] = 1;
        mtx[y][x] = 1;
    }

    void deleteEdge(int x, int y) {
        mtx[x][y] = 0;
        mtx[y][x] = 0;
    }

    void setInfo(int pos, E info) {
        infos[pos] = new GraphInfo(info);
    }

    E getInfo(int pos) {
        return infos[pos].info;
    }

    int getIndex(E info) {
        for (int i = 0; i < n; i++) {
            if (infos[i].info == info) {
                return i;
            }
        }
        return -1;
    }

    boolean neighbors(int x, int y) {
        if (mtx[x][y] == 1) {
            return true;
        } else {
            return false;
        }
    }

    void addNode(E info) {
        ++n;
        GraphInfo[] infospom = new GraphInfo[n];
        for (int i = 0; i < n - 1; i++) {
            infospom[i] = infos[i];
        }
        int[][] mtxpom = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                mtxpom[i][j] = mtx[i][j];
            }
        }
        infospom[n - 1] = new GraphInfo(info);
        for (int i = 0; i < n; i++) {
            mtxpom[i][n - 1] = 0;
            mtxpom[n - 1][i] = 0;
        }
        mtx = mtxpom;
        infos = infospom;
    }

    void deleteNode(E info) {
        int ind = getIndex(info);

        if (ind != n - 1)//stanuva zbor za poslednata redica/kolona, samo namali go brojot na elementi
        {
            for (int i = ind; i < n - 1; i++) { //???
                infos[i] = infos[i + 1];
            }

            for (int i = ind; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    mtx[j][i] = mtx[j][i + 1];  //pomesti kolona vo levo
                }
            }
            for (int i = ind; i < n - 1; i++) {
                for (int j = 0; j < n; j++) {
                    mtx[i][j] = mtx[i + 1][j];  //pomesti redica nagore
                }
            }
        }
        n--;
    }

    public void printMatrix() {
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mtx[i][j] + " ");
            }
            System.out.println();
        }
    }

    int deleteTask() {
        LinkedList<Integer> list = new LinkedList();
        this.iterate(list);
        System.out.println("Size: " + list.size());
        this.delete(list);
        return this.count();
    }

    private void iterate(LinkedList<Integer> list) {
        for(int k = 0; k < n; k++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (mtx[k][i] > 0 && infos[k].info.compareTo(infos[i].info) == 0) {
                    count++;
                }
            }
            if (count > 2) {
                list.add(k);
            }
        }
    }

    private void delete(LinkedList<Integer> list) {
        for(Integer k : list) {
            for (int i = 0; i < n; i++) {
                if (mtx[k][i] > 0 && infos[k].info.compareTo(infos[i].info) == 0) {
                    this.deleteEdge(k, i);
                }
            }
        }
    }

    private int count() {
        int count = 0;
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (mtx[i][j] != 0) {
                    break;
                }
            }
            if (j == n) {
                count++;
            }
        }

        return count;
    }
    public int func(int broj)
    {
        int countt=0;
        boolean niza[]=new boolean[n];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
                niza[j]=false;
            countt+=func1(i,i,broj,niza);
        }
        return countt;
    }
    public int func1(int start,int moment,int broj,boolean []niza)
    {
        if (broj==1)
        {
            System.out.println("Stignavme do"+ moment);
            if (mtx[moment][start]>0)
                return 1;
            else
                return 0;
        }
        int count=0;
        niza[moment]=true;
        for (int k=0;k<n;k++)
        {
            System.out.println("mtx["+moment+"]["+k+"]="+mtx[moment][k]);
            if (mtx[moment][k]==1 && niza[k]==false)
            {
                System.out.println("Odime do "+k);
                count+=func1(start,k,broj-1,niza);
            }
        }
        niza[moment]=false;
        return count;
    }
    public void dfs(int start)
    {
        ArrayList<Integer> niza=new ArrayList<>();
        niza.add(start);
        boolean pom[]=new boolean[n];
        for (int i = 0; i < pom.length; i++) {
            pom[i]=false;
        }
        pom[0]=true;
        while (niza.size()!=0)
        {
            int t=niza.remove(0);
            if (t!=start)
                System.out.print(infos[t].info+" ");
            for (int i=0;i<n;i++)
            {
                if (mtx[t][i]==1 && !pom[i])
                {
                    niza.add(i);
                    pom[i]=true;
                }
            }
        }
        System.out.println();
    }
    public void bfs(int start,boolean []niza,int poc)
    {
        niza[start]=true;
        if (poc!=start) System.out.print(infos[start].info+" ");
        for (int i=0;i<n;i++)
        {
            if (mtx[start][i]==1 && !niza[i])
                bfs(i,niza,poc);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Graph<String> graph=new Graph<>(0);
        graph.addNode("Ez0");
        graph.addNode("Ez1");
        graph.addNode("Ez2");
        graph.addNode("Ez3");
        graph.addNode("Ez4");
        graph.addNode("Ez5");
        graph.addNode("Ez6");
        graph.addEdge(0,1);
        graph.addEdge(1,5);
        graph.addEdge(0,2);
        graph.addEdge(2,5);
        graph.addEdge(6,5);
        boolean niza[]=new boolean[graph.n];
        for (int i = 0; i < niza.length; i++) {
            niza[i]=false;

        }
        graph.bfs(0,niza,0);
        System.out.println();
        graph.dfs(0);




    }

}