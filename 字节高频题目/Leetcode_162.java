class Solution {
    public int findPeakElement(int[] nums) {
        
        // 二分查找
        int l = 0;
        int r = nums.length-1;
        while(l<r){
            int mid = l + ((r-l)>>1);
            if(nums[mid]>nums[mid+1]){
                r= mid;
            }else{
                l = mid + 1;
            }
        }
        return l;
    }
}