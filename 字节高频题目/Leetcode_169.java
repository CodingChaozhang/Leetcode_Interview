class Solution {
    public int majorityElement(int[] nums) {
        int candatite = nums[0];
        int count = 1;
        for(int num:nums){
            if(count==0){
                candatite = num;
            }
            count += num==candatite?1:-1;
        }
        return candatite;
    }
}