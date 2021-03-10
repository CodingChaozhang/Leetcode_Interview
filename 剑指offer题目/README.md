### [1.剑指Offer 03 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 

> 解题思路：注意长度为n，数字为0-n-1之间。
>
> 采用的思路：原地排序+指针 for循环 + while指针

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        // 原地排序 + 指针
        for(int i=0;i<nums.length;i++){
            while(i!=nums[i]){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                // 交换
                int temp = nums[i];
                nums[i]   = nums[temp];
                nums[temp] = temp;
            }

        }
        return -1;
    }
}
```

### [2.剑指offer 04二维数组中的查找](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。

 

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length<1||matrix[0].length<1){
            return false;
        }
        // 思路就是从左上角开始找
        int row = matrix.length;
        int col = matrix[0].length;
        int start_r = 0;
        int start_c = col-1;
        int end_r = row-1;
        int end_c = 0;
        // 都要满足
        while(start_r<=end_r&&start_c>=end_c){
            if(matrix[start_r][start_c]==target){
                return true;
            }else if(matrix[start_r][start_c]>target){
                start_c--;
            }else if(matrix[start_r][start_c]<target){
                start_r++;
            }
        }
        return false;
    }
}
```

### [3.剑指Offer 05 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)

请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

 

示例 1：

输入：s = "We are happy."
输出："We%20are%20happy."

> 考察将其转换为数组，用stringbuilder来拼接

```java
class Solution {
    public String replaceSpace(String s) {
        // 用stringbuilder来拼接字符串
        // 将其转换为数组
        char[] arr = s.toCharArray();
        // 结果
        StringBuilder res = new StringBuilder();
        // 对其遍历
        for(int i=0;i<arr.length;i++){
            // 对其遍历
            if(arr[i]==' '){
                // 空格
                res.append("%20");
            }else{
                res.append(arr[i]);
            }
        }
        return res.toString();
    }
}
```

### [4.剑指Offer 06 从尾到头打印链表](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

示例 1：

输入：head = [1,3,2]
输出：[2,3,1]


限制：

0 <= 链表长度 <= 10000



> 解题思路：如果第一遍获取length的长度，也可以。当然直接利用栈更好

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
    public int[] reversePrint(ListNode head) {
        // 正序遍历，逆序出来，考察栈
        Stack<Integer> stack = new Stack<>();
        // 对其遍历
        while(head!=null){
            stack.push(head.val);
            head = head.next;
        }
        // 逆序出来
        int[] res = new int[stack.size()];
        for(int i=0;i<res.length;i++){
            res[i] = stack.pop();
        }
        return res;
    }
}
```

### [5.剑指Offer07  重建二叉树](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

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

> 解题思路：用HashMap来存储键值对有关中序遍历的；整体用递归的思路

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
    // 存储中序遍历
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 通过前序遍历和中序遍历恢复二叉树，需要递归
        for(int i=0;i<inorder.length;i++){
            hashMap.put(inorder[i],i);
        }
        return myTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    // 递归
    public TreeNode myTree(int[] preorder,int preorder_left,int preorder_right,int[] inorder,int inorder_left,int inorder_right){
        // 递归截止条件
        if(inorder_left>inorder_right){
            return null;
        }
        // 前序遍历的结点索引下标，值
        int preorder_root = preorder_left;
        int preorder_root_value = preorder[preorder_left];
        // 中序遍历的结点索引下标，值
        int inorder_root = hashMap.get(preorder_root_value);

        // 新树
        TreeNode root = new TreeNode(preorder_root_value);

        // 左子树的大小
        int left_size = inorder_root - inorder_left;
        // 构建
        root.left     = myTree(preorder,preorder_left+1,          preorder_left+left_size,inorder,inorder_left,  inorder_root-1);
        root.right    = myTree(preorder,preorder_left+left_size+1,inorder_right          ,inorder,inorder_root+1,inorder_right);
        
        // 返回值
        return root;

    }
}
```

### [6.剑指Offer09 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

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

> 用两个栈来模拟队列。
>
> 一个栈进；
>
> 另外一个栈出，没有元素就从另外一个进。

```java
class CQueue {
    // 用两个栈实现队列
    Stack<Integer> s1;
    Stack<Integer> s2;
    public CQueue() {
        // 初始化
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    // 入队列
    public void appendTail(int value) {
        // 直接入栈1
        s1.push(value);
    }
    // 出队列
    public int deleteHead() {
        // 看栈2是否有值
        if(s2.isEmpty()){
            //栈2为空，则从栈1借
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.isEmpty()?-1:s2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```

### [7.剑指Offer10-I 斐波那契数列](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 

示例 1：

输入：n = 2
输出：1
示例 2：

输入：n = 5
输出：5

> 解题思路：用三个变量即可实现。重点是每次得到的结果都需要取余。

```java
class Solution {
    public int fib(int n) {
        // 如果n小于2
        if(n<2){
            return n;
        }
        int f0 = 0;
        int f1 = 1;
        // 结果
        int f  = f0+f1;
        while(n>2){
            f0 = f1;
            f1 = f;
            f = f0+f1;
            f = f%(1000000007);
            n--;
        }
        return f;
    }
}
```

### [8.剑指Offer10 -II青蛙台阶问题](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

输入：n = 2
输出：2
示例 2：

输入：n = 7
输出：21
示例 3：

输入：n = 0
输出：1

> 解题思路：动态规划。用空间换时间。开辟数组来存储。

```java
class Solution {
    public int numWays(int n) {
        if(n<=1){
            return 1;
        }
       // 开辟
       int[] dp = new int[n+1];
       dp[0] = 1;
       dp[1] = 1;
       for(int i=2;i<n+1;i++){
           dp[i] = (dp[i-1]+dp[i-2])%(1000000007);
       }
       return dp[n];
    }
}
```

### [9.剑指Offer11 旋转数组的最小数字](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

示例 1：

输入：[3,4,5,1,2]
输出：1
示例 2：

输入：[2,2,2,0,1]
输出：0

> 注意，无target值，与nums[mid]比较。且数组中存在重复值，要针对重复值处理

```java
class Solution {
    public int minArray(int[] numbers) {
        // 之前有序，二分查找最小值
        int left = 0;
        int right = numbers.length-1;
        int mid =0;
        while(left<=right){
            mid = left + ((right-left)>>1);
            // 先看在哪个区间
            if(numbers[mid]>numbers[right]){
                // 左边有序
                left = mid+1;
            }else if(numbers[mid]<numbers[right]){
                // 右边有序
                right = mid;
            }else if(numbers[mid]==numbers[right]){
                // 存在重复值
                right--;
            }
        }
        return numbers[mid];
    }
}
```

> 若没有重复值 https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/

```java
class Solution {
    public void rotate(int[] nums, int k) {
        
        k = k % nums.length;
        // 解题思路：同时旋转
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    // 翻转
    public void reverse(int[] nums,int left,int right){
        while(left<right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }
}
```

> 在无重复值的旋转数组中找target https://leetcode-cn.com/problems/search-in-rotated-sorted-array/

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            if(nums[mid]==target){
                return mid;
            }
            // 判断哪边有序
            if(nums[mid]>=nums[left]){
                // 左边有序
                //查找
                if(nums[left]<=target&& target<nums[mid]){
                    // 在左边
                    right = mid - 1;
                }else{
                    // 在右边
                    left = mid + 1;
                }

            }else{
                // 右边有序
                if(nums[mid]<target&&target<=nums[nums.length-1]){
                    // 在右边
                    left = mid + 1;
                }else{
                    // 在左边
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

> 在上述的基础上，数组中有重复值 https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            // 有重复元素
            while(left<right&&nums[left]==nums[left+1]){
                left++;
            }
            while(left<right&&nums[right]==nums[right-1]){
                right--;
            }
            
            int mid = left + ((right-left)>>1);

            if(nums[mid]==target){
                return true;
            }
            // 哪边有序
            if(nums[mid]>=nums[0]){
                // 左边有序
                if(nums[left]<=target&&target<nums[mid]){
                    // 在左边
                    right = mid-1;
                }else{
                    left = mid+1;
                }

            }else{
                // 右边有序
                if(nums[mid]<target&&target<=nums[right]){
                    // 在右边
                    left = mid + 1;
                }else{
                    right = mid-1;
                }

            }

        }
        return false;
    }
}
```

> 经过k次旋转了，找寻其值，若存在多个元素找出左边界 https://leetcode-cn.com/problems/search-rotate-array-lcci/

```java
class Solution {
    public int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            // 求最小索引
            if(arr[left]==target){
                return left;
            }
            // 判断两次与target 一个是left一个mid
            int mid = left + ((right-left)>>1);
            // 中间值与target等了
            if(arr[mid]==target){
                right = mid;
            }else if(arr[mid]>arr[left]){
                // 左有序
                if(arr[left]<=target&&target<arr[mid]){
                    // 在左边
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else if(arr[mid]<arr[left]){
                // 右边有序
                if(arr[mid]<target&&target<=arr[right]){
                    left = mid + 1;
                }else{
                    right = mid-1;
                }
            }else{
                // 当中间与左边相等
                left = left + 1;
            }

        }
        return -1;
    }
}
```

### [10.剑指Offer12 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]

但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

 

示例 1：

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
示例 2：

输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false

> 解题思路：dfs的思路

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        // 解题思路 dfs
        char[] dict = word.toCharArray();
        // 记录被访问过
        boolean[][] visited = new boolean[board.length][board[0].length];
        // 对其遍历
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,visited,i,j,dict,0)){
                    return true;
                }
            }
        }
        return false;
    }

    // dfs
    public boolean dfs(char[][] board,boolean[][] visited,int i,int j, char[] dict,int index){
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || dict[index]!=board[i][j] || visited[i][j]){
            return false;
        }

        if(index==dict.length-1){
            return true;
        }

        // 当前值已访问
        visited[i][j] = true;
        // 访问
        boolean res = dfs(board,visited,i+1,j,dict,index+1) ||
                      dfs(board,visited,i-1,j,dict,index+1) ||
                      dfs(board,visited,i,j+1,dict,index+1) ||
                      dfs(board,visited,i,j-1,dict,index+1);
        visited[i][j] = false;
        return res;

    }
}
```

### [11.剑指Offer13 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：

输入：m = 3, n = 1, k = 0
输出：1

> 解题思路：用dfs算法

```java
class Solution {
    public int movingCount(int m, int n, int k) {
        // 解题思路：运动范围dfs 上下左右
        // 建立一个方格
        int[][] matrix = new int[m][n];
        // 已遍历到的
        boolean[][] visited = new boolean[m][n];
        return dfs(matrix,visited,0,0,k);
    }

    // 回溯
    public int dfs(int[][] matrix,boolean[][] visited,int i,int j,int k){
        // 递归结束 已遍历到 以及 题意
        if(i<0 || i>=matrix.length || j<0 || j>=matrix[0].length || visited[i][j] || (i/10+i%10 + j/10+j%10)>k){
            return 0;
        }
        // 已遍历到的便是遍历到了 不需要再返回
        visited[i][j] = true;
        int count  = 1;
        count += dfs(matrix,visited,i+1,j,k);
        count += dfs(matrix,visited,i-1,j,k);
        count += dfs(matrix,visited,i,j+1,k);
        count += dfs(matrix,visited,i,j-1,k);

        return count;
       
    }
}
```

> 类似题目 [Leetcode200岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)
>
> 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
>
> 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
>
> 此外，你可以假设该网格的四条边均被水包围。
>
>  
>
> 示例 1：
>
> 输入：grid = [
>   ["1","1","1","1","0"],
>   ["1","1","0","1","0"],
>   ["1","1","0","0","0"],
>   ["0","0","0","0","0"]
> ]
> 输出：1
> 示例 2：
>
> 输入：grid = [
>   ["1","1","0","0","0"],
>   ["1","1","0","0","0"],
>   ["0","0","1","0","0"],
>   ["0","0","0","1","1"]
> ]
> 输出：3

```java
class Solution {
    public int numIslands(char[][] grid) {
        // 用dfs即递归
        
        // 结果
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        // 对其遍历
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1'){
                    // 统计数量
                    count++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
    // 浸染
    public void dfs(char[][] grid,int i,int j){
        // 递归截止条件
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!='1'){
            return;
        }
        // 遍历过就将其更改其值
        grid[i][j] = '2';
        // 遍历其它边
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}
```

> 统计封闭岛屿的问题
>
> 有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。
>
> 我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
>
> 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
>
> 请返回封闭岛屿的数目。
>
> **示例 2：**
>
> ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/07/sample_4_1610.png)
>
> ```
> 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
> 输出：1
> ```

> 解题思路：如果从岛屿开始走，遇到边界就false。没遇到就true。

```java
class Solution {
    public int closedIsland(int[][] grid) {
        // dfs
        // 统计岛屿0
        int row = grid.length;
        int col = grid[0].length;
        // 结果
        int res = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                // 边界上的不算岛屿
                if(grid[i][j]==0){
                    if(dfs(grid,i,j)){
                        res++;
                    }
                }
            }
        }
        return res;
    }

    // 深度优先搜索
    public boolean dfs(int[][] grid,int i,int j){
        // 判断条件
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length){
            // 肯定不是孤岛
            return false;
        }
        
        // 未访问过的1水域和已访问过的
        if(grid[i][j]!=0){
            return true;
        }
        grid[i][j] = 2;
        boolean up = dfs(grid,i-1,j);
        boolean down = dfs(grid,i+1,j);
        boolean left = dfs(grid,i,j-1);
        boolean right = dfs(grid,i,j+1);
        return up&&down&&left&&right;      

    }
}
```

> 岛屿的最大面积
>
> 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
>
> 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
>
> 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
>
>  
>
> 示例 1:
>
> [[0,0,1,0,0,0,0,1,0,0,0,0,0],
>  [0,0,0,0,0,0,0,1,1,1,0,0,0],
>  [0,1,1,0,1,0,0,0,0,0,0,0,0],
>  [0,1,0,0,1,1,0,0,1,0,1,0,0],
>  [0,1,0,0,1,1,0,0,1,1,1,0,0],
>  [0,0,0,0,0,0,0,0,0,0,1,0,0],
>  [0,0,0,0,0,0,0,1,1,1,0,0,0],
>  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
> 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
>
> 示例 2:
>
> [[0,0,0,0,0,0,0,0]]
> 对于上面这个给定的矩阵, 返回 0。

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        // 求最大面积
        // 解题思路：用dfs
        int res = 0;
        // 已访问到的
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    // 访问相邻土地
                    res  = Math.max(res,dfs(grid,i,j)); 
                }
            }
        }
        return res;
    }

    // 对其求最大面积
    public int dfs(int[][] grid,int i,int j){
        // 截止条件 遇见水了或已经递归过的值
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!=1){
            return 0;
        }

        grid[i][j] = 2;
        int count = 1;
        count += dfs(grid,i+1,j);
        count += dfs(grid,i-1,j);
        count += dfs(grid,i,j-1);
        count += dfs(grid,i,j+1);
        return count;

    }
}
```

> 岛屿的周长
>
> 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
>
> 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
>
> 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
>
>  
>
> 示例 1：
>
> ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/island.png)
>
> 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
> 输出：16
> 解释：它的周长是上面图片中的 16 个黄色的边
> 示例 2：
>
> 输入：grid = [[1]]
> 输出：4
> 示例 3：
>
> 输入：grid = [[1,0]]
> 输出：4
>
>
> 

```java
class Solution {
    public int islandPerimeter(int[][] grid) {
        // 定义周长，是岛屿与湖的分界线
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 如果是岛屿
                if(grid[i][j]==1){
                    res += dfs(grid,visited,i,j);
                }
            }
        }
        return res;
    }
    //  dfs
    public int dfs(int[][] grid,boolean[][] visited,int i,int j){
        //带返回值
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==0){
            return 1;
        }
        // 定义已访问过的
        if(visited[i][j]){
            return 0;
        }
        visited[i][j] = true;
        // 其余走法
        int count = 0;
        count += dfs(grid,visited,i+1,j);
        count += dfs(grid,visited,i-1,j);
        count += dfs(grid,visited,i,j+1);
        count += dfs(grid,visited,i,j-1);

        return count;

    }
}
```

