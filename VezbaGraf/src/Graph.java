import java.util.ArrayList;

public class Graph<E extends Comparable<E>> {
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
        mtx[x][y]=mtx[y][x]=1;
    }
    public void deleteEdge(int x,int y)
    {
        mtx[x][y]=mtx[y][x]=0;
    }
    public void setInfo(int pos,E infov)
    {
        infos[pos]=new GraphInfo<>(infov);
    }
    public E getInfo(int pos)
    {
        return infos[pos].info;
    }
    public int getIndex(E infov)
    {
        for (int i=0;i<n;i++)
        {
            if (infos[i].info==infov)
                return i;
        }
        return -1;
    }
    public boolean neighbours(int x,int y)
    {
        return mtx[x][y]==1;
    }
    public void addNode(E infov)
    {
        ++n;
        GraphInfo<E> infosPom[]=new GraphInfo[n];
        int mtxPom[][]=new int[n][n];
        for (int i=0;i<n-1;i++)
        {
            infosPom[i]=infos[i];
            for (int j=0;j<n-1;j++)
                mtxPom[i][j]=mtx[i][j];
        }
        for (int i=0;i<n;i++)
        {
            mtxPom[i][n-1]=0;
            mtxPom[n-1][i]=0;
        }
        infosPom[n-1]=new GraphInfo<>(infov);
        mtx=mtxPom;
        infos=infosPom;
    }
    public void deleteNode(E infov)
    {
        int t=getIndex(infov);
        if(t==-1)
            return;
        if(t==n-1)
            n--;
        else
        {
            GraphInfo<E> infosPom[]=new GraphInfo[n-1];
            int j=0;
            int mtxPom[][]=new int[n-1][n-1];
            for (int i=0;i<n;i++)
            {
                if(i!=t)
                {
                    infosPom[j]=infos[i];
                }
            }
            int s=0;
            for (int i=0;i<n;i++)
            {
                if(i!=t)
                {
                    int k = 0;
                    for (j = 0; j < n; j++)
                    {
                        if (k != t)
                        {
                            mtxPom[i][k] = mtx[i][k];
                            k++;
                        }
                    }
                    s++;
                }
            }
            n--;
            mtx=mtxPom;
            infos=infosPom;
        }
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
            if (mtx[moment][start]>0)
                return 1;
        }
        int count=0;
        niza[moment]=true;
        for (int i=0;i<n;i++)
        {
            if (mtx[moment][i]>0 && niza[i]==false)
            {
                count+=func1(start,i,broj-1,niza);
            }
        }
        niza[moment]=false;
        return count;
    }
}
