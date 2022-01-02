import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            int num = scan.nextInt();
            
            int curCount =0;
            int maxCount = 0;
            
            while(num != 0){
                if((num & 1) == 1){
                    
                    curCount++;
                    if(curCount > maxCount){
                        maxCount = curCount;
                    }
                    
                }else{
                    curCount = 0;
                }  
                
                num = num >>> 1;
            }
            
            System.out.println(maxCount);
        }
    }
}