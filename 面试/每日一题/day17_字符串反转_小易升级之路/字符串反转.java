import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        
        
        while(scan.hasNext()){
            String str = scan.nextLine();
            char[] arr = str.toCharArray();
            int begin = 0;
            int end = str.length()-1;
            
            while(begin < end){
                char tmp = arr[begin];
                arr[begin]= arr[end];
                arr[end] = tmp;
                begin++;
                end--;
            }
            
            System.out.println(arr);
        }
    }
}