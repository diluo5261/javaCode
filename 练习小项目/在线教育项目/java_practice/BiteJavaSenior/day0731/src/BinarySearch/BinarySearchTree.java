package BinarySearch;

public class BinarySearchTree {
    public static void main(String... args) {

    }

    static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    //1、二叉搜索树的查找
    public Node search(Node root,int val){
        Node cur = root;
        while(cur != null){
            if(cur.val == val){
                return cur;
            }else if(cur.val > val){
                cur =cur.left;
            }else{
                cur = cur.right;
            }
        }
        return  null;
    }

    //2、二叉搜索树的插入
    public boolean insert(Node root,int val){
        Node node = new Node(val);
        if(root == null){
            root = node;
            return true;
        }

        Node parent = null;
        Node child = root;
        while(child != null){
            if(child.val == val){
                return false;
            }else if(child.val > val){
                parent = child;
                child = child.left;
            }else{
                parent = child;
                child = child.right;
            }
        }

        if(parent.val > val){
            parent.left = node;
        }else{
            parent.right = node;
        }

        return true;
    }

    //2、二叉搜索树的删除
    public boolean removeKey(Node root,int val){
        if(root == null) return false;

        Node parent = null;
        Node cur = root;
        while(cur != null){
            if(cur.val == val){
                remove(root,parent,cur);
                return true;
            }else if(cur.val > val){
                parent = cur;
                cur = cur.left;
            }else{
                parent = cur;
                cur = cur.right;
            }
        }
        return false;
    }

    private void remove(Node root,Node parent,Node cur){
        //1、待删除节点是cur，待删除节点的双亲是parent
        if(cur.left == null){
            if(cur == root){
                root = cur.right;
            }else{

            }

        }else if(cur.right == null){

        }

    }
}
