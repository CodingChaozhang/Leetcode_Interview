class Solution {
    // 子集
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        // 临时值
        List<Integer> list = new ArrayList<>();
        dfs(nums,0,list);
        return res;
    }
    // 回溯
    public void dfs(int[] nums,int index,List<Integer> list){
        res.add(new ArrayList<>(list));

        for(int i=index;i<nums.length;i++){
            list.add(nums[i]);
            dfs(nums,i+1,list);
            list.remove(list.size()-1);
        }
    }
}