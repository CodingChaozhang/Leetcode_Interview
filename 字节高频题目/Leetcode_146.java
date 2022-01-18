class LRUCache {
    // hashMap+双向链表
    // 变量
    class DLinkedList{
        int key;
        int value;
        DLinkedList prev;
        DLinkedList next;
        DLinkedList(){

        }
        DLinkedList(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    private HashMap<Integer,DLinkedList> hashMap;
    // 双向链表维护两个哑结点
    private DLinkedList head;
    private DLinkedList tail;
    // 最大容量
    private int capacity;
    private int size;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedList();
        tail = new DLinkedList();
        // 连接
        head.next = tail;
        tail.prev = head;
        
        hashMap = new HashMap<>();
        this.size = 0;
    }
    // 获取值
    public int get(int key) {
        DLinkedList node = hashMap.get(key);
        // 如果为空
        if(node==null){
            return -1;
        }else{
           
            moveToHead(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        // 放置该点
        DLinkedList node = hashMap.get(key);
        // 该点不存在则放置 该点存在则更新
        if(node==null){
            // 新建
            node = new DLinkedList(key,value);
            // 先给hashMap添加
            hashMap.put(key,node);
            // 新建结点添加到头结点
            addToHead(node);

            // 需要判断是否超出容量
            size++;
            if(size>capacity){
                // 删除尾结点的前一个
                DLinkedList res =removeTail();
                // hashMap 也要删除
                hashMap.remove(res.key);
                size--;
            }
            
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    // 删除该结点
    public void removeNode(DLinkedList node){
        DLinkedList prev = node.prev;
        DLinkedList next = node.next;

        prev.next = next;
        next.prev = prev;
    }
    // 添加到头结点
    public void addToHead(DLinkedList node){
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }
    // 移动结点
    public void moveToHead(DLinkedList node){
        removeNode(node);
        addToHead(node);
    }


    // 删除尾结点
    public DLinkedList removeTail(){
        DLinkedList res = tail.prev;
        removeNode(res);
        return res;
    }
    
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */