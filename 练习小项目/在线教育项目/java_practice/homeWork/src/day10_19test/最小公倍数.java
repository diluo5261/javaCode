package day10_19test;

import java.util.Scanner;

public class 最小公倍数 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int max_num = scan.nextInt();
        int min_num = scan.nextInt();

        if(max_num < min_num){
            int tmp = max_num;
            max_num = min_num;
            min_num = tmp;
        }

        if(max_num % min_num == 0){
            System.out.println(max_num);
            return;
        }


        int result = max_num;
        while(true){
            result++;
            if (result % min_num == 0 && result % max_num == 0){
                break;
            }
        }

        System.out.println(result);
    }
}
