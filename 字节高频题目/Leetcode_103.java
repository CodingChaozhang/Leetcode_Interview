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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        // 队列辅助
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        int index  = 1;
        // 临时存储
        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            root = queue.poll();
            levelSize--;
            list.add(root.val);

            if(root.left!=null){
                queue.offer(root.left);
            }

            if(root.right!=null){
                queue.offer(root.right);
            }

            if(levelSize==0){
                if(index%2==0){
                    Collections.reverse(list);
                }
                res.add(new ArrayList<>(list));
                index++;
                levelSize = queue.size();
                list = new ArrayList<>();
            }
        }
        return res;

    }
}