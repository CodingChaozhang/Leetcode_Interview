# 滑动窗口解题

>滑动窗口解题，主要是解决区间问题
>
>while
>
>里面可能是while 也可能是if
>
>如果是while，里面可能还有if，也可能没有

```java
def findSubArray(nums):
    N = len(nums) # 数组/字符串长度
    left, right = 0, 0 # 双指针，表示当前遍历的区间[left, right]，闭区间
    sums = 0 # 用于统计 子数组/子区间 是否有效，根据题目可能会改成求和/计数
    res = 0 # 保存最大的满足题目要求的 子数组/子串 长度
    while right < N: # 当右边的指针没有搜索到 数组/字符串 的结尾
        sums += nums[right] # 增加当前右边指针的数字/字符的求和/计数
        while 区间[left, right]不符合题意：# 此时需要一直移动左指针，直至找到一个符合题意的区间
            sums -= nums[left] # 移动左指针前需要从counter中减少left位置字符的求和/计数
            left += 1 # 真正的移动左指针，注意不能跟上面一行代码写反
        # 到 while 结束时，我们找到了一个符合题意要求的 子数组/子串
        res = max(res, right - left + 1) # 需要更新结果
        right += 1 # 移动右指针，去探索新的区间
    return res
```

## 一、连续代价/花费问题

### [1.Leetcode1208尽可能使字符串相等](https://leetcode-cn.com/problems/get-equal-substrings-within-budget/)

给你两个长度相同的字符串，s 和 t。

将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。

用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。

如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。

如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。

 

示例 1：

输入：s = "abcd", t = "bcdf", maxCost = 3
输出：3
解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。

示例 2：

输入：s = "abcd", t = "cdef", maxCost = 3
输出：1
解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。

示例 3：

输入：s = "abcd", t = "acde", maxCost = 0
输出：1
解释：a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。

```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        // 转换成字符数组
        char[] s_arr = s.toCharArray();
        char[] t_arr = t.toCharArray();
        // 代价数组
        int len = s_arr.length;
        int[] cost = new int[len];
        // 对其赋值
        for(int i=0;i<len;i++){
            cost[i] = Math.abs(s_arr[i]-t_arr[i]);
        }
        // 滑动窗口解题
        int left = 0;
        int right = 0;
        // 结果值
        int maxLength = 0;
        int tempCost = 0;
        while(right<len){
            tempCost += cost[right];
            if(tempCost>maxCost){
                tempCost -= cost[left];
                left++;
            }
            maxLength = Math.max(maxLength,right-left+1);
            right++;
        }
        return maxLength;
    }
}
```

### [2.Leetcode1052爱生气的老板](https://leetcode-cn.com/problems/grumpy-bookstore-owner/)

今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。

在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。

请你返回这一天营业下来，最多有多少客户能够感到满意的数量。


示例：

输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
输出：16
解释：
书店老板在最后 3 分钟保持冷静。
感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.

> 滑动窗口，长度计算用right-left+1

```java
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        // 先计算不生气的时候的值
        int res_1 = 0;
        int len = customers.length;
        for(int i=0;i<len;i++){
            res_1 += customers[i]*(1-grumpy[i]);
            // 访问该值之后将置为0
            if(grumpy[i]==0){
                customers[i] = 0;
            }
        }
        // 第二次遍历控制情绪
        int left = 0;
        int right = 0;
        int res_2 = 0;
        int temp = 0;
        //滑动窗口
        while(right<len){
           temp += customers[right];
           // 超过
           if(right-left+1>X){
               temp-=customers[left];
               left++;
           }
           res_2 = Math.max(res_2,temp);
           right++;

        }
        return res_1+res_2;
    }
}
```

### [3.Leetcode424替换后的最长重复字符](https://leetcode-cn.com/problems/longest-repeating-character-replacement/)

给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。

注意：字符串长度 和 k 不会超过 104。

 

示例 1：

输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。

示例 2：

输入：s = "AABABBA", k = 1
输出：4
解释：
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。

> 生成一个频率表，通过频率表中的最大值与k来决定是否需要缩放

```java
class Solution {
    public int characterReplacement(String s, int k) {
        char[] arr = s.toCharArray();
        //滑动窗口
        int left = 0;
        int right = 0;
        int res = 0;
        // 临时的频率最大值
        int maxCount = 0;
        int[] frequency = new int[26];
        // 对其遍历
        while(right<arr.length){
            frequency[arr[right]-'A']++;
            maxCount = Math.max(maxCount,frequency[arr[right]-'A']);
            // 判断是否需要缩
            if(right-left+1>maxCount+k){ 
                frequency[arr[left]-'A']--;
                left++;
            }
            res = Math.max(res,right-left+1);
            right++;
        } 
        return res;
    }
}
```



### [4.Leetcode1004 最大连续1的个数III](https://leetcode-cn.com/problems/max-consecutive-ones-iii/)

给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

返回仅包含 1 的最长（连续）子数组的长度。

 

示例 1：

输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释： 
[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。

示例 2：

输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 10。

```JAVA
class Solution {
    public int longestOnes(int[] A, int K) {
        // 滑动窗口解题
        int left = 0;
        int right = 0;
        int len = A.length;
        // 结果
        int res = 0;
        // 临时
        int zeros = 0;
        while(right<len){
            if(A[right]==0){
                zeros++;
            }
            // K使用为0
            while(zeros>K){
                // 移动left 只移动产生的
                if(A[left]==0){
                    zeros--;
                }
                left++;
            }
            // 记录
            res = Math.max(right-left+1,res);
            right++;
        }
        return res;
    }
}
```

### [5.Leetcode1493删掉一个元素以后全为1的最长子数组](https://leetcode-cn.com/problems/longest-subarray-of-1s-after-deleting-one-element/)

给你一个二进制数组 nums ，你需要从中删掉一个元素。

请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。

如果不存在这样的子数组，请返回 0 。

 

提示 1：

输入：nums = [1,1,0,1]
输出：3
解释：删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。

示例 2：

输入：nums = [0,1,1,1,0,1,1,0,1]
输出：5
解释：删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。

示例 3：

输入：nums = [1,1,1]
输出：2
解释：你必须要删除一个元素。

> 解题思路：类似于上述题目的最长连续1的个数

```java
class Solution {
    public int longestSubarray(int[] nums) {
        // 滑动窗口的解题
        int left = 0;
        int right = 0;
        int len = nums.length;
        // 对其统计长度
        int res = 0;
        // 统计遇到0的
        int zeros = 0;
        // 只能更改一次
        while(right<len){
            if(nums[right]==0){
                zeros++;
            }
            while(zeros>1){
                if(nums[left]==0){
                    zeros--;
                }
                left++;
            }
            // 在去掉中间那个0
            res = Math.max(res,right-left);
            right++;
        }
        return res;
    }
}
```

###  [6.Leetcode209长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

 

示例 1：

输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。

示例 2：

输入：target = 4, nums = [1,4,4]
输出：1

示例 3：

输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
       // 滑动窗口解题
       int left = 0;
       int right = 0;
       int len = nums.length;
       // 结果
       int res = Integer.MAX_VALUE;
       // 临时结果
       int temp = 0;
       // 遍历
       while(right<len){
           temp += nums[right];
            // 缩放
            while(temp>=target){
                temp -= nums[left];
                res = Math.min(res,right-left+1);
                left++;

            }
           right++;
       }
       return res==Integer.MAX_VALUE?0:res;
    }
}
```



## 二、两个字符串的问题 

> 注意里面是while循环

### [1.Leetcode438找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)

给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例 1:

输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。

 示例 2:

输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。

> 解题思路：频率表

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // 滑动窗口
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = 0;
        int[] frequency = new int[26];
        // 转换数组
        char[] s_arr = s.toCharArray();
        char[] p_arr = p.toCharArray();
        // 初始化
        for(int i=0;i<p_arr.length;i++){
            frequency[p_arr[i]-'a']++;
        }
        // 开始滑动
        while(right<s_arr.length){
            frequency[s_arr[right]-'a']--;
            // 缩放
            while(frequency[s_arr[right]-'a']<0){
                // 不符合条件
                frequency[s_arr[left]-'a']++;
                left++;
            }

            // 只找符合长度的
            if(right-left+1==p_arr.length){
                res.add(left);
            }
            right++;
        }
        return res;
    }
}
```

### [2.Leetcode567字符串的排列](https://leetcode-cn.com/problems/permutation-in-string/)

给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

 

示例 1：

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").

示例 2：

输入: s1= "ab" s2 = "eidboaoo"
输出: False

> 解题思路：还是频率表

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        char[] s1_arr = s1.toCharArray();
        char[] s2_arr = s2.toCharArray();
        // 频率表
        int[] fre = new int[26];
        for(int i=0;i<s1_arr.length;i++){
            fre[s1_arr[i]-'a']++;
        }
        // 滑动窗口的方法
        int left = 0;
        int right = 0;
        while(right<s2_arr.length){
            fre[s2_arr[right]-'a']--;
            // 缩招
            while(fre[s2_arr[right]-'a']<0){
                fre[s2_arr[left]-'a']++;
                left++;
            }

            // 符号条件的
            if(right-left+1==s1_arr.length){
                return true;
            }
            right++;
        }
        return false;
    }
}
```







## 三、单个字符串连续无代价的问题

### [1.Leetcode003无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

 

示例 1:

输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:

输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:

输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

示例 4:

输入: s = ""
输出: 0

> 用HashMap来辅助解题

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        // 用HashMap来辅助解题
        HashMap<Character,Integer> dict = new HashMap<>();
        // 滑动窗口解题
        int left = 0;
        int right = 0;
        // 结果
        int res = 0;
        while(right<arr.length){
 
            // 包含该值
            if(dict.containsKey(arr[right])){
                // 更新left
                left = Math.max(left,dict.get(arr[right])+1);
            }
            dict.put(arr[right],right);
            
            res = Math.max(res,right-left+1);
            right++;
        }
        return res;
    }
}
```

### [2.Leetcode674最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/)

给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。

连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。

 

示例 1：

输入：nums = [1,3,5,4,7]
输出：3
解释：最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。 

示例 2：

输入：nums = [2,2,2,2,2]
输出：1
解释：最长连续递增序列是 [2], 长度为1。

> 解题思路：记录结果

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length<1){
            return 0;
        }
        // 最长连续递增子序列是滑动窗口解题
        int n = nums.length;
        int left = 0;
        int right = 1;
        int maxLength = 1;
        while(right<n){
            if(nums[right]<=nums[right-1]){
                left = right;
            }
            maxLength = Math.max(maxLength,right-left+1);
            right++;
        }
        return maxLength;
    }
}
```





## 四、摆动序列的问题-两个状态

### [1.Leetcode376摆动序列](https://leetcode-cn.com/problems/wiggle-subsequence/)

如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

示例 1:

输入: [1,7,4,9,2,5]
输出: 6 
解释: 整个序列均为摆动序列。

示例 2:

输入: [1,17,5,10,13,15,10,5,16,8]
输出: 7
解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。

示例 3:

输入: [1,2,3,4,5,6,7,8,9]
输出: 2

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length<1){
            return 0;
        }
        // 最大的摆动序列
        int down = 1;
        int up =1;
        int res = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                up = down+1;
            }else if(nums[i]<nums[i-1]){
                down = up + 1;
            }

            res = Math.max(down,up);
        }
        return res;
    }
}
```

### [2.Leetcode978最长湍流子数组](https://leetcode-cn.com/problems/longest-turbulent-subarray/)

当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

返回 A 的最大湍流子数组的长度。

 

示例 1：

输入：[9,4,2,10,7,8,8,1,9]
输出：5
解释：(A[1] > A[2] < A[3] > A[4] < A[5])

示例 2：

输入：[4,8,12,16]
输出：2

示例 3：

输入：[100]
输出：1

```java
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if(arr.length<=1){
            return arr.length;
        }
        // 两个状态
        int up = 1;
        int down = 1;
        int res = 1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                up = down + 1;
                down = 1;
            }else if(arr[i]<arr[i-1]){
                down = up + 1;
                up = 1;
            }else if(arr[i]==arr[i-1]){
                down = 1;
                up = 1;
            }
            res = Math.max(res,Math.max(up,down));
        }
        return res;
    }
}
```


