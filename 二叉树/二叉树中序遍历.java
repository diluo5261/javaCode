

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