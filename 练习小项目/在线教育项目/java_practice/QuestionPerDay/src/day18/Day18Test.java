package day18;
/*
一、选择题
容器中线程安全的如：vectory,hashtable,非线程安全的如：hashmap,arrylist等。
1、AE       BCD
2、B
3、D
4、D
5、C

二、
1.a
2.public
3.0000 00f7  ffff fff7


四、

 */
class Node{
    int val;
    Node next;
}
public class Day18Test {
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
