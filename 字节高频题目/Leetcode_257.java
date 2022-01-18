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
    public List<String> binaryTreePaths(TreeNode root) {
        // 层次遍历
        List<String> res = new ArrayList<>();
        // 双队列
        Queue<TreeNode> queue_node = new LinkedList<>();
        Queue<String>   queue_path = new LinkedList<>();
        // 对其遍历
        queue_node.offer(root);
        queue_path.offer(Integer.toString(root.val));
        while(!queue_node.isEmpty()){
            root = queue_node.poll();
            String str = queue_path.poll();
            // 叶子结点了
            if(root.left==null&&root.right==null){
                res.add(str);
            }

            if(root.left!=null){
                queue_node.offer(root.left);
                queue_path.offer(new StringBuilder(str).append("->").append(root.left.val).toString());
            }

            if(root.right!=null){
                queue_node.offer(root.right);
                queue_path.offer(new StringBuilder(str).append("->").append(root.right.val).toString());

            }
        }
        return res;

    }
   
}