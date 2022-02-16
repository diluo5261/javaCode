package day07;

import org.junit.Test;

/*
一、选择题
1、A
2、A、C
3、C
4、D
5、C

二、填空题
1、装饰设计模式
2、finally
3、5，11

三、算法题：

四、智力题
利用灯泡会发光发热的原理，仅仅看灯泡是否亮不能判断出开关顺序
假设将开关分别命名为A B C，
1、先将A 开关打开，让电灯通电一段时间
2、再打开开关B，推开屋门
3.灯亮的是开关B控制，判断剩余两盏灯的温度，温度高的为开关A控制，温度低的为开关C控制


 */
public class Day07Test {

    //输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的
    //最大值。要求时间复杂度为O(n)
    @Test
    public void maxSum(){
        int [] array = {1,-2,3,10,-4,7,2,-5};
        int len = array.length;
        if (len <= 1){
            System.out.println("max = " + array[0]);
            return;
        }

        int max = array[0];
        int sum = array[0];

        for (int i = 1; i < len; i++) {
            //如果数组之和为负数时，上一个元素肯定为负数
            //从新开始计算子数组之和，max存放上一个最大值子数组的和
            if(sum <= 0){
                sum = array[i];
            }else{
                sum += array[i];
            }

            if (sum > max){
                max =sum;
            }
        }
        System.out.println("max = " + max);

    }
}
