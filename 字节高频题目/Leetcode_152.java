class Solution {
    public int maxProduct(int[] nums) {
        //解题思路：动态规划
        int n = nums.length;
        int[] maxNum = new int[n];
        int[] minNum = new int[n];
        // 初始化
        maxNum[0] = nums[0];
        minNum[0] = nums[0];
        // 结果
        int res = nums[0];
        // 转移方程
        for(int i=1;i<n;i++){
            maxNum[i] = Math.max(nums[i],Math.max(nums[i]*maxNum[i-1],nums[i]*minNum[i-1]));
            minNum[i] = Math.min(nums[i],Math.min(nums[i]*maxNum[i-1],nums[i]*minNum[i-1]));
            res = Math.max(res,maxNum[i]);
        }
        return res;
    }
}