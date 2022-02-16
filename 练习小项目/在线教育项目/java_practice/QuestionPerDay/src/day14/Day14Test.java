package day14;
/*
* 一、选择题
* 1.A
* 2.A
* 3.A，B
* 4.B
* 5.C
* 二、填空题
*1.String 、接口 、数组
* 2.接口
* 3.4 1
* 四、智力题
*两次
* 方法一：将球分为三份，每份两个，将其中两组放在天平上称重，如果重量相等，则再称最后一组，如果重量不相等，在继续称重的一组
* 方法二：
* 天平两端分别放3个球，
* 1、如果重量相等，则称剩下的两个球
* 2、如果重量不相等，将三个球其中的两个放在天平上，即可得出较重的球；
*
*
* */
public class Day14Test {
    public static void main(String[] args) {
        int [] array = {3,2,6,3,1,9,2,6,1};
        int ret = 0;
        for (int i = 0; i <array.length ; i++) {
            ret ^= array[i];
        }
        System.out.println("出现1次的数字是 ："+ ret);
    }
}
