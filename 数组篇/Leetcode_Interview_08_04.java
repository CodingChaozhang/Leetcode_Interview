// 幂集
import java.util.*;
public class Leetcode_Interview_08_04 {
	class Solution {
		
    	List<List<Integer>> res = new ArrayList<>();
	    public List<List<Integer>> subsets(int[] nums) {
	    	// 解题思路：dfs
	    	// 结果
	    	// 临时元素
	    	List<Integer> list = new ArrayList<>();
	    	
	    	dfs(nums,0,list);
	    	
	    	return res;
	    }
	    // 开始
	    public void dfs(int[] nums,int index,List<Integer> list) {
	    	//添加结果
	    	res.add(new ArrayList<>(list));
	    	
	    	//  备选
	    	for(int i=index;i<nums.length;i++) {
	    		list.add(nums[i]);
	    		dfs(nums,i,list);
	    		list.remove(list.size()-1);	    		
	    	}
	    	
	    }
	}
}
