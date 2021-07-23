//https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/submissions/

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(k,(o1,o2)->o2.get(0)+o2.get(1)-o1.get(0)-o1.get(1));//建立一个容量为k的大堆

        for(int i =0;i < nums1.length; i++){
            for(int j=0;j<nums2.length;j++){
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);

                if(minHeap.size() < k){
                    minHeap.offer(list);
                }else{
                    List<Integer> top = minHeap.peek();
                    int topVal = top.get(0) + top.get(1);
                    if(topVal > nums1[i]+nums2[j]){
                        minHeap.poll();
                        minHeap.offer(list);
                    }
                }
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        for(int i =0;i<k && minHeap.peek() != null;i++){
            ret.add(minHeap.poll());
        }
        return ret;

    }
}