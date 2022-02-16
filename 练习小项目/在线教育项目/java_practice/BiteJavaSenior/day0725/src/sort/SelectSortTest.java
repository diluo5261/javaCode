package sort;

import java.util.Arrays;

public class SelectSortTest {
    public static void main(String[] args) {
        int[] array = {0,4,7,8,9,34,5,22,56,6,10,12,20};
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void selectSort(int[] array){
       if(array == null) return;
        for (int i = 0; i <array.length-1; i++) {
            for (int j = i+1; j <array.length ; j++) {
                if(array[i] > array[j]){
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }
}
