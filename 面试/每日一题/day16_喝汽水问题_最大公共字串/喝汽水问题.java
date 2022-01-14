import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        //while((num = s.nextInt()) != 0)

        while(scan.hasNext()){
            int num = scan.nextInt();
            if(num == 0){
                break;
            }
            
            int sum = 0;
            while(num > 1){ 	//不能是 num >0 ,否则当num== 1时,会出现死循环,
                sum += num /3;
                num = num/3 + num%3;
                
                if(num == 2){
                    sum++;
                    break;
                }
            }
            System.out.println(sum);
        }
    }
}