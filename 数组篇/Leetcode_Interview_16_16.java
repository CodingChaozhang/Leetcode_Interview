// 部分排序
import java.util.*;
public class Leetcode_Interview_16_16 {
	class Solution {
	    public int[] subSort(int[] array) {
	    	if(array.length<2) {
	    		return new int[] {-1,-1};
	    	}
	    	
	    	// 解题思路 遍历从左到右，找最右边的值
	    	int left = -1;
	    	int right = -1;
	    	// 从左到右开始找
	    	int max = array[0];
	    	for(int i=1;i<array.length;i++) {
	    		if(array[i]>=max) {
	    			max = array[i];
	    		}else {
	    			right = i;
	    		}
	    			
	    	}
	    	// 从右到左开始找
	    	int min = array[array.length-1];
	    	for(int i=array.length-1;i>=0;i--) {
	    		if(array[i]<=min) {
	    			min = array[i];
	    		}else {
	    			left = i;
	    		}
	    	}
	    	return new int[] {left,right};
	    	
	    	
	    }
	}
}
