//https://leetcode-cn.com/problems/last-stone-weight/submissions/

class Solution {
    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(stones.length,(o1,o2)-> o2-o1);
        //创建一个容量为stones长度的大堆

        for(int i =0; i< stones.length;i++){
            maxHeap.offer(stones[i]);
        }

        while(maxHeap.size() > 1){
            int y = maxHeap.poll();
            int x = maxHeap.poll();

            if(y > x) maxHeap.offer(y-x);
        }

       int ret =  maxHeap.isEmpty()? 0:maxHeap.peek();
       return ret;
        // maxHeap.isEmpty() == true? return 0 : return maxHeap.peek();
    }
}