// 三个数的最大乘积
import java.util.*;
public class Leetcode628 {
	class Solution {
		// 解题思路：排序 + 思考 如果数组全是正数那么 就是最后三个乘积 如果数组有负数，那么要不就是三个乘积 要不就是两个最小的负数*一个正数
	    public int maximumProduct(int[] nums) {
	    	Arrays.sort(nums);
	    	int len = nums.length;
	    	int res = Math.max(nums[len-1]*nums[len-2]*nums[len-3], nums[0]*nums[1]*nums[len-1]);
	    	return res;
	    }
	}
}
