public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return null;

        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<nums1.length;i++){
            map.put(nums1[i],map.getOrDefault(nums1[i],0) + 1);
        }

        for(int i =0;i<nums2.length;i++){
            if(map.containsKey(nums2[i])){
                list.add(nums2[i]);
                int val = map.get(nums2[i]);
                if(val > 1){
                    map.put(nums2[i],val-1);
                }else{
                    map.remove(nums2[i]);
                }
            }
        }

        Object[] obj = list.toArray();
        int len = list.size();
        int[] ret = new int[len];
        for(int i =0; i< len;i++){
            ret[i] = (int)obj[i];
        }
        
        return ret;
    }
