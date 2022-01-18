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
    // 存储中序遍历
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 通过前序遍历和中序遍历恢复二叉树，需要递归
        for(int i=0;i<inorder.length;i++){
            hashMap.put(inorder[i],i);
        }
        return myTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
    // 递归
    public TreeNode myTree(int[] preorder,int preorder_left,int preorder_right,int[] inorder,int inorder_left,int inorder_right){
        // 递归截止条件
        if(inorder_left>inorder_right){
            return null;
        }
        // 前序遍历的结点索引下标，值
        int preorder_root = preorder_left;
        int preorder_root_value = preorder[preorder_left];
        // 中序遍历的结点索引下标，值
        int inorder_root = hashMap.get(preorder_root_value);

        // 新树
        TreeNode root = new TreeNode(preorder_root_value);

        // 左子树的大小
        int left_size = inorder_root - inorder_left;
        // 构建
        root.left     = myTree(preorder,preorder_left+1,          preorder_left+left_size,inorder,inorder_left,  inorder_root-1);
        root.right    = myTree(preorder,preorder_left+left_size+1,inorder_right          ,inorder,inorder_root+1,inorder_right);
        
        // 返回值
        return root;

    }
}