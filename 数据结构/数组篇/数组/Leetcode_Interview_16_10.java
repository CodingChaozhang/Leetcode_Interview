// 生存人数
public class Leetcode_Interview_16_10 {
	class Solution {
	    public int maxAliveYear(int[] birth, int[] death) {
	    	//  从1990到2000年 一共101个数，但是当年死亡也是计数的，所以是第二年
	    	int[] res = new int[102];
	    	// 开始遍历
	    	for(int i=0;i<birth.length;i++) {
	    		// 上车
	    		res[birth[i]-1900] += 1;
	    		// 下车
	    		res[death[i]-1900+1] -= 1;
	    	}
	    	// 统计数组
	    	int temp = 0;
	    	int result = 0;
	    	// 记录索引
	    	int index = 0;
	    	for(int i=0;i<res.length;i++) {
	    		// 前缀和
	    		temp += res[i];
	    		if(temp>result) {
	    			result = temp;
	    			index = i+1900;
	    		}
	    	}
	    	return index;
	    	
	    }
	}
}
