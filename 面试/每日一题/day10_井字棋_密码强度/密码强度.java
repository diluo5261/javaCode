import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String str = scan.nextLine();
            
            int sum = getAward(str);
            if(sum >= 90){
                System.out.println("VERY_SECURE");
            }else if(sum >= 80){
                System.out.println("SECURE");
            }else if(sum >= 70){
                System.out.println("VERY_STRONG");
            }else if(sum >= 60){
                System.out.println("STRONG");
            }else if(sum >= 50){
                System.out.println("AVERAGE");
            }else if(sum >= 25){
                System.out.println("WEAK");
            }else{
                System.out.println("VERY_WEAK");
            }
            
        }
    }
    
    public static int getLen(String str){
        int len = str.length();
        
        if(len >= 8){
            return 25;
        } else if(len >4){
            return 10;
        }else {
            return 5;
        }
    }
    
    public static int getChar(String str){
        int len = str.length();
        boolean big = false;
        boolean small = false;
        
        for(int i =0; i<len;i++){
            if(big && small) break;
            char ch = str.charAt(i);
            if(ch >= 65 && ch <= 90){
                big = true;
            }else if(ch >= 97 && ch <= 122){
                small = true;
            }
        }
        
        if(big && small){
            return 20;
        }else if(big || small){
            return 10;
        }else{
            return 0;
        }
    }
    
    public static int getNum(String str){
        int len = str.length();
        
        int sum = 0;
        for(int i =0;i<len;i++){
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9'){
                sum++;
            }
        }
        
        if(sum >1){
            return 20;
        }else if(sum == 1){
            return 10;
        }else{
            return 0;
        }
    }
    
    public static int getSign(String str){
        int len = str.length();
        int sum =0;
        for(int i=0;i<len;i++){
            char ch = str.charAt(i);
            if(!(ch >= 65 && ch <= 90) && 
               !(ch >= 97 && ch <= 122) && 
               !(ch >= '0' && ch <= '9')){
                sum++;
               }
        }
        
        if(sum > 1){
            return 25;
        }else if(sum == 1){
            return 10;
        }else{
            return 0;
        }
    }
    

    
    public static int getAward(String str){
        int sum1= getLen(str);
        int sum2 = getChar(str);
        int sum3 = getNum(str);
        int sum4 = getSign(str);
        if(sum2 == 20 && sum3 >= 10 && sum4 >= 10){
            return sum1+sum2+sum3+sum4+5;
        }else if(sum2 >= 10 && sum3 >= 10 && sum4 >=10){
            return sum1+sum2+sum3+sum4+3;
        }else if(sum2 >= 10 && sum3 >= 10){
            return sum1+sum2+sum3+sum4+2;
        }else{
            return sum1+sum2+sum3+sum4;
        }
      
    }
}