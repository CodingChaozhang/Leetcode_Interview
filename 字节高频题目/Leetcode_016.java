class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        // 结果
        int closeNum = nums[0]+nums[1]+nums[2];
        
        // 第一重for循环
        for(int i=0;i<nums.length-2;i++){
            // 左指针
            int left = i+1;
            int right = nums.length-1;
            while(left<right){
                
                // 当前值
                int curNum = nums[i] + nums[left] + nums[right];

                // 记录最接近的值
                if(Math.abs(curNum-target)<Math.abs(closeNum-target)){
                    closeNum = curNum;
                }

                if(curNum==target){
                    return curNum;
                }else if(curNum>target){
                    right--;
                }else if(curNum<target){
                    left++;    
                }
            }
        }
        return closeNum;
    }
}