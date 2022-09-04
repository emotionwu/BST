//基于二分搜索树的映射
public class BSTMap<K extends Comparable<K>, V> implements Map<K,V>  {

    private class Node{
        public  K key;
        public  V value;
        public Node left;
        public Node right;
        public Node(K key,V value){
            this.key =key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size;

    public BSTMap(){
         root = null;
         size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void add(K key,V value){
        root =add(root,key,value);
    }

    //向以node为根的二分搜索树中插入元素(key,value)，递归算法
    //返回插入新节点后二分搜索树的根
    private Node add(Node node, K key,V value){
        /*   过于冗长，不是深度递归
        if(e.equals(node.e))
            return;
        else if(e.compareTo(node.e)<0 && node.left==null) {
            node.left = new Node(e);
            size++;
            return;
        }
        else if(e.compareTo(node.e)>0 && node.right == null){
            node.right =new Node(e);
            size ++;
            return;
        }
         */
        if(node == null){
            size ++;
            return new Node(key,value) ;
        }

        if(key.compareTo(node.key)<0)
            node.left =  add(node.left ,key,value);
        else if(key.compareTo(node.key)>0)
            node.right=  add(node.right,key,value);
        else // key.compare to(node.key)==0
        node.value =value;
        return node;
    }

    //返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node,K key){

        if(node == null)
            return null;

        if(key.compareTo(node.key)==0)
            return node;
        else if(key.compareTo(node.key)<0)
            return getNode(node.left,key);
        else
            return getNode(node.right,key);
    }

    @Override
    public boolean contains(K key){
        return getNode(root,key)!=null;
    }

    @Override
    public V get(K key){
        Node node= getNode(root,key);
        return node==null ? null :node.value;
    }

    @Override
    public void set(K key,V newValue){
        Node node= getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + "does not exist!");

        node.value=newValue;
    }

    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }


    //删除掉以node为根的二分搜索树中的最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        if(node.left == null){
            //暂存node的右孩子
            Node rightNode = node.right;
            node.right =null;
            //删除结点
            size--;
            return rightNode;
        }
        //node.left不为空，则递归到底，直到node.left为空，找到最小值
        node.left= removeMin(node.left);
        return node;
    }

    @Override
    //删除映射中任意一个值
    public V remove(K key){
        Node node = getNode(root ,key);
        if(node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //删除掉以node为根的二分搜索树中键为key的结点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key){
        if( node == null)
            return null;
        //首先找到值为e的node
        if(key.compareTo(node.key)<0){
            node.left= remove(node.left,key);
            return node;
        }
        else if(key.compareTo(node.key)>0){
            node.right= remove(node.right,key);
            return node;
        }
        else {
            //key == node.key
            //前面两种为只有左/右孩子的结点
            //类似于删除最大/小值，直接删除
            if(node.left ==null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode =node.left;
                node.left =null;
                size--;
                return leftNode;
            }

            //待删除节点左右子树均不为空的情况
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点顶替删除节点的位置

            //node的后继
            Node successor =minimum(node.right);
            //右子树中的最小值赋值给successor.right
            successor.right = removeMin(node.right);
            successor.left = node.left;
            //删除node
            node.left = node.right =null;
            return successor;
        }
    }

}
