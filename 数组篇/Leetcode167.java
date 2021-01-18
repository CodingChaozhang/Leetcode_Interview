// 两数之和II-输入有序数组
// 解题思路：双指针来解题，双指针的关键是从left和right从哪里开始算
public class Leetcode167 {
	class Solution {
	    public int[] twoSum(int[] numbers, int target) {
	    	int left = 0;
	    	int right = numbers.length-1;
	    	while(left<=right) {
	    		if(numbers[left]+numbers[right]==target) {
	    			return new int[] {left+1,right+1};
	    		}else if(numbers[left]+numbers[right]>target) {
	    			right--;
	    		}else if(numbers[left]+numbers[right]<target) {
	    			left++;
	    		}
	    	}
	    	return new int[] {-1,-1};
	    }
	}
}
