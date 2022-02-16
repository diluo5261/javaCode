public class Test {
    public static void main(String[] args) {
        BinaryTree b = new BinaryTree();
        TreeNode root = b.createTree();

        System.out.print("递归前序遍历:");
        b.preOrderTraversal(root);

        System.out.print("\n递归中序遍历:");
        b.inOrderTraversal(root);

       System.out.print("\n递归后序遍历:");
        b.postOrderTraversal(root);

         System.out.print("\n遍历:节点的个数:");
        b.getSize1(root);
        System.out.print(BinaryTree.size);

        System.out.print("\n子问题思路:节点个数:");
        System.out.println(b.getSize2(root));

        System.out.print("遍历:求叶子节点的个数:");
        b.getLeafSize1(root);
        System.out.println(BinaryTree.leafSize);
//
        System.out.print("子问题思路:求叶子节点的个数:");
        System.out.println(b.getLeafSize2(root));
//
        System.out.print("子问题思路:求K层节点个数:");
        System.out.println(b.getKLevelSize(root, 1));

        System.out.print("子问题思路:获取二叉树的高度:");
        System.out.println(b.getHeight(root));

        System.out.print("查找val所在节点:");
        System.out.println(b.find(root, 'A').val);

        System.out.println("层序遍历:");
        b.levelOrderTraversal(root);


    }
}
