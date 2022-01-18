class Solution {
    public int maxProfit(int[] prices) {
        // 解题思路 当天股票存在两个状态 一个是未持有 一个是持有状态
        int dp0 = 0;
        int dp1 = -prices[0];
        // 对其遍历
        for(int i=1;i<prices.length;i++){
            // 之后一天的未持有 前一天未持有 前一天持有了 今儿卖了
            dp0 = Math.max(dp0,dp1+prices[i]);
            // 之后一天的持有 前一天未持有 今儿买了  前一天持有了 未操作
            dp1 = Math.max(dp0-prices[i],dp1);
        }
        // 返回 最后是未持有赚的多
        return dp0;
        
    }
}