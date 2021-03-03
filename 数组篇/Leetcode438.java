import java.util.*;
public class Leetcode0438 {
	class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // 对其统计
	    	int[] freq = new int[26];
	    	for(int i=0;i<p.length();i++) {
	    		freq[p.charAt(i)-'a']++;
	    	}
	    	// 滑窗
	    	int l = 0;
	    	int r = 0;
	    	List<Integer> res = new ArrayList<>();
	    	while(r<s.length()) {
	    		// 扩张
	    		freq[s.charAt(r)-'a']--;
	    		while(freq[s.charAt(r)-'a']<0) {
	    			// 移动
	    			freq[s.charAt(l)-'a']++;
	    			l++;
	    		}
                r++;

	    		if(r-l==p.length()){
	    			res.add(l);
	    		}
	    		
	    	}
	    	return res;
    }
}

	class Solution {
	    public List<Integer> findAnagrams(String s, String p) {
	    	// 统计次数
	    	// 起到hashMap的作用了
	    	int[] need = new int[26];
	    	for(int i=0;i<p.length();i++) {
	    		need[p.charAt(i)-'a']++;
	    	}
	    	// 结果
	    	List<Integer> res = new ArrayList<>();
	    	// 对其遍历
	    	for(int i=0;i<=s.length()-p.length();i++) {
	    		
	    		int j=i;
	    		// 复制数组
	    		int[] temp = Arrays.copyOf(need,26);
	    		for(;j<s.length()&&j<i+p.length();j++) {
	    			// 开始统计
	    			if(--temp[s.charAt(j)-'a']<0){
	    				break;
	    			}
	    		}
	    		if(j>=i+p.length()) {
	    			res.add(i);
	    		}
	    	}
	    	return res;
	    	
	    	
	    }
	}
}
