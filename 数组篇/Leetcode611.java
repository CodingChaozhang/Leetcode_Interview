// 有效三角形的个数
import java.util.*;
public class Leetcode611 {
	class Solution {
		// 第一种方法
	    public int triangleNumber(int[] nums) {
	    	// 判断数组的长度
	    	if(nums.length<3) {
	    		return 0;
	    	}
	    	// 存储结果
	    	int res = 0;
	    	// 遍历
	    	for(int i=0;i<nums.length-2;i++) {
	    		for(int j=i+1;j<nums.length-1;j++) {
	    			for(int k=j+1;k<nums.length;k++) {
	    				if(nums[i]+nums[j]>nums[k]&&nums[i]+nums[k]>nums[j]&&nums[j]+nums[k]>nums[i]) {
	    					res++;
	    				}
	    			}
	    		}
	    	}
	    	return res;
	    }
	    
	    // 类似从数组中挑选3个数，但是可重复。
	    public int triangleNumber_2(int[] nums) {
	    	int res = 0;
	    	if(nums.length<3) {
	    		return res;
	    	}
	    	// 排序
	    	Arrays.sort(nums);
	    	// 对其遍历最长边 剩余两值左右指针
	    	for(int i=2;i<nums.length;i++) {
	    		int left = 0;
	    		int right = i-1;
	    		while(left<right) {
	    			// 三角形需满足条件2边大于第三边
		    		if(nums[left]+nums[right]>nums[i]) {
		    			// 那么从左边继续走 肯定还满足条件那么就有right-left
		    			// 即右边固定，左边都满足条件
		    			res += (right-left);
		    			// 查看别的满足条件的值
		    			right--;
		    		}else {
		    			// 此时不满足条件，调大最大值
		    			left++;
		    		}
	    		}
	    		
	    	}	    	
	    	return res;
	    }
	}
}
