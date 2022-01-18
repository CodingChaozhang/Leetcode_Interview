class Solution {
    public int findRepeatNumber(int[] nums) {
        // 原地排序 + 指针
        for(int i=0;i<nums.length;i++){
            while(i!=nums[i]){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                // 交换
                int temp = nums[i];
                nums[i]   = nums[temp];
                nums[temp] = temp;
            }

        }
        return -1;
    }
}