// 较大分组的位置
import java.util.*;
public class Leetcode830 {
	class Solution {
	    public List<List<Integer>> largeGroupPositions(String s) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	char[] arr = s.toCharArray();
	    	// 遍历
	    	int left = 0;
	    	int right = 1;
	    	while(right<arr.length) {
	    		if(arr[right]==arr[left]) {
	    			right++;
	    		}else if(arr[right]!=arr[left]) {
	    			// 计算
	    			int len = right-left;
	    			if(len>=3) {
	    				// 符合条件记录
	    		    	List<Integer> res_temp = new ArrayList<>();
	    		    	res_temp.add(left);
	    		    	res_temp.add(right-1);
	    		    	res.add(res_temp);
	    			}
	    			//重新移动
	    			left = right;
	    			right = right+1;
	    		}
	    	}
	    	// 判断最后那段
	    	int len = right-left;
			if(len>=3) {
				// 符合条件记录
		    	List<Integer> res_temp = new ArrayList<>();
		    	res_temp.add(left);
		    	res_temp.add(right-1);
		    	res.add(res_temp);
			}
	    	
	    	return res;
	    }
	    
	    public List<List<Integer>> largeGroupPositions_2(String s) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	char[] arr = s.toCharArray();
	    	int left = 0;
	    	while(left<arr.length) {
	    		int count = 0;
	    		while(left+count<arr.length&&arr[left]==arr[left+count]) {
	    			count++;
	    		}
	    		if(count>=3) {
	    			res.add(Arrays.asList(left,left+count-1));
	    		}
	    		left = left+count;
	    	}
	    	return res;
	    }

	}
}
