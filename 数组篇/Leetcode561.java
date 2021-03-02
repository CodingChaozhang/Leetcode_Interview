import java.util.*;
public class Leetcode561 {
	// 数组拆分I
	class Solution {
	    public int arrayPairSum(int[] nums) {
	    	// 排序
	    	Arrays.sort(nums);
	    	// 和
	    	int sum = 0;
	    	for(int i=0;i<nums.length-1;i+=2) {
	    		sum += nums[i];
	    	}
	    	return sum;
	    }
	}
}
