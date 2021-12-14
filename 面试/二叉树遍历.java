/*
二叉树前序遍历递归实现
*/

class Solution {

 List<Integer> ret = new ArrayList<>();

 public List<Integer> preorderTraversal(TreeNode root) {

 	if(root == null){
 		return ret;
 }

 	ret.add(root.val);
 	preorderTraversal(root.left);
 	preorderTraversal(root.right);
 	return ret;
 	}
}


//二叉树非递归实现

public static void preOrders(TreeNode root){
	if(root == null) return ;
	
	TreeNode cur =root;
	Stack<TreeNode> stack = new Stack<>();
	
	stack.push(cur);
	
	while(!stack.isEmpty()){
		cur = stack.pop();
		System.out.print(cur.val);
		
		if(cur.right != null){
			stack.push(right);
		}
		
		
		if(cur.left != null){
			stack.push(cur.left);
		}
	}
}