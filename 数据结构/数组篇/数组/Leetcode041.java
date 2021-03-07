// 缺失的第一个正数
import java.util.*;
public class Leetcode041 {
	class Solution {
	    public int firstMissingPositive(int[] nums) {
	    	// 用HashSet辅助，但是不符合常数数组的原则
	    	HashSet<Integer> hashset = new HashSet<>();
	    	for(int num:nums) {
	    		hashset.add(num);
	    	}
	    	// 从容器中查看
	    	for(int i=1;i<=nums.length;i++) {
	    		if(!hashset.contains(i)) {
	    			return i;
	    		}
	    	}
	    	return nums.length+1;
	    }
	    
	    //常数空间在原数组上直接操作
	    public int firstMissingPositive_2(int[] nums) {
	    	// 把每个数放到对应索引位置上 比如3放到索引位置2上，之后检查当前数和索引值是否等 不等则返回索引值+1
	    	
	    	int len = nums.length;
	    	for(int i=0;i<len;i++) {
	    		// 符合条件才放到对应索引位置上 有的数大于0或者len了
	    		while(nums[i]>0&&nums[i]<=len&&(nums[i]!=nums[nums[i]-1])) {
	    			swap(nums,i,nums[i]-1);
	    		}
	    	}
	    	
	    	// 之后检查
	    	for(int i=0;i<len;i++) {
	    		if(nums[i]!=i+1) {
	    			return i+1;
	    		}
	    	}
	    	return len+1;
	    }
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	}
}
