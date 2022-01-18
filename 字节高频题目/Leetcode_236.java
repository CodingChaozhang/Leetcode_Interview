/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 层次遍历
        HashMap<TreeNode,TreeNode> hashMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();

            if(root.left!=null){
                hashMap.put(root.left,root);
                queue.offer(root.left);
            }

            if(root.right!=null){
                hashMap.put(root.right,root);
                queue.offer(root.right);
            }

        }
        // 不一定 走的步数一样 对其寻找共同的父节点
        Set<TreeNode> set = new HashSet<>();
        while(p!=null){
            set.add(p);
            p = hashMap.get(p);
        }
        while(q!=null){
            if(set.contains(q)){
                return q;
            }
            q = hashMap.get(q);
        }
        return null;

    }
}