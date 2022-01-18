class Solution {
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }

        if(nums.length==1){
            return nums[0];
        }

        int n = nums.length;
        // 动态规划 贪心思想
        int[] dp = new int[n];
        // 只有一家就打劫该家
        dp[0] = nums[0];
        // 有两家就打劫多的
        dp[1] = Math.max(nums[0],nums[1]);
        // 别的
        for(int i=2;i<nums.length;i++){
            // 转移方程前一家已经被打劫了
            dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        // 返回
        return dp[n-1];
    }
}