
/*
给出一组区间，请合并所有重叠的区间。
请保证合并后的区间按区间起点升序排列。

数据范围：区间组数 0 \le n \le 10000≤n≤1000，区间内 的值都满足 0 \le val \le 100000≤val≤10000
要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)
进阶：空间复杂度 O(val)O(val)，时间复杂度O(val)O(val)
*/

import java.util.*;
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        Collections.sort(intervals, (v1, v2)->v1.start - v2.start);
        ArrayList<Interval> res = new ArrayList<Interval>();
        int idx = -1;
        for(Interval interval : intervals){
            if(idx == -1 || interval.start > res.get(idx).end){ //若数组为空，或当前区间的起始位置小于结果list中最后区间的终止位置
                //不合并，直接将当前区间加入结果list
                res.add(interval);
                idx ++;
            }else{    //合并，选择较大的数作为最后区间的终止位置
                res.get(idx).end = Math.max(interval.end, res.get(idx).end);
            }
        }
        return res;
    }
}