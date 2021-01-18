
public class Leetcode0121 {
	class Solution {
	    public int maxProfit(int[] prices) {
	        if(prices==null || prices.length<2) {
	        	return 0;
	        }
	    	int price = prices[0];
	    	int profit = 0;
	    	for(int i=1;i<prices.length;i++) {
	    		profit = Math.max(prices[i]-price, profit);
	    		price = Math.min(price, prices[i]);
	    	}
	    	return profit;
	    }
	}
}
