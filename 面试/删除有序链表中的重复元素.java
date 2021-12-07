import java.util.*;
 
/*
 * public class ListNode {
 *   int val;
 *   ListNode next = null;
 * }
 */
 
public class Solution {
    /**
     *
     * @param head ListNode类
     * @return ListNode类
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        if(head.next != null && head.val == head.next.val){//发现有重复值
                while(head.next != null && head.val == head.next.val){
                    head = head.next;//删除
            }
            return deleteDuplicates(head.next);//把与删除的那个结点相同的结点也进行删除
        }
        head.next = deleteDuplicates(head.next);//当没有发现重复值的情况，就一直进行递归操作
        return head;
    }
}