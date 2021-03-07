**二叉树的性质**

对于任何一棵非空二叉树，**如果叶子节点个数为n0, 度为2的节点个数为n2, 则有n0 = n2 + 1;**

**完全二叉树的性质**

度为1的节点要么是1个，要么是0个；

**面试题：**

如果一棵完全二叉树有768个节点，求叶子节点的个数

- 叶子节点个数为n0 = floor((n+1)/2)
- 非叶子节点个数为n1+n2=floor(n/2)

**比较策略**

![image-20210307155612469](imgs_tree\1.png)

## 二叉树的遍历

### 前序遍历

**递归方式**

```java
// 递归方式前序遍历
	public void preorder(Visitor<E> visitor) {
		if(visitor==null)return;
		preorder(root,visitor); 
	}
	private void preorder(Node<E> node,Visitor<E> visitor) {
		if(node==null || visitor.stop)return;
		
		visitor.stop = visitor.visit(node.element);
		preorder(node.left,visitor);
		preorder(node.right,visitor);
		
	}
	
	// 对元素的处理
	public static abstract class Visitor<E>{
		boolean stop;
		protected abstract boolean visit(E element);
	}
	
```



**非递归方式**

```java
public static void preOder(Node node){
    if(head!=null){
        Stack<Node> stack = new Stack<Node>();
        stack.add(head);
        while(!stack.isEmpty()){
            head = stack.pop();
            System. out.println(head.value + " ");
            if(head.right!=null){
                stack.push(head.right);
            }
            if(head.left!=null){
                stack.push(head.left);
            }
        }
    }
    System.out.println();
}
```

### 中序遍历

**递归方式**

```java
// 递归方式中序遍历
	public void inorder(Visitor<E> visitor) {
		if(visitor==null)return;
		inorder(root,visitor); 
	}
	private void inorder(Node<E> node,Visitor<E> visitor) {
		if(node==null || visitor.stop)return;
		inorder(node.left,visitor);
		visitor.stop = visitor.visit(node.element);
		inorder(node.right,visitor);
		
	}
```

**非递归方式**

当前节点不为空, 入栈，左节点

当前节点为空，栈弹出，打印，右节点

```java
public static void inorder(Node head){
	if(head!=null){
        Stack<Node> stack = new Stack<Node>();
        while(!stack.isEmpty()||head!=null){
            if(head!=null){
                stack.push(head);
                head = head.left;
            }else{
                head  = stack.pop();
                System.out.println(head.value + " ");
                head = head.right;
            }
        }
    }
    System.out.println();
}
```

### 后序遍历

**递归方式**

```java
// 递归方式后序遍历
		public void postorder(Visitor<E> visitor) {
			if(visitor==null)return;
			postorder(root,visitor); 
		}
		private void postorder(Node<E> node,Visitor<E> visitor) {
			if(node==null || visitor.stop)return;
			postorder(node.left,visitor);
			postorder(node.right,visitor);
			visitor.stop = visitor.visit(node.element);			
		}
```

**非递归方式**

```java
public static void postOrder(Node head){
	if(head!=null){
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while(!s1.isEmpty()){
            head = s1.pop();
            s2.push(head);
            if(head.left!=null){
                s1.push(head.left);
            }
            if(head.right!=null){
                s2.push(head.right);
            }
        }
        while(!s2.isEmpty()){
            System.out.print(s2.pop().value + " ");
        }
    }
    System.out.println();
}
```

### 层序遍历

```java
// 层序遍历
		public void levelOrder() {
			levelOrder(root);
		}
		private void levelOrder(Node<E> node) {
			Queue<Node<E>> queue = new LinkedList<>();
			queue.offer(node);
			while(!queue.isEmpty()) {
				node = queue.poll();
				System.out.print(node.element+" ");
				if(node.left!=null) {
					queue.offer(node.left);
				}
				
				if(node.right!=null) {
					queue.offer(node.right);
				}
			}
		}
```

## 二叉树的题目

### 1 翻转二叉树

```java
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
    public TreeNode invertTree(TreeNode root) {
        // 前序遍历
		if(root==null)
			return root;
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
    }
}
```

### 2判断是否为完全二叉树

```java
// 判断一棵树是否为完全二叉树
		public  boolean isComplete() {
			Queue<Node<E>> queue = new LinkedList<>();
			queue.offer(root);
			
			boolean leaf = true;
			while(!queue.isEmpty()) {
				Node<E> node = queue.poll();
				if(leaf&&!node.isLeaf()) {
					return false;
				}
				
				if(node.left!=null) {
					queue.offer(node.left);
				}else if(node.right!=null) {
					return false;
				}
				
				
				if(node.right!=null) {
					queue.offer(node.right);
				}else {
					leaf = true;
				}
			}
			return true;
		}
```

### 3.前序遍历+中序遍历构造二叉树

```java
package com.lcz.leetcode;
/**
 * 从前序和中序遍历序列构造二叉树
 * @author LvChaoZhang
 *
 */
import java.util.*;
public class Leetcode105 {
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	 private HashMap<Integer,Integer> hashMap;
	 private TreeNode myBuildTree(int[] preorder,int[] inorder,int preorder_left,int preorder_right,int inorder_left,int inorder_right) {
		 if(preorder_left>preorder_right) {
			 return null;
		 }
		 // 前序遍历的第一个结点是根节点
		 int preorder_root = preorder_left;
		 // 中序遍历中定位根节点
		 int inorder_root = hashMap.get(preorder[preorder_root]);
		 
		 // 根节点建立
		 TreeNode root = new TreeNode(preorder[preorder_root]);
		 // 得到左节点数量
		 int size_left_subtree = inorder_root-inorder_left;
		 root.left = myBuildTree(preorder, inorder, preorder_left+1, preorder_left+size_left_subtree, inorder_left, inorder_root-1);
		 root.right = myBuildTree(preorder, inorder, preorder_left+size_left_subtree+1, preorder_right, inorder_root+1, inorder_right);
		 return root;		 
	 }
	 public TreeNode buildTree(int[] preorder, int[] inorder) {
		 int n = preorder.length;
		 // 构造哈希映射，快速定位根节点
		 hashMap = new HashMap<Integer, Integer>();
		 for(int i=0;i<n;i++) {
			 hashMap.put(inorder[i],i);
		 }
		 return myBuildTree(preorder,inorder,0,n-1,0,n-1);
	 }
}

```

### 4.后序遍历+中序遍历构造二叉树

```java
package com.lcz.leetcode;
/**
 * 从中序与后续遍历序列构造二叉树
 * @author LvChaoZhang
 *
 */
import java.util.*;
public class Leetcode106 {
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	private HashMap<Integer,Integer> hashMap;
	
	private TreeNode myBuildTree(int[] inorder,int[] postorder,int inorder_left,int inorder_right,int postorder_left,int postorder_right) {
		if(inorder_left>inorder_right) {
			return null;
		}
		//  从后序遍历中找到根节点
		int postorder_root = postorder_right;
		// 从中序遍历中找到根节点
		int inorder_root = hashMap.get(postorder[postorder_right]);
		// 构建根节点
		TreeNode root = new TreeNode(postorder[postorder_right]);
		// 计算左节点的数量
		int left_size_subtree = inorder_root-inorder_left;
		
		// 递归构建
		root.left = myBuildTree(inorder, postorder, inorder_left, inorder_root-1, postorder_left, postorder_left+left_size_subtree-1);
		root.right = myBuildTree(inorder, postorder, inorder_root+1, inorder_right, postorder_left+left_size_subtree, postorder_right-1);
		return root;
	}
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		int n = inorder.length;
		// 构建哈希映射
		hashMap = new HashMap<>();
		for(int i=0;i<n;i++) {
			hashMap.put(inorder[i],i);
		}
		return myBuildTree(inorder,postorder,0,n-1,0,n-1);
    }
}

```



## 二叉搜索树

### 删除结点的步骤

- 删除叶子结点-度为0的结点

```java
node == node.parent.left
node.parent.left=null

node == node.parent.right
node.parent.right=null

node.parent == null
root = null
```

- 删除结点-度为1的结点

```java
// 用子节点替代原节点的位置
child是node.left或者child是node.right

// 用child替代node的位置
// 如果node是左子节点
child.parent = node.parent
node.parent.left = child

// 如果node是右子节点
child.parent = node.parent
node.parent.right = child

// 如果node是根结点
root = child
child.parent = null
```

- 删除结点-度为2的结点

// 删除元素
	public void remove(E element) {
		remove(node(element));
	}
	private void remove(Node<E> node) {
		if(node==null)
			return;
		size--;
		

```java
	// 度为2的结点
	if(node.hasTwoChildren()) {
		// 找到后继结点
		Node<E> s = successor(node);
		node.element = s.element;
		node = s;
	}
	
	// 删除node结点，node结点必然是度为0或者度为1
	Node<E> replacement = node.left!=null?node.left:node.right;
	if(replacement!=null) {
		// node的度为1的结点
		replacement.parent = node.parent;
		// 更改指向
		if(node.parent==null) {
			// node是度为1的结点并且是根节点
			root = replacement;
		}else if(node==node.parent.left) {
			node.parent.left = replacement;
		}else {
			node.parent.right = replacement;
		}
	}else if(node.parent==null) {
		// node是叶子结点并且是叶子结点
		root = null;
	}else {
		// node是叶子结点，但不是根节点
		if(node==node.parent.left) {
			node.parent.left = null;
		}else {
			node.parent.right = null;
		}
	}
	
}

// 后继结点
	protected Node<E> successor(Node<E> node){
		if(node==null)
			return null;
		// 后继结点在右子树
		Node<E> p = node.right;
		if(p!=null) {
			while(p.left!=null) {
				p = p.left;
			}
			return p;
		}
		// 从父节点、祖父结点寻找后继结点
		while(node.parent!=null&&node==node.parent.right) {
			node = node.parent;
		}
		return node.parent;
	}
```

## 二叉搜索树的题目

### 1.二叉搜索树删除某个节点的递归

```java
// 后继
	private int successor(TreeNode root) {
		root = root.right;
		while(root.left!=null) {
			root = root.left;
		}
		return root.val;
	}
	
	// 前驱
	private int predecessor(TreeNode root) {
		root = root.left;
		while(root.left!=null) {
			root = root.right;
		}
		return root.val;
	}
	public TreeNode deleteNode(TreeNode root,int key) {
		// 递归结束条件
		if(root==null)
			return null;
		// 判断条件
		if(key>root.val)
			root.right = deleteNode(root.right, key);
		else if(key<root.val)
			root.left = deleteNode(root.left, key);
		else {
			if(root.left==null&&root.right==null)
				root = null;
			else if(root.right!=null) {
				root.val = successor(root);
				root.right = deleteNode(root.right, root.val);
			}else {
				root.val = predecessor(root);
				root.left = deleteNode(root.left, root.val);
			}
		}
		return root;
	}
```



## 平衡二叉树

### AVL

- LL
- RR
- LR
- RL

### 红黑树

- 节点是红色或黑色；
- 根节点是黑色；
- 所有叶子是黑色的；
- 每个红色节点必须有两个黑色的子节点(从每个叶子到根的所有路径上不能有两个连续的红色节点)；
- 从任一节点到每个结点的所有简单路径都包含相同数目的黑色结点

### B树

对于一棵m阶B树，需要满足以下条件：

- 根节点至少包含两个孩子；
- 树中每个结点最多包含有m个孩子(m>=2);
- 除根节点和叶结点外，其他每个结点至少有ceil(m/2)个孩子‘
- 所有叶子结点都位于同一层
- 假设每个非终端结点中包含有n个关键点信息，其中：
  - Ki(i=1,2..n)为关键字，且关键点按顺序圣墟排序Ki-1<Ki
  - 关键点的个数n必须满足 [ceil(m/2)-1]<=n<=m-1;

### B+树

B+树也是多路平衡查找树，其与B树的区别在于：

- B树中每个节点（包括叶节点和非叶节点）都存储真实的数据，B+树中只有叶子节点存储真实的数据，非叶节点只存储键。在MySQL中，这里所说的真实数据，可能是行的全部数据（如Innodb的聚簇索引），也可能只是行的主键（如Innodb的辅助索引），或者是行所在的地址（如MyIsam的非聚簇索引）。
- B+树的叶节点之间通过双向链表链接。
- B树中的非叶节点，记录数比子节点个数少1；而B+树中记录数与子节点个数相同。
- B树中一条记录只会出现一次，不会重复出现，而B+树的键则可能重复重现，一定会在叶节点出现，也可能在非叶节点重复出现。

