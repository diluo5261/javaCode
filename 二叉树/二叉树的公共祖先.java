
https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;

        TreeNode leftTree = lowestCommonAncestor(root.left,p,q);
        TreeNode rightTree = lowestCommonAncestor(root.right,p,q);

        if(leftTree != null && rightTree != null) return root;
        if(leftTree != null)  return leftTree;
        if(rightTree != null) return rightTree;

        return null;
        
    }
}