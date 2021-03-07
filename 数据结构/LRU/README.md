## LRU

RU全称Least Recently Used**，也就是最近最少使用的意思，是一种内存管理算法，最早应用于Linux操作系统。

LRU算法基于一种假设：长期不被使用的数据，在未来被用到的几率也不大。因此，当数据所占内存达到一定阈值时，我们要移除掉最近最少被使用的数据。

LRU算法应用：可以在内存不够时，从哈希表移除一部分很少访问的用户。



LRU是什么？按照英文的直接原义就是Least Recently Used,最近最久未使用法，它是按照一个非常著名的计算机操作系统基础理论得来的：**最近使用的页面数据会在未来一段时期内仍然被使用,已经很久没有使用的页面很有可能在未来较长的一段时间内仍然不会被使用**。基于这个思想,会存在一种缓存淘汰机制，每次从内存中找到最久未使用的数据然后置换出来，从而存入新的数据！它的主要衡量指标是使用的时间，附加指标是使用的次数。在计算机中大量使用了这个机制，它的合理性在于优先筛选热点数据，所谓热点数据，就是最近最多使用的数据！因为，利用LRU我们可以解决很多实际开发中的问题，并且很符合业务场景。

## LRU的实现

> 用LinkedHashMap带方向的HashMap。

### 1 直接继承LinkedHashMap来实现

```java
public class Code_LRU {
	class LRUCache extends LinkedHashMap<Integer,Integer>{
		private int capacity;
		public LRUCache(int capacity) {
			super(capacity,0.75F,true);
			this.capacity = capacity;
		}
		
		public int get(int key) {
			return super.getOrDefault(key,-1);
		}
		public void put(int key,int value) {
			super.put(key, value);
		}
		
		// 重写淘汰策略
		protected boolean removeEldestEntry(Map.Entry<Integer, Integer> edlest) {
			return size()>capacity;
		}
	}
}

```

### 2 用双向链表+hashMap来实现

从HashMap中获取链表的结点：

- 存在，hashmap获取，更改位置；
- 不存在，hahsmap获取，返回；

从hashmap中放入一个值：

- 存在，则hashmap获取，之后更改值，更改位置；
- 不存在，则hashmap获取。新建一个值，添加到头部

维护一个size的容量，整个类维护一个size。若放入值，大于初始容量，删除尾部结点。

移动位置：包括两步，删除结点，以及添加结点

尾部结点: 找到伪结点之前的一个结点，删除，size减少即可。

```java
// 解题思路：双向链表+hashmap来实现
	class LRUCache_2{
		// 双向链表
		class DLinkedNode{
			int key;
			int value;
			DLinkedNode prev;
			DLinkedNode next;
			public DLinkedNode() {
				
			}
			public DLinkedNode(int key,int value) {
				this.key = key;
				this.value = value;
			}
		}
		
		// hashmap
		private HashMap<Integer,DLinkedNode> cache = new HashMap<>();
		// 定义私有变量
		private int size;
		private int capacity;
		private DLinkedNode head,tail;
		
		public LRUCache_2(int capacity) {
			this.size = 0;
			this.capacity = capacity;
			// 生成伪头部和伪尾部结点
			head = new DLinkedNode();
			tail = new DLinkedNode();
			head.next = tail;
			tail.prev = head;
		}
		
		//获得值
		public int get(int key) {
			DLinkedNode node = cache.get(key);
			if(node==null) {
				return -1;
			}else {
				// 如果key存在，哈希表定位 结点移动到头部
				moveToHead(node);
				return node.value;
			}
		}
		
		// 放入值的操作
		public void put(int key,int value) {
			DLinkedNode node = cache.get(key);
			// 如果key不存在
			if(node==null) {
				// 新创建一个结点
				DLinkedNode newNode = new DLinkedNode(key,value);
				// 添加
				cache.put(key,newNode);
				// 添加到头部
				addToHead(newNode);
				++size;
				// 判断容量
				if(size>capacity) {
					// 称号出容量
					DLinkedNode tail = removeTail();
					// 删除hash表中对应的项
					cache.remove(tail.key);
					--size;
				}
				
			}else {
				node.value = value;
				// 移动
				moveToHead(node);
			}
		}
		
		// 添加结点的操作
		private void addToHead(DLinkedNode node) {
			node.prev = head;
			node.next = head.next;
			head.next.prev = node;
			head.next = node;
		}
		
		// 删除结点
		private void removeNode(DLinkedNode node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		
		// 移动到头结点 删结点 填充结点
		private void moveToHead(DLinkedNode node) {
			removeNode(node);
			addToHead(node);
		}
		// 删除尾部结点
		private DLinkedNode removeTail() {
			DLinkedNode res = tail.prev;
			removeNode(res);
			return res;
		}
	}

```

