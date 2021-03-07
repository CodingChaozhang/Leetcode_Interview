class Solution {
	    // 解题一、暴力解法
		public List<List<Integer>> fourSum(int[] nums, int target) {
	    	// 结果
			HashSet res = new HashSet();
			if(nums.length<4) {
				return new ArrayList<>();
			}
			// 暴力
			for(int i=0;i<nums.length-3;i++) {
				for(int j=i+1;j<nums.length-2;j++) {
					for(int k=j+1;k<nums.length-1;k++) {
						for(int l=k+1;l<nums.length;l++) {
							if(nums[i]+nums[j]+nums[k]+nums[l]==target) {
								// 临时结果
								List<Integer> res_temp = new ArrayList<>();
								res_temp.add(nums[i]);
								res_temp.add(nums[j]);
								res_temp.add(nums[k]);
								res_temp.add(nums[l]);
								res.add(res_temp);
							}
						}
					}
				}
			}
			
			
			return new ArrayList<>(res);
	    }
		
		
		
		// 解题二、排序+双指针定
		public List<List<Integer>> fourSum_2(int[] nums, int target) {
			// 四个值定下两个 才能定下剩下两个
			HashSet res = new HashSet();
			if(nums.length<4) {
				return new ArrayList<>(res);
			}
			 // 排序
            Arrays.sort(nums);
			// 对其循环两值
			for(int i=0;i<nums.length-3;i++) {
				for(int j=i+1;j<nums.length-2;j++) {
					// 左右指针
					int left = j+1;
					int right = nums.length-1;
					// 剪枝
					// 最小值
					if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target) {
						break;
					}
					// 最大值
					if(nums[i]+nums[j]+nums[nums.length-1]+nums[nums.length-2]<target) {
						continue;
					}
					
					// 循环判断剩下中间的
					while(left<right) {
						int curNum = nums[i] + nums[j] + nums[left] + nums[right];
						if(curNum==target) {
							List<Integer> res_temp = new ArrayList<>();
							res_temp.add(nums[i]);
							res_temp.add(nums[j]);
							res_temp.add(nums[left]);
							res_temp.add(nums[right]);
							res.add(res_temp);
							left++;
							right--;
 						}else if(curNum>target) {
							right--;
						}else if(curNum<target) {
							left++;
						}
					}
					
				}
			}
			
			
			return new ArrayList<>(res);
		}
		 
		 
	}