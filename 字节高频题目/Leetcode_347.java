class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 用优先级队列结合HashMap
        // hashmap存储这个值和次数
        // 优先级队列获取次数
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int num:nums){
            hashMap.put(num,hashMap.getOrDefault(num,0)+1);
        }
        // 优先级队列存储键 比较通过hashMap 最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->(hashMap.get(a)-hashMap.get(b)));
        // 开始对hashMap遍历 entrySet keySet valueSet
        for(int key:hashMap.keySet()){
            // 以便往queue里面放入值
            if(queue.size()<k){
                // 如果里面的值小于直接放入
                queue.offer(key);
            }else{
                if(hashMap.get(key)>hashMap.get(queue.peek())){
                    // 排出来
                    queue.poll();
                    queue.offer(key);
                }
            }            
        }
        // 对priorityqueue排出来
        int[] res = new int[k];
        for(int i=k-1;i>=0;i--){
            res[i] = queue.poll();
        }
        return res;

    }
}