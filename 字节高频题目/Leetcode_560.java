class Solution {
    public int subarraySum(int[] nums, int k) {
        // 前缀和前缀 次数
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        // 存放值
        hashMap.put(0,1);
        // 次数
        int count = 0;
        int preSum = 0;
        // 开始遍历
        for(int num:nums){
            preSum += num;
            // 存在就记录
            if(hashMap.containsKey(preSum-k)){
                // 记录
                count += hashMap.get(preSum-k);
            }

            // 放置并更新
            hashMap.put(preSum,hashMap.getOrDefault(preSum,0)+1);
        }
        return count;
    }
}