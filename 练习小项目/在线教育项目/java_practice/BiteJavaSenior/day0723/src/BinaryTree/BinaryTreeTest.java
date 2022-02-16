package BinaryTree;



public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.createTree();

        //递归前序遍历
        System.out.print("递归前序遍历:");
        binaryTree.preOrder1(root);

        //迭代前序遍历
        System.out.print("\n迭代前序遍历:");
        binaryTree.preOrder2(root);

        //递归中序遍历

        System.out.print("\n\n递归中序遍历:");
        binaryTree.inOrder1(root);

        //迭代中序遍历
        System.out.print("\n迭代_中序遍历");
        binaryTree.inOrder2(root);

        //递归后续遍历
        System.out.print("\n\n递归后续遍历:");
        binaryTree.postOrder1(root);

        //迭代后续遍历
        System.out.print("\n迭代后续遍历:");
        binaryTree.postOrder2(root);

        //获取K层节点的个数
        System.out.print("\n第k层节点的个数_递归:");
        System.out.println(binaryTree.getKleveSize1(root, 4));
        System.out.print("第k层节点的个数_层序遍历的方式:");
        int ret = binaryTree.getKLevelSize2(root,4);
        System.out.println(ret);

        System.out.println(binaryTree.isCompleteTree(root));


    }




}
