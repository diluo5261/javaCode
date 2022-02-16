package set_map;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class Leetcode {
    public static void main(String[] args) {
        int [] nums = {1,3,5,7,9,10,2,4,6,8,10,2,4,8,6,7,5,3,1};
        int ret = singleNumber(nums);
        System.out.println(ret);
    }

    @Test
    //只出现过一次的数据
    //给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    public static int singleNumber(int[] nums){
        if(nums == null) return -1;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(nums[i])){
                set.remove(nums[i]);
            }else{
                set.add(nums[i]);
            }
        }

        for (int i = 0; i <nums.length ; i++) {
            if(set.contains(nums[i])){
                return nums[i];
            }
        }
        return -1;
    }


}
