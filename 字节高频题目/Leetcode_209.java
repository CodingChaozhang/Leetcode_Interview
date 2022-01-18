class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        // 解题思路：有长度 有连续子数组 滑动窗口
        int left = 0;
        int right = 0;
        int n = nums.length;
        int minLength = nums.length+1;
        int tempSum = 0;
        while(right<n){
            tempSum += nums[right];
            while(tempSum>=target){
                // 记录长度
                minLength = Math.min(minLength,right-left+1);
                // 缩短
                tempSum -= nums[left];
                left++;
            }
            right++;
        }
        return minLength==nums.length+1?0:minLength;
    }
}