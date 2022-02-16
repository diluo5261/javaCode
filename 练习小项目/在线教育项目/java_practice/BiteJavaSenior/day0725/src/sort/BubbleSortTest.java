package sort;

import java.util.Arrays;

public class BubbleSortTest {
    public static void main(String[] args) {
        int[] array = {0,4,7,8,9,34,5,22,56,6,10,12,20};
//        bubbleSort(array);
        bubbleSort2(array);
        System.out.println(Arrays.toString(array));

    }

    public static void bubbleSort(int[] array){
        if(array == null) return;
        for (int i = 0; i <array.length-1 ; i++) {
            for (int j = 0; j <array.length-1-i ; j++) {
                if(array[j] > array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    public static void bubbleSort2(int[] array){
        if (array == null) return;

        for (int i = 0; i <array.length-1 ; i++) {
            boolean flag = true;
            for (int j = 0; j <array.length-1-i ; j++) {
                if(array[j] > array[j+1]){
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }
}
