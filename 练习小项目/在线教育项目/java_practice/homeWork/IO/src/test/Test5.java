package test;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            int num = scan.nextInt();
            int count = 0;
            int pre = 0;
            while(num != 0){
                if((num & 1) == 1){
                    pre++;
                    count = Math.max(pre,count);
                }else{
                    pre = 0;
                }
            }

        }
    }
}
