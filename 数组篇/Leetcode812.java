public class Leetcode812 {
	class Solution {
	    public double largestTriangleArea(int[][] points) {
	    	// 存储结果
	    	double res = 0;
	    	// for循环遍历 
	    	int len = points.length;
	    	for(int i=0;i<len-2;i++) {
	    		for(int j=i+1;j<len-1;j++) {
	    			for(int k=j+1;k<len;k++) {
	    				res = Math.max(res,area(points[i],points[j],points[k]));
	    			}
	    		}
	    	}
	    	return res;
	    }
	    
	    // 鞋带公式计算面积
	    public double area(int[] P,int[] Q,int[] R) {
	    	return 0.5 * Math.abs(P[0]*Q[1]+Q[0]*R[1]+R[0]*P[1]-
	    						  P[1]*Q[0]-Q[1]*R[0]-R[1]*P[0]);
	    }
	}
}
