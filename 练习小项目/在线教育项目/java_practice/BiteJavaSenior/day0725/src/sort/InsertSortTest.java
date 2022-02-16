package sort;


import java.util.Arrays;

public class InsertSortTest {
    public static void main(String[] args) {
        int[] array = {0,4,7,8,9,34,5,22,56,6,10,12,20};
        insertSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void insertSort(int [] array){
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
}
