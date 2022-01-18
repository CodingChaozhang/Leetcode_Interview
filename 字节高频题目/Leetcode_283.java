class Solution {
    public void moveZeroes(int[] nums) {
        // 等于0的移动到数组的末尾，保持非零元素的相对顺序
        int less = -1;
        int R = nums.length;
        int L = 0;
        while(L<R){
            if(nums[L]!=0){
                //交换
                swap(nums,++less,L++);
            }else if(nums[L]==0){
                L++;
            }
        }
    }

    // 交换
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }
}