// 查找常用字符
import java.util.*;
public class Leetcode1002 {
	class Solution {
	    public List<String> commonChars(String[] A) {
	    	List<String> res = new ArrayList<>();
	    	// 用数组来存储
	    	int[] dict = new int[26];
	    	// 对第一个遍历
	    	char[] dict_arr = A[0].toCharArray();
	    	for(int i=0;i<dict_arr.length;i++) {
	    		dict[dict_arr[i]-'a']++;
	    	}
	    	// 遍历其他的
	    	for(int i=1;i<A.length;i++) {
	    		char[] word_arr = A[i].toCharArray();
	    		int[] word = new int[26];
	    		for(int j=0;j<word_arr.length;j++) {
	    			word[word_arr[j]-'a']++;
	    		}
	    		// 统计
	    		for(int j=0;j<26;j++) {
	    			dict[j] = Math.min(dict[j],word[j]);
	    		}
	    	}
	    	// 得到最终结果
	    	for(int j=0;j<26;j++) {
	    		if(dict[j]>0) {
	    			// 次数
	    			for(int k=0;k<dict[j];k++) {
                        res.add(((char) ('a' + j) + ""));
	    			}
	    		}
	    	}
	    	return res;
	    	
	    }
	}
}
