// 组合总和
// 回溯法解决
import java.util.*;
public class Leetcode039 {
	class Solution {
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> res_temp = new ArrayList<>();
	    	// 回溯法调用
	    	// 排序 剪枝
	    	Arrays.sort(candidates);
	    	backtrack(candidates,0,0,target,res_temp,res);
	    	return res;
	    }
	    // 回溯法
	    public void backtrack(int[] candidates,int index,int sum,int target,List<Integer> res_temp,List<List<Integer>> res) {
	    	// 判断是否满足条件
	    	if(sum==target) {
	    		res.add(new ArrayList<>(res_temp));
	    		return;
	    	}
	    	
	    	// for循环
	    	for(int i=index;i<candidates.length;i++) {
	    		// 临时值
	    		int temp = candidates[i] + sum;
	    		if(temp<=target) {
	    			res_temp.add(candidates[i]);
		    		backtrack(candidates, i, temp, target, res_temp, res);
		    		res_temp.remove(res_temp.size()-1);
	    		}else {
	    			break;
	    		}
	    	}
	    	
	    }
	}
}
