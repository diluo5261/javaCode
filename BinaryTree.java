
package day0608;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    public char val;
    public TreeNode left;
    public TreeNode right;

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

    // 前序遍历
    void preOrderTraversal(TreeNode root){
        if(root == null) {
            return;
        }
        System.out.print(root.val+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }


    // 中序遍历
    void inOrderTraversal(TreeNode root){
        if(root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.val+" ");
        inOrderTraversal(root.right);
    }

    // 后序遍历
    void postOrderTraversal(TreeNode root){
        if(root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val+" ");
    }

    // 遍历思路-求结点个数  前序遍历
    static int size = 0;
    void getSize1(TreeNode root) {
        if(root == null) {
            return;
        }
        size++;
        getSize1(root.left);
        getSize1(root.right);
    }

    // 子问题思路-求结点个数
    int getSize2(TreeNode root) {
        if(root == null){
            return 0;
        }
        return getSize2(root.left) + getSize2(root.right)+1;
    }

    //遍历思路-求叶子节点个数
    static int leafSize = 0;
    void getLeafSize1(TreeNode root){

        if (root == null){
            return;
        }
        if (root.left == null && root.right == null){
            leafSize++;
        }

        getSize1(root.left);
        getSize1(root.right);

    }

    //遍历思路-求叶子节点个数
    int getLeafSize2(TreeNode root){
        if (root == null){
            return 0;
        }
        if (root.left == null && root.right == null){
            return 1;
        }

       return  getSize2(root.left) + getSize2(root.right);
    }

    //求第k层节点个数
    int getKLevel(TreeNode root , int k){
        if (root == null){
            return 0;
        }

        if (k == 1){
            return 1;
        }

        return getKLevel(root.left,k-1) + getKLevel(root.right,k-1);

    }

    // 前序遍历
    TreeNode find(TreeNode root,char val){
        if(root == null) {
            return null;
        }

        if (root.val == val){
            return root;
        }

        TreeNode ret = find(root.left,val);
        if (ret != null){
            return ret;
        }


        ret = find(root.right,val);
        if (ret != null){
            return ret;
        }

        return  null;
    }

    //获取二叉树得高度
    int getHeight(TreeNode root){
        if (root == null) return 0;

        return (getHeight(root.left)+1 )  >  (getHeight(root.right) + 1) ? (getHeight(root.left)+1 )  :  (getHeight(root.right) + 1) ;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null || q == null) return false;
        if (p == q) return true;

        if (p.val == q.val){
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
        }else{
            return false;
        }

    }




}








