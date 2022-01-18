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

### [12.剑指offer14-I 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-lcof/)

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1

示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36

> 动态规划解题，绳子长度为，之后第一刀为从2到第一个之后转移方程即可

```java
class Solution {
    public int cuttingRope(int n) {
        // 剪绳子
        // 动态规划
        int[] dp = new int[n+1];
        //初始化
        dp[2] = 1;
        // 别的长度
        for(int i=3;i<n;i++){
            // 第一刀剪1没意义
            for(int j=2;j<i;j++){
                // 当前值的=维护当前 或者(剩下i-j不动)
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));
            }
        }
        return dp[n];
    }
}
```

> 剪绳子的数论：
>
> 任何数都可以由2和3组成，2和3组成的乘积最大；

```java
class Solution {
    public int cuttingRope(int n) {
        if(n<4){
            return n-1;
        }
        // 大于4
        int res = 1;
        while(n>4){
            res*=3;
            n-=3;
        }
        return res*(n-1);
    }
}
```

### [13.剑指offer14-II 剪绳子](https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/submissions/)

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

 

示例 1：

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1

示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36

> 解题思路：动态规划或者数论
>
> 取余用long

```java
class Solution {
    public int cuttingRope(int n) {
        if(n<4){
            return n-1;
        }
        long res = 1;
        while(n>4){
            res = (res*3)%1000000007;
            n-=3;
        }
        res = (res*n)%1000000007;
        return (int)res;
    }
}
```

### [14.剑指offer15 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

 

示例 1：

输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        // n与n-1进行与运算，即可 
        while(n!=0){
            count++;
            n &= (n-1);
        }
        return count;
    }
}
```

### [15.剑指offer16 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。

 

示例 1：

输入：x = 2.00000, n = 10
输出：1024.00000
示例 2：

输入：x = 2.10000, n = 3
输出：9.26100

示例 3：

输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25

> 解题思路：快速幂，记得n转换为long。原来的数基础上相乘

```java
class Solution {
    public double myPow(double x, int n) {
        long power = n;
        // 计算数值的整数次方
        double res = 1;
        // 可能有表示整数
        boolean flag = true;
        if(n<0){
            flag = false;
            power = (-1)*(n);
        }
        while(power!=0){
            if(power%2!=0){
                res *= x;
            }
            power = power/2;
            x = x*x;
        }
        return flag?res:(1/res);
    }
}
```

### [16.剑指offer17 打印从1到最大的n位数](https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

示例 1:

输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]


说明：

用返回一个整数列表来代替打印
n 为正整数

> 解题思路：位数

```java
class Solution {
    public int[] printNumbers(int n) {
        int sum = (int)Math.pow(10,n);
        sum = sum-1;
        // 开始存储
        int[] arr = new int[sum];
        for(int i=1;i<=sum;i++){
            arr[i-1] = i;
        }
        return arr;
    }
}
```

### [17.剑指Offer18 删除链表的结点](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

注意：此题对比原题有改动

示例 1:

输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
示例 2:

输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

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
    public ListNode deleteNode(ListNode head, int val) {
        // 哑结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 指针
        ListNode node = head;
        ListNode pre = dummy;
        while(node!=null){
            if(node.val==val){
                // 找到了
                break;
            }
            pre = node;
            node = node.next;
        }
        pre.next = node.next;
        return dummy.next;
    }
}
```

### [18.剑指Offer19.正则表达式匹配](https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)

请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

示例 1:

输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:

输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
示例 3:

输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

> 动态规划解决正则表达式匹配

```java
class Solution {
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray(), ptr = p.toCharArray();
	    	// 动态规划
	    	boolean[][] dp = new boolean[s.length()+1][p.length()+1];
	    	// 初始化
	    	dp[0][0] = true;
	    	// 开始遍历 j为空字符串无意义 p[j-1]表示的是状态dp[i][j]下的p的最后一个字符
	    	for(int i=0;i<=str.length;i++) {
	    		for(int j=1;j<=ptr.length;j++) {
	    			// 判断p的 字符串是否为*
	    			if(ptr[j-1]!='*') {
	    				//再次进行判断
	    				if(i>0&&(str[i-1]==ptr[j-1]||ptr[j-1]=='.')) {
	    					dp[i][j] = dp[i-1][j-1];
	    				}
	    			}else {
	    				// 0次或多次
	    				if(j>1) {
	    					// 不看即0次
	    					dp[i][j] |= dp[i][j-2];
	    				}
	    				
	    				//看了多次
	    				if(i>0&&j>1&&(str[i-1]==ptr[j-2]||ptr[j-2]=='.')) {
	    					dp[i][j] |= dp[i-1][j];
	    				}
	    			}
	    		}
	    	}
	    	
	    	return dp[s.length()][p.length()];
    }
}
```

### [19.剑指Offer20 表示数字的字符串](https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。

> 优先状态机，5种状态
>
> 数字0-9  标点符号.  e/E   正负号  空格
>
> 针对每种情况单独处理即可。空格去掉

```java
class Solution {
    public boolean isNumber(String s) {
        // 表示数值的字符串
        // 优先状态机的字符串5个状态：数字0-9 标点.  e   正负号  空格
        // 空格可以先去掉
        char[] str = s.trim().toCharArray();
        // 标记情况数字 标点 e号
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        // 开始判断
        for(int i=0;i<str.length;i++){
            // 数字出现
            if(str[i]>='0'&&str[i]<='9'){
                numSeen = true;
            }else if(str[i]=='.'){
                // .之前不能出现.或者e
                if(dotSeen || eSeen){
                    return false;
                }
                dotSeen = true;
            }else if(str[i]=='e' || str[i]=='E'){
                // e之前不能出现e，且必须出现数字
                if(eSeen || !numSeen){
                    return false;
                }
                eSeen = true;
                // 出现之后
                numSeen = false;
            }else if(str[i]=='-' || str[i]=='+'){
                // +出现在0位置或者e/E的后面第一个位置才是合法的
                if(i!=0&&str[i-1]!='e'&&str[i-1]!='E'){
                    return false;
                }
            }else{
                // 其他不合法
                return false;
            }
        }
        return numSeen;
    }
}
```

### [20.剑指Offer21 调整数组顺序使奇数位于偶数前面](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

 

示例：

输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。

> 荷兰国旗问题

```java
class Solution {
    public int[] exchange(int[] nums) {
        // 荷兰国旗问题
        int less = -1;
        int L = 0;
        int more = nums.length;
        while(L<more){
            if(nums[L]%2==1){
                // 奇数 交换
                swap(nums,++less,L++);
            }else{
                // 偶数
                L++;
            }
        }
        return nums;
    }
    // 交换
    public void swap(int[] nums,int l,int r){
        int temp = nums[l];
        nums[l]  = nums[r];
        nums[r]  = temp;
    }
}
```

### [21.剑指offer22 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

 

示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.

> 解题思路

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
       
        ListNode fast = head;
        for(int i=1;i<k;i++){
            fast = fast.next;
        }

        ListNode slow = head;
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }   
}
```

### [22.剑指offer24 反转链表](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

 

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL


限制：

0 <= 节点个数 <= 5000

> 反转链表

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

### [23.剑指Offer25 合并两个排序的链表](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

限制：

0 <= 链表长度 <= 1000

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 合并两个有序的链表
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                node.next = l1;
                l1 = l1.next;
                node = node.next;
            }else if(l1.val>l2.val){
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            }
        }
        // 还没走完
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

### 不熟悉-[24.剑指Offer26 树的子结构](https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/)

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

例如:
给定的树 A:

 3
/ \

   4   5
  / \
 1   2
给定的树 B：

   4 
  /
 1
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。

示例 1：

输入：A = [1,2,3], B = [3,1]
输出：false

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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 递归截止条件
        if(A==null&&B==null){
            return true;
        }
        if(A==null||B==null){
            return false;
        }
        
        // 判断当前节点
        if(A.val==B.val&&check(A.left,B.left)&&check(A.right,B.right)){
            return true;
        }
        return isSubStructure(A.left,B) | isSubStructure(A.right,B);
    }
    // 先写一个递归的判断
    public boolean check(TreeNode A,TreeNode B){
        // 递归截止条件
        if(B==null){
            return true;
        }
        // b还没有结束a结束了
        if(A==null){
            return false;
        }
        // 判断值
        if(A.val==B.val&&check(A.left,B.left)&&check(A.right,B.right)){
            return true;
        }else{
            return false;
        }
    }
}
```

### 不熟悉-25.剑指Offer27 二叉树的镜像

请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：

 4

   /   \
  2     7
 / \   / \
1   3 6   9
镜像输出：

 4

   /   \
  7     2
 / \   / \
9   6 3   1

 

示例 1：

输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]

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
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null){
            return root;
        }
        TreeNode temp = root.left;
        root.left     = root.right;
        root.right    = temp;

        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }
}
```

### 不熟悉-26.剑指Offer28 对称的二叉树

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3

 

示例 1：

输入：root = [1,2,2,3,4,4,3]
输出：true

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
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        // 检查左边和右边分别为两棵树
        return check(root.left,root.right);
    }
    public boolean check(TreeNode A,TreeNode B){
        // 递归截止条件
        if(A==null&&B==null){
            return true;
        }
        if(A==null||B==null){
            return false;
        }
        // 判断值
        if(A.val!=B.val){
            return false;
        }
        // 继续判断
        return check(A.left,B.right)&&check(A.right,B.left);

    }
}
```

### 27.剑指Offer29 顺时针打印矩阵

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]

> 解题思路：逻辑

```java
class Solution {
    List<Integer> list = new ArrayList<>();
    public int[] spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        if(rows==0){
            return new int[]{};
        }
        int cols = matrix[0].length;
        

        int start_row = 0;
        int start_col = 0;
        int end_row = rows-1;
        int end_col = cols-1;
        // 开始
        while(start_row<=end_row&&start_col<=end_col){
            // 调用函数
            print(matrix,start_row++,start_col++,end_row--,end_col--);
        }
        // 转为array数组
        int[] res = new int[rows*cols];
        for(int i=0;i<res.length;i++){
            res[i] = list.get(i);
        }
        return res;
    }

    // 打印
    public void print(int[][] matrix,int start_row,int start_col,int end_row,int end_col){
        // 打印
        for(int i=start_col;i<=end_col;i++){
            list.add(matrix[start_row][i]);
        }
  
        for(int i=start_row+1;i<=end_row;i++){
            list.add(matrix[i][end_col]);
        }

        for(int i=end_col-1;i>=start_col;i--){
            list.add(matrix[end_row][i]);
        }

        for(int i=end_row-1;i>start_row;i--){
            list.add(matrix[i][start_col]);
        }

    }
}
```

### 28. 剑指Offer30 包含min函数的栈

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

    Stack<Integer> s_data;
    Stack<Integer> s_min;
    /** initialize your data structure here. */
    public MinStack() {
        s_data = new Stack<>();
        s_min  = new Stack<>();
    }
    
    public void push(int x) {
        s_data.push(x);
        // 判断
        if(s_min.isEmpty() || s_min.peek()>x){
            s_min.push(x);
        }else{
            s_min.push(s_min.peek());
        }
    }
    
    public void pop() {
        s_data.pop();
        s_min.pop();
    }
    
    public int top() {
        return s_data.peek();
    }
    
    public int min() {
        return s_min.peek();
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

### 29.剑指Offer31 栈的压入、弹出序列

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

 

示例 1：

输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
示例 2：

输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。

>- 初始化： 辅助栈 stackstack ，弹出序列的索引 ii ；
>- 遍历压栈序列： 各元素记为 numnum ；
>  元素 numnum 入栈；
>- 循环出栈：若 stackstack 的栈顶元素 == 弹出序列元素 popped[i]popped[i] ，则执行出栈与 i++i++ ；
>  返回值： 若 stackstack 为空，则此弹出序列合法。

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 用栈
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num:pushed){
            stack.push(num);
            // 如果相等就排
            while(!stack.isEmpty()&&stack.peek()==popped[i]){
                i++;
                stack.pop();
            }
        }
        // 最后判断
        return stack.isEmpty();
    }
}
```

### 30.剑指Offer32-I 从上到下打印二叉树

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

 

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回：

[3,9,20,15,7]

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
    public int[] levelOrder(TreeNode root) {
        if(root==null){
            return new int[]{};
        }
        // 层序遍历
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            list.add(root.val);

            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }
        }
        int[] res = new int[list.size()];
        for(int i=0;i<list.size();i++){
            res[i] = list.get(i);
        }
        return res;
    }
}
```

### 31.剑指Offer32-II 从上到下打印二叉树II

从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

 

例如:
给定二叉树: [3,9,20,null,null,15,7],

3

   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 结果
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 层序遍历
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
                levelSize = queue.size();
                res.add(new ArrayList<>(list));
                list = new ArrayList<>();
            }
        }
        return res;
    }
}
```

### 32.剑指Offer32-III 从上到下打印二叉树III


请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

 

例如:
给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其层次遍历结果：

```
[
  [3],
  [20,9],
  [15,7]
]
```

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        int index = 1;
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
                if(index%2==0){
                    Collections.reverse(list);
                }
                levelSize = queue.size();
                res.add(new ArrayList<>(list));
                list = new ArrayList<>();
                index++;
            }
        }
        return res;

    }
}
```

### (不熟悉)33.剑指Offer33-二叉搜索树的后序遍历

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        // 中序遍历的结果是升序，而后序遍历的结果是左右根
        // 二叉搜索树的左边比根节点小 右边比根节点大
        return recur(postorder,0,postorder.length-1);
    }
    public boolean recur(int[] postorder,int l,int r){
        if(l>=r){
            return true;
        }
        int i = l;
        // 左边比其小
        while(postorder[i]<postorder[r]){
            i++;
        }
        // 记录该点
        int m = i;
        // 并接着走
        while(postorder[i]>postorder[r]){
            i++;
        }
        // 判断
        return i==r&&recur(postorder,l,m-1)&&recur(postorder,m,r-1);

    }
}
```

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        // 单调栈
        Deque<Integer> stack = new  LinkedList<>();
        int preElem = Integer.MAX_VALUE;
        // 逆向遍历
        for(int i=postorder.length-1;i>=0;i--){
            if(postorder[i]>preElem){
                return false;
            }
            while(!stack.isEmpty()&&postorder[i]<stack.peek()){
                preElem = stack.pop();
            }
            // 入栈
            stack.push(postorder[i]);
        }
        return true;
    }
}
```

### （不熟悉）34.剑指Offer34 二叉树中和为某一值的路径

输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。

 

示例:
给定如下二叉树，以及目标和 target = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

> 解题思路：用回溯法 
>
> 先序遍历就可以解题：从树的根节点到叶子结点的路径

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
    // 从根节点到叶子结点的路径
    // 记录总的结果
    List<List<Integer>> res = new ArrayList<>();
    // 每一条路径的结果
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root,target);
        return res;
    }
    // 回溯法
    public void dfs(TreeNode root,int target){
        // 先序遍历
        if(root==null){
            return;
        }
        path.add(root.val);
        target -= root.val;
         // 添加判断是否到了
        if(target==0&&root.left==null&&root.right==null){
            res.add(new ArrayList<>(path));
        }

        dfs(root.left,target);
        dfs(root.right,target);

        path.remove(path.size()-1);
    }
}
```

### （不熟悉）35.剑指Offer35 复杂链表的复制

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png)

```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        // 复制链表用HashMap
        HashMap<Node,Node> hashMap = new HashMap<>();
        // 先挨个复制
        Node node = head;
        while(node!=null){
            hashMap.put(node,new Node(node.val));
            node = node.next;
        }
        // 接着
        node = head;
        while(node!=null){
            hashMap.get(node).next = hashMap.get(node.next);
            hashMap.get(node).random = hashMap.get(node.random);
            node = node.next;
        }
        return hashMap.get(head);
    }
}
```

### （不熟悉）36.剑指Offer36 二叉搜索树与双向链表

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

 

为了让您更好地理解问题，以下面的二叉搜索树为例：

 ![img](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png)



 

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

 ![img](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png)



```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    Node head,pre;
    public Node treeToDoublyList(Node root) {
        if(root==null){
            return null;
        }
        // 先给head和pre赋值
        dfs(root);
        head.left = pre;
        pre.right = head;

        return head;
    }
    public void dfs(Node cur){
        if(cur==null){
            return;
        }
        dfs(cur.left);
        if(pre==null){
            head = cur;
        }else{
            pre.right = cur;
        }
        cur.left = pre;
        pre = cur;

        dfs(cur.right);
    }
}
```

### （不熟悉）37.剑指Offer37 序列化二叉树

请实现两个函数，分别用来序列化和反序列化二叉树。

示例: 

你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"

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
public class Codec {

    // Encodes a tree to a single string.
    // 序列化：将二叉树转换为字符串
    public String serialize(TreeNode root) {
        if(root==null){
            return " ";
        }
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            if(root!=null){
                res.append(root.val+",");
                // 入队列
                queue.offer(root.left);
                queue.offer(root.right);
            }else{
                res.append("null,");
            }
        }
        return res.toString().substring(0,res.length()-1);
    }

    // Decodes your encoded data to tree.
    // 反序列化 将字符串转为二叉树
    public TreeNode deserialize(String data) {
        if(data==" "){
            return null;
        }
        String[] arr = data.split(",");
        int index = 0;
        // 建立根节点
        TreeNode root = new TreeNode(Integer.valueOf(arr[index]));
        index++;
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if(!"null".equals(arr[index])){
                //左子树
                cur.left = new TreeNode(Integer.valueOf(arr[index]));
                //入队列
                queue.offer(cur.left);
            }
            index++;
            if(!"null".equals(arr[index])){
                // 右子树
                cur.right = new TreeNode(Integer.valueOf(arr[index]));
                // 入队列
                queue.offer(cur.right);
            }
            index++;
        }
        return root;



    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

### 38.剑指Offer38 字符串的排列

输入一个字符串，打印出该字符串中字符的所有排列。

 

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 

示例:

输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]

> 回溯解法：解题全排列的问题

```java
class Solution {
    // 结果存储
    Set<String> res = new HashSet<>();
    public String[] permutation(String s) {
        // 将其转换为数组
        char[] arr = s.toCharArray();
        // 防止重复访问
        boolean[] visited = new boolean[arr.length];
        // 回溯,临时存一个结果
        dfs(arr,visited,"");
        //真实结果
        String[] realRes = new String[res.size()];
        int index = 0;
        for(String value:res){
            realRes[index++] = value;
        }
        return realRes;
    }

    // 回溯
    public void dfs(char[] arr,boolean[] visited,String temp){
        //判断是否满足条件
        if(temp.length()==arr.length){
            res.add(temp);
            return;
        }
        // 回溯
        for(int i=0;i<arr.length;i++){
            // 避免重复访问
            if(visited[i]){
                continue;
            }


            // 添加
            visited[i] = true;

            // 继续回溯
            dfs(arr,visited,temp+arr[i]);

            visited[i] = false;

        }

    }

}
```

### 39.剑指Offer39 数组中出现次数超过一半的数字

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

 

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1:

输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2

> 摩尔投票法来找众数

```java
class Solution {
    public int majorityElement(int[] nums) {
        int candda = nums[0];
        int count = 1;
        for(int i=1;i<nums.length;i++){
            if(count==0){
                candda = nums[i];
            }
            count += nums[i]==candda?1:-1;
        }
        return candda;
    }
}
```

### 40. 剑指Offer40 最小的k个数

输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

 

示例 1：

输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
示例 2：

输入：arr = [0,1,2,1], k = 1
输出：[0]

> 排序，直接找

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        // 最小的k个数
        Arrays.sort(arr);
        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = arr[i];
        }
        return res;
    }
}
```

> 优先级队列

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        // 优先级队列
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->(b-a));
        // 开始往里面放
        for(int i=0;i<k;i++){
            queue.offer(arr[i]);
        }
        // 接着走
        for(int i=k;i<arr.length;i++){
            //判断
            if(!queue.isEmpty()&&queue.peek()>arr[i]){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        // 结果
        int[] res = new int[k];
        int index = 0;
        for(int num:queue){
            res[index++] = num;
        }
        return res;
    }
}
```

### 41. 剑指Offer41 数据流中的中位数

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例 1：

输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]

> 解题思路：大顶堆和小顶堆的解题思路

```java
class MedianFinder {
    // 寻找数据流中的中位数
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> big  ;
    /** initialize your data structure here. */
    public MedianFinder() {
        small = new PriorityQueue<>((a,b)->(b-a));
        big   = new PriorityQueue<>((a,b)->(a-b));
    }
    
    public void addNum(int num) {
        // 添加
        if(small.size()>big.size()){
            small.offer(num);
            big.offer(small.poll());
        }else{
            big.offer(num);
            small.offer(big.poll());
        }
    }
    
    public double findMedian() {
        if(small.size()>big.size()){
            return small.peek();
        }else if(small.size()<big.size()){
            return big.peek();
        }else{
            return (double)(small.peek()+big.peek())/2;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

### 42. 剑指Offer42 连续子数组的最大和

输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

 

示例1:

输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

> 解题思路：动态规划解题思路

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        // 遍历
        for(int i=1;i<nums.length;i++){
            nums[i] = Math.max(nums[i],nums[i-1]+nums[i]);
            res     = Math.max(res,nums[i]);
        }
        return res;
    }
}
```

### （不会）43.剑指Offer43 1~n整数中1出现的次数

输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

 

示例 1：

输入：n = 12
输出：5
示例 2：

输入：n = 13
输出：6

```java
class Solution {
    public int countDigitOne(int n) {
        int digit = 1;
        int high = n/10;
        int low   = 0;
        int cur = n%10;
        int res = 0;
        while(high!=0 || cur!=0){
            if(cur==0){
                res += high*digit;
            }else if(cur==1){
                res += high*digit + low+1;
            }else{
                res += (high+1)*digit;
            }

            low += cur*digit;
            cur = high%10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
```

### （不会）44.剑指Offer44 数字序列中某一位的数字

数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

请写一个函数，求任意第n位对应的数字。

 

示例 1：

输入：n = 3
输出：3

示例 2：

输入：n = 11
输出：0

```java
class Solution {
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.

    }
}
```

### （不熟悉）45.剑指Offer45 把数组排成最小的数

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

 

示例 1:

输入: [10,2]
输出: "102"

示例 2:

输入: [3,30,34,5,9]
输出: "3033459"

> 解题思路：自定义排序
>
> 其中最重要的函数是 compareTo()

```java
class Solution {
    public String minNumber(int[] nums) {
        // 将int数组变为string数组
        int len = nums.length;
        String[] strs = new String[len];
        for(int i=0;i<len;i++){
            strs[i] = String.valueOf(nums[i]);
        }
        // 自定义排序 数组的比较是compareTo
        Arrays.sort(strs,(a,b)->((a+b).compareTo(b+a)));
        // 结果
        StringBuilder res = new StringBuilder();
        for(String str:strs){
            res.append(str);
        }
        return res.toString();
    }
}
```

### （不熟悉） 46.剑指Offer46 把数字翻译成字符串

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

 

示例 1:

输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"

> 动态规划，判断条件
>
> 什么时候能+，什么时候只是前一个而已

```java
class Solution {
    public int translateNum(int num) {
        // 将这个数字转换为string
        String str = String.valueOf(num);
        // 动态规划的解题思路
        int[] dp = new int[str.length()+1];
        //初始化
        dp[0] = 1;
        dp[1] = 1;
        // 转移方程
        for(int i=2;i<=str.length();i++){
            String tempStr = str.substring(i-2,i);
            if(tempStr.compareTo("10")>=0&&tempStr.compareTo("25")<=0){
                dp[i] = dp[i-1] + dp[i-2];
            }else{
                dp[i] = dp[i-1];
            }
        }
        return dp[str.length()];
    }
}
```

### 47.剑指Offer47 礼物的最大价值

在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 

示例 1:

输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

> 解题思路：动态规划

```java
class Solution {
    public int maxValue(int[][] grid) {
        // 从左上角到右下角
        // 向右或者向下移动一格
        int m = grid.length;
        int n = grid[0].length;
        // 动态规划的思路
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = grid[0][0];
        for(int i=1;i<m;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j=1;j<n;j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];  
        }
        // 继续走
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                // 转移方程
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
```

### 48.剑指Offer48 最长不含补充字符的子字符串

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

 

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

> 解题思路：用HashMap，滑动窗口

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 将字符串转为数组
        char[] arr = s.toCharArray();
        int len = arr.length;
        // hashMap存储字符以及索引index
        HashMap<Character,Integer> dict = new HashMap<>();
        // 滑动窗口的解题思路
        int left = 0;
        int right = 0;
        // 结果的存储
        int maxLen = 0;
        while(right<len){
            // 判断是否包含
            if(dict.containsKey(arr[right])){
                left = Math.max(dict.get(arr[right])+1,left);
            }
            dict.put(arr[right],right);
            maxLen = Math.max(maxLen,right-left+1);
            
            right++;
        }
        return maxLen;
    }
}
```

### （不熟悉）49.剑指Offer49 丑数

我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

 

示例:

输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。

> 解题思路：动态规划

```java
class Solution {
    public int nthUglyNumber(int n) {
        // 求出第n个丑数
        // 动态规划
        int[] dp = new int[n];
        // 初始化
        dp[0]  = 1;
        int a = 0, b = 0, c = 0;
        // 开始放
        for(int i=1;i<n;i++){
            int n2 = dp[a]*2;
            int n3 = dp[b]*3;
            int n5 = dp[c]*5;
            dp[i] = Math.min(Math.min(n2,n3),n5);
            // 转移
            if(dp[i]==n2){
                a++;
            }
            if(dp[i]==n3){
                b++;
            }
            if(dp[i]==n5){
                c++;
            }
        }
        return dp[n-1];
    }
}
```

### 50.第一个只出现一次的字符

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

示例:

s = "abaccdeff"
返回 "b"

s = "" 
返回 " "

```java
class Solution {
    public char firstUniqChar(String s) {
        // 遍历一遍用hashmap来存储
        char[] arr = s.toCharArray();
        HashMap<Character,Integer> dict = new HashMap<>();
        for(int i=0;i<arr.length;i++){
            dict.put(arr[i],dict.getOrDefault(arr[i],0)+1);
        }
        // 再次遍历一遍
        for(int i=0;i<arr.length;i++){
            if(dict.get(arr[i])==1){
                return arr[i];
            }
        }

        return ' ';
    }
}
```

### （不熟悉）51.剑指Offer51 数组中的逆序对

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 

示例 1:

输入: [7,5,6,4]
输出: 5

```java
class Solution {
    int res = 0;
    public int reversePairs(int[] nums) {
        // 解题思路 归并排序
        mergeSort(nums,0,nums.length-1);
        return res;
    }
    // 归并排序
    public void mergeSort(int[] nums,int l,int r){
        if(l>=r){
            return;
        }
        int mid = l + ((r-l)>>1);
        mergeSort(nums,l,mid);
        mergeSort(nums,mid+1,r);
        merge(nums,l,mid,r);
    }
    // 归并
    public void merge(int[] nums,int l,int mid,int r){
        // 新的数组
        int[] temp = new int[r-l+1];
        // 开始合并比较
        int i1 = l;
        int i2 = mid+1;
        int i = 0;
        while(i1<=mid&&i2<=r){
            if(nums[i1]>nums[i2]){
                temp[i++] = nums[i2++];
                // 记录逆序对
                res += (mid-i1+1);
            }else{
                temp[i++] = nums[i1++];
            }
        }
        while(i1<=mid){
            temp[i++] = nums[i1++];
        }
        while(i2<=r){
            temp[i++] = nums[i2++];
        }
        // 赋值回去
        for(int j=0;j<temp.length;j++){
            nums[l+j] = temp[j];
        }
    }
}
```

### 52.剑指Offer52 两个链表的第一个公共节点

输入两个链表，找出它们的第一个公共节点。

如下面的两个链表**：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。

 

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
        ListNode newHeadA = headA;
        ListNode newHeadB = headB;
        while(headA!=headB){
            headA = headA==null?newHeadB:headA.next;
            headB = headB==null?newHeadA:headB.next;
        }
        return headA;
    }
}
```

### 53.剑指Offer53-I 在排序数组中查找数字I

统计一个数字在排序数组中出现的次数。

 

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: 0

> 二分查找

```java
class Solution {
    public int search(int[] nums, int target) {
        // 找左边界与右边界
        int l = binarySearchLeft(nums,target);
        int r = binarySearchRight(nums,target);
        if(l==-1&&r==-1){
            return 0;
        }else if(l==-1 || r==-1){
            return l==-1?r:l;
        }else{
            return r-l+1;
        }
    }
    // 找左边界
    public int binarySearchLeft(int[] nums,int target){
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]>target){
                r = mid - 1;
            }else if(nums[mid]<target){
                l = mid + 1;
            }else if(nums[mid]==target){
                r = mid - 1;
            }   
        }
        if(l>=nums.length || nums[l]!=target){
            return -1;
        }
        return l;
    }
    // 找右边界
    public int binarySearchRight(int[] nums,int target){
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]>target){
                r = mid - 1;
            }else if(nums[mid]<target){
                l = mid + 1;
            }else if(nums[mid]==target){
                l = mid + 1;
            }
        }
        if(r<0||nums[r]!=target){
            return -1;
        }
        return r;
    }

}
```

### （不熟悉）53.剑指Offer53-II 0~n-1中缺失的数字

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

 

示例 1:

输入: [0,1,3]
输出: 2
示例 2:

输入: [0,1,2,3,4,5,6,7,9]
输出: 8


限制：

1 <= 数组长度 <= 10000

```java
class Solution {
    public int missingNumber(int[] nums) {
        // 二分查找
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]==mid){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return l;
    }
}
```

### (不熟悉)54.剑指Offer二叉搜索树的第k大节点

给定一棵二叉搜索树，请找出其中第k大的节点。

 

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 4

> 解题思路：中序遍历一遍，之后求出第k大节点

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
    // 记录
    List<Integer> list  = new ArrayList<>();
    public int kthLargest(TreeNode root, int k) {
        dfs(root);
        return list.get(k-1);
    }
    // 开始走
    // 中序遍历是 左根右 
    public void dfs(TreeNode root){
        if(root==null){
            return;
        }
        dfs(root.left);
        // 处理
        list.add(0,root.val);
        dfs(root.right);
    }

}
```

> 提前结束的中序遍历
>
> 中序遍历 正常是升序的 左根右
>
> 而中序遍历的逆序是倒序的即 右根左

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
    int res,k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    //开始
    public void dfs(TreeNode root){
        if(root==null){
            return;
        }
        dfs(root.right);
        // 处理该结点
        if(k==0){
            return;
        }
        if(--k==0){
            res = root.val;
        }

        dfs(root.left);
    }
}
```

### 55.剑指Offer-I 二叉树的深度


输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

例如：

给定二叉树 `[3,9,20,null,null,15,7]`，

```
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最大深度 3 。

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
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        int height = Math.max(leftHeight,rightHeight)+1;
        return height;
    }
}
```

### (不熟悉)-55.剑指Offer55-II 平衡二叉树

输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

 

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

3

   / \
  9  20
    /  \
   15   7
返回 true 。

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
    // 平衡二叉树，要求就是左右子树深度相差不超过1
    // 对求高度进行改进
    public boolean isBalanced(TreeNode root) {
        return height(root)>=0;
    }
    // 求高度
    public int height(TreeNode root){
        if(root==null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if(leftHeight==-1 || rightHeight==-1 || Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }else{
            return Math.max(leftHeight,rightHeight)+1;
        }
    }
}
```

### (不会)- 56.剑指Offer56-I 数组中数字出现的次数

一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 

示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]

> 解题思路：
>
> 先来考虑一个比较简单的问题：
>
> 如果除了一个数字以外，其它数字都出现了两次，那么如何找到出现一次的数字呢？
>
> 答案很简单：全员进行异或操作即可。考虑异或操作的性质：对于两个操作数的每一位，相同结果为 00，不同结果为 11。那么在计算过程中，成对出现的数字的所有位会两两抵消为 00，最终得到的结果就是那个出现了一次的数字。

那么这一方法如何扩展到找出两个出现一次的数字呢？

如果我们可以把所有数字分成两组，使得：

两个只出现一次的数字在不同的组中；

相同的数字会被分到相同的组中。



那么对两个组分别进行异或操作，即可得到答案的两个数字。**这是解决这个问题的关键。**

```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        // 异或运算以及左移运算
        // 分组
        int x=0,y=0;
        // 整体结果存储
        int n = 0;
        // 作为一个数组分割的中介
        int m = 1;

        // 1.先整体异或运算
        for(int num:nums){
            n ^= num;
        }
        // 2.分割
        while((n & m) == 0){
            // m左移运算符
            m <<= 1;
        }
        //3.分组计算
        for(int num:nums){
            if((m&num)!=0){
                x ^= num;
            }else{
                y ^= num;
            }
        }
        // 4.返回结果
        return new int[]{x,y};
    }
}
```

### （不会）56.剑指Offer56II 数组中数字出现的次数II

在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

 

示例 1：

输入：nums = [3,4,3,3]
输出：4

```java
class Solution {
    public int singleNumber(int[] nums) {
        //异或运算
        int ones = 0;
        int twos = 0;
        for(int num:nums){
            ones = ones^num&~twos;
            twos = twos^num&~ones;
        }
        return ones;
    }
}
```

### （不熟悉）57.剑指Offer57 和为s的两个数字

输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
示例 2：

输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        //和为s的两个数字
        // 用hashMap来存储
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            //
            if(hashMap.containsKey(target-nums[i])){
                return new int[]{nums[i],target-nums[i]};
            }
            hashMap.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
}
```

> 而这个数组是排序的，可以用双指针的思路更快

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 两个指针解题
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int temp = nums[l]+nums[r];
            if(temp<target){
                l++;
            }else if(temp>target){
                r--;
            }else if(temp==target){
                return new int[]{nums[l],nums[r]};
            }
        }
        return new int[]{-1,-1};
    }
}
```

### （不熟悉）57.剑指Offer57 II 和为s的连续正数序列

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

 

示例 1：

输入：target = 9
输出：[[2,3,4],[4,5]]
示例 2：

输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]

> 解题思路：滑窗

```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        //滑动窗口的解题思路
        int l = 1;
        int r = 2;
        int temp_sum = 3;
        // 结果存储
        List<int[]> list = new ArrayList<>();
        while(l<r){
            // 如果符合条件
            if(temp_sum==target){
                int[] temp = new int[r-l+1];
                for(int k=l;k<=r;k++){
                    temp[k-l] = k;
                }
                list.add(temp);
            }

            if(temp_sum>=target){
                temp_sum -= l;
                l++;
            }else{
                r++;
                temp_sum += r;
            }
        }
        return list.toArray(new int[0][]);
    }
}
```

### （不熟悉）- 58.剑指Offer58-I 翻转单词顺序

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

 

示例 1：

输入: "the sky is blue"
输出: "blue is sky the"
示例 2：

输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。

```java
class Solution {
    public String reverseWords(String s) {
        // 1.去除前后空格
        s = s.trim();
        // 2.按照空格来分割字符
        String[] arr = s.split("\\s+");
        // 3.将其转为list
        List<String> worList = Arrays.asList(arr);
        // 4.翻转
        Collections.reverse(worList);
        // 5.对结果的拼接操作
        return String.join(" ",worList);
    }
}
```

### 58.剑指Offer58 II.左旋转字符串

字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

 

示例 1：

输入: s = "abcdefg", k = 2
输出: "cdefgab"
示例 2：

输入: s = "lrloseumgh", k = 6
输出: "umghlrlose"

> 解题思路：

```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        // 分割
        String s1 = s.substring(0,n);
        String s2 = s.substring(n);
        return s2+s1;
    }
}
```

### （不熟悉）59. 剑指Offer59-I 滑动窗口的最大值

给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k==0){
            return new int[]{};
        }
        // 解题思路 单调队列来解题
        Deque<Integer> queue = new LinkedList<>();
        // 结果存储
        int len = nums.length;
        int[] res = new int[len-k+1];
        int index = 0;
        // 滑窗
        int i = 0;
        while(i<len){    
            // 判断添加的值是否合法
            while(!queue.isEmpty()&&nums[i]>nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);

            // 判断是否超过了
            if(i>=queue.peekFirst()+k){
                queue.pollFirst();
            }

            // 判断是否应该记录值了
            if(i-k+1>=0){
                res[index++] = nums[queue.peekFirst()];
            }

            i++;
        }
        return res;
    }
}
```

### （不熟悉）-59.剑指Offer59-II 队列的最大值

请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

示例 1：

输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
示例 2：

输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]

```java
class MaxQueue {
    // 单调队列的
    Queue<Integer> queue_num;
    Deque<Integer> queue_max;
    public MaxQueue() {
        queue_num = new LinkedList<>();
        queue_max = new LinkedList<>();
    }
    
    public int max_value() {
        if(queue_max.isEmpty()){
            return -1;
        }
        return queue_max.peekFirst();
    }
    
    public void push_back(int value) {
        queue_num.offer(value);
        while(!queue_max.isEmpty()&&queue_max.peekLast()<value){
            queue_max.pollLast();
        }
        queue_max.offerLast(value);
    }
    
    public int pop_front() {
        if(queue_num.isEmpty()){
            return -1;
        }
        int res = queue_num.poll();
        if(res==queue_max.peekFirst()){
            queue_max.pollFirst();
        }
        return res;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
```

### （不会）- 60.剑指Offer60 n个骰子的点数

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 

示例 1:

输入: 1
输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
示例 2:

输入: 2
输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]

```java
class Solution {
    public double[] dicesProbability(int n) {
        // 动态规划
        double[] dp = new double[6];
        // 初始化
        Arrays.fill(dp,1.0/6.0);
        // 其余骰子
        for(int  i=2;i<=n;i++){
            double[] temp = new double[5*i+1];
            for(int j=0;j<dp.length;j++){
                for(int k=0;k<6;k++){
                    temp[j+k] += dp[j]/6.0;
                }
            }
            dp = temp;
        }
        return dp;
    }
}
```

### （不会）61.剑指Offer61 扑克牌中的顺子

从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

 

示例 1:

输入: [1,2,3,4,5]
输出: True

> 从理解题意来看，5张牌是顺子的**充分条件**如下：
>
> - 除大小王外，所有牌**无重复**；
> - 设此5张牌

> 用hashset

```java
class Solution {
    public boolean isStraight(int[] nums) {
        //用hashset来解题
        Set<Integer> set = new HashSet<>();
        // 记录最大值和最小值
        int max = 0;
        int min = 14;
        for(int num:nums){
            // 跳过大小王
            if(num==0){
                continue;
            }
            // 最大牌和最小牌的记录
            max = Math.max(max,num);
            min = Math.min(min,num);
            // 如果有重复
            if(set.contains(num)){
                return false;
            }
            // 添加
            set.add(num);
        }
        return max-min < 5;
    }
}
```



```java
class Solution {
    // 用排序来解题
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int joker = 0;
        for(int i=0;i<4;i++){
            // 统计大小王
            if(nums[i]==0){
                joker++;
            }else if(nums[i]==nums[i+1]){
                return false;
            }
        }
        // 判断最大牌-最小牌<5则可构成顺子
        return nums[4]-nums[joker]<5;
    }
}
```

### （不熟悉）62.剑指Offer圆圈中最后剩下的数字

0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

 

示例 1：

输入: n = 5, m = 3
输出: 3

示例 2：

输入: n = 10, m = 17
输出: 2

> arraylist的解题思路

```java
class Solution {
    // 约瑟夫环
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        // 赋值
        for(int i=0;i<n;i++){
            list.add(i);
        }
        // 开始
        int index = 0;
        while(list.size()>1){
            // 开始计时淘汰
            // for(int i=1;i<m;i++){
            //     index = index+1;
            // }
            index = (index+m-1)%list.size();
            // 删除
            list.remove(index);
        }
        return list.get(0);
    }
}
```

> 数学公式的方法

```java
class Solution {
    public int lastRemaining(int n, int m) {
        int x = 0;
        for(int i=2;i<=n;i++){
            x = (x+m)%i;
        }
        return x;
    }
}
```

### 63.剑指Offer63 股票的最大利润

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

 

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

> 动态规划

```java
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        // 进价
        int in_price = prices[0];
        // 最大利润
        int max_profit = 0;
        // 转移方程
        for(int i=1;i<prices.length;i++){
            max_profit = Math.max(max_profit,prices[i]-in_price);
            // 更新
            in_price = Math.min(in_price,prices[i]);
        }
        return max_profit;
    }
}
```

### （不会）64.剑指Offer64 求1+2+...+n

求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

 

示例 1：

输入: n = 3
输出: 6
示例 2：

输入: n = 9
输出: 45

```java
class Solution {
    int res = 0;
    public int sumNums(int n) {
        boolean x = n>1&&sumNums(n-1)>0;
        res += n;
        return res;
    }
}
```

### (不会) 65.剑指Offer65 不用加减乘除做加法

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

 

示例:

输入: a = 1, b = 1
输出: 2

> 解题思路：位运算

```java
class Solution {
    public int add(int a, int b) {
        // 当进位为0时
        while(b!=0){
            int c= (a&b)<<1;
            a ^= b;
            b =  c;
        }
        return a;
    }
}
```

### (不熟悉) 66.剑指Offer66 构建乘积数组

给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。

 

示例:

输入: [1,2,3,4,5]
输出: [120,60,40,30,24]

```java
class Solution {
    public int[] constructArr(int[] a) {
        // 记录
        int n = a.length;
        // 开辟结果数组
        int[] res = new int[n];
        // 左边
        int left = 1;
        for(int i=0;i<n;i++){
            res[i] = left;
            left *= a[i];
        }
        // 右边
        int right = 1;
        for(int i=n-1;i>=0;i--){
            // 都改成*
            res[i] *= right;

            right *= a[i];
        }
        return res;
    }
}
```

### （不熟悉）67.剑指Offer67 把字符串转成整数

示例 1:

输入: "42"
输出: 42
示例 2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。

```java
class Solution {
    // 实现字符串转成整数
    public int strToInt(String str) {
  
        // 1.先去除空格
        str = str.trim();
      
        // 2.开始
        char[] arr = str.toCharArray();
        // 3.开始记录
        int num = 0;
        int flag = 0;
        int i = 0;
        // 判断
        if(i==arr.length){
            return 0;
        }
        if(arr[i]=='-'){
            flag = -1;
            i++;
        }else if(arr[i]=='+'){
            i++;
        }

        // 判断是否为数字
        while(i<arr.length&&Character.isDigit(arr[i])){
            int cur = arr[i] - '0';
            // 判断是否超出范围了
            if((num)>(Integer.MAX_VALUE-cur)/10){
                return flag==0?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            num = num*10 + cur;
            i++;
        }
        return flag==0?num:(-1)*num;
    }
}
```

### (不熟悉)-68.剑指Offer68二叉搜索树的最近公共祖先

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]



 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/binarysearchtree_improved.png)

示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。

> 注意此题的解题关键是二叉搜索树
>
> 在两边就直接返回当前；
>
> 同一边才走

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
        if(root.val<p.val&&root.val<q.val){
            return lowestCommonAncestor(root.right,p,q);
        }else if(root.val>p.val&&root.val>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        return root;
    }
}
```

> 非递归

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
        while(root!=null){
            if(root.val>p.val&&root.val>q.val){
                root = root.left;
            }else if(root.val<p.val&&root.val<q.val){
                root = root.right;
            }else{
                break;
            }
        }
        return root;
    }
}
```

### (不熟悉)-68.剑指Offer68 II 二叉树的最近公共祖先

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]



 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/15/binarytree.png)

示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。

> 二叉树的最近公共祖先 就不能利用与root.val的比较了

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
        if(root==null || root==p || root==q){
            return root;
        }
        // 递归
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        // 判断
        if(left==null&&right==null){
            return null;
        }
        if(left==null){
            return right;
        }
        if(right==null){
            return left;
        }
        return root;
    }
}
```

