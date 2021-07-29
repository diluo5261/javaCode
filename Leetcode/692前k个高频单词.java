public List<String> topKFrequent(String[] words, int k) {
        List<String> ret = new ArrayList<>();
        if(words == null) return ret;

        //1.统计每个单词出现的次数,使用HashMap
        HashMap<String,Integer> map = new HashMap<>();
        for(String str : words){
            if(map.get(str) == null){
                map.put(str,1);
            }else{
                int count = map.get(str);
                map.put(str,count+1);
            }
        }

        //2、求出现次数最多的前k个单词，建立一个大小为k的小根堆
        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() == o2.getValue()){
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue() - o2.getValue();
            }
        });

        //3、遍历map里面的元素，先将前k个建成小根堆，然后从第k+1个开始和堆顶元素进行比较
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(minHeap.size() < k){
                minHeap.offer(entry);
            }else{
                Map.Entry<String,Integer> top = minHeap.peek();
                //1、频率相同，判断key字母小的入堆
                if(top.getValue() == entry.getValue()){
                    if(top.getKey().compareTo(entry.getKey()) > 0){
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }else{
                    //2、频率不同，频率大的入堆
                    if(top.getValue() < entry.getValue()){
                        minHeap.poll();
                        minHeap.offer(entry);
                    }
                }
            }
        }

        //4、将堆中的数据，存储到ret当中
        for(int i=0; i<k; i++){
            Map.Entry<String,Integer> top = minHeap.poll();
            if(top != null){
                ret.add(top.getKey());
            }
        }

        Collections.reverse(ret);
        return ret;
    }