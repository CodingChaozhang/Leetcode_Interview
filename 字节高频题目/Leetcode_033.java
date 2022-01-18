class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums,target);
    }

    // 二分查找
    public int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            // 判断
            if(nums[mid]==target){
                return mid;
            }

            if(nums[mid]>=nums[left]){
                // 左边有序
                if(nums[left]<=target&&target<nums[mid]){
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else{
                // 右边有序
                if(nums[mid]<target&&target<=nums[right]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }
        return -1;
    }
}