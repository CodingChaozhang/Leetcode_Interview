// 买卖股票的最佳时机III
public class Leetcode123 {
	class Solution {
	    // 买卖股票有限制次数 2次
		public int maxProfit(int[] prices) {
			if(prices.length==0||prices.length==1) {
				return 0;
			}
			// 未买入
			int dp0 = 0;
			// 第一次买入
			int dp1 = -prices[0];
			// 第一次卖出
			int dp2 = Integer.MIN_VALUE;
			// 第二次买入
			int dp3 = Integer.MIN_VALUE;
			// 第二次卖出
			int dp4 = Integer.MIN_VALUE;
			
			// 开始遍历
			for(int i=1;i<prices.length;i++) {
				dp0 = 0;
				//第一次买入：前方未买入 这次买入了
				dp1 = Math.max(dp1, dp0-prices[i]);
				//第一次卖出：第一次买入 今天卖了
				dp2 = Math.max(dp2, dp1+prices[i]);
				// 第二次买入 第一次卖出之后买入了
				dp3 = Math.max(dp3, dp2-prices[i]);
				// 第二次卖出  第二次买入之后卖出了
				dp4 = Math.max(dp4, dp3+prices[i]);
			}
			// 返回结果
			return Math.max(dp2, dp4);
	    }
	}
}
