// 黄金矿工
public class Leetcode1219 {
	class Solution {
	    // 回溯网格问题：任意位置出发，上下左右都可以走
		public int getMaximumGold(int[][] grid) {
	    	// 结果
			int res = 0;
			// 因为从任意一点出发
			int n = grid.length;
			int m = grid[0].length;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					res = Math.max(dfs(grid,i,j),res);
				}
			}
			return res;
	    }
		
		// 回溯
		public int dfs(int[][] grid,int i,int j) {
			// 递归结束条件
			if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
				return 0;
			}
			// 网格回溯 记录当前值 当前值已访问 恢复当前值
			int temp = grid[i][j];
			// 当前值已经被访问了
			grid[i][j] = 0;
			// 沿着继续走
			int up = dfs(grid,i,j-1);
			int down = dfs(grid,i,j+1);
			int left = dfs(grid,i-1,j);
			int right = dfs(grid,i+1,j);
			int max = Math.max(up, Math.max(right, Math.max(down, left)));
			// 恢复当前值
			grid[i][j] = temp;
			return grid[i][j]+max;
		}
	}
}
