// 组合总和II
import java.util.*;
public class Leetcode040 {
	class Solution {
	    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> path = new ArrayList<>();
	    	// 排序 剪枝
	    	Arrays.sort(candidates);
	    	
	    	backtrack(candidates,0,0,target,path,res);
	    	return res;
	    }
	    public void backtrack(int[] candidates,int index,int sum,int target,List<Integer> path,List<List<Integer>> res) {
	    	// 判断是否满足条件了
	    	if(sum==target) {
	    		// 拷贝一份path防止影响
	    		res.add(new ArrayList<>(path));
	    		return;
	    	}
	    	// for循环
	    	for(int i=index;i<candidates.length;i++) {
	    		// 去重
	    		if(i>index&&candidates[i]==candidates[i-1]) {
	    			continue;
	    		}
	    		// 临时值
	    		int temp = sum + candidates[i];
	    		// 判断满足条件
	    		if(temp<=target) {
	    			path.add(candidates[i]);
		    		backtrack(candidates, i+1, temp, target, path, res);
		    		path.remove(path.size()-1);
	    		}else {
	    			break;
	    		}
	    	}    	
	    }
	}
}
