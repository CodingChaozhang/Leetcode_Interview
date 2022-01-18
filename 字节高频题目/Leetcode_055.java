class Solution {
    public boolean canJump(int[] nums) {
        
        // 贪心算法，看其最大的跳跃长度
        int maxLen = nums[0];
        for(int i=0;i<nums.length;i++){
            // 可以访问的长度
            if(i<=maxLen){
                // 更新
                maxLen = Math.max(maxLen,i+nums[i]);
                // 走到最后一个即可
                if(maxLen>=nums.length-1){
                    return true;
                }
            }
        }
        return false;
        
    }
}