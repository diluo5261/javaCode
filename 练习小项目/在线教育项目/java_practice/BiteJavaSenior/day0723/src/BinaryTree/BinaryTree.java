package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode{
    char val;
    TreeNode left;
    TreeNode right;

    public TreeNode(char val) {
        this.val = val;
    }
}

public class BinaryTree {
    public TreeNode createTree() {
        TreeNode A = new TreeNode('A');
        TreeNode B = new TreeNode('B');
        TreeNode C = new TreeNode('C');
        TreeNode D = new TreeNode('D');
        TreeNode E = new TreeNode('E');
        TreeNode F = new TreeNode('F');
        TreeNode G = new TreeNode('G');
        TreeNode H = new TreeNode('H');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        C.left = F;
        C.right = G;
        E.right = H;
        return A;
    }

    //递归前序遍历
    public void preOrder1(TreeNode root){
        if(root == null) return;

        System.out.print(root.val + " ");
        preOrder1(root.left);
        preOrder1(root.right);
    }

    //迭代前序遍历
    //建一栈,判断栈是否为空,不为空,出栈,判断当前节点的左孩子和有孩子是否为空,
    // 不为空先push右孩子,再push左孩子,
    // 如为空,则不进行操作
    public void preOrder2(TreeNode root){
        if (root == null) return;

        TreeNode cur = root;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);
        while(!stack.empty()){
            TreeNode top = stack.pop();
            System.out.print(top.val+ " ");

            if(top.right != null) stack.push(top.right);

            if(top.left != null) stack.push(top.left);
        }
    }

    //递归方法_二叉树的中序遍历
    public void inOrder1(TreeNode root){
        if(root == null) return ;

        inOrder1(root.left);
        System.out.print(root.val + " ");
        inOrder1(root.right);
    }

    //迭代_中序遍历
    public void inOrder2(TreeNode root){
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while(cur != null || !stack.empty()){

            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode top = stack.pop();
            System.out.print(top.val+ " ");
            cur = top.right;
        }
    }

    //递归_后续遍历
    public void postOrder1(TreeNode root){
        if(root == null) return ;

        postOrder1(root.left);
        postOrder1(root.right);
        System.out.print(root.val + " ");
    }

    //迭代后续遍历
    public void postOrder2(TreeNode root){
        if(root == null) return;
        TreeNode cur = root;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(cur);

        while(!stack1.empty()){
            cur = stack1.pop();
            stack2.push(cur);

            if(cur.left != null) stack1.push(cur.left);
            if(cur.right != null) stack1.push(cur.right);

        }
        while(!stack2.empty()){
            System.out.print(stack2.pop().val+ " ");
        }
    }

    //求节点的个数
    static int size=0;
    public void getSize1(TreeNode root){
        if(root == null) return ;

        size++;
        getSize1(root.left);
        getSize1(root.right);
    }

    //子问题思路
    public int getSize2(TreeNode root){
        if(root == null) return 0;
        int leftTree = getSize2(root.left);
        int rightTree = getSize2(root.right);

        return Math.max(leftTree,rightTree) +1;
    }

    //求叶子节点的个数
    static int leafSize =0;
    public void getLeafSize1(TreeNode root){
        if(root == null) return;
        if(root.left == null && root.right == null){
            leafSize++;
        }

        getLeafSize1(root.left);
        getLeafSize1(root.right);
    }

    //子问题思路
    public int getLeafSize2(TreeNode root){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        return getLeafSize2(root.left) + getLeafSize2(root.right);
    }

    //求第k层节点个数
    public int getKleveSize1(TreeNode root,int k){
        if(root == null) return 0;
        if(k == 1) return 1;

        return getKleveSize1(root.left,k-1) + getKleveSize1(root.right,k-1);
    }

    //使用迭代的方式
    public int getKLevelSize2(TreeNode root,int k){
        if(root == null) return 0;
        TreeNode cur = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(cur);

        int ret= 0;
        while(!queue.isEmpty() && k-- > 0 ){
            int size = queue.size();
            ret = size;

                while (size-- > 0){
                    TreeNode top = queue.poll();
                    if(top.left != null) {
                        queue.offer(top.left);
                    }
                    if(top.right!= null) {
                        queue.offer(top.right);
                    }
                }
        }
        return ret;
    }

    //判断一个树死否是完全二叉树
    public boolean isCompleteTree(TreeNode root){
        if(root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode top = queue.poll();
            if(top != null){
                queue.offer(top.left);
                queue.offer(top.right);
            }else{
                break;
            }
        }

        while(!queue.isEmpty()){
            TreeNode ret = queue.poll();
            if (ret != null) return false;
        }
        return true;
    }

}
