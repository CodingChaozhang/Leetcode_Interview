















### [子序列问题1.编辑距离](https://leetcode-cn.com/problems/edit-distance/)

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

> 动态规划解题

```java
class Solution {
    public int minDistance(String word1, String word2) {
        // 动态规划
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        // 初始化
        for(int i=0;i<=m;i++){
            dp[i][0] = i;
        }
        for(int j=0;j<=n;j++){
            dp[0][j] = j;
        }

        // 开始搜索
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    // 从剩下的三个操作选择其中一个
                    dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[m][n];
    }
}
```



[子序列问题2：最大递增子序列问题-信封嵌套问题](https://leetcode-cn.com/problems/longest-increasing-subsequence/)





### [2.Leetcode300最长递增子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。


示例 1：

输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

示例 2：

输入：nums = [0,1,0,3,2,3]
输出：4

示例 3：

输入：nums = [7,7,7,7,7,7,7]
输出：1

> 解题思路：没有连续，考虑动态规划

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        // 动态规划的题目
        int n = nums.length;
        // 动态规划数组
        int[] dp = new int[n];
        dp[0] = 1;
        // 记录结果
        int res = 1;
        // 对第二个进行遍历
        for(int i=1;i<n;i++){
            dp[i] = 1;
            //  对其前面的挨个遍历
            for(int j=0;j<i;j++){
                // 符合条件
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res  = Math.max(res,dp[i]);
        }
        return res;
    }
}
```

> 贪心+二分查找解题
>
> 维护一个结果数组（值不一定是递增序列的值，先不用管这点），如果当前元素比结果数组的值都大的的话，就追加在结果数组后面（相当于递增序列长度加1了哈）；否则的话用当前元素覆盖掉第一个比它大的元素（这样的话后续递增序列才有可能更长，即使并没有更长，这个覆盖操作也并没有副作用哈，当然这个覆盖操作可能会让最终的结果数组值并不是最终的递增序列值，这无所谓）

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        // 贪心+二分查找
        // 存储最长递增子序列的数组
        int n = nums.length;
        int[] dp = new int[n+1];
        int len = 1;
        // 初始化
        dp[len] = nums[0];
        // 对其转移方程
        for(int i=1;i<n;i++){
            if(nums[i]>dp[len]){
                // 直接放入
                dp[++len] = nums[i];
            }else{
                int l =1;
                int r = len;
                int pos = 0;
                while(l<=r){
                    int mid = l +((r-l)>>1);
                    if(dp[mid]<nums[i]){
                        l = mid+1;
                        pos = mid;
                    }else{
                        r = mid-1;
                    }
                }
                // 最后赋值
                dp[pos+1] = nums[i]; 
            }
        }
        return len;
    }
}
```

### [4.Leetcode673最长递增子序列的个数](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/)

给定一个未排序的整数数组，找到最长递增子序列的个数。

示例 1:

输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。

示例 2:

输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

> 再加一个数组，动态维护

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
       // 最长递增子序列
       int len = nums.length;
       // 动态数组
       int[] dp = new int[len];
        // 存储
        int[] combination = new int[len];
        dp[0]   = 1;
        combination[0] = 1;
        // 记录
        int max = 1;
        // 对其遍历
        for(int i=1;i<len;i++){
            dp[i] = 1;
            combination[i] = 1;
            // 前面的值比较
            for(int j=0;j<i;j++){
                // 是否符合条件
                if(nums[i]>nums[j]){
                    // 对其修改
                    if(dp[j]+1>dp[i]){
                        dp[i] = dp[j]+1;
                        combination[i] = combination[j];
                    }else if(dp[j]+1==dp[i]){
                        combination[i] += combination[j];
                    }
                }
            }
            max = Math.max(dp[i],max);
        }
        // 统计次数
        int count = 0;
        for(int i=0;i<len;i++){
            if(dp[i]==max){
                count+=combination[i];
            }
        } 
        return count;
    }
}
```

### [5.Leetcode354俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/)

给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。

当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

注意：不允许旋转信封。


示例 1：

输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
输出：3
解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。

示例 2：

输入：envelopes = [[1,1],[1,1],[1,1]]
输出：1

> 解题思路：排序+最长递增子序列问题

```java
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        // 最长递增序列
        // 先排序
        Arrays.sort(envelopes,(a,b)->(a[0]-b[0]));
        // 再最长递增序列
        int n = envelopes.length;
        int[] dp = new int[n];
        dp[0] = 1;
        // 记录
        int maxLen = 1;
        // 转移方程
        for(int i=1;i<n;i++){
            dp[i] = 1;
            // 与之前的数据进行比较
            for(int j=0;j<i;j++){
                if(envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]){
                    // 放入进去
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            maxLen = Math.max(maxLen,dp[i]);
        }
        return maxLen;
    }
}
```

