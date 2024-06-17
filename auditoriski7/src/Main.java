public class Main {
    public static void main(String[] args) {
        int mat[][]=new int[4][4];
        for (int i=0;i<4;i++)
        {
            for (int j=0;j<4;j++)
            {
                System.out.print(1+" ");
                mat[i][j]=1;
            }
            System.out.println();
        }
        System.out.println(pateka(mat,4,4));
    }
    private static int pateka(int [][]A,int m,int n)
    {
        int [][]B=new int[m][n];
        B[0][0]=A[0][0];
        for(int i=1;i<m;i++)
            B[i][0]=B[i-1][0]+A[i][0];
        for (int i=1;i<n;i++)
            B[0][i]=B[0][i-1]+A[0][i];
        for (int i=1;i<m;i++)
        {
            for (int j=1;j<n;j++)
            {
                int p1=B[i-1][j];
                int p2=B[i][j-1];
                if(p1>p2)
                    B[i][j]=p1+A[i][j];
                else
                    B[i][j]=p2+A[i][j];

            }
        }
        return B[m-1][n-1];
    }
}