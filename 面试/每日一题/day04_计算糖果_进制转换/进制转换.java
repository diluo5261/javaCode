import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int n = scan.nextInt();
        
        StringBuilder builder = new StringBuilder();
        String table = "0123456789ABCDEF";
        
        boolean flag = false;
        if(m < 0){
            m = -m;
            flag = true;
        }
        
        while(m != 0){
            builder.append(table.charAt(m % n));
            m /= n;
            
        }
        
        if(flag){
            builder.append("-");
        }
        
        builder.reverse();
        System.out.println(builder.toString());
    }
}