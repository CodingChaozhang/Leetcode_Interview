// 
import java.util.*;
public class Leetcode106 {
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(){
			
		}
		TreeNode(int val){
			this.val = val;
		}
		TreeNode(int val,TreeNode left,TreeNode right){
			this.val = val;
			this.left = left;
			this.right = right;
		}
		
	}
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
			HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
		    // 通过前序遍历来构建  中序 左根右 后序 左右根
			public TreeNode buildTree(int[] inorder, int[] postorder) {
				// 将中序的结果存在
				for(int i=0;i<inorder.length;i++) {
					hashMap.put(inorder[i],i);
				}
				// 调用另外一个
				return buildMyTree(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
		    }
			// 生成二叉树
			public TreeNode buildMyTree(int[] inorder,int inorder_left,int inorder_right,int[] postorder,int postorder_left,int postorder_right) {
				// 递归截止条件
				if(inorder_left>inorder_right ) {
					return null;
				}
	            TreeNode root = new TreeNode(postorder[postorder_right]);
				// 后序根结点的索引
				int root_postindex = postorder_right;
				int root_value  = postorder[postorder_right];
				//中序遍历中根结点的索引
				int root_inindex = hashMap.get(root_value);
				// 计算长度
				int len = root_inindex-inorder_left;
				// 开始
				root.left = buildMyTree(inorder, inorder_left, root_inindex-1, postorder, postorder_left, postorder_left+len-1);
				root.right = buildMyTree(inorder, root_inindex+1, inorder_right, postorder, postorder_left+len, postorder_right-1);
				
				
				
				return root;
			}
		}
}
