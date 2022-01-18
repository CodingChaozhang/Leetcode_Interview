class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 用数组存储之前的数
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        // 对数组进行遍历
        for(int i=0;i<nums.length;i++){

            // 不为空
            if(!hashMap.isEmpty()&&hashMap.containsKey(target-nums[i])){
                int index = hashMap.get(target-nums[i]);
                return new int[]{index,i};
            }else{
                hashMap.put(nums[i],i);
            }

        }
        return new int[]{-1,-1};

    }
}