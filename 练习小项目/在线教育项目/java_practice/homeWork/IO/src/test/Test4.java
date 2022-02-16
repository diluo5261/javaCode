package test;

import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String password = scan.nextLine();

            int score = passwordLength(password) + isContainLetters(password) + isContainNum(password)+
                    isContainSymbol(password) + award(password);

            if (score >= 90){
                System.out.println("VERY_SECURE");
            }else if (score >= 80){
                System.out.println("SECURE");
            }else if (score >= 70){
                System.out.println("VERY_STRONG");
            }else if (score >= 60){
                System.out.println("STRONG");
            }else if (score >= 50){
                System.out.println("AVERAGE");
            }else if (score >= 25){
                System.out.println("WEAK");
            }else{
                System.out.println("VERY_WEAK");
            }
        }
                

    }

    private static int award(String password){

        if(isContainLetters(password) == 20 && isContainNum(password) >0 && isContainSymbol(password) >0){
            return 5;
        }else if (isContainLetters(password) > 0 && isContainNum(password) >0 && isContainSymbol(password) >0){
            return 3;
        }else if (isContainLetters(password) >0 && isContainNum(password) > 0){
            return 2;
        }

        return 0;

    }

    //判断密码长度
    private static int passwordLength(String password){
        int len = password.length();
        if (len >= 8){
            return 25;
        }else if (len >= 5){
            return 10;
        }else{
            return 5;
        }
    }

    //判断是否包含字母

    private static int isContainLetters(String password){
        int len = password.length();

        //判断是否包含字母标志位
        int upSum = 0;
        int lowSum = 0;
        for (int i = 0; i < len; i++) {
            if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z' ){
                upSum++;
               
            }
            
            if ( password.charAt(i) >= 'a' && password.charAt(i) <= 'z'){
                lowSum++;

            }
        }
        
        if (upSum  == 0 && lowSum == 0){
            return 0;
        }else if(upSum  != 0 && lowSum != 0){
            return 20;
        }else{
            return 10;
        }
    }
    
    private static int isContainNum(String password){
        int len = password.length();

        int numCount = 0;
        for (int i = 0; i < len; i++) {
            if (password.charAt(i) >= '0' && password.charAt(i) <= '9'){
                numCount++;
            }
        }
        
        if (numCount >1){
            return 25;
        }else if (numCount == 1){
            return 10;            
        }else{
            return 0;
        }
    }
    
    private static int isContainSymbol(String password){
        int len = password.length();

        int symCount = 0;

        for (int i = 0; i < len; i++) {
            if (password.charAt(i) >= 0x21 && password.charAt(i) <= 0x2f ||
                    password.charAt(i) >= 0x3A && password.charAt(i) <= 0x40||
                    password.charAt(i) >= 0x5B && password.charAt(i) <= 0x60||
                    password.charAt(i) >= 0x7B && password.charAt(i) <= 0x7E){
                symCount++;
            }
            
        }

        if (symCount > 1){
            return 25;
        }else if (symCount == 1){
            return 10;
        }else{
            return  0;
        }
    }
}
