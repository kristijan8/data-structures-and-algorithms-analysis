import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {

        String str="abaalalax";
        System.out.println(f(str,0,str.length()-1));
    }
    private static boolean isPalindrome(String s,int start,int end)
    {
        while(s.charAt(start)!=s.charAt(end))
        {

        }

        return true;
    }
    private static int f(String str,int start,int end)
    {
        if(start>=end || isPalindrome(str))
            return 0;
        int ans=Integer.MAX_VALUE;
        int count;
        for(int k=start;k<end;k++)
        {
            count=1+f(str,start,k)+f(str,k+1,end);
            ans=Math.min(ans,count);
        }



        return ans;
    }
}