import java.util.*;

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
        if (root == null) return ;

        System.out.print(root.val+ " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }
    // 中序遍历
    void inOrderTraversal(TreeNode root){
            if (root == null) return;

        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }
    // 后序遍历
    void postOrderTraversal(TreeNode root){
            if(root == null) return;
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
        System.out.print(root.val + " ");

    }

    //层序遍历
    void levelOrderTraversal(TreeNode root){
            if (root == null) return;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()){
                TreeNode top = queue.poll();
                System.out.print(top.val + " ");

                if (top.left != null){
                    queue.offer(top.left);
                }

                if (top.right != null){
                    queue.offer(top.right);
                }

            }
        System.out.println();
    }

    // 遍历思路-求结点个数
    static int size = 0;
    void getSize1(TreeNode root){
        if (root == null) return;

        size++;
        getSize1(root.left);
        getSize1(root.right);
    }
    // 子问题思路-求结点个数
    int getSize2(TreeNode root){
        if(root == null) return 0;

        return getSize2(root.left) + getSize2(root.right) + 1;


    }
    // 遍历思路-求叶子结点个数
    static int leafSize = 0;
    void getLeafSize1(TreeNode root){
        if (root == null) return;

        if(root.left == null && root.right == null){
            leafSize++;
        }

        getLeafSize1(root.left);
        getLeafSize1(root.right);
    }
    // 子问题思路-求叶子结点个数
    int getLeafSize2(TreeNode root){
        if (root == null) return 0;
        if (root.left == null && root.right == null){
            return 1;
        }
        return getLeafSize2(root.left) + getLeafSize2(root.right);

    }
    // 子问题思路-求第 k 层结点个数
    int getKLevelSize(TreeNode root,int k){
        if (root == null) return 0;

        if (k == 1){
            return 1;
        }

        return getKLevelSize(root.left,k-1) + getKLevelSize(root.right,k-1);

    }
    // 获取二叉树的高度
    int getHeight(TreeNode root){
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight,rightHeight) + 1;

    }
    // 查找 val 所在结点，没有找到返回 null
// 按照 根 -> 左子树 -> 右子树的顺序进行查找
// 一旦找到，立即返回，不需要继续在其他位置查找
    TreeNode find(TreeNode root, int val){
        if (root == null) return null;

        if (root.val == val){
            return root;
        }

        TreeNode ret = find(root.left,val);
        if (ret != null) return ret;

        ret = find(root.right,val);
        if (ret != null) return ret;

        return  null;
    }


}
