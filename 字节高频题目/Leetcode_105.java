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
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 对中序存储
        for(int i=0;i<inorder.length;i++){
            hashMap.put(inorder[i],i);
        }
        return buildMyTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    // 对其构建
    public TreeNode buildMyTree(int[] preorder,int preorder_left,int preorder_right,int[] inorder,int inorder_left,int inorder_right){
        // 递归结束条件
        if(inorder_right<inorder_left){
            return null;
        }
        // 找根节点
        int root_value = preorder[preorder_left];
        // 构建根节点
        TreeNode root = new TreeNode(root_value);
        // 在中序遍历中
        int inorder_root = hashMap.get(root_value);
        // 长度
        int left_size = inorder_root - inorder_left;

        // 递归构建
        root.left = buildMyTree(preorder,preorder_left+1,preorder_left+left_size,inorder,inorder_left,inorder_root-1);
        root.right = buildMyTree(preorder,preorder_left+left_size+1,preorder_right,inorder,inorder_root+1,inorder_right);

        return root;

    }
}