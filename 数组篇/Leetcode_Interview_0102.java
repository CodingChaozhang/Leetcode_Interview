// 判断是否互为字符重排
import java.util.*;
public class Leetcode_Interview_0102 {
	class Solution {
	    public boolean CheckPermutation(String s1, String s2) {
	    	// 转成数组
	    	char[] arr_1 = s1.toCharArray();
	    	char[] arr_2 = s2.toCharArray();
	    	// 判断长度
	    	if(arr_1.length!=arr_2.length) {
	    		return false;
	    	}
	    	// 两个HashMap即可
	    	HashMap<Character,Integer> hashMap1 = new HashMap<>();
	    	HashMap<Character,Integer> hashMap2 = new HashMap<>();
	    	for(int i=0;i<arr_1.length;i++) {
	    		hashMap1.put(arr_1[i],hashMap1.getOrDefault(arr_1[i],0)+1);
	    		hashMap2.put(arr_2[i],hashMap2.getOrDefault(arr_2[i], 0)+1);
	    	}
	    	// 遍历
	    	for(int i=0;i<arr_1.length;i++) {
	    		if(!hashMap2.containsKey(arr_1[i])||hashMap2.get(arr_1[i])!=hashMap1.get(arr_1[i])) {
	    			return false;
	    		}
	    	}
	    	return true;
 	    }
	}
}
