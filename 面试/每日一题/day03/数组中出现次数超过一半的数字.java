//方法一:数组排序,取中间值
import java.util.*;
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {

        
        Arrays.sort(array);
        int mid = array.length >> 1;
        
        return array[mid];
    }
}






//方法二:消除法

import java.util.*;
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {

        int result = array[0];
        int times= 1;
        
        int i = 1;
        while(i < array.length){
            
            if(times != 0){
                
                if(result != array[i]){
                    times--;
                }else{
                    times++;
                }
                
            }else{
                
                result= array[i];
                times = 1;
            }
            
            i++;
        }
        return result;
    }
}