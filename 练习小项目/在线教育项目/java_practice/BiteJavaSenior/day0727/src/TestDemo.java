/*
public class TestDemo {

    public static void main(String[] args) {
        BinarySearchTree b1 = new BinarySearchTree();
        BinarySearchTree.Node root = new BinarySearchTree.Node(5);

        b1.insert(root,3);
        b1.insert(root,1);
        b1.insert(root,4);
        b1.insert(root,0);
        b1.insert(root,2);
        b1.insert(root,7);
        b1.insert(root,8);
        b1.insert(root,9);
        b1.insert(root,6);

        System.out.println(b1.search(root, 6).val);
        b1.removeKey(root,3);

    }
}

class BinarySearchTree{
    public static class Node{
        public int val;
        public Node left;
        public Node right;

        public Node(int val){
            this.val = val;
        }

    }

//***********************查找*********************
    public Node search(Node root,int key){
        Node cur = root;

        while(cur != null){
            if(cur.val == key){
                return cur;
            }else if(cur.val < key){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        return null;
    }
//**************************************************


//        ***********************插入*********************
//    每次插入,一定是插入到叶子节点的位置
    public boolean insert(Node root,int key){
        Node node = new Node(key);
        if(root == node) return true;

        Node parent = null;
        Node cur = root;

        while(cur != null){
            if(cur.val == key){
                return false;
            }else if(cur.val < key){
                parent = cur;
                cur = cur.right;
            }else {
                parent = cur;
                cur = cur.left;
            }
        }

        if(parent.val > key){
            parent.left = node;
        }else{
            parent.right = node;
        }

        return true;
    }
//**************************************************


        //***********************删除*********************
    public void removeKey(Node root,int key){
        if(root == null) return;

        Node parent = null;
        Node cur = root;

        while(cur != null){
            if(cur.val == key){
                remove(root,parent,cur);
                return;

            }else if(cur.val < key){
                parent = cur;
                cur = cur.right;
            }else{
                parent = cur;
                cur = cur.left;
            }
        }

    }

    public void remove(Node root,Node parent,Node cur){
        //1、待删除节点为cur，待删除节点的双亲节点为parent
        if(cur.left == null){
            //-->1.1 cur.left == null
            if(cur == root){
                root = cur.right;
            }else if(parent.left == cur){//1.2、cur不是root，cur是parent.left
                parent.left = cur.left;
            }else{	//1.3、cur不是root，cur是parent.right
                parent.right = cur.right;
            }

        }else if(cur.right == null){
            //1.cur是root，则root = cur.left
            if(cur == root){
                root = cur.left;
                //2.cur不是root，cur是parent.left,则parent.left = cur.left
            }else if(parent.left == cur){
                parent.left = cur.left;
            }else{
                parent.right = cur.left;
            }
        }else{

            Node rev = cur;
            parent = cur;
            cur = cur.right;

            while(cur.left != null){
                parent = cur;
                cur =cur.left;
            }

            rev.val = cur.val;
            parent.left = null;

        }
    }
}

3、*/
