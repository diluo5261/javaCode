import java.util.*;

public class Test2 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();

        if (m == 0 || n == 10){
            System.out.println(m);
            return;
        }

        boolean flag = true;
        if (m < 0){
            m =-m;
            flag = false;
        }


        String hex ="0123456789ABCDEF";
        StringBuilder builder = new StringBuilder();

        while(m != 0){
            builder.append(hex.charAt( m%n));
            m = m/n;
        }

        if (flag){
            System.out.println(builder.reverse().toString());
        }else{
            System.out.println("-"+builder.reverse().toString());
        }

    }
}
