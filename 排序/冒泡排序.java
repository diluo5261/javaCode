/**********************方法一:未优化*************************************/

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


/**************************方法二:优化后**************************/
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