import java.util.*;
public class Leetcode119 {
	class Solution {
	    public List<Integer> getRow(int rowIndex) {
	    	// 全部的
	    	List<List<Integer>> res_all = new ArrayList<>();
	    	for(int i=0;i<=rowIndex;i++) {
	    		// 每一行的List
	    		List<Integer> res = new ArrayList<>();
	    		//每一行的值
	    		for(int j=0;j<=i;j++) {
	    			// 头是1 尾巴是1
	    			if(j==0 || j==i) {
	    				res.add(1);
	    			}else {
	    				//值(i,j)= (i-1,j)+(i-1,j-1)
	    				int lastValue_1 = res_all.get(i-1).get(j);
	    				int ladtValue_2 = res_all.get(i-1).get(j-1);
	    				res.add(lastValue_1+ladtValue_2);
	    			}
	    		}
	    		// 添加
	    		res_all.add(res);
	    	}
	    	// 返回
	    	return res_all.get(rowIndex);
	    }
	}
}
