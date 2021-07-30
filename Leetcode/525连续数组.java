public int findMaxLength(int[] nums) {
        if(nums == null) return -1;

        int sum =0;
        int len =0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);

        for(int i =0; i<nums.length;i++){
            if(nums[i] == 0){
                sum--;
            }else{
                sum++;
            }

            if(map.containsKey(sum)){
                len = Math.max(len,i - map.get(sum));
            }else{
                map.put(sum,i);
            }
        }
        return len;
    }