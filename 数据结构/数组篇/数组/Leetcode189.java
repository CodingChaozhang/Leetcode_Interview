// 旋转数组
public class Leetcode189 {
	class Solution {
		// 粗暴的方法 直接新建一个数组，之前i的位置变为 (i+k)%nums.length
	    public void rotate(int[] nums, int k) {
	    	int[] arr = new int[nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		arr[(i+k)%nums.length]  = nums[i];
	    	}
	    	System.arraycopy(arr,0,nums,0,nums.length);
	    }
	    
	    //通过反转数组来实现，整体先反转，前半分翻转 后半部分翻转
	    // 确定前半分k%nums.length-1 即k=3 翻转的是0-2索引
	    // 1 2 3 4 5 6 7
	    // 7 6 5 4 3 2 1
	    // 5 6 7 4 3 2 1
	    // 5 6 7 1 2 3 4
	    public void rotate_2(int[] nums, int k) {
	    	int index = k%nums.length - 1;
	    	reverse(nums,0,nums.length-1);
	    	reverse(nums,0,index);
	    	reverse(nums,index+1,nums.length-1);
	    }
	    
	    // 翻转数组 左右双指针，同时移动 替换
	    public void reverse(int[] nums,int left,int right) {
	    	while(left<right) {
	    		int temp   = nums[left];
	    		nums[left] = nums[right];
	    		nums[right]= temp;
	    		
	    		left++;
	    		right--;
	    	}
	    	
	    }

	}
}
