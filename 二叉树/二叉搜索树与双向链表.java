
https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13&&tqId=11179&rp=1&ru=/activity/oj&qru=/ta/coding-interviews/question-ranking

public class Solution {
    public TreeNode pre = null;
    public void convertChild(TreeNode pCur){
        
        if(pCur == null) return;
        
        convertChild(pCur.left);
        pCur.left = pre;
        if(pre != null){
            pre.right = pCur;
        }
        pre = pCur;
        convertChild(pCur.right);
        return;
    }
    
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return null;
        
        convertChild(pRootOfTree);//中序遍历,并转换成链表
        TreeNode head = pRootOfTree;
        while(head.left != null){
            head = head.left;
        }
        return head;
    }
}