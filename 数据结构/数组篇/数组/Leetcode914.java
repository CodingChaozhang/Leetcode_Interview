
import java.util.*;

public class Leetcode914 {
	class Solution {
	    public boolean hasGroupsSizeX(int[] deck) {
	    	int len = deck.length;

	    	//用HashMap来存储
	    	HashMap<Integer, Integer> hashMap = new HashMap<>();
	    	for(int i=0;i<len;i++) {
	    		// 存在
	    		if(hashMap.containsKey(deck[i])) {
	    			hashMap.put(deck[i],hashMap.get(deck[i])+1);
	    		}else {
	    			// 不存在
	    			hashMap.put(deck[i],1);
	    		}
	    		
	    	}
	    	int res = 0;
	    	// 遍历HashMap
	    	for(Map.Entry<Integer,Integer> entry:hashMap.entrySet()) {
	    		// 求多个数的最大公约数    		
	    		int value = entry.getValue();
	    		res = gcd(res,value);
	    		if(res==1) {
	    			return false;
	    		}
	    		
	    	}
	    	return res>=2;
	    }
	    
	    // 辗转相除法
	    public int gcd(int a,int b) {
	    	return b==0?a:gcd(b,a%b);
	    }
	}
}
