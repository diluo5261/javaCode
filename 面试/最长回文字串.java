/*
对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。


数据范围： 1 \le n \le 10001≤n≤1000
要求：空间复杂度 O(1)O(1)，时间复杂度 O(n^2)O(n 
2
 )
进阶:  空间复杂度 O(n)O(n)，时间复杂度 O(n)O(n)

*/


import java.util.*;


public class Solution {
    /**
     * 反转字符串
     * @param str string字符串 
     * @return string字符串
     */import java.util.*;

public class Solution {
    public int getLongestPalindrome(String A, int n) {
        //manacher算法
        
        char[] chArr = manacherString(A);
        
        int[] pArr = new int[chArr.length];//最长回文半径数组
        int R = -1;//最右回文右边界
        int C = -1;//中心位置，伴随着R一起变化
        
        int max = -1;
        for(int i = 0; i < chArr.length; i++){
            //i位置至少不用验的回文区域
            pArr[i] = R > i ? Math.min(pArr[2*C-i], R-i) : 1;
            
            while(i+pArr[i] < chArr.length && i-pArr[i] > -1){
                //至少不用验的区域跳过之后，接下来的区域能否扩得更大
                if(chArr[i+pArr[i]] == chArr[i-pArr[i]])
                    pArr[i]++;
                else
                    break;
            }
            //更新R和C
            if(i+pArr[i] > R){
                R = i+pArr[i];
                C = i;
            }
            
            max = Math.max(max, pArr[i]);
        }
        
        return max-1;//最大半径减1就是原串最大回文长度
    }
    
    public char[] manacherString(String s){
        //char[] chs = s.toCharArray();
        char[] res = new char[s.length()*2+1];
        int index = 0;
        for(int i = 0; i < res.length; i++){
            res[i] = (i % 2 == 0) ? '#' : s.charAt(index++);
        }
        
        return res;
    }
}
    public String solve (String str) {
        // write code here
         // write code here
        
       StringBuffer sb=new StringBuffer(str);
       sb.reverse();
       return sb.toString();
     
    }
}
