import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int num = scan.nextInt();
            int count=0;
            while(num != 0){
                num = num & (num-1);
                count++;
            }
            System.out.println(count);
        }
    }
}