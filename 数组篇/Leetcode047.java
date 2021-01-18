// 全排列II
import java.util.*;
public class Leetcode047 {
	class Solution {
	    public List<List<Integer>> permuteUnique(int[] nums) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> path = new ArrayList<>();
	    	// 数组去重
	    	Arrays.sort(nums);
	    	// 统计次数
	    	boolean[] visited = new boolean[nums.length];
	    	backtrack(nums,visited,path,res);
	    	return res;
 	    }
	    public void backtrack(int[] nums,boolean[] visited,List<Integer> path,List<List<Integer>> res) {
	    	if(path.size()==nums.length) {
	    		res.add(new ArrayList<>(path));
	    		return;
	    	}
	    	//  遍历
	    	for(int i=0;i<nums.length;i++) {
	    		// 防止多次访问
	    		if(visited[i]==true) {
	    			continue;
	    		}
	    		// 防止数组重复
	    		if(i>0&&nums[i]==nums[i-1]&&visited[i-1]==false) {
	    			continue;
	    		}
	    		// 回溯
	    		visited[i] = true;
	    		path.add(nums[i]);
	    		backtrack(nums, visited, path, res);
	    		visited[i] = false;
	    		path.remove(path.size()-1);
	    		
	    	}
	    }
	}
}
