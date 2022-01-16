import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int n = scan.nextInt();
            int base = scan.nextInt();
            int[] arr = new int[n];
            
            for(int i =0;i<arr.length;i++){
                arr[i] = scan.nextInt();
                
                if(base >= arr[i]){
                    base += arr[i];
                }else{
                    base +=  getMaxCommon(base,arr[i]);
                }
            }
            
            System.out.println(base);
        }
    }
    
    public static int getMaxCommon(int num1,int num2){
        if(num1 < num2){
            int tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        
        int ret = 0;
        while((ret = num1%num2) != 0){
            num1 = num2;
            num2 = ret; 
        }
        
        return num2;
    }
}