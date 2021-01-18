// 种花问题
public class Leetcode605 {
	class Solution {
	    public boolean canPlaceFlowers(int[] flowerbed, int n) {
	    	// 只要能种 那么都种上 贪心思想
	    	// 什么情况下能种花？ 本身为空 左为空(当前为首) 右为空(当前为尾巴)
	    	// 遍历
	    	int res =  0;
	    	for(int i=0;i<flowerbed.length;i++) {
	    		if(flowerbed[i]==0 && (i==0 || flowerbed[i-1]==0) && (i==flowerbed.length-1 || flowerbed[i+1]==0)){
	    			// 可以种花
	    			flowerbed[i]=1;
	    			res++;
	    		}
	    	}
	    	return res>=n;
	    }
	}
}