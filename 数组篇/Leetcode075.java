// 颜色分类 双指针的思路
public class Leetcode075 {
	class Solution {
	    public void sortColors(int[] nums) {
	    	int index = 0;
	    	int left = -1;
	    	int right = nums.length;
	    	while(index<right) {
	    		if(nums[index]==1) {
	    			index++;
	    		}else if(nums[index]<1) {
	    			swap(nums,index++,++left);
	    		}else if(nums[index]>1) {
	    			// 右边交换
	    			swap(nums,index,--right);
	    		}
	    	}
	    }
	    // 交换函数
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	}
}
