class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 建立最小的优先级队列
        PriorityQueue<Integer> min_queue = new PriorityQueue<>((a,b)->(a-b));
        // 先放入k个元素
        for(int i=0;i<k;i++){
            min_queue.offer(nums[i]);
        }
        // k个元素之后
        for(int i=k;i<nums.length;i++){
            // 比较
            if(nums[i]>min_queue.peek()){
                min_queue.poll();
                min_queue.offer(nums[i]);
            }
        }
        return min_queue.peek();
    }
}