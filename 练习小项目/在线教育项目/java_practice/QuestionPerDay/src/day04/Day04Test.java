package day04;

import java.util.Arrays;

public class Day04Test {
    public static void main(String[] args) {

        int [] array ={1,2,3,4,5,7,7,8};
        int key = 9;
        System.out.println(Arrays.toString(findIndex(array,key)));

    }

    public static int[] findIndex(int[] array,int key){
        int left =0;
        int right = array.length -1;

        while(left < right){
            int sum = array[left] + array[right];
            if(sum == key){
                return  new int[]{left,right};
            }else if (sum > key){
                right--;
            }else if (sum < key){
                left++;
            }else{
                break;
            }
        }
        return null;
    }
}
