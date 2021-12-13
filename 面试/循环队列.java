public class MyCircularQueue {
    public int[] elem;
    public int front;
    public int rear;

    // 初始化循环队列空间大小
    public MyCircularQueue(int k){
    	// 因为设定是K长度的数组，而我们浪费了一个空间，所以需要加1
        this.elem = new int[k+1];
    }

    //  入队
    public boolean enQueue(int val){
        if(isFull()){
            return false;
        }
        //  这里多余了，因为队头默认是0，而绑定（或改变）队头位置是在出队方法中做标记
        /*if(isEmpty()){
            this.elem[this.front] = val;
            this.rear = (this.rear+1)%this.elem.length;
            return true;
        }*/
        this.elem[this.rear] = val;
        this.rear = (this.rear+1)%this.elem.length;
        return true;
    }

    // 判断是否为满
    public boolean isFull(){
        if((this.rear+1)%this.elem.length == this.front) return true;
        return  false;
    }

    // 判断是否为空
    public boolean isEmpty(){
        if(this.front == this.rear) return true;
        return false;
    }

    // 出队
    public boolean deQueue(){
        if(isEmpty()) return false;
        this.front = (this.front + 1)%this.elem.length;
        return true;
    }

    // 队头元素
    public int front(){
        if(isEmpty()) return -1;
        return this.elem[this.front];
    }

    // 队尾元素
    public int rear(){
        if(isEmpty())return -1;

        int index = this.rear == 0? this.elem.length-1 : this.rear -1;
        return this.elem[index];
    }
}
