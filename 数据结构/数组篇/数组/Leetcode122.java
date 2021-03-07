// 买卖股票的最佳时机II
public class Leetcode122 {
	class Solution {
	    public int maxProfit(int[] prices) {
	        // 股票的两个状态 持有 还是未持有
	    	// 动态规划 定义 初始化 以及转移
	    	// 第一天的状态初始化
	    	int dp0 = 0; // 未持有
	    	int dp1 = -prices[0]; // 持有
	    	for(int i=1;i<prices.length;i++) {
	    		// 后一天如果是未持有 前一天本来就未持有或者前一天持有了但是今天卖了
	    		int newdp0 = Math.max(dp0, dp1+prices[i]);
	    		// 后一天如果是持有 前一天本来就持有或者前一天未持有今天买了
	    		int newdp1 = Math.max(dp1, dp0-prices[i]);
	    		// 记录
	    		dp0 = newdp0;
	    		dp1 = newdp1;
	    	}
	    	// 最后一天的值
	    	return dp0;
	    }
	}
}
