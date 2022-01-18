/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root==null){
            return res;
        }
        // 临时值
        int target = 0;
        List<Integer> list = new ArrayList<>();
        dfs(root,targetSum,list,target);
        return res;
    }

    public void dfs(TreeNode root,int targetSum,List<Integer> list,int target){
        // 截止条件
        if(root==null){
            return; 
        }
        // 前序遍历
        list.add(root.val);
        target += root.val;
        if(root.left==null&&root.right==null&&target==targetSum){
            res.add(new ArrayList<>(list));
        }
        dfs(root.left,targetSum,list,target);
        dfs(root.right,targetSum,list,target);

        target-=root.val;
        list.remove(list.size()-1);



    }
}