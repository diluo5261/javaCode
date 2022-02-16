package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Test {
    //查找最小的K对数组
    public List<List<Integer>> kSmallestPairs(int[] nums1,int[] nums2,int k){
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(k,(o1,o2)-> o1.get(0)-o2.get(0));
       /* PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(k, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o2.get(0) + o2.get(1) - o1.get(0) - o1.get(1);
            }
        });
*/
        for (int i = 0; i <nums1.length ; i++) {


            for (int j = 0; j <nums2.length ; j++) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);

                if(minHeap.size() < k){
                    minHeap.offer(list);

                }else{
                    
                    List<Integer> top = minHeap.peek();
                    int topValue = top.get(0) + top.get(1);
                    if(topValue > nums1[i] + nums2[j]){
                        minHeap.poll();
                        minHeap.offer(list);
                    }
                }
            }
        }
        
        List<List<Integer>> ret = new ArrayList<>();
        while(k-- > 0 && minHeap.peek()!= null ){
            ret.add(minHeap.poll());
        }
        return ret;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 -o1;
            }
        });

        for (int i = 0; i <arr.length ; i++) {
            if(minHeap.size() < k){
                minHeap.offer(arr[i]);
            }else{
                int topVal = Math.abs(minHeap.peek() - x);
                int newVal = Math.abs(arr[i] - x);
                if(topVal > newVal){
                    minHeap.poll();
                    minHeap.offer(arr[i]);
                }
            }
        }

        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i <k && minHeap.peek() != null ; i++) {
            ret.add(minHeap.poll());
        }
        ret.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 -o2;
            }
        });
        return ret;

    }






}
