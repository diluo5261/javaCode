public class HashBucket {  
    static class Node{  
        int key;  
 		int val;  
 		Node next;  
  
 		public Node(int key, int val) {  
            this.key = key;  
 		this.val = val;  
 		}  
    }  
  
  
 	private Node[] arrays;  
 	private int usedSize;  
  
 	public HashBucket() {  
        this.arrays = new Node[10];  
 		this.usedSize =0;  
 	}  
  
    public void put(int key,int val){  
		//1、确定下标  
		int index = key % this.arrays.length;  
		Node cur = arrays[index];  
		//2、判断当前位置是否有元素  
		while(cur != null){  
				if(cur.key == key){  
					cur.val = val;  
					return; 
				}  
				cur = cur.next;  
		}  

		//3、执行到此位置时，说明该位置没有元素  
		//进行头插法  
		Node node = new Node(key, val);  
		node.next = arrays[index];  
		arrays[index] = node;  
		this.usedSize++;  

	 //4、判断当前负载因子，是否超过，超过负载因子进行扩容  
		if(loadFactory() >= 0.75){  
				reSize();  
		}  
    }  
  
  //**********************  负载因子  **************************************
    
    public double loadFactory(){  
        return this.usedSize*1.0 / arrays.length;  
 	}  
  
  
  //*****************************  扩容  *********************************
  
    public void reSize(){  
        //1、创建新的数组，容量为原来的2倍  
 		Node[] newArrays = new Node[arrays.length * 2];  
  
 		//2、遍历原有数组，将原数组数据存储到新的数组当中  
        //最外层控制数组的下标
 		for(int i =0;i<arrays.length;i++){  
            Node cur = arrays[i];  
 			Node curNext = null;  
  
 			while(cur != null){  
                curNext = cur.next;  
                //在新的数组里面的下标
				int index = cur.key % newArrays.length; 
                //进行头插法
				cur.next = newArrays[index];  
				newArrays[index] =cur;  
				cur =curNext;  
				}  
        }  
        this.arrays = newArrays;  
 }  
  
  //*************************** 获取数据 ************************
  
    public int get(int key){  
        int index = key % arrays.length;  
 		Node cur = arrays[index];  
  
 		while(cur != null){  
            if(cur.key == key){  
                return cur.val;  
 			}  
			
            cur = cur.next;  
 		}  
        return -1;  
}