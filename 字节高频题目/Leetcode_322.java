class Solution {
    public int coinChange(int[] coins, int amount) {
        // 背包问题 从里面取找其最少的数量
        int[] dp = new int[amount+1];
        //初始化
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        // 完全背包问题恰好等于amount
        for(int coin:coins){
            for(int i=coin;i<=amount;i++){
                // 转移方程
                dp[i] = Math.min(dp[i],dp[i-coin]+1);
            }
        }        
        // 返回结果
        return dp[amount]==amount+1?-1:dp[amount];
    }
}