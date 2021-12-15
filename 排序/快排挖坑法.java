public static int partition(int[] array,int left,int right){
    int tmp = array[left];
    while(left < right){
        while(left < right && array[right]>=tmp){
            right--;
        }
        array[left] = array[right];
        
        while(left < right && array[left] <= tmp){
            left++;            
        }
        
        array[right] = array[left];
    }
    
    array[left] = tmp;
    return left;
}

public void quickSort(int[] array,int start,int end){
    if(start >= end){
        return;
    }
    int pivot = partition(array,start,end);
    quickSort(array,start,pivot-1);
    quickSort(array,pivot+1,end);
}