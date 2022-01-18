class Solution {
    // 结果
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        // 遍历过了
        boolean[] visited = new boolean[nums.length];
        // 对其回溯
        // 临时值
        List<Integer> list = new ArrayList<>();
        dfs(nums,list,visited);
        return res;
    }

    // 全部都遍历一遍
    public void dfs(int[] nums,List<Integer> list,boolean[] visited){
        if(list.size()==nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(visited[i]==true){
                // 遍历过一遍了
                continue;
            }
            
            visited[i] = true;
            list.add(nums[i]);

            dfs(nums,list,visited);
            
            visited[i] = false;
            list.remove(list.size()-1);

        }

    }
}