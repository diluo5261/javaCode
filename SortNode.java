
//给定一个不带头结点的单链表，对链表进行插入排序(从小到大排序)

class ListNode{
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}

public class SortNode {
    public static void main(String[] args) {
        ListNode list1 = new ListNode(9);
        ListNode list2 = new ListNode(5);
        ListNode list3 = new ListNode(18);
        ListNode list4 = new ListNode(2);
        ListNode list5 = new ListNode(7);

        list1.next = list2;
        list2.next = list3;
        list3.next = list4;
        list4.next = list5;
        list5.next = null;

        ListNode tmp = sortNode(list1);
        while(tmp != null){
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }

    public static ListNode sortNode(ListNode node){
        ListNode head = null;
        ListNode last = null;

        ListNode cur = node;
        ListNode curNext = node.next;

        //头插

        while(cur != null){
            if (head == null){
                //第一次插入
                head = cur;
                last = cur;
                last.next = null;

            }else{
                //比头还小
                if (head.value > cur.value){
                    cur.next = head;
                    head = cur;
                }else if (last.value <= cur.value){
                    //大于等于尾巴
                    last.next = cur;
                    last =cur;
                    last.next = null;
                }else if (head.value < cur.value){
                    //在头尾中间
                    ListNode tmp = head;
                    while(tmp.next.value <= cur.value){
                        tmp = tmp.next;
                    }
                    cur.next = tmp.next;
                    tmp.next = cur;
                }
            }

            cur = curNext;
            if (curNext != null)
                curNext = curNext.next;
        }

        return head;
    }
}
