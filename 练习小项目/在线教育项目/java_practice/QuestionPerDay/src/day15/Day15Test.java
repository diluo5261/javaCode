package day15;
/*
一、选择题
1.B
2.A，C
3.B
4.B
5.D

二、填空题
1.byte 、short 、int 、long
2.线性结构，链式结构
3.250

四、智力题
1.140 g 分为两份 70g
2.70g分为 两份35g
3.砝码放在天平两端，7g砝码 + 15g盐   =   2g砝码  + 20g盐

70g+ 20g = 90g
35 g+ 15g = 50g

 */
public class Day15Test {
    public static void main(String[] args) {
        System.out.println(CheckBinary(10));
    }

    public static boolean CheckBinary(int num){
        if (1 > num) return false;

       if((num & (num -1)) == 0){
           return true;
       }
       return false;
    }
}
