package day20;

import java.util.HashSet;
import java.util.Set;

/*
一、选择题
1、B
2、
3、B
4、C
5、B

二、填空题
1、OutputStream
2、String  StringBuilder
3、10

四、智力题


 */
public class Day20Test {

    public class Solution {
        public int Add(int num1,int num2) {

            while(num2!=0){
                int carry = (num1&num2)<<1;//进位。即二进制与，再左移
                num1 = num1^num2;//直接相加，不考虑进位。即二进制异或操作
                num2 = carry;    //num2作为进位
            }
            return num1;

        }
    }


    public static void main(String[] args) {
        int [] array ={3,2,1,4,1};
        System.out.println(sumNum(array,5));
    }

    public static int sumNum(int[] array , int num){
        int count =0;
        int len = array.length;
        for (int i = 0; i <len ; i++) {
            int sum = 0;
            for (int j = i; j <len ; j++) {
                sum += array[j];
                if (sum == num){
                    count++;
                    break;
                }else if (sum > num){
                    break;
                }

            }

        }
        return count;
    }
}


/*public class Day20Test {
    public static void main(String args[]) {
        int [][]array = {
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };

        int key = 3;
        findArray(array,key);
        System.out.println(findArray(array,key));

    }

    public static boolean findArray(int [][] array,int key){
        int row = 0;
        int col = array[0].length-1;

        while(row < array.length || col >=0 ){
            if (array[row][col] == key ){
                return true;
            }else if (col >0 && array[row][col] > key  ) {
                col--;
            }else if(row < array.length-1 && array[row][col] < key){
                row++;
            }else{
                break;
            }
        }

        return false;
    }
}*/

