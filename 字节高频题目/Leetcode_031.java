class Solution {
    public void nextPermutation(int[] nums) {
        // 例如1 2 3 4 5 下一个更大的是 1 2 3 5 4
        int l = 0;
        int r = nums.length-1;
        // 第一次遍历查找
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]<nums[i+1]){
                l = i;
            }
        }
        // 第二次基于第一个的基础上
        for(int j=l+1;j<nums.length;j++){
            if(nums[j]>nums[l]){
                r = j;
            }
        }

        // 交换
        int temp = nums[l];
        nums[l]  = nums[r];
        nums[r]  = temp;

        // 排序 如果是最大值了，重新排序
        Arrays.sort(nums,l+1,nums.length);
    }
}