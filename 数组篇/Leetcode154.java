class Solution {
    public int findMin(int[] nums) {
        // 允许重复
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while(left<=right){
            mid = left + ((right-left)>>1);
            if(nums[mid]>nums[right]){
                // 左边有序
                left = mid + 1;
            }else if(nums[mid]<nums[right]){
                right = mid;
            }else if(nums[mid]==nums[right]){  
                // 重复
                right = right-1; 
            }
        }
        return nums[mid];
    }
}