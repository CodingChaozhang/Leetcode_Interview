class Solution {
    public int maxProfit(int[] prices) {
        // 维护当前值 之前的最小值
        int minPrice = prices[0];
        // 最大利润
        int maxProfit = 0;
        // 对其遍历
        for(int i=1;i<prices.length;i++){
            maxProfit = Math.max(maxProfit,prices[i]-minPrice);
            minPrice = Math.min(minPrice,prices[i]);
        }
        return maxProfit;
    }
}