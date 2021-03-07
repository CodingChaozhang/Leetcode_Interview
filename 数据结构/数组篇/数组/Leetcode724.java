// 寻找数组的中心索引
import java.util.*;
public class Leetcode724 {
	class Solution {
	    public int pivotIndex(int[] nums) {
	    	// 前缀和
	    	// 先统计总和
	    	int total = Arrays.stream(nums).sum();
	    	// 左边和
	    	int sum = 0;
	    	for(int i=0;i<nums.length;i++) {
	    		if(sum==total-sum-nums[i]) {
	    			return i;
	    		}
	    		sum += nums[i];
	    	}
	    	return -1;
	    }
	}
}
