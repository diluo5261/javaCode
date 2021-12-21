public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {  
        PriorityQueue<List<Integer>> maxHeap = new PriorityQueue<>(k, new Comparator<List<Integer>() {  
            @Override  
 			public int compare(List<Integer> o1, List<Integer> o2) {  
                return (o2.get(0) + o2.get(1)) - (o1.get(0) + o1.get(1)) ;  
			}  
        });  
  
	for (int i = 0; i < nums1.length ; i++) { 
		for (int j = 0; j <nums2.length ; j++) { 
			List<Integer> list = new ArrayList<>();
			list.add(nums1[i]);
			list.add(nums2[j]);
			
			if(maxHeap.size() < k){
				maxHeap.offer(list);
			}else{
				int top = maxHeap.peek().get(0) + maxHeap.peek().get(1);
				
				if(top > nums1[i] + nums2[j]){
				
					maxHeap.poll();
					maxHeap.offer(list);
				}  
             }  
		}  
	}  
    //堆当中放的就是结果
	
    List<List<Integer>> ret = new ArrayList<>();
	for (int i =0; i< k && maxHeap.peek() != null;i++){  
            ret.add(maxHeap.poll());  
	}  
        return ret;  
}  