// 合并区间
import java.util.*;
public class Leetcode056 {
	class Solution {
	    public int[][] merge(int[][] intervals) {
	    	// 先排序后遍历
	    	
	    	// 自定义排序
	    	Arrays.sort(intervals,(a,b)->(a[0]-b[0]));
	    	// 结果存储
	    	int n = intervals.length;
	    	int[][] res = new int[n][2];
	    	// 定义结果的索引
	    	int index = 0;
	    	
	    	// 开始遍历
	    	for(int i=0;i<intervals.length;i++) {
	    		int L = intervals[i][0];
	    		int R = intervals[i][1];
	    		
	    		// 查看是否需要合并
	    		if(index==0 || L>res[index-1][1]) {
	    			res[index][0] = L;
	    			res[index][1] = R;
	    			index++;
	    		}else {
	    			// 合并
	    			res[index-1][1] = Math.max(res[index-1][1], R);
	    		}
	    	}
	    	
	    	// 对后面多余的去除
	    	int[][] newres = new int[index][2];
	    	for(int i=0;i<index;i++) {
	    		for(int j=0;j<2;j++) {
	    			newres[i][j] = res[i][j];
	    		}
	    	}
	    	
	    	return newres;
	    }
	    
	    
	    // 辅助结构ArrayList来解题
	    public int[][] merge_2(int[][] intervals) {
	    	// 自定义排序
	    	Arrays.sort(intervals,(a,b)->(a[0]-b[0]));
	    	// 存储结构
	    	ArrayList<int[]> res = new ArrayList<>();
	    	// 遍历
	    	for(int i=0;i<intervals.length;i++) {
	    		int L = intervals[i][0];
	    		int R = intervals[i][1];
	    		
	    		if(res.size()==0 || L>res.get(res.size()-1)[1]) {
	    			// 不合并
	    			res.add(new int[] {L,R});
	    		}else {
	    			// 合并
	    			res.get(res.size()-1)[1] = Math.max(res.get(res.size()-1)[1], R);
	    		}	    		
	    	}
	    	// 转成数组
	    	return res.toArray(new int[res.size()][]);
	    }
	}
}
