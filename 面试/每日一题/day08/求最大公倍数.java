import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int num1 = scan.nextInt();
        int num2 = scan.nextInt();
        
        int common = maxCommon(num1,num2);
        
        System.out.println(num1*num2/common);
    }
    
    //求最大公约数
    public static int maxCommon(int num1 , int num2){
        //两个数相等,返回其中一个
        if(num1 == num2) return num1;
        
        if(num2 < num1){
            int tmp = num2;
            num2 = num1;
            num1 = tmp;
        }
        
        int r;
        while((r = num1 % num2) != 0){
            num1 = num2;
            num2 = r;
        }
        
        return num2;
    }
}