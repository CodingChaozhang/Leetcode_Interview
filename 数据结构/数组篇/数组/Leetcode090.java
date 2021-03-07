// 子集II (子集可能重复 需要去重这一操作)
import java.util.*;
public class Leetcode090 {
	class Solution {
	    public List<List<Integer>> subsetsWithDup(int[] nums) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> path = new ArrayList<>();
	    	// 排序
	    	Arrays.sort(nums);
	    	backtrack(nums,0,path,res);
	    	return res;
	    }
	    public void backtrack(int[] nums,int index,List<Integer> path,List<List<Integer>> res) {
	    	res.add(new ArrayList<>(path));
	    	// for循环
	    	for(int i=index;i<nums.length;i++) {
	    		// 去重
	    		if(i>index&&nums[i]==nums[i-1]) {
	    			continue;
	    		}
	    		path.add(nums[i]);
	    		backtrack(nums, i+1, path, res);
	    		path.remove(path.size()-1);
	    	}
	    }
	}
}
