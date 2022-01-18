class Solution {
    public int lengthOfLIS(int[] nums) {
        // 贪心算法
        int len = nums.length;
        //动态数组
        int[] dp = new int[len];
        dp[0] = 1;
        // 结果
        int res = 1;
        // 对剩下的遍历
        for(int i=1;i<nums.length;i++){
            dp[i] = 1;
            // 对其余遍历
            for(int j=0;j<i;j++){
                //看是否递增
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}