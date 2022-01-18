# 单调队列 

**保证队首到队尾是单调递增的**



【单调队列】就是【队列】，只是使用了一点巧妙的方法，使得**队列中的元素全都是单调递增(或递减)的。**



【单调栈】主要解决 Next Great Number这一类算法问题，而【单调队列】这个数据结构可以解决滑动窗口相关的问题。













































一个普通的队列一定有这两个操作：



```java
class Queue {
    // enqueue 操作，在队尾加入元素 n
    void push(int n);
    // dequeue 操作，删除队头元素
    void pop();
}
```



一个「单调队列」的操作也差不多：



```java
class MonotonicQueue {
    // 在队尾添加元素 n
    void push(int n);
    // 返回当前队列中的最大值
    int max();
    // 队头元素如果是 n，删除它
    void pop(int n);
}
```

这道「滑动窗口」问题的解答框架搭出来：

```java
int[] maxSlidingWindow(int[] nums, int k) {
    MonotonicQueue window = new MonotonicQueue();
    List<Integer> res = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
        if (i < k - 1) {
            //先把窗口的前 k - 1 填满
            window.push(nums[i]);
        } else {
            // 窗口开始向前滑动
            // 移入新元素
            window.push(nums[i]);
            // 将当前窗口中的最大元素记入结果
            res.add(window.max());
            // 移出最后的元素
            window.pop(nums[i - k + 1]);
        }
    }
    // 将 List 类型转化成 int[] 数组作为返回值
    int[] arr = new int[res.size()];
    for (int i = 0; i < res.size(); i++) {
        arr[i] = res.get(i);
    }
    return arr;
}
```

**单调队列的实现**

「单调队列」的核心思路和「单调栈」类似，`push` 方法依然在队尾添加元素，但是要把前面比自己小的元素都删掉：



```java
class MonotonicQueue {// 双链表，支持头部和尾部增删元素private LinkedList<Integer> q = new LinkedList<>();
public void push(int n) {    // 将前面小于自己的元素都删除    while (!q.isEmpty() && q.getLast() < n) {        q.pollLast();    }    q.addLast(n);}
```

如果每个元素被加入时都这样操作，最终单调队列中的元素大小就会保持一个**单调递减**的顺序，因此我们的 `max` 方法可以可以这样写：



```java
public int max() {
    // 队头的元素肯定是最大的
    return q.getFirst();
}
```

`pop` 方法在队头删除元素 `n`，也很好写：



```java
public void pop(int n) {
    if (n == q.getFirst()) {
        q.pollFirst();
    }
}
```

之所以要判断 `data.front() == n`，是因为我们想删除的队头元素 `n` 可能已经被「压扁」了，可能已经不存在了，所以这时候就不用删除了：

### Leetcode239【滑动窗口最大值】



```java
// 单调队列
class MonitorQueue{
    // 用队列来实现
    private LinkedList<Integer> q = new LinkedList<>();
    // 队尾添加元素
    public void push(int n){
        // 查看该值与队列的值大小比较
        while(!q.isEmpty()&&n>q.getLast()){
            // 排出来
            q.pollLast();
        }
        q.addLast(n);
    }
    // 取得最大值
    public int getmax(){
        return q.getFirst();
    }

    // 删除元素
    public void pop(int n){
        if(n==q.getFirst()){
            q.pollFirst();
        }
        // 其余情况已经被push去掉了
    }
}
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonitorQueue window = new MonitorQueue();

       // 滑动窗口解题
       // 结果存储
       List<Integer> res = new ArrayList<>();
       // 开始
       for(int i=0;i<nums.length;i++){
           // 先把窗口的前k-1装满
           if(i<k-1){
               window.push(nums[i]);
           }else{
               // 装入
               window.push(nums[i]);
               res.add(window.getmax());
               // 排出去
               window.pop(nums[i-k+1]);
           }
       }
       // 将list转为int[]
       int[] arr = new int[res.size()];
       for(int i=0;i<res.size();i++){
           arr[i] = res.get(i);
       }
       return arr;

    }
}


```



### 双端队列来实现单调队列

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组
        int[] result = new int[nums.length-k+1];
        // 遍历nums数组
        for(int i = 0;i < nums.length;i++){
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            if(queue.peek() <= i-k){
                queue.poll();   
            } 
            // 当窗口长度为k时 保存当前窗口中最大值
            if(i+1 >= k){
                result[i+1-k] = nums[queue.peek()];
            }
        }
        return result;
    }
}


```

### 优先级队列也可以

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}

```



###  b.切蛋糕

维护一个下标位置递增，对应前缀和sum也递增的单调队列。