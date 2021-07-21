https://leetcode-cn.com/problems/binary-tree-level-order-traversal/submissions/

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if(root == null) return ret;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            while(size >0){
                TreeNode top = queue.poll();
                list.add(top.val);
                if(top.left != null){
                    queue.offer(top.left);
                }
                if(top.right != null){
                    queue.offer(top.right);
                }
                size--;
            }
            ret.add(list);
        }
        return ret;
    }
}