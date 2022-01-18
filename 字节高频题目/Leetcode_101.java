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
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        // 两课子树
        return isValid(root.left,root.right);
    }
    // 判断其是否是对称二叉树
    public boolean isValid(TreeNode l,TreeNode r){
        //层序遍历
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(l);
        q2.offer(r);
        while(!q1.isEmpty()&&!q2.isEmpty()){
            l = q1.poll();
            r = q2.poll();
            // 对其判断
            if(l==null&&r==null){
                continue;
            }

            if(l==null||r==null){
                return false;
            }
            
            if(l.val!=r.val){
                return false;
            }
            // 直接入队列
            q1.offer(l.left);
            q2.offer(r.right);


            q1.offer(l.right);
            q2.offer(r.left);

        }
        return true;


    }


}