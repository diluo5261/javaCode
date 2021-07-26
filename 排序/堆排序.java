public class HeapSortTest {
    public static void main(String[] args) {
        int[] array = {0,4,7,8,9,34,5,22,56,6,10,12,20};
        createHeap(array);

        int end = array.length -1;
        while(end > 0){
            int tmp = array[end];
            array[end] = array[0];
            array[0] = tmp;

            siftDown(array,0,end);
            end--;
        }

        System.out.println(Arrays.toString(array));


    }

    public static void createHeap(int[] array){
        for (int i = (array.length-1-1)/2; i >=0; i--) {
            siftDown(array,i,array.length);
        }
    }

    public static void siftDown(int[] array,int root,int len){
        if(array == null) return;

        int parent = root;
        int child = parent*2+1;

        while(child < len){

            if ( child+1 <len && array[child] < array[child+1]){
                child++;
            }

            if(array[parent] < array[child]){
                int tmp = array[child];
                array[child] = array[parent];
                array[parent] = tmp;

                parent = child;
                child = parent*2+1;
            }else{
                break;
            }

        }

    }

}