class Solution {
    public int singleNumber(int[] nums) {
        // 用异或运算符
        int res = 0;
        for(int num:nums){
            res^=num;
        }        
        return res;
    }
}