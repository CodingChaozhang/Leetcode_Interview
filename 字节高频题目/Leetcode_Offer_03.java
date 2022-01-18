class Solution {
    public int findRepeatNumber(int[] nums) {
        // 原数组中排序查找0-n-1之间
        for(int i=0;i<nums.length;i++){
            while(i!=nums[i]){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                // 交换
               swap(nums,i,nums[i]);
            }
        }
        // 最后返回
        return -1;
    }

    // 交换排序
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j] = temp;
    }
}