// 插入区间
import java.util.*;
public class Leetcode057 {
	class Solution {
	    // 解题思路 可用辅助结构ArrayList
		public int[][] insert(int[][] intervals, int[] newInterval) {
	    	List<int[]> list = new ArrayList<>();
	    	// 对其遍历
	    	for(int[] interval:intervals) {
	    		list.add(new int[] {interval[0],interval[1]});
	    	}
	    	list.add(new int[] {newInterval[0],newInterval[1]});
	    	// 排序
	    	Collections.sort(list,(a,b)->(a[0]-b[0]));
	    	// 之后找寻结果
	    	List<int[]> res = new ArrayList<>();
	    	for(int[] cur:list) {
	    		// 如果为空添加
	    		if(res.isEmpty()) {
	    			res.add(cur);
	    		}else {
	    			// 里面有值了判断是否需要合并
	    			int[] last = res.get(res.size()-1);
	    			if(cur[0]>last[1]) {
	    				res.add(cur);
	    			}else {
	    				res.get(res.size()-1)[1] = Math.max(last[1], cur[1]);
	    			}
	    		}
	    	}
	    	return res.toArray(new int[res.size()][]);
	    }
	}
}
