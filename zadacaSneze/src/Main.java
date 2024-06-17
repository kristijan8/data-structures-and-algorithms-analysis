import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Character> magacin=new Stack<Character>();
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        char niza[];
        niza=str.toCharArray();
        String[] arr=new String[niza.length];
        for (int i = 0; i < niza.length; i++) {
            if (niza[i]=='(')
                magacin.push(niza[i]);
            else if (niza[i]==')')
            {
                while(!magacin.empty())
                {

                    char pom1=magacin.pop();
                    char pom2=magacin.pop();
                    char pom3=magacin.pop();
                    int o1=(int) pom1-48;
                    int o2=(int) pom3-48;
                    Integer rez;
                    if (pom2=='+')
                        rez=o1+o2;
                    else if (pom2=='-')
                        rez=o1-o2;
                    else
                    {
                        System.out.println("Greska");
                        break;
                    }
                    magacin.pop();
                    magacin.push(rez.toString().charAt(0));
                    break;
                }
            }
            else
            {
                magacin.push(niza[i]);
            }
        }
        System.out.println(magacin.pop());


    }
}