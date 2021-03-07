
public class Leetcode_Interview_16_15 {
	class Solution {
	    public int[] masterMind(String solution, String guess) {
	    	// 统计桶1
	    	int[] bucket_s = new int[4];
	    	int[] bucket_g = new int[4];
	    	// 计算相等的次数
	    	int flag = 0;
	    	// 开始遍历
	    	int index = 0;
	    	// 两个数组
	    	char[] s_arr = solution.toCharArray();
	    	char[] g_arr = guess.toCharArray();
	    	// 对其遍历
	    	for(int i=0;i<s_arr.length;i++) {
	    		// 先查看是否相等
	    		if(s_arr[i]==g_arr[i]) {
	    			flag++;
	    			continue;
	    		}
	    		// 不相等统计次数
	    		if(s_arr[i]=='R') {
	    			bucket_g[0]++;
	    		}else if(s_arr[i]=='Y') {
	    			bucket_g[1]++;
	    		}else if(s_arr[i]=='G') {
	    			bucket_g[2]++;
	    		}else if(s_arr[i]=='B') {
	    			bucket_g[3]++;
	    		}
	    		
	    		if(g_arr[i]=='R') {
	    			bucket_s[0]++;
	    		}else if(g_arr[i]=='Y') {
	    			bucket_s[1]++;
	    		}else if(g_arr[i]=='G') {
	    			bucket_s[2]++;
	    		}else if(g_arr[i]=='B') {
	    			bucket_s[3]++;
	    		}
	    		
	    		
	    	}
	    	
	    	// 统计伪猜中的
	    	int fake_guess = 0;
	    	for(int i=0;i<4;i++) {
	    		fake_guess += Math.min(bucket_s[i], bucket_g[i]);
	    	}
	    	return new int[] {flag,fake_guess};
	    	
	    }
	}
}
