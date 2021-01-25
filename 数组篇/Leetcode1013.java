// 将数组分成和相等的三个部分
public class Leetcode1013 {
	class Solution {
	    public boolean canThreePartsEqualSum(int[] arr) {
	    	// 指针先遍历第一层
	    	for(int i=0;i<arr.length-2;i++) {
	    		for(int j=i+2;j<arr.length;j++) {
	    			// 然后三等分
	    			int sum1=0,sum2=0,sum3 = 0;
	    			for(int k=0;k<=i;k++) {
	    				sum1 += arr[k];
	    			}
	    			for(int k=i+1;k<j;k++) {
	    				sum2 += arr[k];
	    			}
	    			for(int k=j;k<arr.length;k++) {
	    				sum3 += arr[k];
	    			}
	    			if(sum1==sum2&&sum2==sum3) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
	    }
	    // 左右指针的思路解题
	    public boolean canThreePartsEqualSum_2(int[] arr) {
	    	 // 三等分，遍历完整个数组求和 就知道每份多少了。
	    	int sum = 0;
	    	for(int i=0;i<arr.length;i++) {
	    		sum += arr[i];
	    	}
	    	// 如果不能整除 直接返回
	    	if(sum%3!=0) {
	    		return false;
	    	}
	    	int count = sum/3;
	    	// 左右指针开始解题
	    	int left = 0;
	    	int left_sum = arr[left];
	    	int right = arr.length-1;
	    	int right_sum = arr[right];
	    	while(left+1<right) {
	    		if(left_sum==count&&right_sum==count) {
	    			return true;
	    		}
	    		
	    		if(left_sum!=count) {
	    			left_sum += arr[++left];
	    		}
	    		
	    		if(right_sum!=count) {
	    			right_sum += arr[--right];
	    		}
	    		
	    	}
	    	return false;
	    }

	}
}
