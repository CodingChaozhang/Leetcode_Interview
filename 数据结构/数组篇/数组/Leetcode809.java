// 情感丰富的文字
public class Leetcode809 {
	class Solution {
	    public int expressiveWords(String S, String[] words) {
	    	char[] dict = S.toCharArray();
	    	int res = 0;
	    	for(String word_str:words) {
	    		// 转换
	    		char[] word = word_str.toCharArray();
	    		// 对其进行比较
	    		if(compare(dict,word)) {
	    			res++;
	    		}
	    	}
	    	return res;
	    }
	    // 对其比较
	    public boolean compare(char[] dict,char[] word) {
	    	int len1 = dict.length;
	    	int len2 = word.length;
	    	int i=0,j=0;
	    	while(i<len1&&j<len2) {
	    		int cur1 = i;
	    		int cur2 = j;
	    		// 当前字符
	    		if(dict[i]!=word[j]) {
	    			return false;
	    		}
	    		// 相等的话 统计长度
	    		while(i<len1&&dict[cur1]==dict[i]) {
	    			i++;
	    		}
	    		while(j<len2&&word[cur2]==word[j]) {
	    			j++;
	    		}
	    		// 长度 并且i和j也走动了
	    		cur1 = i-cur1;
	    		cur2 = j-cur2;
	    		if(cur1==cur2) {
	    			// 继续走
	    			continue;
	    		}else if(cur1<cur2 || cur1<3) {
	    			return false;
	    		}
	    		
	    	}
	    	// 判断走完了吗
	    	return i==len1&&j==len2;
	    }
	    	
	    
	}
}
