import java.util.*;
// 组合总和III 
public class Leetcode216 {
	class Solution {
	    // 解题思路:从9个数中挑选k个数，符合总和n则加入进去
		List<Integer> temp = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		public List<List<Integer>> combinationSum3(int k, int n) {
			//从9个数挑选k个数
			dfs(1,9,k,n);
			return res;
	    }
		// 回溯
		public void dfs(int cur,int n,int k,int sum) {
			// 递归截止条件
			if(temp.size()+(n-cur+1) < k || temp.size()>k) {
				return;
			}
			if(temp.size()==k) {
				int tempSum = 0;
				for(int num:temp) {
					tempSum+=num;
				}
				if(tempSum==sum) {
					// 加入
					res.add(new ArrayList<>(temp));
				}
				
			}
			// for循环
			for(int i=cur;i<=n;i++) {
				// 判断是否满足条件
				if(temp.size()<=k) {
					temp.add(i);
					dfs(cur+1, n, k, sum);
					temp.remove(temp.size()-1);
				}				
			}
		}
	}
}
