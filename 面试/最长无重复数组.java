/*
给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组

数据范围：0\le arr.length \le 10^60≤arr.length≤10 
6
 ，0 < arr[i] \le 10^50<arr[i]≤10 
5
 
要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)

*/


    public int maxLength(int[] arr) {
        //用链表实现队列，队列是先进先出的
        Queue<Integer> queue = new LinkedList<>();
        int res = 0;
        for (int c : arr) {
            while (queue.contains(c)) {
                //如果有重复的，队头出队
                queue.poll();
            }
            //添加到队尾
            queue.add(c);
            res = Math.max(res, queue.size());
        }
        return res;
    }