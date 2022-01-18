class Solution {
    public int removeDuplicates(int[] nums) {
        // 新数组中的索引是从0开始的
        int left = 0;
        // 旧数组中的索引是从1开始的
        int right = 1;
        // 就数组遍历
        while(right<nums.length){
            // 相等
            if(nums[right]==nums[left]){
                right++;
            }else{
                // 不相等了
                nums[++left] = nums[right++];
            }
        }
        // 返回长度
        return left+1;
    }
    
    
}