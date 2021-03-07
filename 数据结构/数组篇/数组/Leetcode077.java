// 组合
import java.util.*;
public class Leetcode077 {
	// 解题思路从1-n这个集合中选出k个子集即可
	class Solution {
		List<Integer> temp = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
	    public List<List<Integer>> combine(int n, int k) {
	    	// 回溯算法
	    	dfs(1,n,k);
	    	return res;
	    }
	    public void dfs(int cur,int n,int k) {
	    	// 长度限制即递归结束条件
	    	if(temp.size() + (n-cur+1) < k || temp.size()>k) {
	    		return;
	    	}
	    	// 满足条件的话
	    	if(temp.size()==k) {
	    		// 添加
	    		res.add(new ArrayList<>(temp));
	    		return;
	    	}
	    	
	    	// for循环
	    	for(int i=cur;i<=n;i++) {
	    		temp.add(i);
	    		dfs(i+1,n,k);
	    		temp.remove(temp.size()-1);
	    	}
	    	
	    }
	}
}
