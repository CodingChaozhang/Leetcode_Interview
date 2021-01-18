package com.lcz.leetcode;
import java.util.*;
// 杨辉三角
public class Leetcode118 {
	class Solution {
	    public List<List<Integer>> generate(int numRows) {
	    	// 结果
	    	List<List<Integer>> res = new ArrayList<>();
	    	int i = 1;
	    	// 临时值保存上一个的结果
			List<Integer> temp_past = new ArrayList<>();
	    	while(i<=numRows) {
	    		
    			List<Integer> temp = new ArrayList<>();
	    		// 内循环
	    		for(int j=1;j<=i;j++) {
	    			if(j==1) {
	    				temp.add(1);
	    			}else if(j==i) {
	    				temp.add(1);
	    			}else {
	    				// 其余的时候
	    				temp.add(temp_past.get(j-2)+temp_past.get(j-1));
	    			}
	    		}
	    		
	    		if(i!=1) {
    				//保存结果
	    			temp_past = temp;
    			}
	    		// 添加到结果中
	    		res.add(temp);
	    		i++;
	    	}
	    	
	    	return res;
	    }
	}
}
