// 合并两个有序数组
public class Leetcode088 {
	class Solution {
	    public void merge(int[] nums1, int m, int[] nums2, int n) {
	    	// 归并排序
	    	int p1 = 0;
	    	int p2 = 0;
	    	// 创建一个新数组
	    	int[] temp = new int[m+n];
	    	int index = 0;
	    	//开始遍历
	    	while(p1<m&&p2<n) {
	    		temp[index++] = nums1[p1]>nums2[p2]?nums2[p2++]:nums1[p1++];
	    	}
	    	while(p1<m) {
	    		temp[index++] = nums1[p1++];
	    	}
	    	while(p2<n) {
	    		temp[index++] = nums2[p2++];
	    	}
	    	// 重新赋值
	    	for(int i=0;i<temp.length;i++) {
	    		nums1[i] = temp[i];
	    	}
	    	
	    }
	    
	    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
	    	// 在原数组中进行操作
	    	int p1 = m-1;
	    	int p2 = n-1;
	    	// 新数组的索引
	    	int p = m+n-1;
	    	// 逆序操作
	    	while(p1>=0&&p2>=0) {
	    		nums1[p--] = nums1[p1]>nums2[p2]?nums1[p1--]:nums2[p2--];
	    	}
	    	// 如果nums2还有剩余
	    	while(p2>=0) {
	    		nums1[p--] = nums2[p2--];
	    	}
	    }
	}
}
