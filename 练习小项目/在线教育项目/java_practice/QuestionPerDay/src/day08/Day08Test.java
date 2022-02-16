package day08;
/*
一、选择题
1.A
2.B
3.B
4.A
5.B

二、填空题
1、抽象类，abstract
2、a[6]
3、


 */


import org.junit.Test;

public class Day08Test {

    //三、算法题：
    //一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
    @Test
    public void findNum(){
        int [] array = {1,3,5,7,1,3,5,9};
        int result = 0;
        for (int i = 0; i <array.length ; i++) {
            result ^= array[i];
        }

        int n = 0;
        for (int i =0; i < 32; i++) {
            if ((result & (1 << i)) != 0){
                n = 1 << i;
                break;
            }
        }

        int ret1 = 0;
        int ret2 = 0;
        for (int j = 0; j < array.length; j++) {
            if ((array[j] & n) == 0){
                ret1 ^= array[j];
            }else{
                ret2 ^= array[j];
            }
        }

        System.out.println("num1 = "+ ret1 + ",num2 = " + ret2);

    }


}
