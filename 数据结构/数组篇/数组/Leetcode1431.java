// 拥有最多糖果的孩子
import java.util.*;
public class Leetcode1431 {
	class Solution {
	    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
	    	List<Boolean> res = new ArrayList<>();
	    	// 先遍历找到其最大值
	    	int max = candies[0];
	    	for(int i=1;i<candies.length;i++) {
	    		max = Math.max(max, candies[i]);
	    	}
	    	// 之后统计结果 贪心算法
	    	for(int i=0;i<candies.length;i++) {
	    		if(candies[i]+extraCandies>=max) {
	    			res.add(true);
	    		}else {
	    			res.add(false);
	    		}
	    	}	    	
	    	return res;
	    }
	}
}
