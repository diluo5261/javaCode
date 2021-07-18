/*
给定一个链表(不带头结点)，删除链表的倒数第K个节点，并且返回链表的头结点。时间复杂度O(n)
示例：1->2->3->4->5 删除倒数第2个节点后 变为：1->2->3->5

*/

class Node{
    int val;
    Node next;
}
public class RemoveNode {
   public Node removeKthNode(Node head, int k) {
       Node cur = head;
       while(k-1 != 0){
           if(cur.next == null){
               return null;
           }

           cur = cur.next;
           k--;
       }

       Node del = head;
       Node pre = null;

       while(cur.next != null){
           pre = del;
           cur =cur.next;
           del = del.next;
       }

       pre.next = cur.next;

       return head;
    }
}
