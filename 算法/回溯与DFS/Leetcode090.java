class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 临时
        List<Integer> temp = new ArrayList<>();
        // 解集中需要去重
        Arrays.sort(nums);
        // 调用
        dfs(nums,temp,0);
        return res;
    }

    // 对其回溯算法
    public void dfs(int[] nums,List<Integer> temp,int index){
        res.add(new ArrayList<>(temp));

        for(int i=index;i<nums.length;i++){
            // 去重
            if(i>index&&nums[i]==nums[i-1]){
                continue;
            }

            temp.add(nums[i]);
            dfs(nums,temp,i+1);
            temp.remove(temp.size()-1);

        }

    }
}