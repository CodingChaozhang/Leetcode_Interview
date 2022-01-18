# 路径问题

## 一、最小路径和问题

### [1.Leetcode三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

 

示例 1：

输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：如下面简图所示：
   2
  3 4
 6 5 7
4 1 8 3
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
示例 2：

输入：triangle = [[-10]]
输出：-10

> 走路姿势为向下 右下
>
> 且求到达一行的值

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 开辟一个动态数组
        int n = triangle.size();
        int[][] dp = new int[n][n];
        // 初始化
        dp[0][0] = triangle.get(0).get(0);

        // 转移方程
        for(int i=1;i<n;i++){
            for(int j=0;j<=i;j++){
                if(j==0){
                    // 列为0的情况
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                }else{
                    if(j==i){
                            // 不能转移方程
                            dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                        }else{
                            // 转移方程
                            dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1]) + triangle.get(i).get(j);
                    }
                }
            }
        }
        // 最后一列单独
        int min = dp[n-1][0];
        for(int i=1;i<=n-1;i++){
            min = Math.min(min,dp[n-1][i]);
        }
        // 返回
        return min;
    }
}
```

> 优化 从第一列开始走起

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] dp = new int[len+1];

        // 从最后一行开始
        for(int i=len-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                dp[j] = Math.min(dp[j],dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
```

### [2.Leetcode064最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg)

输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。

示例 2：

输入：grid = [[1,2,3],[4,5,6]]
输出：12

> 解题思路：路径和问题

```java
class Solution {
    public int minPathSum(int[][] grid) {
        // 最小路径和
        int m = grid.length;
        int n = grid[0].length;
        // 动态规划的解题
        int[][] dp = new int[m][n];
        // 初始化
        dp[0][0] = grid[0][0];
        for(int i=1;i<m;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int j=1;j<n;j++){
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        // 转移方程
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }
        }
        // 返回值
        return dp[m-1][n-1];
    }
}
```

> 优化空间

```java
class Solution {
    public int minPathSum(int[][] grid) {
        // 最小路径和
        int m = grid.length;
        int n = grid[0].length;
        // 动态规划的解题
        int[] dp = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        // 初始化
        dp[0] = 0;
        // 转移方程
        for(int i=0;i<m;i++){
            // 解决j==0的问题
            dp[0] = dp[0] + grid[i][0];
            for(int j=1;j<n;j++){
                dp[j] = Math.min(dp[j],dp[j-1]) + grid[i][j];
            }
        }
        // 返回值
        return dp[n-1];
    }
}
```

### [3.Leetcode120三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。

 

示例 1：

输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：如下面简图所示：
   2
  3 4
 6 5 7
4 1 8 3
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

示例 2：

输入：triangle = [[-10]]
输出：-10

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 自顶向下
        int row = triangle.size();
        // 开辟空间
        int[][] dp = new int[row][row];
        // 初始化
        dp[0][0] = triangle.get(0).get(0);
        // 列为0的时候
        for(int i=1;i<row;i++){
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
        }
        // 转移方程
        for(int i=1;i<row;i++){
            for(int j=1;j<=i;j++){
                if(i==j){
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                }else{
                    dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1]) + triangle.get(i).get(j);
                }
            }
        }
        // 结果
        int res = dp[row-1][0];
        for(int i=1;i<row;i++){
            res = Math.min(res,dp[row-1][i]);
        }
        return res;
    }
}
```

> 优化空间

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
       // 优化空间
       int n = triangle.size();
       // 开辟空间
       int[] dp = new int[n+1];
       // 从最后一行开始 从第一列开始
       for(int i=n-1;i>=0;i--){
           for(int j=0;j<=i;j++){
               dp[j] = Math.min(dp[j],dp[j+1]) + triangle.get(i).get(j);
           }
       }
       return dp[0];
    }
}
```



## 二、不同路径问题

### [1.Leetcode062不同路径](https://leetcode-cn.com/problems/unique-paths/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

 

示例 1：

![img](https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png)

输入：m = 3, n = 7
输出：28

示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下
示例 3：

输入：m = 7, n = 3
输出：28

示例 4：

输入：m = 3, n = 3
输出：6

```java
class Solution {
    public int uniquePaths(int m, int n) {
        // 动态规划解题思路
        // 开辟数组
        int[][] dp = new int[m][n];
        // 初始化
        for(int i=0;i<m;i++){
            dp[i][0] = 1;
        }
        for(int j=0;j<n;j++){
            dp[0][j] = 1;
        }
        // 其余值
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                // 转移方程计算总共路径
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        // 最后的结果值
        return dp[m-1][n-1];
    }
}
```

> 优化空间复杂度

```java
class Solution {
    public int uniquePaths(int m, int n) {
        // 动态规划解题思路
        // 优化空间
        int[] dp = new int[n];
        dp[0] = 1;
        // 对其遍历
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }
}
```

### [2.Leetcode063不同路径II](https://leetcode-cn.com/problems/unique-paths-ii/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/robot_maze.png)

网格中的障碍物和空位置分别用 1 和 0 来表示。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/11/04/robot1.jpg)

输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：

1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右



示例 2：

![img](https://assets.leetcode.com/uploads/2020/11/04/robot2.jpg)


输入：obstacleGrid = [[0,1],[0,0]]
输出：1

> 初始化的时候判断，以及转移方程的判断

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 动态规划
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==1){
                break;
            }
            dp[i][0] = 1;
        }
        for(int j=0;j<n;j++){
            if(obstacleGrid[0][j]==1){
                break;
            }
            dp[0][j] = 1;
        }
        // 转移方程
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = obstacleGrid[i][j]==1?0:dp[i-1][j]+dp[i][j-1];
            }
        }
        // 返回
        return dp[m-1][n-1];
    }
}
```

> 对其优化

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 动态规划
         int m = obstacleGrid.length;
         int n = obstacleGrid[0].length;
         int[] dp = new int[n];
         // 初始化
         dp[0] = obstacleGrid[0][0]==1?0:1;
        // 转移方程
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    dp[j] = dp[j]+dp[j - 1];
                }
            }
        }

        // 返回
        return dp[n-1];
    }
}
```


## 三、其它的一些动态规划

### [1.Leetcode746使用最小花费爬楼梯](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)

数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。

每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。

请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。

 

示例 1：

输入：cost = [10, 15, 20]
输出：15
解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。

 示例 2：

输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出：6
解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。



> 解题思路：动态规划

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // 开辟数组
        int n = cost.length;
        int[] dp = new int[n];
        // 初始化
        dp[0] = cost[0];
        dp[1] = cost[1];
        // 对其遍历
        for(int i=2;i<n;i++){
            // 转移方程
            dp[i] = Math.min(dp[i-1],dp[i-2])+cost[i];
        }
        // 可能达到最后一个也可能最后两个
        return Math.min(dp[n-2],dp[n-1]);
    }
}
```

## 四、连续数组区间问题

### [1.Leetcode152乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)

给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

 

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

> 开辟空间

```java
class Solution {
    public int maxProduct(int[] nums) {
        // 动态规划 存在正负号 需要维护两个值
        int n = nums.length;
        int[] maxdp = new int[n];
        int[] mindp = new int[n];
        // 初始化
        maxdp[0] = nums[0];
        mindp[0] = nums[0];
        // 结果
        int res = nums[0];
        // 转移方程
        for(int i=1;i<n;i++){
            maxdp[i] = Math.max(maxdp[i-1]*nums[i],Math.max(mindp[i-1]*nums[i],nums[i]));
            mindp[i] = Math.min(mindp[i-1]*nums[i],Math.min(maxdp[i-1]*nums[i],nums[i]));
            res = Math.max(maxdp[i],res);
        }
        return res;
    }
}
```

> 优化空间

```java
class Solution {
    public int maxProduct(int[] nums) {
        // 动态规划 存在正负号 需要维护两个值
        int n = nums.length;
        int maxdp = nums[0];
        int mindp = nums[0];
        // 结果
        int res = nums[0];
        // 转移方程
        for(int i=1;i<n;i++){
            int max = maxdp; 
            int min = mindp;
            maxdp = Math.max(maxdp*nums[i],Math.max(min*nums[i],nums[i]));
            mindp = Math.min(mindp*nums[i],Math.min(max*nums[i],nums[i]));
            res = Math.max(maxdp,res);
        }
        return res;
    }
}
```

### [2.Leetcode053最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 

示例 1：

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。

示例 2：

输入：nums = [1]
输出：1

示例 3：

输入：nums = [0]
输出：0

示例 4：

输入：nums = [-1]
输出：-1

```java
class Solution {
    public int maxSubArray(int[] nums) {
        // 开辟空间
        int n = nums.length;
        int[] dp = new int[n];
        // 初始化
        dp[0] = nums[0];
        int res = dp[0];
        for(int i=1;i<n;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            res = Math.max(dp[i],res);
        }
        return res;
    }
}
```

> 优化空间

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        // 对其余遍历
        for(int i=1;i<nums.length;i++){
            nums[i] = Math.max(nums[i-1]+nums[i],nums[i]);
            res = Math.max(res,nums[i]);
        }
        return res;
    }
}
```

