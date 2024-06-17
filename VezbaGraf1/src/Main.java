import java.util.Scanner;

class GraphInfo<E extends Comparable<E>>
{
    public E  info;

    public GraphInfo(E info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Key value pair: " + info.toString();
    }
}
class Graph<E extends Comparable<E>>
{
    public GraphInfo<E>[] infos;
    public int mtx[][];
    public int n;
    Graph(int n)
    {
        infos=new GraphInfo[n];
        mtx=new int[n][n];
        for (int i=0;i<n;i++)
        {
            infos[i]=null;
            for (int j=0;j<n;j++)
                mtx[i][j]=0;
        }
    }
    public void addEdge(int x,int y)
    {
        mtx[x][y]=mtx[y][x]=1;
    }
    public void deleteEdge(int x,int y)
    {
        mtx[x][y]=mtx[y][x]=0;
    }
    public void setInfo(int pos,E info)
    {
        infos[pos]=new GraphInfo(info);
    }
    public E getInfo(int pos)
    {
        return infos[pos].info;
    }
    public int getIndex(E info)
    {
        for (int i=0;i< infos.length;i++)
        {
            if (infos[i].info==info)
                return i;
        }
        return -1;
    }
    public boolean neighbours(int x,int y)
    {
        return mtx[x][y]==1;
    }
    public void addNode(E info)
    {
        n++;
        GraphInfo<E>[] pom=new GraphInfo[n];
        int mtxpom[][]=new int[n][n];
        for (int i=0;i<n-1;i++)
        {
            pom[i]=infos[i];
            mtx[n-1][i]=0;
            mtx[i][n-1]=0;
            for (int j=0;j<n-1;j++)
                mtxpom[i][j]=mtx[i][j];
        }
        pom[n-1]=new GraphInfo<>(info);
        mtx=mtxpom;
        infos=pom;
    }
    public void deleteNode(E info)
    {
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
    public void printMatrix()
    {
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
                System.out.print(mtx[i][j]+" ");
            System.out.println();
        }
    }
    public int deleteTask()
    {
        boolean niza[]=new boolean[n];
        for (int i=0;i<n;i++)
            niza[i]=false;
        for (int i=0;i<n;i++)
        {
            int count=0;
            for (int j=0;j<n;j++)
            {
                if (mtx[i][j]==1)
                {
                    if (infos[i].info.compareTo(infos[j].info)==0)
                    {
                        count++;
                    }
                }
            }
            if (count>2)
                niza[i]=true;
        }
        for (int i=0;i<n;i++)
        {
            if (niza[i])
            {
                for (int j=0;j<n;j++)
                {
                    if (mtx[i][j]==1 && infos[i].info.compareTo(infos[j].info)==0)
                    {
                        deleteEdge(i,j);
                    }

                }
            }
        }
        int Count=0;
        for (int i=0;i<n;i++)
        {
            int j;
            for (j=0;j<n;j++)
            {
                if(mtx[i][j]>0)
                    break;
            }
            if (j==n)
                Count++;
        }
        return Count;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int count=scanner.nextInt();
        Graph<String> graph=new Graph<>(count);
        scanner.nextLine();
        String []colours=scanner.nextLine().split(" ");
        for (int i=0;i< count;i++)
        {
            graph.setInfo(i,colours[i]);
        }
        int conn = scanner.nextInt();
        for(int i = 0; i < conn; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        graph.printMatrix();

        System.out.println(graph.deleteTask());


    }
}