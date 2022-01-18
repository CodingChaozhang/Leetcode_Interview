class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        // 对其遍历
        for(int i=0;i<n;i++){
            while(i!=nums[i]-1){
                if(nums[i]==nums[nums[i]-1]){
                    return nums[i];
                }
                // 交换
                swap(nums,i,nums[i]-1);
            }
        }
        return -1;
    }
    // 交换
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }
}