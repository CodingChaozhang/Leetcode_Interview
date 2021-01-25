//从前序与中序序列中构造二叉树
import java.util.*;
public class Leetcode0105 {
	public class TreeNode{
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
	
	class Solution {
    	HashMap<Integer,Integer> hashMap = new HashMap<>();

		// 构建二叉树
		public TreeNode myBuildTree(int[] preorder,int preorder_left,int preoder_right,int[] inorder,int inorder_left,int inorder_right) {
			if(preorder_left>preoder_right) {
				return null;
			}
			// 找其根节点的索引
			int preorder_root = preorder_left;
			int inorder_root = hashMap.get(preorder[preorder_root]);
			
			// 构建二叉树走起
			TreeNode root = new TreeNode(preorder[preorder_root]);
			// 左子树的个数
			int left_size = inorder_root-inorder_left;
			root.left  = myBuildTree(preorder,preorder_left+1,preorder_left+left_size,
									 inorder, inorder_left,inorder_root-1);
			root.right = myBuildTree(preorder, preorder_left+left_size+1, preoder_right, 
									 inorder, inorder_root+1, inorder_right);
			return root;
			
			
		}
	    public TreeNode buildTree(int[] preorder, int[] inorder) {
	    	//构建二叉树 第一部 先拿HashMap存储中序遍历的结果
	    	for(int i=0;i<inorder.length;i++) {
	    		hashMap.put(inorder[i],i);
	    	}
	    	return myBuildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
	    }
	}
}
