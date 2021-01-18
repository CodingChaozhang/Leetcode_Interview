public class Leetcoode015 {
	class Solution {
		// 解法一、暴力解法
	    public List<List<Integer>> threeSum(int[] nums) {
	            HashSet res = new HashSet();
	            Arrays.sort(nums);
		    	for(int i=0;i<nums.length-2;i++) {
		    		for(int j=i+1;j<nums.length-1;j++) {
		    			for(int k=j+1;k<nums.length;k++) {
		    				if(nums[i]+nums[j]+nums[k]==0) {
		    					List<Integer> res_temp = new ArrayList<>();
	                            res_temp.add(nums[i]);
	                            res_temp.add(nums[j]);
	                            res_temp.add(nums[k]);
	                            res.add(res_temp);
		    				}else if(nums[i]>0 || nums[i]+nums[j]>0 || nums[i]+nums[j]+nums[k]>0){
	                            break;
	                        }
		    			}
		    		}
		    	}
		    	
		    	return new ArrayList<>(res);
	    }
	    
	    // 解法二、排序+双指针的解法
        public List<List<Integer>> threeSum_2(int[] nums) {
        	 // 结果去重
        	HashSet res = new HashSet();
        	// 继续优化 是否可以剪枝
        	
        	// 先判断数组
        	if(nums.length<3) {
        		return new ArrayList<>(res);
        	}
        	// 排序
        	Arrays.sort(nums);
        	// 双指针解法 定一个 
        	for(int i=0;i<nums.length-2;i++) {
        		int left = i+1;
        		int right = nums.length-1;
        		
        		// 去重
        		if(i!=0&&nums[i]==nums[i-1]) {
        			continue;
        		}
        		
        		//剪枝
        		if(nums[i]>0) {
        			break;
        		}
        		// 剪枝
        		if(nums[i]+nums[i+1]+nums[i+2]>0) {
        			break;
        		}
        		// 当前
        		if(nums[i]+nums[nums.length-1]+nums[nums.length-2]<0) {
        			continue;
        		}
        		// 循环
        		while(left<right) {
            		int curNum = nums[i] + nums[left] + nums[right];
        			if(curNum>0) {
            			right--;
            		}else if(curNum<0) {
            			left++;
            		}else if(curNum==0) {
                        // 结果
            			List<Integer> res_temp = new ArrayList<>();
            			res_temp.add(nums[i]);
            			res_temp.add(nums[left]);
            			res_temp.add(nums[right]);
                        res.add(res_temp);
                        // 走步
                        left++;
                        right--;
            		}
        		}
        	}
        	return new ArrayList<>(res);
        }
	}
}
