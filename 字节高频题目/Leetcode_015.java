class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 先排序
        Arrays.sort(nums);
        // 结果存储
        List<List<Integer>> res = new ArrayList<>();
        // 后for循环+双指针
        for(int i=0;i<nums.length-2;i++){

            // 去重
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }

            //剪枝
            int maxNum = nums[i] + nums[nums.length-1] + nums[nums.length-2];
            if(maxNum<0){
                continue;
            }
            int minNum = nums[i] + nums[i+1] + nums[i+2];
            if(minNum>0){
                break;
            }

            
            int left = i+1;
            int right = nums.length-1;
            // 循环
            while(left<right){
                // 当前值
                int curNum = nums[i] + nums[left] + nums[right];
                if(curNum==0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    // 满足条件的话如何移动
                    // 去重的移动
                    while(left<right&&nums[left+1]==nums[left]){
                        left++;
                    }
                    left++;
                    while(left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    right--;

                }else if(curNum>0){
                    right--;
                }else if(curNum<0){
                    left++;
                }
            }
        }
        return res;
    }
}