package sort;

import java.util.Arrays;

public class ShellSortTest {

    public static void main(String[] args) {
        int[] array = {0,4,7,8,9,34,5,22,56,6,10,12,20};
        int gap = array.length;
        while(gap > 1){
            gap = gap /3 +1;
            shellSort(array,gap);
        }
        System.out.println(Arrays.toString(array));
    }

    public static void shellSort(int[] array,int gap){
       if(array == null) return;

        for (int i = gap; i <array.length ; i++) {

            int tmp = array[i];
            int j = i-gap;
            for ( ;j >= 0 ; j -= gap) {
                if(array[j] > tmp){
                    array[j+gap] = array[j];
                }else{
                    break;
                }
            }
            array[j+gap] = tmp;
        }
    }
}
