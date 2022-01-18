class Solution {
    public int maxSubArray(int[] nums) {
        // 最大子序和 用动态规划的思路
        int[] dp = new int[nums.length];
        int maxValue = nums[0];
        dp[0] = nums[0];
        // 遍历
        for(int i=1;i<nums.length;i++){
            // 转移方程最大值 要不就是当前值 要不就是前一个+当前值
            dp[i] = Math.max(nums[i],dp[i-1]+nums[i]);
            maxValue = Math.max(maxValue,dp[i]);
        }
        return maxValue;
    }
}