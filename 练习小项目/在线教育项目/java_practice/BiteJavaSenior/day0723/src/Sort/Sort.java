package Sort;

import org.junit.Test;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int [] array = {21,35,21,85,65,98,41,20,30,01,21};
//        directInsertSort(array);
        selectSort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public static void directInsertSort(int [] array){
       if(array == null) return;

        for (int i = 1; i <array.length ; i++) {
            int tmp = array[i];
            int j = i-1;
            for (; j >= 0 ; j--) {
                if(array[j] > tmp){
                    array[j+1] = array[j];
                }else{
                    break;
                }
            }
            array[j+1] = tmp;

        }

    }

    public static void selectSort(int [] array){
        if(array == null) return;
        for (int i = 0; i <array.length ; i++) {
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
