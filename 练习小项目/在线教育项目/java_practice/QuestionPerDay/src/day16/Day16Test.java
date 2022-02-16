package day16;
/*
一\选择题
1.A
2.C
3.C
4.D
5.D

二、填空题
1、Unicode 3
2、30
3、n+1


log()
1x2x3x4 .... x1000
求位数只要求其对数log(1) + log(2) + ... ... +log(1000) = log(1 + .. 1000) = log(1000x(1 +1000)/2) = 500500
2568位

 */


import javax.crypto.spec.DESKeySpec;
import java.util.Scanner;

public class Day16Test {
    /*
    1^3 = 1
    2^3 =  3  +  5
    3 ^3 = 7 + 9 + 11
    4^3 = 13 + 15 + 17 + 19


     首项= n*(n-1)+1 + n*(n-2)+1+2 + n*(n-1)+1+2+2+ ...n*(n-1)+1+2*(n-1)(等差数列)


     */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            long num = scan.nextLong();
            System.out.println(Nicosiche(num));
        }



    }

    public static String Nicosiche(long num){
        if (num <= 0){
            return null;
        }

        long result  = (long) Math.pow(num,3);
        long sum =0 ;//第一项

        StringBuilder stringBuilder = new StringBuilder();

        for (long i = 0; i < num; i++) {
            long numOfItems = num*(num-1)+1+2*(i);
            sum += numOfItems;
            if (i < num -1){
                stringBuilder.append(numOfItems).append(" + ");
            }else{
                stringBuilder.append(numOfItems).append(" = ");
            }
        }

        if (sum == result){
            stringBuilder.append(sum);
            return stringBuilder.toString();
        }else{

            return null;
        }


    }
}
