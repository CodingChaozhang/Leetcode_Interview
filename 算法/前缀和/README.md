### 前缀和思路[1.Leetcode724寻找数组的中心索引](https://leetcode-cn.com/problems/find-pivot-index/)

给你一个整数数组 nums，请编写一个能够返回数组 “中心索引” 的方法。

数组 中心索引 是数组的一个索引，其左侧所有元素相加的和等于右侧所有元素相加的和。

如果数组不存在中心索引，返回 -1 。如果数组有多个中心索引，应该返回最靠近左边的那一个。

注意：中心索引可能出现在数组的两端。

 

示例 1：

输入：nums = [1, 7, 3, 6, 5, 6]
输出：3
解释：
中心索引是 3 。
左侧数之和 (1 + 7 + 3 = 11)，
右侧数之和 (5 + 6 = 11) ，二者相等。

示例 2：

输入：nums = [1, 2, 3]
输出：-1
解释：
数组中不存在满足此条件的中心索引。

示例 3：

输入：nums = [2, 1, -1]
输出：0
解释：
中心索引是 0 。
索引 0 左侧不存在元素，视作和为 0 ；
右侧数之和为 1 + (-1) = 0 ，二者相等。

> 解题思路：左边的值=右边的值即可

```java
class Solution {
    // 前缀和的解题思路
    public int pivotIndex(int[] nums) {
        int Sum = 0;
        for(int num:nums){
            Sum += num;
        }
        int leftSum = 0;
        for(int i=0;i<nums.length;i++){
            if(leftSum ==Sum-leftSum-nums[i]){
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}
```

### [前缀和的思路 1.Leetcode560和为K的连续子数组](https://leetcode-cn.com/problems/subarray-sum-equals-k/)

给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。

> 解题思路：前缀和的思路 解法类似于两数之和
>
> 建立map表用于存储每个连续子数组sum求和出现的次数，初始化为（0,1），表示和为0的连续子数组出现1次。
>
> sum的值是在对nums数组的循环中不断累加当前元素的，res的值则需要查找map中是否已存在sum-k的元素，也就是在查找此前所有从0项开始累加的连续子项和中有没有sum-k。
>
> 如果有的话，则说明从该项到当前项的连续子数组和必定为k，那么res则可以和这个sum的对应值，即这个sum出现的次数，相加得到新的res。
>
> 对于当前sum如果已存在与map中则其对应值+1，不存在则添加新项，初始值为1。

**(1) 暴力破解**

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        // 左边和右边即可
        int count = 0;
        int len = nums.length;

        // 临时的总和值
        int sum = 0;
        for(int left=0;left<len;left++){
            for(int right=left;right<len;right++){
                sum += nums[right];
                if(sum==k){
                    count++;
                }
            }
            // 重新置于0
            sum = 0;
        }
        return count;
    }
}
```



**(2) 前缀和**

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        // 前缀和
        int len = nums.length;
        int count = 0;
        // 前缀和赋值
        int[] preSum = new int[len+1];
        for(int i=0;i<len;i++){
            // 这里需要的注意，前缀和是从presum[1]开始填充的
            preSum[i+1] = preSum[i]+nums[i];
        }
        // 遍历
        for(int left=0;left<len;left++){
            for(int right=left;right<len;right++){
                // 通过前缀和得到值 即可以得到left-right之间的值
                if(preSum[right+1]-preSum[left]==k){
                    count++;
                }
            }
        }
        return count;
    }
}
```

**(3) hashMap+前缀和**

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        // 前缀和+hashMap来解题
        HashMap<Integer,Integer> dict = new HashMap<>();
        // 先放一个
        dict.put(0,1);
        int preSum = 0;
        // 结果
        int count = 0;
        for(int i=0;i<nums.length;i++){
            preSum += nums[i];
            if(dict.containsKey(preSum-k)){
                count += dict.get(preSum-k);
            }   
            dict.put(preSum,dict.getOrDefault(preSum,0)+1);
        }
        return count;
    }
}
```

### 2.Leetcoce1248 统计【优美子数组】

给你一个整数数组 nums 和一个整数 k。

如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。

请返回这个数组中「优美子数组」的数目。

 

示例 1：

输入：nums = [1,1,2,1,1], k = 3
输出：2
解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。

示例 2：

输入：nums = [2,4,6], k = 1
输出：0
解释：数列中不包含任何奇数，所以不存在优美子数组。
示例 3：

输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
输出：16

> 解题思路：类似于上述的和为k的连续子数组

```java
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        // 前缀和+hashMap来解题的思路
        HashMap<Integer,Integer> dict = new HashMap<>();
        dict.put(0,1);
        // 结果
        int count = 0;
        // 前缀和记录前oddSum
        int oddSum = 0;
        // 遍历
        for(int i=0;i<nums.length;i++){
            oddSum += nums[i] & 1;
            if(dict.containsKey(oddSum-k)){
                count += dict.get(oddSum-k);
            }
            dict.put(oddSum,dict.getOrDefault(oddSum,0)+1);
        }
        return count;
    }
}
```

### 3.[Leetcode523 连续的子数组和]

给定一个包含 非负数 的数组和一个目标 整数 k ，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n * k ，其中 n 也是一个整数。

 

示例 1：

输入：[23,2,4,6,7], k = 6
输出：True
解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。

> 暴力求和

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 暴力破解
        int count = 0;
        for(int left=0;left<nums.length;left++){
            for(int right=left+1;right<nums.length;right++){
                int sum = 0;
                // 计算
                for(int i=left;i<=right;i++){
                    sum+=nums[i];
                }
                // 
                if(sum==k || k!=0&&sum%k==0){
                   return true;
                }
            }
        }
        return false;
    }
}
```



> 前缀和

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 计算
        int len = nums.length;
        int[] preSum = new int[len+1];
        for(int i=0;i<len;i++){
            preSum[i+1] = preSum[i] + nums[i];
        }
        for(int left=0;left<len;left++){
            for(int right=left+1;right<len;right++){
                // 得到left-right之间的值
                int sum = preSum[right+1]-preSum[left];
                if(sum==k || (k!=0&&sum%k==0)){
                    return true;
                }
            }
        }
        return false;
    }
}
```

> hashmap+前缀和

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;

        // key：区间 [0..i] 里所有元素的和 % k
        // value：下标 i
        Map<Integer, Integer> map = new HashMap<>();
        // 理解初始化的意义
        map.put(0, -1);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }

        }
        return false;

    }
}
```

