// 在排序数组中查找数字I
public class Leetcode_Offer_53 {
	class Solution {
	    // 找到一个数字在排序数组出现的次数
		public int search(int[] nums, int target) {
	    	// 解题思路找其左右边界
			int leftIndex = binarySearch_Left(nums,target);
			if(leftIndex==-1) {
				return 0;
			}
			int rightIndex = binarySearch_Right(nums,target);
			return rightIndex-leftIndex+1;
	    }
		// 找左边界
		public int binarySearch_Left(int[] nums,int target) {
			int left = 0;
			int right = nums.length-1;
			while(left<=right) {
				int mid = left + ((right-left)>>1);
				if(nums[mid]>=target) {
					right--;
				}else {
					left++;
				}
			}
			if(left>=nums.length||nums[left]!=target) {
				return -1;
			}
			return left;
		}
		// 找右边界
		public int binarySearch_Right(int[] nums,int target) {
			int left = 0;
			int right = nums.length-1;
			while(left<=right) {
				int mid = left + ((right-left)>>1);
				if(nums[mid]<=target) {
					left++;
				}else {
					right--;
				}
			}
			if(right<0||nums[right]!=target) {
				return -1;
			}
			return right;
		}
	}
}
