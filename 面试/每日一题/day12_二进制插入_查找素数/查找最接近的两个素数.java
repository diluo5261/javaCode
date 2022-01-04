import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        while(scan.hasNext()){
            int n = scan.nextInt();
            
            int half = n/2;
            
            for(int i =half;i>0;i--){
                if(isPrime(i)&&isPrime(n-i)){
                    System.out.println(i);
                    System.out.println(n-i);
                    break;
                }
            }
        }
    }
    
    public static boolean isPrime(int n){
        for(int i =2;i*i<=n;i++){
            if(n%i == 0){
                return false;
            }
        }
        return true;
    }
}