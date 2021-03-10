# 回溯算法

回溯与DFS算法相似，但具体实现上还存在不同。

在DFS中，主要用于上下左右。

而在回溯中主要用于子集  全排列 组合  N皇后等问题。





## 一、子集问题

### [1.Leetcode076 子集](https://leetcode-cn.com/problems/subsets/)

给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

 

示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

示例 2：

输入：nums = [0]
输出：[[],[0]]

> 子集 ；
>
> 注意长度不一致；
>
> 以及是否需要+1

```java
class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        // 临时存储的值
        List<Integer> temp = new ArrayList<>();
        // 从第0处开始
        dfs(nums,temp,0);
        return res;
    }

    public void dfs(int[] nums,List<Integer> temp,int index){
        // 直接加入
        res.add(new ArrayList<>(temp));

        // 全部遍历 变量则长度不一
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            // 子集这里是i+1
            dfs(nums,temp,i+1);
            temp.remove(temp.size()-1);
        }

    }
}
```

### [2.Leetcode090子集II](https://leetcode-cn.com/problems/subsets-ii/)

给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

> 数组可能重复，用到排序，来去重！！！记得要排序
>
> 且注意：变量+1

```java
class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 临时
        List<Integer> temp = new ArrayList<>();
        // 解集中需要去重
        Arrays.sort(nums);
        // 调用
        dfs(nums,temp,0);
        return res;
    }

    // 对其回溯算法
    public void dfs(int[] nums,List<Integer> temp,int index){
        res.add(new ArrayList<>(temp));

        for(int i=index;i<nums.length;i++){
            // 去重
            if(i>index&&nums[i]==nums[i-1]){
                continue;
            }

            temp.add(nums[i]);
            dfs(nums,temp,i+1);
            temp.remove(temp.size()-1);

        }

    }
}
```

## 二、排列问题

### [1.Leetcode046全排列](https://leetcode-cn.com/problems/permutations/)

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

> 全排列，都用上了。直接for循环，防止重复使用用一个visited数组来防止

```java
class Solution {
    // 全排列 没有重复
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        // 临时值
        List<Integer> temp = new ArrayList<>();
        // 记录
        boolean[] visited = new boolean[nums.length];
        // 对其回溯
        dfs(nums,temp,visited);
        return res;
    }

    // 对其全排列
    public void dfs(int[] nums,List<Integer> temp,boolean[] visited){
        // 符合结果
        if(temp.size()==nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }

        // 对其遍历
        for(int i=0;i<nums.length;i++){
            // 防止多次使用
            if(visited[i]==true){
                continue;
            }

            visited[i] = true;
            temp.add(nums[i]);

            dfs(nums,temp,visited);

            visited[i] = false;
            temp.remove(temp.size()-1);
        }

    }
}
```

### [2.Leetcode047 全排列II](https://leetcode-cn.com/problems/permutations-ii/)

给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

 

示例 1：

输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]

示例 2：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

> 一方面用visited防止重复使用；
>
> 另一方面用排序+判断数组可重复的元素

```java
class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        // 临时值
        List<Integer> temp = new ArrayList<>();
        // 记录遍历过的
        boolean[] visited = new boolean[nums.length];
        // 去重
        Arrays.sort(nums);
        // 回溯
        dfs(nums,temp,visited);
        return res;
    }
    // 回溯算法
    public void dfs(int[] nums,List<Integer> temp,boolean[] visited){
        // 满足条件则返回
        if(temp.size()==nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }

        // 对其遍历
        for(int i=0;i<nums.length;i++){
            // 防止多次访问
            if(visited[i]==true){
                continue;
            }
            // 去重
            if(i>0&&nums[i]==nums[i-1]&&visited[i-1]==false){
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);

            dfs(nums,temp,visited);
            
            visited[i] = false;
            temp.remove(temp.size()-1);

        }

    }
}
```



> 总结：在子集和排列中；一个是任意长度；一个是固定长度；但是都是用一次，子集用变量+1来防止 而排列中用visited数组来防止

## 三、组合问题

### [1.Leetcode039组合总和](https://leetcode-cn.com/problems/combination-sum/)

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

> 组合问题。满足结果就加入，且是重复使用，数组无重复元素。

```java
class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    // 数组无重复元素，但是可以无限制重复被选取，但是需满足target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 临时存储值
        List<Integer> temp_list = new ArrayList<>();
        // 存储数组中的结果
        // 回溯算法
        dfs(candidates,0,temp_list,target,0);
        return res;
    }

    // 回溯算法
    public void dfs(int[] candidates,int index,List<Integer> temp_list,int target,int sum){
        // 符合条件则加入
        if(sum==target){
            res.add(new ArrayList<>(temp_list));
            return;
        }
        // 不符合条件
        if(sum>target){
            return;
        }
        // 继续
        for(int i=index;i<candidates.length;i++){
            
            // 建立一个临时值
            int temp = sum + candidates[i];
            // 加入
            temp_list.add(candidates[i]);
            // 选择i是为了重复利用
            dfs(candidates,i,temp_list,target,temp);
            temp_list.remove(temp_list.size()-1);

        }

    }
}
```

> 上述的改进，排序来剪枝

```java
class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    // 数组无重复元素，但是可以无限制重复被选取，但是需满足target
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 临时存储值
        List<Integer> temp_list = new ArrayList<>();
        // 排序剪枝
        Arrays.sort(candidates);
        // 存储数组中的结果
        // 回溯算法
        dfs(candidates,0,temp_list,target,0);
        return res;
    }

    // 回溯算法
    public void dfs(int[] candidates,int index,List<Integer> temp_list,int target,int sum){
        // 符合条件则加入
        if(sum==target){
            res.add(new ArrayList<>(temp_list));
            return;
        }
        // 不符合条件
        if(sum>target){
            return;
        }
        // 继续
        for(int i=index;i<candidates.length;i++){
            // 判断
            if(sum<target){
                 // 建立一个临时值
                int temp = sum + candidates[i];
                // 加入
                temp_list.add(candidates[i]);
                // 选择i是为了重复利用
                dfs(candidates,i,temp_list,target,temp);
                temp_list.remove(temp_list.size()-1);
            }else{
                break;
            }
        }

    }
}
```

### [2.Leetcode040组合总和II](https://leetcode-cn.com/problems/combination-sum-ii/)

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]

> 解题思路：排序对数组中的元素处理。不重复的话只使用一次

```java
class Solution {
    // 数组中有重复元素，且只能使用一次
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 临时值
        List<Integer> list = new ArrayList<>();
        // 重复元素 排序
        Arrays.sort(candidates);
        // 记住
        int sum = 0;
        // 回溯
        dfs(candidates,0,list,sum,target);
        return res;
    }

    // 回溯
    public void dfs(int[] candidates, int index,List<Integer> list,int sum,int target){
        if(sum==target){
            res.add(new ArrayList<>(list));
            return;
        }
        if(sum>target){
            return;
        }
        // 回溯
        for(int i=index;i<candidates.length;i++){
            if(sum<target){
                // 去重
                if(i>index&&candidates[i]==candidates[i-1]){
                    continue;
                }
                int temp = sum + candidates[i];
                list.add(candidates[i]);
                dfs(candidates,i+1,list,temp,target);
                list.remove(list.size()-1);
            }else{
                break;
            }

        }

    }
}
```

### [3.Leetcode077组合](https://leetcode-cn.com/problems/combinations/)

给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

> 开辟一个数组，类似子集，只不过条件限制而已

```java
class Solution {
    // 对其在k限制
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        // 存储临时值
        List<Integer> list = new ArrayList<>();
        // 生成数组
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = i+1;
        }
        dfs(nums,list,0,k);
        return res;
    }

    // 回溯
    public void dfs(int[] nums,List<Integer> list,int index,int k){
        // 判断
        if(list.size()==k){
            res.add(new ArrayList<>(list));
            return;
        }

        // 判断
        for(int i=index;i<nums.length;i++){
            list.add(nums[i]);
            dfs(nums,list,i+1,k);
            list.remove(list.size()-1);

        }

    }
}
```

> 优化

```java
class Solution {
    // 对其在k限制
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        // 存储临时值
        List<Integer> list = new ArrayList<>();
       int cur = 1;
        dfs(list,cur,n,k);
        return res;
    }

    // 回溯
    public void dfs(List<Integer> list,int cur,int n,int k){
        // 判断
        if(list.size()==k){
            res.add(new ArrayList<>(list));
            return;
        }
        // 提前优化
        if(list.size()>k){
            return;
        }
        

        // 判断
        for(int i=cur;i<=n;i++){
            list.add(i);
            dfs(list,i+1,n,k);
            list.remove(list.size()-1);

        }

    }
}
```

### [4.Leetcode216组合总和III](https://leetcode-cn.com/problems/combination-sum-iii/)

找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]

示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]

> 解题思路：在上述的基础上。总之都是基于子集

```java
class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        // 临时存储值
        List<Integer> list = new ArrayList<>();
        // 里面的结果值
        int sum = 0;
        // 从1开始遍历
        dfs(1,list,sum,k,n);
        return res;
    }

    // 回溯
    public void dfs(int index,List<Integer> list,int sum,int k,int target){
        // 先看大小
        if(list.size()==k){
            // 再看其余的
            if(sum==target){
                res.add(new ArrayList<>(list));
                return;
            }
            return;
        }

        // 回溯
        for(int i=index;i<=9;i++){
            int temp = sum + i;
            list.add(i);
            dfs(i+1,list,temp,k,target);
            list.remove(list.size()-1);

        }

    }
}
```

## 四、回溯与DFS结合的题

### [1.Leetcode1219黄金矿工](https://leetcode-cn.com/problems/path-with-maximum-gold/)

你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：

每当矿工进入一个单元，就会收集该单元格中的所有黄金。
矿工每次可以从当前位置向上下左右四个方向走。
每个单元格只能被开采（进入）一次。
不得开采（进入）黄金数目为 0 的单元格。
矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。


示例 1：

输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
输出：24
解释：
[[0,6,0],
 [5,8,7],
 [0,9,0]]
一种收集最多黄金的路线是：9 -> 8 -> 7。

示例 2：

输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
输出：28
解释：
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。

> 解题思路：dfs结合回溯

```java
class Solution {
    public int getMaximumGold(int[][] grid) {
        // dfs 上下左右
        // 结果
        int res = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                // 从黄金进入
                if(grid[i][j]!=0){
                    res = Math.max(res,dfs(grid,i,j));
                }
            }
        }
        return res;
    }

    // dfs
    public int dfs(int[][] grid,int i,int j){
        // 遇到边界或者有黄金的单元格就停止
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==0){
            return 0;
        }
        
        // 记录开采过的
        int temp = grid[i][j];
        grid[i][j] = 0;
        int count = temp;
        int left = dfs(grid,i-1,j);
        int right =dfs(grid,i+1,j);
        int up =   dfs(grid,i,j-1);
        int down =  dfs(grid,i,j+1);
        // 需要计算最大值
        int max = Math.max(up,Math.max(right,Math.max(down,left)));

        grid[i][j] = temp;



        return count+max;


    }
}
```

## 五、回溯经典问题

### [1.Leetcode051 N皇后](https://leetcode-cn.com/problems/n-queens/)

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/11/13/queens.jpg)

输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。

> 解题思路：回溯的时候是遍历列，dfs行+1

```java
class Solution {
  

    public List<List<String>> solveNQueens(int n) {
        // 先开辟一个棋盘
        char[][] chess = new char[n][n];
        // 初始化
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                chess[i][j]='.';
            }
        } 
        // 结果
        List<List<String>> res = new ArrayList<>();

        // dfs
        dfs(chess,res,0);
        return res;   
    }

    // dfs
    public void dfs(char[][] chess,List<List<String>> res,int row){
        // 从row=0开始
        if(row==chess.length){
            // 对其constructor封装
            res.add(constructor(chess));
            return;
        }
        // 开始遍历列
        for(int col=0;col<chess[0].length;col++) {
            // 判断是否合法 合法才记录
            if(valid(chess,row,col)) {
                chess[row][col] = 'Q';
                dfs(chess,res,row+1);
                chess[row][col] = '.';
            }
        }
    }
    // 判断是否合法
    public boolean valid(char[][] chess,int row,int col){
        // 保证其三叉坐标行位置没有皇后
	    	for(int i=0;i<row;i++) {
	    		if(chess[i][col]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 判断当前坐标的右上角对角线
	    	for(int i=row-1,j=col+1;i>=0&&j<chess[0].length;i--,j++) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 判断当前坐标的左上角对角线
	    	for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 都没问题
	    	return true;

    }

    // constructor封装
    public List<String> constructor(char[][] chess){
        // 临时值
        List<String> list = new ArrayList<>();
        // 对其遍历
        for(int i=0;i<chess.length;i++){
            list.add(new String(chess[i]));
        }
        return list;

    }
}
```

