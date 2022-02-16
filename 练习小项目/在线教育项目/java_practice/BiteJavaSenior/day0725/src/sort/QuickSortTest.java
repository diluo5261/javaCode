package sort;

import java.util.Arrays;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] array = {0,4,7,8,9,34,5,22,56,6,10,12,20};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array){
        quick(array,0,array.length-1);

    }

    public static void quick(int[] array,int start,int end){
        if(start >= end) return;

        int pivot = partition(array,start,end);
        quick(array,start,pivot-1);
        quick(array,pivot+1,end);
    }

    public static int partition(int[] array,int low,int high){
        int tmp = array[low];
        while(low < high){

            while(low < high && array[high] >= tmp){
                high--;
            }

            array[low] = array[high];
            while (low < high &&array[low] <= tmp){
                low++;
            }
            array[high] = array[low];
        }
        array[low] = tmp;
        return low;
    }

    //优化后的快排
    public static void selectPivotMedianOfThree(int[] array,int start,int end,int mid){
        int [] tmp = {array[start],array[end],array[mid]};

        Arrays.sort(array);

        array[start] = tmp[0];
        array[mid] = tmp[1];
        array[end] = tmp[2];
    }
}
