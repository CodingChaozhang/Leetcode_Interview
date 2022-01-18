# DFS算法

> 个人在DFS算法的理解觉得关键在于递归的函数书写。
>
> 判断是否用DFS算法关键在于是否是上下左右都可以走的类型。
>
> 递归返回1还是0 递归返回true还是false
>
> 更改其值，防止重复；
>
> 接着走；

## 一、无返回值

### [1.Leetcode200 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)

给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

 

示例 1：

输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1

示例 2：

输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3

> 解题思路：无返回值的思路。主要是修改其值。接着遍历

```java
class Solution {
    public int numIslands(char[][] grid) {
        int count =  0;
        // dfs
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 如果是岛屿
                if(grid[i][j]=='1'){
                    // 统计数量
                    count++;
                    // dfs将相邻的1标记
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
    // dfs
    public void dfs(char[][] grid,int i,int j){
        // 递归截止条件
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!='1'){
            return; 
        }
        // 已遍历过的
        grid[i][j] = '2';
        // 遍历其它的
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}
```



## 二、判断true还是flase

### [1.Leetcode1254统计封闭岛屿的数量](https://leetcode-cn.com/problems/number-of-closed-islands/)

有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。

我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。

如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。

请返回封闭岛屿的数目。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/07/sample_3_1610.png)

输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
输出：2
解释：
灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
示例 2：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/11/07/sample_4_1610.png)

输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
输出：1

> 解题思路：与统计岛屿数量很相似，遍历过的标记。
>
> 不同在于边界的处理上

```java
class Solution {
    public int closedIsland(int[][] grid) {
        // 访问
        // 孤岛
        int res=0;
        // 判断true或者false
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 判断 0附近都是1
                if(grid[i][j]==0&&dfs(grid,i,j)){
                    res++;
                }
            }
        }
        return res;
    }

    // 孤岛
    public boolean dfs(int[][] grid,int i,int j){
        // 越界肯定不是
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length){
            return false;
        }
        // 为岛屿
        if(grid[i][j]!=0){
            return true;
        }
        // 记录访问过的
        grid[i][j] = 2;
        // 看别的
        boolean left = dfs(grid,i+1,j);
        boolean right = dfs(grid,i-1,j);
        boolean up = dfs(grid,i,j+1);
        boolean down = dfs(grid,i,j-1);
        return left&&right&&up&&down;
    }
}
```



## 三、计算路径相邻的单元

### [1.剑指Offer13 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：

输入：m = 3, n = 1, k = 0
输出：1

> 解题思路：路径的返回值单独列出来。

```java
class Solution {
    public int movingCount(int m, int n, int k) {
        // 建立方格
        int[][] square = new int[m][n];
        // 从(0,0)开始走 计算方格
        int count = dfs(square,0,0,k);
        return count;

    }
    // dfs
    public int dfs(int[][] square,int i,int j,int k){
        // 返回值走不了 走过了
        if(i<0 || i>=square.length || j<0 || j>=square[0].length || (i%10+i/10+j%10+j/10)>(k) || square[i][j]==1){
            return 0;
        }


        // 走过当前格子了
        square[i][j] = 1;

        //当前格子
        int left = dfs(square,i+1,j,k);
        int right = dfs(square,i-1,j,k);
        int up  = dfs(square,i,j+1,k);
        int down = dfs(square,i,j-1,k);


        return 1+left+right+up+down;
    } 
}
```

### [2.Leetcode695岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)

给定一个包含了一些 0 和 1 的非空二维数组 grid 。

一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)

 

示例 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。

示例 2:

[[0,0,0,0,0,0,0,0]]
对于上面这个给定的矩阵, 返回 0。

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        // dfs统计最大数量
        int max_count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 如果为1则开始统计
                if(grid[i][j]==1){
                    max_count = Math.max(max_count,dfs(grid,i,j));
                }
            }
        }
        return max_count;
    }

    // 统计与其相邻的1的个数
    public int dfs(int[][] grid,int i,int j){
        // 遇到已访问过的 统计要注意已访问过的
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0){
            return 0;
        }

        // 已访问
        grid[i][j]=0;
        // 遍历其它
        int count = 1;
        int left = dfs(grid,i-1,j);
        int right = dfs(grid,i+1,j);
        int up = dfs(grid,i,j-1);
        int down = dfs(grid,i,j+1);
        return count+left+right+up+down;

    }
}
```





### [3.Leetcode463岛屿的周长](https://leetcode-cn.com/problems/island-perimeter/)

给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。

网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

 

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/island.png)

输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
输出：16
解释：它的周长是上面图片中的 16 个黄色的边

```java
class Solution {
    public int islandPerimeter(int[][] grid) {
        // 统计边界数量 边界=岛屿与外
        int count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    // 从陆地开始找
                    count += dfs(grid,i,j);
                }
            }
        }
        return count;
    }

    // 对其dfs
    public int dfs(int[][] grid,int i,int j){
        // 递归截止条件 有一条边界
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==0){
            return 1;
        }
        // 遇到重复的
        if(grid[i][j]==2){
            return 0;
        }
        grid[i][j]=2;

        // 其余
        int left = dfs(grid,i+1,j);
        int right = dfs(grid,i-1,j);
        int down = dfs(grid,i,j-1);
        int up = dfs(grid,i,j+1);

        return left+right+down+up;

    }
}
```

### [4.剑指Offer12 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)

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

> 解题思路：
>
> 两个if的返回值
>
> 当前值已访问过的
>
> 接着访问；
>
> 此题可能超时

```java
class Solution {
    public boolean exist(char[][] board, String word) {
       
        // dfs走法
        char[] arr = word.toCharArray();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,i,j,arr,0)){
                    return true;
                }
            }
        }
        return false;
    }

    // dfs
    public boolean dfs(char[][] board,int i,int j,char[] dict,int index){
        // 下一步走到边界都找不到 已访问过了
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=dict[index]){
            return false;
        }
        if(index==dict.length-1){
            return true;
        }
        // 走过的处理
        char temp = board[i][j];
        board[i][j] = '1';

        // 接着走
        boolean res = dfs(board,i+1,j,dict,index+1) || 
                      dfs(board,i-1,j,dict,index+1) || 
                      dfs(board,i,j-1,dict,index+1) ||
                      dfs(board,i,j+1,dict,index+1);

        // 恢复
        board[i][j] = temp;

        return res;

    }
}
```









