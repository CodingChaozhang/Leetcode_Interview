// 最大矩形 动态规划解题思路
import java.util.*;
public class Leetcode085 {
	class Solution {
		// 利用之前求柱形的最大面积
	    public int maximalRectangle(char[][] matrix) {
            if(matrix.length==0||matrix[0].length==0){
                return 0;
            }
	    	int row = matrix.length;
	    	int col = matrix[0].length;
	    	int[] height = new int[col];
	    	// 结果
	    	int res = 0;
	    	for(int i=0;i<row;i++) {
	    		for(int j=0;j<col;j++) {
	    			if(matrix[i][j]=='1') {
	    				height[j]+=1;
	    			}else {
	    				height[j]=0;
	    			}
	    		}
                res = Math.max(res,largestRectangle(height));
	    	}
	    	return res;

	    }
	    
	    // 单调栈
	    public int largestRectangle(int[] heights) {
	    	// 结果返回
	    	int res = 0;
	    	Deque<Integer> stack = new ArrayDeque<>();
	    	// 方便计算扩充
	    	int[] new_heights = new int[heights.length+2];
            for (int i = 1; i < heights.length + 1; i++) {
                new_heights[i] = heights[i - 1];
            }	    	// 遍历
	    	for(int i=0;i<new_heights.length;i++) {
	    		while(!stack.isEmpty()&&new_heights[stack.peek()]>new_heights[i]) {
	    			// 计算每个i后面的连续递增，找到一个不满足的结束 不需要全部计算，单调栈剩余都是小数
	    			int curIndex = stack.pop();
	    			res = Math.max(res, (i-stack.peek()-1)*new_heights[curIndex]);
	    		}
	    		
	    		stack.push(i);
	    	}
	    	return res;
	    }

	}
}
