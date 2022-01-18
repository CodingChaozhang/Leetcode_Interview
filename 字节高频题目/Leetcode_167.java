class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // 解题思路：hashMap 或者指针
        // 双指针
        int left = 0;
        int right = numbers.length-1;
        while(left<=right){
            int sum = numbers[left] + numbers[right];
            if(sum==target){
                return new int[]{left+1,right+1};
            }else if(sum>target){
                right--;
            }else if(sum < target){
                left++;
            }
        }
        return new int[]{-1,-1};
    }
}