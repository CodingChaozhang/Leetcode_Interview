class Solution {
    public int[] exchange(int[] nums) {
        // 荷兰国旗问题
        int less = -1;
        int L = 0;
        int more = nums.length;
        while(L<more){
            if(nums[L]%2==1){
                // 奇数 交换
                swap(nums,++less,L++);
            }else{
                // 偶数
                L++;
            }
        }
        return nums;
    }
    // 交换
    public void swap(int[] nums,int l,int r){
        int temp = nums[l];
        nums[l]  = nums[r];
        nums[r]  = temp;
    }
}