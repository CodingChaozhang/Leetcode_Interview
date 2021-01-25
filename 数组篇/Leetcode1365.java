// 有多少小于当前数字的数字
import java.util.*;
public class Leetcode1365 {
	class Solution {
		// 暴力破解法
	    public int[] smallerNumbersThanCurrent(int[] nums) {
	    	// 对每个数都对其统计
	    	int[] res = new int[nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		int count = 0;
	    		for(int j=0;j<nums.length;j++) {
	    			if(nums[i]>nums[j]) {
	    				count++;
	    			}
	    		}
	    		res[i] = count;
	    	}
	    	return res;
	    }
	    // 排序来解题 但需一个辅助结构来记录之前的索引
	    public int[] smallerNumbersThanCurrent_2(int[] nums) {
	    	// 第一维度记录原来数据 第二维度记录之前的索引
	    	int[][] new_nums = new int[nums.length][nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		new_nums[i][0] = nums[i];
	    		new_nums[i][1] = i;
	    	}
	    	// 对其按照第一维度排序
	    	Arrays.sort(new_nums,(a,b)->(a[0]-b[0]));
	    	
	    	//结果存储
    		int prev = -1;
    		int[] res = new int[nums.length];
	    	// 接下来统计可
	    	for(int i=0;i<new_nums.length;i++) {
	    		// 第一个数或者两个数同
	    		if(prev==-1 || new_nums[i][0]!=new_nums[i-1][0]) {
	    			prev = i;
	    		}
	    		res[new_nums[i][1]] = prev;
	    	}
	    	return res;
	    }
	    // 计数排序 桶排序
	    public int[] smallerNumbersThanCurrent_3(int[] nums) {
	    	// 观察数据范围为0-100之间
	    	int[] bucket = new int[101];
	    	// 统计每个数出现的频率
	    	for(int num:nums) {
	    		bucket[num]++;
	    	}
	    	// 之后对其相加
	    	for(int i=1;i<101;i++) {
	    		bucket[i] = bucket[i] + bucket[i-1];
	    	}
	    	// 结果
	    	int[] res = new int[nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		// 这里需考虑0
	    		res[i] = nums[i]==0?0:bucket[nums[i]-1];
	    	}
	    	return res;
	    }

	}
}
