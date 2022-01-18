# 企业高频题目

## 字节

### 字节-数据研发  [Leetcode611 有效三角形的个数](https://leetcode-cn.com/problems/valid-triangle-number/comments/)-排序+双指针

```java
class Solution {
    public int triangleNumber(int[] nums) {
        // 类似三数之和
        // 先对其排序
        Arrays.sort(nums);
        int sum = 0;
        // 后对其for循环
        for(int i=2;i<nums.length;i++){
            int left = 0;
            int right = i-1;
            while(left<right){
                //判断
                if(nums[left]+nums[right]>nums[i]){
                    // 符合条件
                    // 那么left-right-1之间 匹配right都符合
                    sum += (right-left);
                    // 此时right移动
                    right--;
                }else if(nums[left]+nums[right]<=nums[i]){
                    // 不符合条件
                    left++;
                }
            }
        }
        return sum;
    }
}
```

### [字节-财经 Leetcode765 情侣牵手](https://leetcode-cn.com/problems/couples-holding-hands/)-并查集

N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。

人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。

这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。

示例 1:

输入: row = [0, 2, 1, 3]
输出: 1
解释: 我们只需要交换row[1]和row[2]的位置即可。

> 首先，我们总是以「情侣对」为单位进行设想：
>
> 当有两对情侣相互坐错了位置，ta们两对之间形成了一个环。需要进行一次交换，使得每队情侣独立（相互牵手）
>
> 如果三对情侣相互坐错了位置，ta们三对之间形成了一个环，需要进行两次交换，使得每队情侣独立（相互牵手）
>
> 如果四对情侣相互坐错了位置，ta们四对之间形成了一个环，需要进行三次交换，使得每队情侣独立（相互牵手）
>
> 也就是说，如果我们有 `k` 对情侣形成了错误环，需要交换 `k - 1` 次才能让情侣牵手。
>
> 用并查集来解题由于 0和1配对、2和3配对 ... 因此互为情侣的两个编号除以 2 对应同一个数字，可直接作为它们的「情侣组」编号：
>
> 我们将每对情侣当成一个组，然后对每个组进行编号，`0,1,2,3,4,5...`
>
> 遍历row数组，每次取两个数
> 分别计算这两个人的组编号，如果组编号相同，说明这两个人是情侣，已经不用交换；如果两个人的组编号不同，说明这两个人不是情侣，后面肯定是需要进行交换的，我们将这两个整体编号进行合并。
> 最后判断并查集中有几个连通分量，以及每个连通分量中有多少个组
> 统计需要交换的次数

```java
class Solution {
    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int n = len/2;
        Union union = new Union(n);
        // 开始循环
        for(int i=0;i<len;i+=2){
            union.union(row[i]/2,row[i+1]/2);
        }
        return n-union.getcount();
    }
}

// 并查集
class Union{
    // 数组
    private int[] parent;
    // 数量
    private int count;
    // 初始化
    public Union(int n){
        this.count = n;
        this.parent = new int[n];
        // 赋值
        for(int i=0;i<n;i++){
            parent[i] = i;
        }
    }

    // 求数量
    public int getcount(){
        return this.count;
    }
    // 求查找find
    public int find(int x){
        while(x!=parent[x]){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    // 合并union
    public void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX==rootY){
            return;
        }
        // 将其转换父节点
        parent[rootX] = rootY;
        count--;
    }

}
```

### [Leetcode003 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)-子串问题滑动窗口解题

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1

> 解题思路：最长子串的长度。滑动窗口，在框架的范围内考虑

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 滑动窗口的解题思路
        char[] arr = s.toCharArray();
        HashMap<Character,Integer> hashMap = new HashMap<>();
        // 对其计算
        int left = 0;
        int right = 0;
        // 记录最长长度
        int minLength = 0;
        while(right<arr.length){
            // 窗口要开始缩放是什么时候  窗口中出现了重复的了
            if(hashMap.containsKey(arr[right])){
                left = Math.max(left,hashMap.get(arr[right])+1);
            }
            // 窗口中维护不重复的字串
            // 维护当前字符串出现的下标索引
            hashMap.put(arr[right],right);
            minLength = Math.max(minLength,right-left+1);
            right++;
        }
        return minLength;
    }
}
```

### [Leetcode206 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)-递归迭代的都要掌握

反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

> 迭代或者递归的解题思路来解题

> 先写迭代的思路

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

> 递归的思路

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // 迭代来解决
        if(head==null || head.next==null){
            return head;
        }
        // 开始翻转下一个
        ListNode newHead = reverseList(head.next);
        // 连接
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
```

### [Leetcode025 K个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)-递归解题思路

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

进阶：

你可以设计一个只使用常数额外空间的算法来解决此问题吗？
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。


示例 1：


输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]

> 迭代的思路来解题，使其通过常数

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 递归 
        if(head==null){
            return null;
        }
        ListNode a = head;
        ListNode b = head;
        // 开始走
        for(int i=0;i<k;i++){
            // 如果走到头了
            if(b==null){
                return head;
            }
            b = b.next;
        }
        // b走到了下一组的开始结点 a记录当前的开始
        // 得到翻转后的新的头结点 
        ListNode newHead = reverse(a,b);
        // 开始继续走
        a.next = reverseKGroup(b,k);
        return newHead;
    }


    // 翻转
    public ListNode reverse(ListNode head,ListNode tail){
        ListNode pre = null;
        ListNode next = null;
        while(head!=tail){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

### [Leetcode215 数组中的第k个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)-优先级队列

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5

示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4

> 解题思路：可以用排序，之后再找。

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        return nums[len-k];
    }
}
```

> 解题思路2：用优先级队列

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 开始找 找第k最大元素，那么用小顶堆的
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->(Integer.compare(a,b)));
        // 开始遍历
        int len = nums.length;
        // 先往里面放入k个元素
        for(int i=0;i<k;i++){
            queue.offer(nums[i]);
        }
        // 接着需要排出来
        for(int i=k;i<len;i++){
            // 比较
            if(nums[i]>queue.peek()){
                queue.poll();
                // 并且入
                queue.offer(nums[i]);
            }
            // 不大于就不做更改
        }
        int res = queue.peek();
        return res;
    }
}
```

### [Leetcode160 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)-解题思路存在多个 暴力 hashMap或者双指针

编写一个程序，找到两个单链表相交的起始节点。

如下面的两个链表**：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。

> 解题思路：存在多个。

> 最优雅的思路：双指针的思路

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 双指针的思路
        if(headA == null || headB == null) return null;
        ListNode newHeadA = headA;
        ListNode newHeadB = headB;
        while(newHeadA!=newHeadB){
            newHeadA = newHeadA==null?headB:newHeadA.next;
            newHeadB = newHeadB==null?headA:newHeadB.next;
        }
        return newHeadA;
    }
}
```

> 个人理解的思路

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 个人的思路
        ListNode newHeadA = headA;
        int lenA = 0;
        ListNode newHeadB = headB;
        int lenB = 0;
        while(newHeadA!=null){
            lenA++;
            newHeadA = newHeadA.next;
        }
        while(newHeadB!=null){
            lenB++;
            newHeadB = newHeadB.next;
        }
        // 开始走了
        ListNode fast = lenA>lenB?headA:headB;
        ListNode slow = lenA>lenB?headB:headA;
        // 长的先走
        int len = Math.abs(lenA-lenB);
        for(int i=0;i<len;i++){
            fast = fast.next;
        }
        while(slow!=fast){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }
}
```

### 不熟悉-[Leetcode146 LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/)-LinkedHashMap

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

> 解题思路：可以直接继承LinkedHashMap

```java
class LRUCache extends LinkedHashMap<Integer,Integer>{
    private int capacity;
    public LRUCache(int capacity) {
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key,-1);
    }
    
    public void put(int key, int value) {
        super.put(key,value);
    }

    // 重写父类方法
    public boolean removeEldestEntry(Map.Entry<Integer,Integer> old){
        return size()>capacity;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

> 双端和hashmap实现

```java
class LRUCache {
    // hashMap和双向链表
    class DListNode{
        private int key;
        private int value;
        private DListNode prev;
        private DListNode next;
        // 初始化
        public DListNode(int key,int value){
            this.key = key;
            this.value = value;
        }
        public DListNode(){

        }
    }
    DListNode head;
    DListNode tail;
    private HashMap<Integer,DListNode> hashMap;
    // 存储容量
    private int capacity;
    // 目前存储的大小
    private int size;
    public LRUCache(int capacity) {
        // 初始化
        hashMap = new HashMap<>();
        head = new DListNode();
        tail = new DListNode();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        this.size = 0;
    }
    
    public int get(int key) {
        DListNode node = hashMap.get(key);
        if(node==null){
            // 不存在
            return -1;
        }else{
            // 取出来，并且将其放置于头
            moveToHead(node);
            return node.value;
        }
    }
    
    public void put(int key, int value) {
        // 查看该关键字是否已经存在
        DListNode node = hashMap.get(key);
        if(node!=null){
            // 已经存在了
            node.value = value;
            // 并且移动
            moveToHead(node);
        }else{
            // 查看节点数量超过了
            if(size==capacity){
                // 超过了删除尾部节点
                DListNode deleteNode = removeTail();
                // 并且hashmap也删除key
                hashMap.remove(deleteNode.key);
                size--;
            }
            // 记得给hashmap添加
            // 节点不存在
            node = new DListNode(key,value);
            hashMap.put(key,node);
            addNode(node);
            size++;

            
        }

    }

    // 删除某个节点
    public void removeNode(DListNode node){
        // 当前节点前一个和当前节点的后一个相连接
        DListNode prev = node.prev;
        DListNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    // 将某个节点添加到头节点
    public void addNode(DListNode node){
        // 添加到头结点之后
        DListNode next = head.next;

        node.prev = head;
        node.next = next;

        next.prev = node;
        head.next = node;


    }

    // 整合在一起
    public void moveToHead(DListNode node){
        removeNode(node);
        addNode(node);
    }

    public DListNode removeTail(){
        DListNode deleteNode = tail.prev;
        removeNode(deleteNode);
        // 返回
        return deleteNode;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

### 不熟悉-Leetcode103 二叉树的锯齿形层序遍历

给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层序遍历如下：

[
  [3],
  [20,9],
  [15,7]
]

> 正常层次遍历，然后在对应层进行翻转

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //结果
        List<List<Integer>> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 每一层的结果
        List<Integer> list = new ArrayList<>();
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 当前层数
        int levelSize = 1;
        // 记录
        int index = 0;
        while(!queue.isEmpty()){
            //开始走
            root = queue.poll();
            list.add(root.val);
            levelSize--;

            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }

            if(levelSize==0){
                //判断
                if(index%2!=0){
                }
                levelSize = queue.size();
                index++;
                res.add(list);
                list = new LinkedList<>();
            }
        }
        return res;
    }
}
```

### Leetcode121 买卖股票的最佳时机

给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

 

示例 1：

输入：[7,1,5,3,6,4]
输出：5
解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 动态规划 维护一个之前的最低进价
        int min_price = prices[0];
        // 维护一个利润
        int max_profit = 0;
        // 开始
        int n = prices.length;
        for(int i=1;i<n;i++){
            max_profit = Math.max(max_profit,prices[i]-min_price);
            min_price = Math.min(min_price,prices[i]);
        }
        return max_profit;
    }
}
```

### Leetcode015 三数之和

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]

示例 2：

输入：nums = []
输出：[]

示例 3：

输入：nums = [0]
输出：[]

> 三数之和，一个for循环，两个指针

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 结果
        List<List<Integer>> res = new ArrayList<>();
        // 符号的结果
        // 一个for循环外加指针解题 且不能重复
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            // 判断i的开头
            if(i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = nums.length-1;
            // 开始
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(new ArrayList<>(list));
                    // 如何继续走
                    // 防止重复
                    while(left+1<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    left++;
                    while(right-1>left&&nums[right]==nums[right-1]){
                        right--;
                    }
                    right--;
                }else if(sum>0){
                    right--;
                }else if(sum<0){
                    left++;
                }

            }
        }
        return res;

    }
}
```

### Leetcode001 两数之和

给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 用HashMap辅助
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(hashMap.isEmpty() || !hashMap.containsKey(target-nums[i])){
                // 放入 
                hashMap.put(nums[i],i);
            }else{
                // 存在
                int first = hashMap.get(target-nums[i]);
                return new int[]{first,i};
            }
        }
        return new int[]{-1,-1};
    }
}
```

### Leetcode053 最大子序和

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：

输入：nums = [1]
输出：1

> 动态规划

```java
class Solution {
    public int maxSubArray(int[] nums) {
        // 连续子序和 动态规划
        if(nums.length<1){
            return 0;
        }
        int res = nums[0];
        for(int i=1;i<nums.length;i++){
            nums[i] = Math.max(nums[i],nums[i-1]+nums[i]);
            res = Math.max(nums[i],res);
        }
        return res;
    }
}
```

### 不熟练-Leetcode199二叉树的右视图

给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        //还是之前的层次遍历
        List<Integer> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        while(!queue.isEmpty()){
            root = queue.poll();
            if(levelSize==1){
                res.add(root.val);
            }
            levelSize--;
            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }

            if(levelSize==0){
                // 重新开始的一层
                levelSize = queue.size();
            }
        }
        return res;
    }
}
```

### 不熟悉-Leetcode42 接雨水

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png)

输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 

> 算上当前的！！！左边和右边的最大值

```java
class Solution {
    public int trap(int[] height) {
        // 是从当前的值算上当前这个左边和右边来走。！！！
        int res = 0;
        for(int i=1;i<height.length;i++){
            int leftmax = 0;
            int rightmax = 0;
            for(int j=i;j>=0;j--){
                leftmax = Math.max(leftmax,height[j]);
            }
            for(int j=i;j<height.length;j++){
                rightmax = Math.max(rightmax,height[j]);
            }
            res += Math.min(leftmax,rightmax)-height[i];
        }
        return res;
    }
}
```

> 优化

```java
class Solution {
    public int trap(int[] height) {
        // 记录左边最大值
        int n = height.length;
        if(n<=0){
            return 0;
        }
        int[] left_max = new int[n];
        left_max[0] = height[0];
        for(int i=1;i<n;i++){
            left_max[i] = Math.max(left_max[i-1],height[i]);
        }
        int[] right_max = new int[n];
        right_max[n-1] = height[n-1];
        for(int i=n-2;i>=0;i--){
            right_max[i] = Math.max(right_max[i+1],height[i]);
        }
        int res = 0;
        for(int i=1;i<n-1;i++){
            res += Math.min(left_max[i],right_max[i])-height[i];
        }
        return res;
    }
}
```

### 不熟悉-Leetcode236 二叉树的最近公共祖先

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

 

示例 1：


输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode,TreeNode> hashMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        hashMap.put(root,null);
        while(!queue.isEmpty()){
            root = queue.poll();
            if(root.left!=null){
                hashMap.put(root.left,root);
                queue.offer(root.left);
            }

            if(root.right!=null){
                hashMap.put(root.right,root);
                queue.offer(root.right);
            }
        }

        // 开始记录一个其中的路径
        Set<TreeNode> set = new HashSet<>();
        while(p!=null){
            set.add(p);
            p = hashMap.get(p);
        }
        while(q!=null){
            if(set.contains(q)){
                return q;
            }
            q = hashMap.get(q);
        }
        return null;

    }
}
```

### Leetcode415 字符串相加

给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

 

提示：

num1 和num2 的长度都小于 5100
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式

> 解题思路：记得字符减去0，要不然得不到数字

```java
class Solution {
    public String addStrings(String num1, String num2) {
        // 两个字符串相加
        char[] arr1 = num1.toCharArray();
        char[] arr2 = num2.toCharArray();
        int index1 = arr1.length-1;
        int index2 = arr2.length-1;
        StringBuilder res = new StringBuilder();
        // 余数
        int mod = 0;
        while(index1>=0 || index2>=0){
            // 记得减去从而计算值
            int s1 = index1>=0?arr1[index1]-'0':0;
            int s2 = index2>=0?arr2[index2]-'0':0;
            int temp_sum = s1+s2+mod;
            
            mod = temp_sum/10;
            res.append(temp_sum%10);

            // 继续走
            index1--;
            index2--;
        }
        if(mod!=0){
            res.append(mod);
        }
        //反转
        String sum = res.reverse().toString();
        return sum;
    }
}
```

### Leetcode21 合并两个有序链表

将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg)


输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

> 解题思路：就直接拼接即可

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 新节点
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                node.next = l1;
                l1 = l1.next;
            }else{
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        // 查看是否还有
        if(l1!=null){
            node.next = l1;
        }
        if(l2!=null){
            node.next = l2;
        }
        return dummy.next;
    }
}
```

### Leetcode88 合并两个有序数组

给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。

 

示例 1：

输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
输出：[1,2,2,3,5,6]

示例 2：

输入：nums1 = [1], m = 1, nums2 = [], n = 0
输出：[1]

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 直接在num1中保存
        int index = m+n-1;
        int index1 = m-1;
        int index2 = n-1;
        while(index1>=0&&index2>=0){
            if(nums1[index1]>=nums2[index2]){
                nums1[index] = nums1[index1];
                index--;
                index1--;
            }else if(nums1[index1]<nums2[index2]){
                nums1[index] = nums2[index2];
                index--;
                index2--;
            }
        }
        while(index2>=0){
            nums1[index--] = nums2[index2--];
        }
    }
}
```

### Leetcode141 环形链表

给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

进阶：

你能用 O(1)（即，常量）内存解决此问题吗？



示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

> 快慢指针来

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }
}
```

### Leetcode105 从前序与中序遍历构造二叉树

根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

```
  3
   / \
  9  20
    /  \
   15   7
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // hashmap
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 对中序遍历
        for(int i=0;i<inorder.length;i++){
            hashMap.put(inorder[i],i);
        }
        TreeNode root = myTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
        return root;
    }

    // 递归
    public TreeNode myTree(int[] preorder,int preorder_left,int preorder_right,int[] inorder,int inorder_left,int inorder_right){
        // 截止条件
        if(inorder_left>inorder_right){
            return null;
        }
        int root_value = preorder[preorder_left];
        int inorder_root_index = hashMap.get(root_value);
        int dis = inorder_root_index-inorder_left;
        TreeNode root = new TreeNode(root_value);

        root.left = myTree(preorder,preorder_left+1,preorder_left+dis,inorder,inorder_left,inorder_root_index-1);
        root.right = myTree(preorder,preorder_left+dis+1,preorder_right,inorder,inorder_root_index+1,inorder_right);
        return root;


    }

}
```

### Leetcode20 有效的括号

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。


示例 1：

输入：s = "()"
输出：true

示例 2：

输入：s = "()[]{}"
输出：true

示例 3：

输入：s = "(]"
输出：false

> 用栈来判断符号是否合法

```java
class Solution {
    public boolean isValid(String s) {
        // 用栈
        char[] arr = s.toCharArray();
        HashMap<Character,Character> hashMap = new HashMap<>();
        hashMap.put(')','(');
        hashMap.put(']','[');
        hashMap.put('}','{');
        // 对其遍历
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]=='(' || arr[i]=='[' || arr[i]=='{'){
                // 入栈
                stack.push(arr[i]);
            }else{
                //判断
                if(!stack.isEmpty()&&hashMap.get(arr[i])==stack.peek()){
                    // 相等
                    stack.pop();
                }else{
                    return false;
                }
            }
        }   
        // 判断栈是否为空
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}
```

### 判断是否为二叉搜索树



## 美团

### 补充题4：手撕快速排序

```java
class Solution {
    public int[] sortArray(int[] nums) {
        // 手撕一个快速排序
        quickSort(nums,0,nums.length-1);
        return nums;
    }
    // 快排
    public void quickSort(int[] nums,int left,int right){
        if(left<right){
            int[] partitaion = sort(nums,left,right);
            quickSort(nums,left,partitaion[0]-1);
            quickSort(nums,partitaion[1]+1,right);
        }
    }

    // 排序
    public int[] sort(int[] nums,int L,int R){
        int less = L-1;
        int more = R;
        while(L<more){
            if(nums[L]<nums[R]){
                swap(nums,++less,L++);
            }else if(nums[L]>nums[R]){
                swap(nums,--more,L);
            }else if(nums[L]==nums[R]){
                L++;
            }
        }
        // 最后交换
        swap(nums,more,R);
        return new int[]{less+1,more};
    }

    //  交换
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }
}
```

### 不熟悉迭代方法-Leetcode206 反转链表

反转一个单链表。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){
            return  head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
}
```

> 非迭代方法

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

### Leetcode88 合并两个有序数组

给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。

 

示例 1：

输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
输出：[1,2,2,3,5,6]

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m-1;
        int index2 = n-1;
        int index = m+n-1;
        // 开始
        while(index1>=0&&index2>=0){
            if(nums1[index1]<nums2[index2]){
                nums1[index--] = nums2[index2--];
            }else{
                nums1[index--] = nums1[index1--];
            }
        }
        while(index2>=0){
            nums1[index--] = nums2[index2--];
        }
        
    }
}
```

### Leetcode141 环形链表

给定一个链表，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

如果链表中存在环，则返回 true 。 否则，返回 false 。

 

进阶：

你能用 O(1)（即，常量）内存解决此问题吗？

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast  = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(slow==fast){
                return true;
            }
        }
        return false;

    }
}
```



### 不熟悉-Leetcode718 最长重复子数组

给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

 

示例：

输入：
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出：3
解释：
长度最长的公共子数组是 [3, 2, 1] 。

> **子数组的问题。**

```java
class Solution {
    // 公共子数组
    public int findLength(int[] A, int[] B) {
        //动态规划
        int n = A.length;
        int m = B.length;
        // 记录最长
        int max = 0;
        // 开辟动态规划的数组
        int[][] dp = new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(A[i-1]==B[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = 0;
                }
                max = Math.max(dp[i][j],max);
            }
        }
        return max;
    }
}
```

> 补充题，最长公共**子序列**问题

**示例 1:**

```
输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace"，它的长度为 3。
```

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // 也还是动态规划
        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();
        int len1 = arr1.length;
        int len2 = arr2.length;
        // 动态规划数组
        int[][] dp = new int[len1+1][len2+1];
        // 开始
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(arr1[i-1]==arr2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
```

### Leetcode232 用栈实现队列

请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（`push`、`pop`、`peek`、`empty`）：

```java
class MyQueue {
    // 两个模拟队列
    Stack<Integer> s1;
    Stack<Integer> s2;
    /** Initialize your data structure here. */
    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    // 入队列
    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    
    // cj
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        int n = s2.peek();
        return n;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty()&&s2.isEmpty();
    }   
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```

### Leetcode53 最大子序和

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

> 最大和的连续子数组

```java
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length<=1){
            return nums[0];
        }
        int res = nums[0];
        int n   = nums.length;
        for(int i=1;i<n;i++){
            nums[i] = Math.max(nums[i],nums[i-1]+nums[i]);
            res = Math.max(res,nums[i]);
        }
        return res;
    }
}
```



### Leetcode20 有效的括号

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。

> 有效括号的判断

```java
class Solution {
    public boolean isValid(String s) {
        //用栈
        HashMap<Character,Character> dict = new HashMap<>();
        dict.put(')','(');
        dict.put('}','{');
        dict.put(']','[');
        // 栈来判断
        Stack<Character> stack = new Stack<>();
        // 开始
        char[] arr = s.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(dict.containsKey(arr[i])){
                // 出栈之前做个判断
                if(!stack.isEmpty() && dict.get(arr[i])==stack.peek() ){
                     stack.pop();
                }else{
                    return false;                    
                }
            }else{
                // 入栈
                stack.push(arr[i]);
            }
        }
        // 如果栈还有
        if(!stack.isEmpty()){
            return false;
        }
        return true;
    }
}
```



### Leetcode21 合并两个有序链表

将两个升序链表合并为一个新的 **升序** 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode l = dummy;
        // 开始遍历
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                l.next = l1;
                l = l.next;
                l1 = l1.next;
            }else{
                l.next = l2;
                l = l.next;
                l2 = l2.next;
            }
        }
        if(l1!=null){
            l.next = l1;
        }
        if(l2!=null){
            l.next = l2;
        }
        return dummy.next;
    }
}
```

### Leetcode142 环形链表II

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

说明：不允许修改给定的链表。

进阶：

你是否可以使用 O(1) 空间解决此题？


示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png)

输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&&fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                fast = head;
                // 开始重新走
                while(slow!=fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
```

### Leetcode_Offer22 链表中倒数第k个结点

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode dummmy = new ListNode(-1,head);
        ListNode slow = dummmy;
        ListNode fast = dummmy;
        for(int i=0;i<k;i++){
            fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

### Leetcode003 无重复字符的最长子串

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 用hashmap的窗口来存储第一次出现子串的位置
        // 窗口来维护
        if(s.length()<=0){
            return 0;
        }
        HashMap<Character,Integer> dict = new HashMap<>();
        char[] arr = s.toCharArray();
        int left = 0;
        int right = 0;
        // 结果
        int res = 0;
        while(right<arr.length){
            if(dict.containsKey(arr[right])){
                // 左边移动
                left = Math.max(left,dict.get(arr[right])+1);
            }
            // 放入以及更新索引
            dict.put(arr[right],right);
            res = Math.max(res,right-left+1);
            right++;
        }
        return res;

    }
}
```

### Leetcode215 数组中的第k个最大元素

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5

示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 优先级队列最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->(a-b));
        for(int i=0;i<k;i++){
            queue.offer(nums[i]);
        }
        for(int i=k;i<nums.length;i++){
            // 判断
            if(queue.peek()<=nums[i]){
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
}
```

### Leetcode015 三数之和

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
示例 2：

输入：nums = []
输出：[]

示例 3：

输入：nums = [0]
输出：[]

> 三数之和求解

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 结果
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if(n<3){
            return res;
        }
        // 排序
        Arrays.sort(nums);
        for(int i=0;i<n-2;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left = i+1;
            int right = n-1;
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(new ArrayList<>(list));
                    // 判断如何走
                    while(left+1<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    left++;
                    while(left<right-1&&nums[right]==nums[right-1]){
                        right--;
                    }
                    right--;
                }else if(sum>0){
                    right--;
                }else if(sum<0){
                    left++;
                }
            }
        }
        return res;
    }
}
```

### [不熟悉-Leetcode008 字符串转换整数(atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)

请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

函数 myAtoi(string s) 的算法如下：

读入字符串并丢弃无用的前导空格
检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
返回整数作为最终结果。
注意：

本题中的空白字符只包括空格字符 ' ' 。
除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。

> 解题思路：通过while和index来判断

```java
class Solution {
    public int myAtoi(String s) {
        // 用while来做
        char[] arr = s.toCharArray();
        int index = 0;
        int len = arr.length;
        // 先去除空格
        while(index<len&&arr[index]==' '){
            index++;
        }
        // 判断
        if(index==len){
            return 0;
        }
        // 再去判断正负号
        boolean flag = true;
        if(arr[index]=='+'){
            flag = true;
            index++;
        }else if(arr[index]=='-'){
            flag = false;
            index++;
        }
        // 之后再去判断是否为数字
        // 结果
        int res = 0;
        while(index<len&&Character.isDigit(arr[index])){
            int digit = arr[index] - '0';
            // 判断是否超过最大值范围 res*10+digit>Integer.max_value;
            if((res)>(Integer.MAX_VALUE-digit)/10){
                return flag?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            res = res*10 + digit;
            index++;
        }
        return flag?res:(-1)*res;

    }
}
```

### [不熟悉-Leetcode007整数反转](https://leetcode-cn.com/problems/reverse-integer/)

给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。


示例 1：

输入：x = 123
输出：321

> 解题思路：

```java
class Solution {
    public int reverse(int x) {
        int res = 0;
        while(x!=0){
            int digit = x%10;
            // 判断是否溢出
            if((res*10)/10!=res){
                return 0;
            }
            res = res*10+digit;
            x = x/10;
        }
        return res;
    }
}
```

### [Leetcode704 二分查找](https://leetcode-cn.com/problems/binary-search/)

给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。


示例 1:

输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4

> 解题思路：二分查找

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right--;
            }else if(nums[mid]<target){
                left++;
            }
        }
        return -1;
    }
}
```

### [Leetcode001 两数之和](https://leetcode-cn.com/problems/two-sum/)



给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

> hashmap来存储

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // hashmap
        HashMap<Integer,Integer> hashmap = new HashMap<>();
        // 对其遍历
        for(int i=0;i<nums.length;i++){
            if(!hashmap.isEmpty()&&hashmap.containsKey(target-nums[i])){
                int pre = hashmap.get(target-nums[i]);
                return new int[]{pre,i};
            }
            hashmap.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
}
```

### [不熟悉-Leetcode54 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg)

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

>判断条件忘了最上面的两个，以及下面的两个if判断

```java
class Solution {
    // 结果存储
    List<Integer> res  = new ArrayList<>();
    public List<Integer> spiralOrder(int[][] matrix) {
        // 开始坐标 结束坐标
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        int start_row = 0;
        int start_col = 0;
        int end_row = rows-1;
        int end_col = cols-1;

        // while循环
        while(start_row<=end_row&&start_col<=end_col){
            process(matrix,start_row++,start_col++,end_row--,end_col--);
        } 
        return res;
    }

    // 处理
    public void process(int[][] matrix,int start_row,int start_col,int end_row,int end_col){
        // 最上边的处理
        for(int i=start_col;i<=end_col;i++){
            res.add(matrix[start_row][i]);
        }
        if(start_row==end_row){
            return;
        }
        // 最右边的处理
        for(int i=start_row+1;i<=end_row;i++){
            res.add(matrix[i][end_col]);
        }
        
        if(start_col==end_col){
            return;
        }
        // 最下边的处理
        for(int i=end_col-1;i>=start_col;i--){
            res.add(matrix[end_row][i]);
        }

        // 最左边的处理
        for(int i=end_row-1;i>start_row;i--){
            res.add(matrix[i][start_col]);
        }

    }
}
```

### [Leetcode102 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

 

示例：
二叉树：[3,9,20,null,null,15,7],

3

   / \
  9  20
    /  \
   15   7
返回其层序遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

> 解题思路：层序遍历

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //结果
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 每一层的值
        List<Integer> list = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 层序遍历用队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        while(!queue.isEmpty()){
            root = queue.poll();
            list.add(root.val);
            levelSize--;

            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }

            if(levelSize==0){
                res.add(new ArrayList<>(list));
                list = new ArrayList<>();
                levelSize = queue.size();
            }
        }
        return res;
    }
}
```

## 华为

### 不熟悉-Leetcode1353 最多可以参加的会议数目

给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。

你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。

请你返回你可以参加的 最大 会议数目。

 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/16/e1.png)

输入：events = [[1,2],[2,3],[3,4]]
输出：3
解释：你可以参加所有的三个会议。
安排会议的一种方案如上图。
第 1 天参加第一个会议。
第 2 天参加第二个会议。
第 3 天参加第三个会议。

> 解题思路：先排序，排序之后，对每天的事件第一个循环，记录最早的天数，符合天数就添加，之后break

```java
class Solution {
    public int maxEvents(int[][] events) {
        // 先去排序 以结束时间来排序
        Arrays.sort(events,(a,b)->(a[1]==b[1]?a[0]-b[0]:a[1]-b[1]));
        // 参观天数
        boolean[] vis = new boolean[100000+1];
        // 结果
        int res = 0;
        for(int[] event:events){
            for(int day=event[0];day<=event[1];day++){
                // 这天是否占了
                if(!vis[day]){
                    vis[day] = true;
                    ++res;
                    break;
                }
            }
        }
        return res;

    }
}
```

### 不熟悉-Leetcode630 课程表III

这里有 n 门不同的在线课程，他们按从 1 到 n 编号。每一门课程有一定的持续上课时间（课程时间）t 以及关闭时间第 d 天。一门课要持续学习 t 天直到第 d 天时要完成，你将会从第 1 天开始。

给出 n 个在线课程用 (t, d) 对表示。你的任务是找出最多可以修几门课。

 

示例：

输入: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
输出: 3
解释: 
这里一共有 4 门课程, 但是你最多可以修 3 门:
首先, 修第一门课时, 它要耗费 100 天，你会在第 100 天完成, 在第 101 天准备下门课。
第二, 修第三门课时, 它会耗费 1000 天，所以你将在第 1100 天的时候完成它, 以及在第 1101 天开始准备下门课程。
第三, 修第二门课时, 它会耗时 200 天，所以你将会在第 1300 天时完成它。
第四门课现在不能修，因为你将会在第 3300 天完成它，这已经超出了关闭日期。

```java
class Solution {
    public int scheduleCourse(int[][] courses) {
        //1.按照结束时间对课程进行排序
        //2.使用一个大顶堆来储存已经选择的课程的长度
        //3.一旦发现安排了当前课程之后，其结束时间超过了最晚结束时间，那么就从已经安排的课程中，取消掉一门最耗时的课程
        Arrays.sort(courses,(a,b)->(a[1]-b[1]));
        // 大顶堆记录最晚的deadline
        Queue<Integer> queue = new PriorityQueue<>((a,b)->(b-a));
        // 开始时间
        int time = 0;
        for(int[] course:courses){
            int duration = course[0];
            int deadline = course[1];
            //记录
            if(time+duration<=deadline){
                // 入队列
                time = time + duration;
                queue.offer(duration);
            }else{
                // 出队列 更新最晚deadline
                queue.add(duration);
                time = time + duration-queue.poll();
            }
        }
        return queue.size();
    }
}
```



### 不熟悉-Leetcode498 对角线遍历

> 相关类似的题目，有转圈打印矩阵，旋转正方形矩阵；之字形打印；
>
> 重点是左上角坐标的两个坐标  右下角坐标的两个坐标；然后一个while循环

给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。

 

示例:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

输出:  [1,2,4,7,5,3,6,8,9]

解释:

 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/diagonal_traverse.png)

> 解题思路：四个坐标值，一个while循环。点的变化很重要。上面那个点先变换row 后col，下面那个先变换col后变换row

```java
class Solution {
    // 记录
    List<Integer> list = new ArrayList<>();
    public int[] findDiagonalOrder(int[][] matrix) {
        // 记录左上角和右下角即可
        int endR = matrix.length-1;
        int endC = matrix[0].length-1;
        // 当前开始和结束的四个坐标值
        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        // 用一个flag来标识
        boolean flag = false;
        // 开始的点走到了最后的一行
        while(tR!=endR+1){
            // 打印
            print(matrix,tR,tC,dR,dC,flag);
            // 开始移动
            // 移动注意先移动左
            tR = tC==endC?tR+1:tR;
            tC = tC==endC?tC:tC+1;
            // 下方移动的时候先移动 要不然报错！！
            dC = dR==endR?dC+1:dC;
            dR = dR==endR?dR:dR+1;
            flag = !flag;
        }
        int len = (endR+1)*(endC+1);
        int[] res = new int[len];
        for(int i=0;i<len;i++){
            res[i] = list.get(i);
        }
        return res;

    }

    public void print(int[][] matrix,int tR,int tC,int dR,int dC,boolean flag){
        if(flag){
            while(tR!=dR+1){
                list.add(matrix[tR][tC]);
                tR++;
                tC--;
            }
        }else{
            while(dR!=tR-1){
                list.add(matrix[dR][dC]);
                dR--;
                dC++;
            }
        }

    }
}
```

### 很不熟悉图的拓扑排序-Leetcode207 课程表

你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

 

示例 1：

输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。

```java
class Solution {
    // 拓扑排序
    List<ArrayList<Integer>> edges;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 结果存储 下标索引
        int index = 0;
        int[] res = new int[numCourses];
        // 每个点的入度数
        int[] indeg = new int[numCourses];
        // 初始化
        edges = new ArrayList<>();
        // 内部初始化 
        for(int i=0;i<numCourses;i++){
            edges.add(new ArrayList<Integer>());
        }
        // 每个有个点 每点连接边 图构造
        for(int[] prerequisite:prerequisites){
            edges.get(prerequisite[1]).add(prerequisite[0]);
            indeg[prerequisite[0]]++;
        }

        // 开始遍历
        Queue<Integer> queue = new LinkedList<>();
        // 遍历课程
        for(int i=0;i<numCourses;i++){
            // 度为0入
            if(indeg[i]==0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            // 出一个结点
            int u = queue.poll();
            // 保存
            res[index++] = u;
            // 遍历相邻结点
            for(int v:edges.get(u)){
                // 入度减少1
                indeg[v]--;
                // 度为0入队列
                if(indeg[v]==0){
                    queue.offer(v);
                }
            }
        }
        if(index!=numCourses){
            return false;
        }
        return true;        
    }
}
```

### 不熟悉动归-Leetcode1563石子游戏V

几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。

游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。

只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。

返回 Alice 能够获得的最大分数 。

 示例 1：

输入：stoneValue = [6,2,3,4,5,5]
输出：18
解释：在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。左行的值是 11 ，右行的值是 14 。Bob 丢弃了右行，Alice 的分数现在是 11 。
在第二轮中，Alice 将行分成 [6]，[2，3] 。这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11 + 5）。
最后一轮 Alice 只能将行分成 [2]，[3] 。Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。

> 解题思路：动归

```java
class Solution {
    public int stoneGameV(int[] stoneValue) {
        // 存储分数
        int n = stoneValue.length;
        // score的含义表示从left到right之间最大的分数
        int[][] score = new int[n][n];
        // dfs
        return dfs(stoneValue,score,0,n-1);
    }
    // 回溯score的含义
    public int dfs(int[] stoneValue,int[][] score,int left,int right){
        // 截止条件
        if(left==right){
            return 0;
        }
        if(score[left][right]!=0){
            return score[left][right];
        }
        // 存储
        int sum = 0;
        for(int i=left;i<=right;i++){
            sum += stoneValue[i];
        }
        // 别的计算
        // 左边
        int suml = 0;
        for(int i=left;i<right;i++){
            suml += stoneValue[i];
            int sumr = sum-suml;
            // 开始比较
            if(suml>sumr){
                // 扔掉左边
                score[left][right] = Math.max(score[left][right],dfs(stoneValue,score,i+1,right)+sumr);
            }else if(suml<sumr){
                // 扔掉右边
                score[left][right] = Math.max(score[left][right],dfs(stoneValue,score,left,i)+suml);
            }else{
                score[left][right] = Math.max(score[left][right],Math.max(dfs(stoneValue,score,left,i),dfs(stoneValue,score,i+1,right))+suml);
            }
        }
        return score[left][right];
    }
}
```

### 不熟悉-Leetcode365 水壶问题

有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？

如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。

你允许：

装满任意一个水壶
清空任意一个水壶
从一个水壶向另外一个水壶倒水，直到装满或者倒空
示例 1: (From the famous "Die Hard" example)

输入: x = 3, y = 5, z = 4
输出: True

> 解题思路：求x和y的最大公约数，看z是否为x和y最大公约数的倍数

```java
class Solution {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        // 该题数学公式可求解就是判断z判断是不是x和y最大公约数的倍数
        // 判断
        if(jug1Capacity+jug2Capacity<targetCapacity){
            return false;
        }
        if(jug1Capacity==0 || jug2Capacity==0){
            return targetCapacity==0 || jug1Capacity+jug2Capacity==targetCapacity;
        }
        return targetCapacity%gcd(jug1Capacity,jug2Capacity)==0;
    }

    public int gcd(int x,int y){
        int remain = x % y;
        while(remain!=0){
            x = y;
            y = remain;
            remain = x % y;
        }
        return y;
    }
}
```

### 略熟悉-Leetcode94 二叉树的中序遍历

给定一个二叉树的根节点 `root` ，返回它的 **中序** 遍历。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)

```
输入：root = [1,null,2,3]
输出：[1,3,2]
```

> 中序遍历，就是左节点一直进

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        // 中序遍历
        List<Integer> list = new ArrayList<>();

        // 用栈
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                // 出栈
                root = stack.pop();
                list.add(root.val);
                // 看右节点
                root = root.right;
            }
        }
        return list;

    }
}
```

### Leetcode344反转字符串

编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

 

示例 1：

输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]

```java
class Solution {
    public void reverseString(char[] s) {
        int n = s.length;
        int left = 0;
        int right = n-1;
        while(left<right){
            char temp = s[left];
            s[left]  = s[right];
            s[right] = temp;
            // 继续走
            left++;
            right--;
        }
    }
}
```

### 不熟悉-Leetcode622 设计循环队列

设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。

循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。

你的实现应该支持如下操作：

MyCircularQueue(k): 构造器，设置队列长度为 k 。
Front: 从队首获取元素。如果队列为空，返回 -1 。
Rear: 获取队尾元素。如果队列为空，返回 -1 。
enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
isEmpty(): 检查循环队列是否为空。
isFull(): 检查循环队列是否已满。


示例：

MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
circularQueue.enQueue(1);  // 返回 true
circularQueue.enQueue(2);  // 返回 true
circularQueue.enQueue(3);  // 返回 true
circularQueue.enQueue(4);  // 返回 false，队列已满
circularQueue.Rear();  // 返回 3
circularQueue.isFull();  // 返回 true
circularQueue.deQueue();  // 返回 true
circularQueue.enQueue(4);  // 返回 true
circularQueue.Rear();  // 返回 4

> 循环队列设计需要四个遍历：
>
> 当前存储的数量；容量；
>
> 头结点的索引，数组；
>
> 尾结点可用(headIndex+count-1)%capacity
>
> 插入时候(headIndex+count)%capacity;
>
> 删除的时候headIndex = (headIndex+1)%capactiy;

```java
class MyCircularQueue {
    // 循环队列用数组来完成
    int[] queue;
    // 需要首个的索引
    int headIndex;
    // 此时存储的大小
    int count;
    // 容量
    int capactiy;
    // 初始化
    public MyCircularQueue(int k) {
        queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
        this.capactiy = k;
    }
    // 入队列
    public boolean enQueue(int value) {
        //判断是否满了
        if(count==capactiy){
            return false;
        }
        // 可以放
        queue[(headIndex+count)%capactiy] = value;
        count++;
        return true;
    }
    // 出队列
    public boolean deQueue() {
        // 判断
        if(count==0){
            return false;
        }
        // 删除就删移动首个索引
        headIndex = (headIndex+1)%capactiy;
        // 数量
        count--;
        return true;
    }
    // 从队首获取元素
    public int Front() {
        if(count==0){
            return -1;
        }
        return this.queue[headIndex];
    }
    // 从队尾获取元素
    public int Rear() {
        if(count==0){
            return -1;
        }
        int tailIndex = (headIndex+count-1)%capactiy;
        return this.queue[tailIndex];
    }
    // 判断是否为空
    public boolean isEmpty() {
        return count==0;
    }
    
    public boolean isFull() {
        return count==capactiy;
    }   
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
```

### 略微不熟-Leetcode54 螺旋矩阵

给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg)


输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

> 解题思路：需要对是否两个点判断处理

```java
class Solution {
    // 结果
    List<Integer> res = new ArrayList<>();
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 左上角和右上角即可
        int tR = 0;
        int tC = 0;
        int dR = m-1;
        int dC = n-1;
        // 开始执行
        while(tR<=dR&&tC<=dC){
            process(matrix,tR++,tC++,dR--,dC--);
        }
        return res;   
    }
    // 处理
    public void process(int[][] matrix,int tR,int tC,int dR,int dC){
        // 第一行处理
        for(int i=tC;i<=dC;i++){
            res.add(matrix[tR][i]);
        }
        // 加个判断
        if(tR==dR){
            return;
        }

        // 第一列处理
        for(int i=tR+1;i<=dR;i++){
            res.add(matrix[i][dC]);
        }
        // 价格判断
        if(tC==dC){
            return;
        }

        // 第二行处理
        for(int i=dC-1;i>=tC;i--){
            res.add(matrix[dR][i]);
        }

        // 第二列处理
        for(int i=dR-1;i>tR;i--){
            res.add(matrix[i][tC]);
        }
    }
}
```

## 阿里巴巴

### [Leetcode215 数组中的第k个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 优先级队列
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->(a-b));
        // 先放进k个
        for(int i=0;i<k;i++){
            queue.offer(nums[i]);
        }
        // 继续
        for(int i=k;i<nums.length;i++){
            if(nums[i]>queue.peek()){
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }
}
```

### [不熟悉-Leetcode146 LRU缓存机制](https://leetcode-cn.com/problems/lru-cache/)

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
class LRUCache extends LinkedHashMap<Integer,Integer>{

    private int count;
    public LRUCache(int capacity) {
        super(capacity,0.75F,true);
        this.count = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key,-1);
    }
    
    public void put(int key, int value) {
        super.put(key,value);
    }

    public boolean removeEldestEntry(Map.Entry<Integer,Integer> oldest){
        return this.size()>count;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

```java
// 不熟悉从头上插入的时候，先记录后面那一个的next以及hashmap中存储键值对

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

### [Leetcode001-两数之和](https://leetcode-cn.com/problems/two-sum/)

给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!hashMap.isEmpty()&&hashMap.containsKey(target-nums[i])){
                int prev = hashMap.get(target-nums[i]);
                return new int[]{prev,i};
            }
            hashMap.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
}
```

### [Leetcode094 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

给定一个二叉树的根节点 `root` ，返回它的 **中序** 遍历。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)

```
输入：root = [1,null,2,3]
输出：[1,3,2]
```

> 解题思路：中序遍历

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        // 结果存储
        List<Integer> res = new ArrayList<>();
        // 用栈来存储
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null||!stack.isEmpty()){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
```

### [Leetcode102 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)

给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

 

示例：
二叉树：[3,9,20,null,null,15,7],

3

   / \
  9  20
    /  \
   15   7
返回其层序遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 结果
        List<List<Integer>> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 临时每一层的结果
        List<Integer> list =  new ArrayList<>();
        // 层序遍历用队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        while(!queue.isEmpty()){
            root = queue.poll();
            list.add(root.val);
            levelSize--;

            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }

            if(levelSize==0){
                res.add(new ArrayList<>(list));
                list = new ArrayList<>();
                levelSize = queue.size();
            }
        }
        return res;
    }
}
```

### [Leetcode003 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

> 解题思路：滑动窗口

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        if(arr.length<=0){
            return 0;
        }
        // hashmap存储
        HashMap<Character,Integer> dict = new HashMap<>();
        // 滑动 窗口
        int left = 0;
        int right = 0;
        int window_size = 0;
        // 开始
        while(right<arr.length){
            // 什么时候缩窗口
            if(!dict.isEmpty()&&dict.containsKey(arr[right])){
                left = Math.max(left,dict.get(arr[right])+1);
            }
            // 更新每一个字母的下标索引
            dict.put(arr[right],right);
            window_size = Math.max(window_size,right-left+1);
            right++;
        }
        return window_size;
    }
}
```

### [Leetcode415 字符串相加](https://leetcode-cn.com/problems/add-strings/)

给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

 

提示：

num1 和num2 的长度都小于 5100
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
通过次数107,066提交次数205,453

```java
class Solution {
    // 两个字符串相加
    public String addStrings(String num1, String num2) {
        char[] arr_1 = num1.toCharArray();
        char[] arr_2 = num2.toCharArray();
        int index_1 = arr_1.length-1;
        int index_2 = arr_2.length-1;
        // 开始的下标索引
        // 存储结果
        StringBuilder res = new StringBuilder();
        // 存储余数
        int mod = 0;
        while(index_1>=0||index_2>=0){
            // 当前值
            int n1 = index_1>=0?arr_1[index_1]-'0':0;
            int n2 = index_2>=0?arr_2[index_2]-'0':0;
            int temp = n1+n2+mod;
            // 开始
            res.append(temp%10);
            mod = temp/10;
            // 下标移动
            index_1--;
            index_2--;
        }
        // 还可能有余数
        if(mod!=0){
            res.append(mod);
        }
        // 最后
        res = res.reverse();
        return res.toString();
    }
}
```

### [Leetcode015 三数之和](https://leetcode-cn.com/problems/3sum/)

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。

 

示例 1：

输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 结果
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(len<3){
            return res;
        }
        // 排序
        Arrays.sort(nums);

        for(int i=0;i<len-2;i++){
            int left = i+1;
            int right = len-1;
            if(i!=0&&nums[i]==nums[i-1]){
                continue;
            }
            while(left<right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum==0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(new ArrayList<>(temp));
                    while(left+1<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    left++;
                    while(left<right-1&&nums[right]==nums[right-1]){
                        right--;
                    }
                    right--;
                }else if(sum>0){
                    right--;
                }else if(sum<0){
                    left++;
                }
            }
        }
        return res;
    }
}
```

### [Leetcode206 反转链表](https://leetcode-cn.com/problems/reverse-linked-list/)

反转一个单链表。

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
```

### [Leetcode145 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

给定一个二叉树，返回它的 后序 遍历。

示例:

输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

> 解题思路：两个栈

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        // 前序和后序遍历 类似于层序遍历
        List<Integer> res = new ArrayList<>();
        //用栈来遍历
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while(!s1.isEmpty()){
            root = s1.pop();
            s2.push(root);

            if(root.left!=null){
                s1.push(root.left);
            }

            if(root.right!=null){
                s1.push(root.right);
            }
        }
        // 最后统计
        while(!s2.isEmpty()){
            res.add(s2.pop().val);
        }
        return res;
    }
}
```

### [Leetcode046 全排列](https://leetcode-cn.com/problems/permutations/)

给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

> 回溯选择状态

```java
class Solution {
    //结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length<=0){
            return res;
        }
        // 临时值
        List<Integer> list = new ArrayList<>();        
        // 回溯算法
        dfs(nums,list);
        return res;
    }
    // 回溯算法
    public void dfs(int[] nums,List<Integer> list){
        if(list.size()==nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        // 选择状态
        for(int i=0;i<nums.length;i++){
            // 防止重复
            if(list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            dfs(nums,list);
            list.remove(list.size()-1);
        }
    }
}
```

### [Leetcode912 手撕快排](https://leetcode-cn.com/problems/sort-an-array/)



给你一个整数数组 nums，请你将该数组升序排列。

 

示例 1：

输入：nums = [5,2,3,1]
输出：[1,2,3,5]
示例 2：

输入：nums = [5,1,1,2,0,0]
输出：[0,0,1,1,2,5]

```java
class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums;
    }
    public void quickSort(int[] nums,int L,int R){
        if(L<R){
            int[] partation = sort(nums,L,R);
            quickSort(nums,L,partation[0]-1);
            quickSort(nums,partation[1]+1,R);
        }
    }

    public int[] sort(int[] nums,int L,int R){
        int less = L-1;
        int more = R;
        while(L<more){
            if(nums[L]==nums[R]){
                L++;
            }else if(nums[L]>nums[R]){
                swap(nums,L,--more);
            }else if(nums[L]<nums[R]){
                swap(nums,L++,++less);
            }   
        }
        swap(nums,more,R);
        return new int[]{less+1,more};
    }

    // 交换
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }
}
```

### [不熟悉-Leetcode165 比较版本号](https://leetcode-cn.com/problems/compare-version-numbers/)

给你两个版本号 version1 和 version2 ，请你比较它们。

版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。

比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。

返回规则如下：

如果 version1 > version2 返回 1，
如果 version1 < version2 返回 -1，
除此之外返回 0。


示例 1：

输入：version1 = "1.01", version2 = "1.001"
输出：0
解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"

> 解题思路：以.为分割符号 分割出数组，然后将其转换 为数字

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        // 对其进行分割
        String[] arr1  = version1.split("\\.");
        String[] arr2  = version2.split("\\.");
        // 对其遍历
        for(int i=0;i<Math.max(arr1.length,arr2.length);i++){
            // 得到对应的值
            int num1 = i<arr1.length?Integer.valueOf(arr1[i]):0;
            int num2 = i<arr2.length?Integer.valueOf(arr2[i]):0;
            // 比较
            if(num1>num2){
                return 1;
            }
            if(num1<num2){
                return -1;
            }
        }
        return 0;
    }
}
```

### [Leetcode_Offer_22 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.

> 倒数第k个节点

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode dummy  = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        for(int i=0;i<k;i++){
            fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

### [Leetcode1143 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)

给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

 

示例 1：

输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace" ，它的长度为 3 。

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        //LCS问题
        int len1 = text1.length();
        int len2 = text2.length();
        // 动态规划
        int[][] dp = new int[len1+1][len2+1];
        // 最大长度
        int max_len = 0;
        // 开始
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    // 相等的转移方程
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    // 不相等
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
                max_len = Math.max(max_len,dp[i][j]);
            }
        }
        return dp[len1][len2];
    }
}
```

### [Leetcode005 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

给你一个字符串 s，找到 s 中最长的回文子串。

 

示例 1：

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。

示例 2：

输入：s = "cbbd"
输出："bb"

> 解题思路：回文子串，中间扩散法

```java
class Solution {
    public String longestPalindrome(String s) {
        if(s.length()<=1){
            return s;
        }
        // 中心扩散法
        // 结果
        String res = "";
        for(int i=0;i<s.length();i++){
            String s1 = judge(s,i,i);
            String s2 = judge(s,i,i+1);
            // 得到一个最大的值
            String temp = s1.length()>s2.length()?s1:s2;
            res = res.length()>temp.length()?res:temp;
            
        }
        return res;
    }
    // 中心扩散法
    public String judge(String s,int i,int j){
        while(i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }
        return s.substring(i+1,j);
    }
}
```

### [Leetcode071 编辑距离](https://leetcode-cn.com/problems/edit-distance/)

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符


示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')

> 解题思路：插入、删除或替换字符的三个操作

```java
class Solution {
    public int minDistance(String word1, String word2) {
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        int len1 = arr1.length;
        int len2 = arr2.length;
        // 动态规划
        int[][] dp = new int[len1+1][len2+1];
        // 初始化
        for(int i=0;i<=len1;i++){
            dp[i][0] = i;
        }
        for(int j=0;j<=len2;j++){
            dp[0][j] = j;
        }
        // 转移方程
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(arr1[i-1]==arr2[j-1]){
                    // 转移
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[len1][len2];
    }
}
```

### [不熟悉-Leetcode_Offer09 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

 

示例 1：

输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]
示例 2：

输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]

> 解题思路：两个栈实现队列

```java
class CQueue {
    // 两个栈模拟队列
    Stack<Integer> s1;
    Stack<Integer> s2;
    public CQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    // 插入
    public void appendTail(int value) {
        s1.push(value);
    }   
    // 删除
    public int deleteHead() {
        // 加入一个判断
        if(s2.isEmpty()&&s1.isEmpty()){
            return -1;
        }
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

### [Leetcode053 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

示例 2：

输入：nums = [1]
输出：1

```java
class Solution {
    public int maxSubArray(int[] nums) {
        // 结果
        int res = nums[0];
        for(int i=1;i<nums.length;i++){
            nums[i] = Math.max(nums[i]+nums[i-1],nums[i]);
            res = Math.max(nums[i],res);
        }
        return res;
    }
}
```

### [Leetcode144 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

给你二叉树的根节点 `root` ，返回它节点值的 **前序** 遍历。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg)

```
输入：root = [1,null,2,3]
输出：[1,2,3]
```



```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        // 存储结果
        List<Integer> list = new ArrayList<>();
        // 栈
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // 开始
        while(!stack.isEmpty()){
            root = stack.pop();
            list.add(root.val);
            if(root.right!=null){
                stack.push(root.right);
            }
            if(root.left!=null){
                stack.push(root.left);
            }
        }
        return list;
    }
}
```

