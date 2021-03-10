class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        // 临时存储的值
        List<Integer> temp = new ArrayList<>();
        // 从第0处开始
        dfs(nums,temp,0);
        return res;
    }

    public void dfs(int[] nums,List<Integer> temp,int index){
        // 直接加入
        res.add(new ArrayList<>(temp));

        // 全部遍历 变量则长度不一
        for(int i=index;i<nums.length;i++){
            temp.add(nums[i]);
            // 子集这里是i+1
            dfs(nums,temp,i+1);
            temp.remove(temp.size()-1);
        }

    }
}