// 汇总区间
import java.util.*;
public class Leetcode228 {
	class Solution {
	    public List<String> summaryRanges(int[] nums) {
	    	// 结果存储
	    	List<String> res = new ArrayList<>();
	    	// 对其遍历
	    	int left = 0;
	    	int right = 0;
	    	int flag = 0;
	    	while(right<nums.length) {
	    		// 判断+1是否等
	    		flag = 0;
	    		while(nums[right]==nums[left]+flag&&right<nums.length) {
	    			flag++;
	    			right++;
	    		}
	    		// 开始计算
	    		if(right-left==1) {
	    			// 单独一个区间
	    			res.add(nums[left]+"");
	    		}else {
	    			// 两个区间
	    			res.add(nums[left]+"->"+nums[right-1]);
	    		}
	    		left = right;
	    	}
	    	return res;
	    	
	    	
	    }
	}
}
