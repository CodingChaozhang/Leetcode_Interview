# 设计类

### 1.LRU缓存机制

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。


进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？

 

示例：

输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4

```java
class LRUCache {
    // 链表
    class DLinkedList{
        int key;
        int value;
        DLinkedList next;
        DLinkedList pre;
        public DLinkedList(){
        }
        public DLinkedList(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    // hashMap
    HashMap<Integer,DLinkedList> hashMap;
    // 维持两个哑结点
    DLinkedList head;
    DLinkedList tail;
    // 维持一个容量
    int size;
    int capacity;
    public LRUCache(int capacity) {
        // 初始化
        hashMap = new HashMap<>();
        head = new DLinkedList();
        tail = new DLinkedList();
        head.next = tail;
        tail.pre = head;
        this.size = 0;
        this.capacity = capacity;
    }
    // 获得值
    public int get(int key) {
        // 如果key在缓存中
        DLinkedList node = hashMap.get(key);
        if(node==null){
            // 不在
            return -1;
        }else{
            // 在的话 移动该结点
            moveNode(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        // 查看其是否存在
        DLinkedList node = hashMap.get(key);
        if(node!=null){
            // 存在更新
            node.value = value;
            // 移动
            moveNode(node);
        }else{
            // 不存在
            node = new DLinkedList(key,value);
            // 判断是否满了
            if(this.size==capacity){
                // 删除尾部结点 返回其key
                int k = removeTail();
                hashMap.remove(k);
                this.size--;
            }
             // 添加节点
             // hahsmap添加
            hashMap.put(key,node);
            addNode(node);
            this.size++;
        }

    }

    public int removeTail(){
        DLinkedList node = tail.pre;
        deleteNode(node);
        return node.key;
    }
    // 整合下面两个操作
    public void moveNode(DLinkedList node){
        deleteNode(node);
        addNode(node);
    }

    public void deleteNode(DLinkedList node){
        // 前一个的结点指向后面一个结点即可了
        DLinkedList pre = node.pre;
        DLinkedList next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    // 当前节点添加到头结点
    public void addNode(DLinkedList node){
        DLinkedList next = head.next;

        node.pre = head;
        node.next = next;

        next.pre = node;
        head.next = node;

    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

### 2.全O(1)的数据结构

请你实现一个数据结构支持以下操作：

Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。


挑战：

你能够以 O(1) 的时间复杂度实现所有操作吗？

```java
public class AllOne {
    /**
     * k-v查找节点
     */
    private final Map<String, ListNode> map = new HashMap<>();
    /**
     * key - 节点的值；
     * value - 链表中第一个值为key的节点。
     */
    private final Map<Integer, ListNode> first = new HashMap<>();
    /**
     * key - 节点的值；
     * value - 链表中最后一个值为key的节点。
     */
    private final Map<Integer, ListNode> last = new HashMap<>();

    /**
     * 链表伪头节点
     */
    private final ListNode head = new ListNode(null, 0);
    /**
     * 链表伪尾节点
     */
    private final ListNode tail = new ListNode(null, 0);

    AllOne() {
        head.next = tail;
        tail.prev = head;
    }

    private class ListNode { // 链表节点
        ListNode prev, next;
        String key;
        int val;

        public ListNode(String key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * 将节点 [insert] 插入到 n1 与 n2 之间
     */
    private void insert(ListNode n1, ListNode n2, ListNode insert) {
        n1.next = insert;
        n2.prev = insert;
        insert.prev = n1;
        insert.next = n2;
    }

    /**
     * 删除链表节点[n]
     */
    private void remove(ListNode n) {
        ListNode prev = n.prev;
        ListNode next = n.next;
        prev.next = next;
        next.prev = prev;
        n.prev = null;
        n.next = null;
    }

    /**
     * 将节点node移动到prev与next之间
     */
    private void move(ListNode node, ListNode prev, ListNode next) {
        remove(node);
        insert(prev, next, node);
    }

    /**
     * 将[node]设置为新的val值起始点
     */
    private void newFirst(int val, ListNode node) {
        first.put(val, node);
        if (!last.containsKey(val)) last.put(val, node);
    }

    /**
     * 将[node]设置为新的val值终止点
     */
    private void newLast(int val, ListNode node) {
        last.put(val, node);
        if (!first.containsKey(val)) first.put(val, node);
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     * <p>
     * 值加一后，当前节点会往左移动。
     * 如果当前key不存在，那就把这个节点插入到链表尾部.
     */
    public void inc(String key) {
        if (!map.containsKey(key)) { // 当前key不存在，插入到链表末尾
            ListNode node = new ListNode(key, 1);
            map.put(key, node);
            insert(tail.prev, tail, node); // 插入
            if (!first.containsKey(1)) newFirst(1, node); // 更新first
            newLast(1, node); // 更新last
        } else {
            ListNode node = map.get(key); // 当前节点
            int val = node.val; // 旧值
            int newVal = val + 1; // 新值
            ListNode firstNode = first.get(val); // 链表中第一个值为val的节点
            ListNode lastNode = last.get(val); // 链表中最后一个值为val的节点

            // 1. 找位置
            node.val = newVal;
            if (firstNode == lastNode) { // 当前节点是唯一一个值为val的节点
                first.remove(val); // 没有值为val的节点了
                last.remove(val); // 没有值为val的节点了
                newLast(newVal, node); // 更新last
            } else if (node == firstNode) { // 该节点是链表中第一个值为val的节点
                // 不动
                newLast(newVal, node);
                newFirst(val, node.next);
            } else {
                if (node == lastNode) newLast(val, node.prev); // 是最后一个值val的节点
                // 这个时候，节点应该移动到链表中第一个值为val的节点之前
                move(node, firstNode.prev, firstNode);
                newLast(newVal, node);
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     * 
     * 值减一之后，节点在链表中的位置会往右移动
     */
    public void dec(String key) {
        // 与inc类似，不过多了一个值为1删除的判断
        ListNode node = map.get(key);
        if (node == null) return;

        int val = node.val;
        int newVal = val - 1;
        ListNode firstNode = first.get(val);
        ListNode lastNode = last.get(val);

        if (val == 1) { // 值为1，删除这个节点
            if (firstNode == lastNode) { // 没有值为1的节点了
                first.remove(1);
                last.remove(1);
            } else if (node == firstNode) { // 起始值右移
                first.put(1, node.next);
            } else if (node == lastNode) { // 终结值左移
                last.put(1, node.prev);
            }
            remove(node);
            map.remove(key);
        } else {
            node.val = newVal;
            if (firstNode == lastNode) { // 唯一值为val的节点
                // 位置不变，成为newVal的首位
                first.remove(val);
                last.remove(val);
                newFirst(newVal, node);
            } else if (node == lastNode) { // 是最后一项val值的节点
                // 位置不变，成为newVal的首位，并且prev成为val的最后一位
                newFirst(newVal, node);
                newLast(val, node.prev);
            } else {
                if (node == firstNode) newFirst(val, node.next); // 是第一项val值的节点
                move(node, lastNode, lastNode.next); // 移动到lastNode之后
                newFirst(newVal, node);
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     * 返回链表头
     */
    public String getMaxKey() {
        return head.next == tail ? "" : head.next.key;
    }

    /**
     * Returns one of the keys with Minimal value.
     * 返回链表尾
     */
    public String getMinKey() {
        return tail.prev == head ? "" : tail.prev.key;
    }
}

```

### 3.单词频率

设计一个方法，找出任意指定单词在一本书中的出现频率。

你的实现应该支持如下操作：

WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
get(word)查询指定单词在书中出现的频率
示例：

WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
wordsFrequency.get("you"); //返回0，"you"没有出现过
wordsFrequency.get("have"); //返回2，"have"出现2次
wordsFrequency.get("an"); //返回1
wordsFrequency.get("apple"); //返回1
wordsFrequency.get("pen"); //返回1

```java
class WordsFrequency {
    // 用hashMap
    HashMap<String,Integer> dict = new HashMap<>();
    public WordsFrequency(String[] book) {
        // 对其遍历
        for(String b:book){
            dict.put(b,dict.getOrDefault(b,0)+1);
        }
    }
    
    public int get(String word) {
        return dict.getOrDefault(word,0);
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */
```

### 4.包含min函数的栈

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

 

示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.

```java
class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> stack_data;
    Stack<Integer> stack_min;
    public MinStack() {
        stack_data = new Stack<>();
        stack_min  = new Stack<>();
    }
    
    public void push(int x) {
        stack_data.push(x);
        if(stack_min.isEmpty()){
            stack_min.push(x);
        }else{
            //再次判断
            if(stack_min.peek()>x){
                stack_min.push(x);
            }else{
                int min = stack_min.peek();
                stack_min.push(min);
            }
        }
    }
    
    public void pop() {
        //排出去
        stack_min.pop();
        stack_data.pop();
    }
    
    public int top() {
        return stack_data.peek();
    }
    
    public int min() {
        return stack_min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
```

### 5.推文计数

请你实现一个能够支持以下两种方法的推文计数类 TweetCounts：

1. recordTweet(string tweetName, int time)

记录推文发布情况：用户 tweetName 在 time（以 秒 为单位）时刻发布了一条推文。
2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)

返回从开始时间 startTime（以 秒 为单位）到结束时间 endTime（以 秒 为单位）内，每 分 minute，时 hour 或者 日 day （取决于 freq）内指定用户 tweetName 发布的推文总数。
freq 的值始终为 分 minute，时 hour 或者 日 day 之一，表示获取指定用户 tweetName 发布推文次数的时间间隔。
第一个时间间隔始终从 startTime 开始，因此时间间隔为 [startTime, startTime + delta*1>,  [startTime + delta*1, startTime + delta*2>, [startTime + delta*2, startTime + delta*3>, ... , [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)>，其中 i 和 delta（取决于 freq）都是非负整数。


示例：

输入：
["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
[[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]

输出：
[null,null,null,null,[2],[2,1],null,[4]]

解释：
TweetCounts tweetCounts = new TweetCounts();
tweetCounts.recordTweet("tweet3", 0);
tweetCounts.recordTweet("tweet3", 60);
tweetCounts.recordTweet("tweet3", 10);                             // "tweet3" 发布推文的时间分别是 0, 10 和 60 。
tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // 返回 [2]。统计频率是每分钟（60 秒），因此只有一个有效时间间隔 [0,60> - > 2 条推文。
tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // 返回 [2,1]。统计频率是每分钟（60 秒），因此有两个有效时间间隔 1) [0,60> - > 2 条推文，和 2) [60,61> - > 1 条推文。 
tweetCounts.recordTweet("tweet3", 120);                            // "tweet3" 发布推文的时间分别是 0, 10, 60 和 120 。
tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // 返回 [4]。统计频率是每小时（3600 秒），因此只有一个有效时间间隔 [0,211> - > 4 条推文。

```java
class TweetCounts {

    private Map<String, TreeMap<Integer, Integer>> tweetMap;

    public TweetCounts() {
        tweetMap = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        TreeMap<Integer, Integer> treeMap = tweetMap.computeIfAbsent(tweetName, v -> new TreeMap<>());
        treeMap.put(time, treeMap.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res = new ArrayList<>();
        int interval;
        if ("minute".equals(freq)) {
            interval = 60;
        } else if ("hour".equals(freq)) {
            interval = 60 * 60;
        } else {
            interval = 60 * 60 * 24;
        }
        TreeMap<Integer, Integer> userTweets = tweetMap.get(tweetName);
        for (int time = startTime; time <= endTime; time += interval) {
            int end = Math.min(time + interval, endTime + 1);
            Map.Entry<Integer, Integer> entry = userTweets.ceilingEntry(time);
            int count = 0;
            while (entry != null && entry.getKey() < end) {
                count += entry.getValue();
                entry = userTweets.higherEntry(entry.getKey());
            }
            res.add(count);
        }
        return res;
    }
}
```



### 6.设计链表

设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。


示例：

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3

```java
// 链表结点
class ListNode{
    int val;
    ListNode next;
    public ListNode(){

    }
    public ListNode(int val){
        this.val = val;
        this.next = null;
    }
    public ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
}
class MyLinkedList {
    ListNode head;
    int size;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new ListNode(-1);
        size  = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index<0 || index>=size){
            return -1;
        }
        ListNode cur = head.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        addAtIndex(0,val);
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(size,val);
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index>size){
            return;
        }
        if(index<0){
            index = 0;
        }
        ListNode prev = head;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        // 创建新节点
        ListNode cur = new ListNode(val);
        cur.next = prev.next;
        prev.next = cur;
        size++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0 || index>=size){
            return;
        }
        ListNode prev = head;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        prev.next = prev.next.next;
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

### 7.设计前中后队列

> 数据结构用list来解题

请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。

请你完成 FrontMiddleBack 类：

FrontMiddleBack() 初始化队列。
void pushFront(int val) 将 val 添加到队列的 最前面 。
void pushMiddle(int val) 将 val 添加到队列的 正中间 。
void pushBack(int val) 将 val 添加到队里的 最后面 。
int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：

将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。

> 示例 1：
>
> 输入：
> ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
> [[], [1], [2], [3], [4], [], [], [], [], []]
> 输出：
> [null, null, null, null, null, 1, 3, 4, 2, -1]
>
> 解释：
> FrontMiddleBackQueue q = new FrontMiddleBackQueue();
> q.pushFront(1);   // [1]
> q.pushBack(2);    // [1, 2]
> q.pushMiddle(3);  // [1, 3, 2]
> q.pushMiddle(4);  // [1, 4, 3, 2]
> q.popFront();     // 返回 1 -> [4, 3, 2]
> q.popMiddle();    // 返回 3 -> [4, 2]
> q.popMiddle();    // 返回 4 -> [2]
> q.popBack();      // 返回 2 -> []
> q.popFront();     // 返回 -1 -> [] （队列为空）
>