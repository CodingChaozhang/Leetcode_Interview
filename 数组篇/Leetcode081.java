class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            // 有重复元素
            while(left<right&&nums[left]==nums[left+1]){
                left++;
            }
            while(left<right&&nums[right]==nums[right-1]){
                right--;
            }
            
            int mid = left + ((right-left)>>1);

            if(nums[mid]==target){
                return true;
            }
            // 哪边有序
            if(nums[mid]>=nums[0]){
                // 左边有序
                if(nums[left]<=target&&target<nums[mid]){
                    // 在左边
                    right = mid-1;
                }else{
                    left = mid+1;
                }

            }else{
                // 右边有序
                if(nums[mid]<target&&target<=nums[right]){
                    // 在右边
                    left = mid + 1;
                }else{
                    right = mid-1;
                }

            }

        }
        return false;
    }
}