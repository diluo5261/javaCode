package day19;
/*
一、选择题
1、D
2、D
3、C
4、C
5、C

二、填空题
1、javac  1  .class
2、useFuls[0],useFuls[0],useFuls[1],useFuls[1]
3.65535
四、智商题
先观察前5层楼的钻石大小，记住其中最大的一个，在后5层中找一个尽可能与前五层之中最大的接近的一个

 */
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
public class Day19Test {
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
