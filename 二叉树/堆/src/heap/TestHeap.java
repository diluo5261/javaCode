package heap;

import java.util.Arrays;

public class TestHeap{
    int [] elem;
    int usedSize;
    
    public TestHeap(){
        elem = new int[10];
    }

    //1、建大堆
    public void createHeap(int [] array){
        for (int i = 0; i <array.length ; i++) {
            elem[i] = array[i];
            usedSize++;
        }

        for (int parent = (usedSize-1-1)/2;  parent >= 0; parent--){
            adjustDown(parent,usedSize-1);
        }
    }

    //2、向下调整
    public void adjustDown(int parent,int limit){
        int p = parent;
        int c = p*2+1;
        while(c <= limit){
            if(c+1 <= limit && elem[c] < elem[c+1]){
                c++;
            }

            if(elem[c] > elem[p]){
                int tmp = elem[c];
                elem[c] = elem[p];
                elem[p] = tmp;

                p =c;
                c= p*2+1;
            }else{
                break;
            }
        }
    }

    //3、向上调整
    public void adjustUp(int child){
        int c = child;
        int p = (c-1)/2;

        while(c > 0){
            if (elem[c] > elem[p]){
                int tmp = elem[c];
                elem[c] = elem[p];
                elem[p] = tmp;

                c= p;
                p= (c-1)/2;
            }else{
                break;
            }
        }
    }

    //4、添加数据
    public void push(int val){
        if(isFull()){
            elem = Arrays.copyOf(elem,2*usedSize);
        }
        elem[usedSize] = val;
        usedSize++;
        adjustUp(usedSize-1);
    }

    public boolean isFull(){
        return usedSize == elem.length;
    }


    //5、弹出最大值
    public int poll(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }

        int tmp = elem[0];
        elem[0] = elem[usedSize-1];
        elem[usedSize-1] = tmp;
        usedSize--;
        adjustDown(0,usedSize-1);

        return  tmp;
    }

    public boolean isEmpty(){
        return usedSize == 0;
    }



    //6、查看堆顶的值
    public int peek(){
        if (isEmpty()) throw  new RuntimeException("队列为空！");
        return elem[0];
    }


    //7、堆排序
    public void heapSort(){
        int end = usedSize-1;
        while(end > 0){
            int tmp = elem[0];
            elem[0] = elem[end];
            elem[end] = tmp;
            System.out.println(elem[end]);
            end--;

            adjustDown(0,end);
        }
    }

}

