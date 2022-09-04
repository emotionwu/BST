public class LinkedList<E> {
    //链表结点
    private class Node{
        public E e;
        public Node next;
        //用户传来的结点,可能有多种形式
        public Node (E e, Node next){
            this.e=e;
            this.next= next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }
    private Node dummyHead;
    private int size;
    //构造函数
    public LinkedList(){
        dummyHead=new Node(null,null);
        size=0;
    }
    //获取链表中的元素个数
    public int getSize(){
        return size;
    }

    //返回链表是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //在链表的index(0 - based)位置添加新的元素e
    //在链表中不是一个常用的操作，练习使用
    //递归添加元素
    private Node add(Node node, int index ,E e){
        if(index == 0)
            return new Node(e,node);
        node.next=add(node.next,index-1,e);
        return node;
    }

    public void add(int index, E e){
        if(index<0 || index>size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
       /* if(index == 0)
            addFirst(e);
        else{

        */
        Node prev= dummyHead;
        for( int i =0; i<index ;i++)
            prev=prev.next;
             /*   Node node= new Node(e);
                node.next= prev.next;
                prev.next= node;
              */
        prev.next= new Node(e,prev.next);
        size ++;

    }

    //在链表头添加新元素e
    public void addFirst( E e){
      /*  Node node = new Node(e);
        node.next= head;
        head=node;
       */
        add(0,e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add (size,e);
    }

    //遍历
    public E get(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Get failed.Illegal index.");
        Node cur =dummyHead.next;
        for(int i =0;i<index;i++)
            cur=cur.next;
        return cur.e;
    }

    //获取链表第一个元素
    public E getFirst(){
        return get(0);
    }
    //修改链表的第index（0-based）个位置的元素为e

    //不常用
    public void set(int index ,E e){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Set failed.Illegal index.");
        Node cur =dummyHead.next;
        for(int i = 0;i<index ;i++)
            cur=cur.next;
        cur.e=e;
    }

    //查找链表中是否有元素e
    public boolean contains(E e){
        Node cur =dummyHead.next;
        while(cur!=null){
            if(cur.e.equals(e))
                return true;
            cur=cur.next;
        }
        return false;
    }

    //从链表中删除index位置的元素，返回删除的元素
    public E remove(int index){
        if(index<0 || index>=size)
            throw new IllegalArgumentException("Remove failed.Illegal index.");
        Node prev = dummyHead;
        for(int i = 0; i< index ; i++)
            prev =prev.next;
        Node retNode = prev.next;
        prev.next =retNode.next;
        retNode.next =null;
        size--;
        return retNode.e;
    }

    //从链表中删除元素e
    public void removeElement(E e){
        Node prev =dummyHead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev =prev.next;
        }

        if(prev.next != null){
            Node delNode =prev.next;
            prev.next = delNode.next;
            delNode.next =null;
        }
    }

    //删除第一个元素
    public E removeFirst(){
        return remove(0);
    }

    //删除最后一个元素
    public E removeLast(){
        return remove(size-1);
    }
    @Override
    public String toString(){
        StringBuilder res= new StringBuilder();
        Node cur=dummyHead.next;
        while(cur !=null){
            res.append(cur + "->");
            cur=cur.next;
        }
        //     for(Node cur =dummyHead.next;cur!=null;cur=cur.next)
        //        res.append(cur + "->");
        res.append("NULL");
        return res.toString();
    }
}
