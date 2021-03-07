// 使数组唯一的最小增量
import java.util.*;
public class Leetcode945 {
	class Solution {
		
		// 超时
	    public int minIncrementForUnique(int[] A) {
	    	// 用HashSet来存储
	    	HashSet<Integer> hashset = new HashSet<>();
	    	int res = 0;
	    	// 对其遍历
	    	for(int num:A) {
	    		// 判断是否存在与hashset
	    		if(hashset.contains(num)) {
	    			// 存在的话 使其不存在
	    			while(hashset.contains(num)) {
	    				num = num + 1;
	    				res += 1;
	    			}
	    			// 并在hashmap中更新该值
	    			hashset.add(num);
	    		}else {
	    			// 不存在
	    			hashset.add(num);
	    		}
	    		
	    	}
	    	return res;	
	    }
	    // 排序的思路
	    public int minIncrementForUnique_2(int[] A) {
	    	int res = 0;
	    	Arrays.sort(A);
	    	// 对其遍历
	    	for(int i=1;i<A.length;i++) {
	    		if(A[i]<=A[i-1]) {
	    			int temp = A[i];
	    			A[i] = A[i-1]+1;
	    			res += A[i]-temp;
	    		}
	    	}
	    	return res;
	    }

	}
}
