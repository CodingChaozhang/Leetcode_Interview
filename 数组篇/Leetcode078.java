public class Leetcode078 {
	class Solution {
		List<List<Integer>> res = new ArrayList<>();
	    public List<List<Integer>> subsets(int[] nums) {
	    	//子集 
	    	List<Integer> temp = new ArrayList<>();
	    	process(nums,0,temp);
	    	return res;
	    }
	    // 递归
	    public void process(int[] nums,int index,List<Integer> temp) {
	    	// 递归截止条件
	    	if(index==nums.length) {
	    		res.add(new ArrayList<Integer>(temp));
	    		return;
	    	}
	    	// 当前值
	    	temp.add(nums[index]);
	    	process(nums, index+1, temp);
	    	// 当前值不要
	    	temp.remove(temp.size()-1);
	    	process(nums, index+1, temp);
	    }
	}
}
