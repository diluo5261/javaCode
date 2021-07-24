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