import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class GraphInfo<E extends Comparable<E>>
{
    public E info;
    public GraphInfo(E info) {
        this.info = info;
    }
    @Override
    public String toString() {
        return "GraphInfo{" +
                "info=" + info +
                '}';
    }
}
class Graph<E extends Comparable<E>>
{
    public int mtx[][];
    public GraphInfo<E> infos[];
    public int n;
    public Graph(int n)
    {
        this.n=n;
        infos=new GraphInfo[n];
        mtx=new int[n][n];
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                mtx[i][j]=0;
    }
    public void addEdge(int x,int y)
    {
        mtx[x][y]=1;
        mtx[y][x]=1;
    }
    public void deleteEdge(int x,int y)
    {
        mtx[x][y]=0;
        mtx[y][x]=0;
    }
    public void setInfo(int pos,E info)
    {
        GraphInfo<E> n=new GraphInfo<>(info);
        infos[pos]=n;
    }
    public E getInfo(int pos)
    {
        return infos[pos].info;
    }
    public int getIndex(E info)
    {
        for (int i=0;i<n;i++)
            if(infos[i]==info)
                return i;
        return -1;
    }
    public boolean neighbours(int x,int y)
    {
        if(mtx[x][y]==1)
            return true;
        return false;
    }
    public void addNode(E info)
    {
        ++n;
        GraphInfo[] infospom=new GraphInfo[n];
        for (int i=0;i<n-1;i++)
            infospom[i]=infos[i];
        int [][] mtxpom=new int[n][n];
        for (int i=0;i<n-1;i++)
            for (int j=0;j<n-1;j++)
                mtxpom[i][j]=mtx[i][j];
        infospom[n-1]=new GraphInfo(info);
        for (int i=0;i<n;i++)
        {
            mtxpom[i][n-1]=0;
            mtxpom[n-1][i]=0;
        }
        mtx=mtxpom;
        infos=infospom;
    }
    public void deleteNode(E info)
    {
        int ind=getIndex(info);
        if(ind!=n-1)
        {
            for (int i=ind;i<n-1;i++)
            {
                infos[i]=infos[i+1];
                for (int j=0;j<n-1;j++)
                {
                    mtx[j][i]=mtx[j][i+1];
                    mtx[i][j]=mtx[i+1][j];
                }
            }
        }
        n--;
    }
    public void delete(LinkedList<Integer> list)
    {
        for (Integer k:list)
        {
            for (int i=0;i<n;i++)
            {
                if (mtx[k][i]>0 && infos[k].info==infos[i].info)
                    deleteEdge(k,i);
            }
        }
    }
    public void interate(LinkedList<Integer> list)
    {
        for(int k=0;k<n;k++)
        {
            int count=0;
            for (int i=0;i<n;i++)
            {
                if(mtx[k][i]>0 && infos[k].info==infos[i].info)
                    count++;
            }
            if (count>=3)
            {
                list.add(k);
            }
        }
    }
    public int count()
    {
        int c=0;
        for (int i=0;i<n;i++) {

            int j;
            for ( j = 0; j < n; j++) {
                if(mtx[i][j] != 0)
                    break;
            }
            if(j==n )
                c++;
        }
        return c;
    }
    public int deleteTest()
    {
        LinkedList<Integer> list=new LinkedList<>();
        this.interate(list);
        System.out.println("Size: "+list.size());
        this.delete(list);
        return count();
    }
    public void dfs(int poseteni[],int start)
    {
        poseteni[start]=1;
        System.out.print(infos[start]+", ");
        for (int i=0;i<n;i++)
        {
            if (mtx[start][i]>0 && poseteni[i]==0)
            {
                dfs(poseteni,i);
            }
        }
    }
    public int pateki(int poc,int kraj,int dolzina,boolean pominatiPriPateka[])
    {
        if(dolzina==0)
        {
            if(poc==kraj)
                return 1;
            else
                return 0;
        }
        else
        {
            pominatiPriPateka[poc]=true;
            int c=0;
            for (int i=0;i<n;i++)
            {
                if (mtx[poc][i]==1 && pominatiPriPateka[i]==false)
                {
                    c+=pateki(i,kraj,dolzina-1/*,dolzinaPoc*/,pominatiPriPateka);
                }
            }
            pominatiPriPateka[poc]=false;
            return c;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph=new Graph<>(7);
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(2,5);
        graph.addEdge(5,4);
        graph.addEdge(0,6);
        graph.addEdge(6,7);
        graph.addEdge(7,5);
        graph.addEdge(6,2);
        boolean niza[]=new boolean[graph.n];
        for (int i=0;i<graph.n;i++)
            niza[i]=false;
        System.out.println(graph.pateki(0,4,4,niza));
    }
}