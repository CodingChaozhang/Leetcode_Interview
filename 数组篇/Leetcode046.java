// 全排列
import java.util.*;
public class Leetcode046 {
	class Solution {
		public List<List<Integer>> permute(int[] nums) {
			List<List<Integer>> res = new ArrayList<>();
			List<Integer> path = new ArrayList<>();
			
			// 所有数字都需要遍历一遍
			boolean[] visited = new boolean[nums.length];
			backtrack(nums,visited, path, res);
			return res;
	    }
		
		public void backtrack(int[] nums,boolean[] visited,List<Integer> path,List<List<Integer>> res) {
			if(path.size()==nums.length) {
				res.add(new ArrayList<>(path));
				return;
			}
			// 全部加入
			for(int i=0;i<nums.length;i++) {
				// 防止一个数字使用多遍
				if(visited[i]==true) {
					continue;
				}
				visited[i]=true;
				path.add(nums[i]);
				backtrack(nums,visited, path, res);
				visited[i]=false;
				path.remove(path.size()-1);
			}
		}
	}
}
