/*
	时间复杂度：
				最好情况：O(N)  		数据是有序的
				最坏情况：O(N^2)  	数据是无序的
				
	空间复杂度：O (1)
	稳定性：稳定
				if(arr[j] >=tmp) ，这里条件如果取 >= 则是不稳定的，一个稳定的排序，可以实现为不稳定的排序
*/
public static void insertSort(int[] arr){
    int len= arr.length;
    for(int i = 1;i<len;i++){
        int tmp = arr[i];
        int j= i-1;
        for(;j>=0;j--){
            if(arr[j] > tmp){		//if(arr[j] >=tmp)  不稳定
                arr[j+1] = arr[j];
            }else{
                break;
            }
        }
        arr[j+1] = tmp;
    }
}