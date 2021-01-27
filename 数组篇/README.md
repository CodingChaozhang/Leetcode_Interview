# Leetcode-数组篇

## HashMap结构辅助解题

### 1. [Leetcode001 两数之和](https://leetcode-cn.com/problems/two-sum/)

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。                          

> 解题思路：HashMap来辅助解题，重点是用`target-nums[i]`在HashMap数据结构中查找该值。

```java
public int[] twoSum(int[] nums, int target) {
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	int index_left=0,index_right = 0;
	    	for(int i=0;i<nums.length;i++) {
	    		// 如果有其值
	    		if(hashMap.containsKey(target-nums[i])) {
	    			index_left  = hashMap.get(target-nums[i]);
	    			index_right = i;
	    			break;
	    		}
	    		hashMap.put(nums[i], i);
	    	}
	    	return new int[] {index_left,index_right};
	    }
```

### [2.Leetcode_Offer数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 

> 解题思路一、用HashSet辅助结构
>
> 解题思路二、直接在原数组中操作，使其在i位置放置nums[i]值，如果不是则交换。交换过程中发生一个值跟排序好的值相等则重复。

```java
class Solution {
		// 辅助数据结构来解题
	    public int findRepeatNumber(int[] nums) {
	    	HashSet<Integer> hashset = new HashSet<>();
	    	for(int i=0;i<nums.length;i++) {
	    		//判断
	    		if(hashset.contains(nums[i])) {
	    			return nums[i];
	    		}else {
	    			hashset.add(nums[i]);
	    		}
	    	}
	    	return -1;
	    }
	    
	    // 在原数组上进行操作 因为原数组范围固定在0-n-1中了 。
	    public int findRepeatNumber_2(int[] nums) {
	    	// 每个位置都在i上即可
	    	int temp;
	    	for(int i=0;i<nums.length;i++) {
	    		while(i!=nums[i]) {
	    			if(nums[i]==nums[nums[i]]) {
	    				// 如果有一个值跟已排序好的值相等
	    				return nums[i];
	    			}
	    			// 交换两值，使其回到原位值
	    			temp = nums[i];
	    			nums[i] = nums[temp];
	    			nums[temp] = temp;
	    		}
	    	}
	    	return -1;
	    }
	}
```

### [3.面试题01.01 判定字符串是否唯一](https://leetcode-cn.com/problems/is-unique-lcci/)

实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

示例 1：

输入: s = "leetcode"
输出: false 
示例 2：

输入: s = "abc"
输出: true

> HashSet辅助结构解题

```java
class Solution {
    public boolean isUnique(String astr) {
        char[] dict = astr.toCharArray();
	    	// 辅助结构
	    	HashSet<Character> hashSet = new HashSet<>();
	    	for(int i=0;i<dict.length;i++) {
	    		if(!hashSet.isEmpty() && hashSet.contains(dict[i])) {
	    			return false;
	    		}else {
	    			hashSet.add(dict[i]);
	    		}
	    	}
	    	return true;
    }
}
```

### [4.Leetcode041缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/)

给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。

进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？

示例 1：

输入：nums = [1,2,0]
输出：3

示例 2：

输入：nums = [3,4,-1,1]
输出：2

示例 3：

输入：nums = [7,8,9,11,12]
输出：1

> 解题思路1：直接用HashSet来解题，但是空间复杂度不符合要求

```java
public int firstMissingPositive(int[] nums) {
	    	// 用HashSet辅助，但是不符合常数数组的原则
	    	HashSet<Integer> hashset = new HashSet<>();
	    	for(int num:nums) {
	    		hashset.add(num);
	    	}
	    	// 从容器中查看
	    	for(int i=1;i<=nums.length;i++) {
	    		if(!hashset.contains(i)) {
	    			return i;
	    		}
	    	}
	    	return nums.length+1;
	    }
```

> 解题思路2：既然用HashSet额外的增加了空间复杂度，那么可不可以直接在原数组上进行修改呢？
>
> 通过将数组中的值放置于对应的索引位置上，1放置索引0 上，3放置索引位置2上。
>
> 之后对其遍历，查看其值是否与当前索引值相同，如果不同，则返回

```java
//常数空间在原数组上直接操作
	    public int firstMissingPositive_2(int[] nums) {
	    	// 把每个数放到对应索引位置上 比如3放到索引位置2上，之后检查当前数和索引值是否等 不等则返回索引值+1
	    	
	    	int len = nums.length;
	    	for(int i=0;i<len;i++) {
	    		// 符合条件才放到对应索引位置上 有的数大于0或者len了
	    		while(nums[i]>0&&nums[i]<=len&&(nums[i]!=nums[nums[i]-1])) {
	    			swap(nums,i,nums[i]-1);
	    		}
	    	}
	    	
	    	// 之后检查
	    	for(int i=0;i<len;i++) {
	    		if(nums[i]!=i+1) {
	    			return i+1;
	    		}
	    	}
	    	return len+1;
	    }
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
```

### [4.Leetcode217存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/)

给定一个整数数组，判断是否存在重复元素。

如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。

 

示例 1:

输入: [1,2,3,1]
输出: true

示例 2:

输入: [1,2,3,4]
输出: false

示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true

> 解题思路：一方面直接排序，看相邻
>
> 另外一方面直接用辅助结构HashSet

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
	    	// 第一种对其用HashSet辅助结构来解题
	    	HashSet<Integer> hashSet = new HashSet<Integer>();
	    	for(int num:nums) {
	    		if(!hashSet.contains(num)) {
	    			hashSet.add(num);
	    		}else {
	    			return true;
	    		}
	    	}
	    	return false;
 	    }
}
```

### [5.Leetcode1160拼写单词](https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/)

给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。

返回词汇表 words 中你掌握的所有单词的 长度之和。

 

示例 1：

输入：words = ["cat","bt","hat","tree"], chars = "atach"
输出：6
解释： 
可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
示例 2：

输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
输出：10
解释：
可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。

> 解题思路：HashMap单词频率表 对比 来解题，看见字母以及次数考虑HashMap

```java
class Solution {
	    public int countCharacters(String[] words, String chars) {
	    	char[] char_arr = chars.toCharArray();
	    	// 用HashMap的辅助结构来解题，一方面要知道该单词在不在，另一方要确保其出现次数
	    	HashMap<Character,Integer> dict = new HashMap<>();
	    	//对其遍历
	    	for(int i=0;i<char_arr.length;i++) {
	    		// 统计字母表以及出现的次数
	    		dict.put(char_arr[i],dict.getOrDefault(char_arr[i], 0)+1);
	    	}
	    	// 词汇表的结果
	    	int res = 0;
	    	// 对词汇表遍历
	    	for(int i=0;i<words.length;i++) {
	    		// 对每个单词建立一个hashMap
	    		String word = words[i];
	    		char[] word_arr = word.toCharArray();
	    		// 是否掌握了
	    		boolean flag = true;
	    		// 词汇表
	    		HashMap<Character,Integer> word_dict = new HashMap<>();
	    		for(int j=0;j<word_arr.length;j++) {
	    			word_dict.put(word_arr[j], word_dict.getOrDefault(word_arr[j],0)+1);
	    		}
	    		
	    		// 对比比较
	    		for(int k=0;k<word_arr.length;k++) {
	    			if(!dict.containsKey(word_arr[k])||dict.get(word_arr[k])<word_dict.get(word_arr[k])) {
	    				flag = false;
	    				break;
	    			}
	    		}
	    		if(flag) {
	    			res += word_arr.length;
	    		}
	    	}
	    	return res;
	    }
	}
```

### [6.Leetcode_Interview 01.02判定是否互为字符重排](https://leetcode-cn.com/problems/check-permutation-lcci/)

给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。

示例 1：

输入: s1 = "abc", s2 = "bca"
输出: true 
示例 2：

输入: s1 = "abc", s2 = "bad"
输出: false
说明：

0 <= len(s1) <= 100
0 <= len(s2) <= 100

> 解题思路：用HashMap辅助 记录单词和数量

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
    // 转成数组
	    	char[] arr_1 = s1.toCharArray();
	    	char[] arr_2 = s2.toCharArray();
	    	// 判断长度
	    	if(arr_1.length!=arr_2.length) {
	    		return false;
	    	}
	    	// 两个HashMap即可
	    	HashMap<Character,Integer> hashMap1 = new HashMap<>();
	    	HashMap<Character,Integer> hashMap2 = new HashMap<>();
	    	for(int i=0;i<arr_1.length;i++) {
	    		hashMap1.put(arr_1[i],hashMap1.getOrDefault(arr_1[i],0)+1);
	    		hashMap2.put(arr_2[i],hashMap2.getOrDefault(arr_2[i], 0)+1);
	    	}
	    	// 遍历
	    	for(int i=0;i<arr_1.length;i++) {
	    		if(!hashMap2.containsKey(arr_1[i])||hashMap2.get(arr_1[i])!=hashMap1.get(arr_1[i])) {
	    			return false;
	    		}
	    	}
	    	return true;
    }
}
```

### [7.Leetcode128最长连续序列](https://leetcode-cn.com/problems/longest-consecutive-sequence/)

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

 

进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？

 

示例 1：

输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。

示例 2：

输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9

> 解题思路：用Hashset辅助。来查找记录值即可了。

```java
class Solution {
    public int longestConsecutive(int[] nums) {
// 就是连续的
	    	HashSet<Integer> hashset = new HashSet<Integer>();
	    	for(int num:nums) {
	    		hashset.add(num);
	    	}
	    	// 结果
	    	int res = 0;
	    	//对hashset遍历
	    	for(int num:hashset) {
	    		// 如果其不存在前缀
	    		if(!hashset.contains(num-1)) {
	    			// 直接找其后缀
	    			int curNum = num;
	    			int curLength = 1;
	    			while(hashset.contains(curNum+1)) {
	    				curNum += 1;
	    				curLength += 1;
	    			}
	    			res = Math.max(res, curLength);
	    		}
	    	}
	    	return res;
    }
}
```



## HashMap辅助+二叉树解题

### [1.Leetcode105从前序与中序遍历序列中构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

> 前序遍历 preorder = [3,9,20,15,7]
> 中序遍历 inorder = [9,3,15,20,7]

返回如下的二叉树：

```
 3
   / \
  9  20
    /  \
   15   7
```

> 解题思路：先用HashMap来存储中序遍历，之后在前序和中序找根节点的索引，并调用递归来解题

```java
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
```







## 排序+辅助结构解题

### [1.Leetcode56合并区间](https://leetcode-cn.com/problems/merge-intervals/)

给出一个区间的集合，请合并所有重叠的区间。

 

示例 1:

输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

示例 2:

输入: intervals = [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。

> 解题思路：先自定义排序之后遍历区间，一个来维护加入的，另外一个查看要加入的 是否能跟之前的合并

```java
class Solution {
    public int[][] merge(int[][] intervals) {
            // 先排序后遍历
	    	
	    	// 自定义排序
	    	Arrays.sort(intervals,(a,b)->(a[0]-b[0]));
	    	// 结果存储
	    	int n = intervals.length;
	    	int[][] res = new int[n][2];
	    	// 定义结果的索引
	    	int index = 0;
	    	
	    	// 开始遍历
	    	for(int i=0;i<intervals.length;i++) {
	    		int L = intervals[i][0];
	    		int R = intervals[i][1];
	    		
	    		// 查看是否需要合并
	    		if(index==0 || L>res[index-1][1]) {
	    			res[index][0] = L;
	    			res[index][1] = R;
	    			index++;
	    		}else {
	    			// 合并
	    			res[index-1][1] = Math.max(res[index-1][1], R);
	    		}
	    	}
            // 对后面多余的去除
	    	int[][] newres = new int[index][2];
	    	for(int i=0;i<index;i++) {
	    		for(int j=0;j<2;j++) {
	    			newres[i][j] = res[i][j];
	    		}
	    	}
	    	
	    	return newres;
    }
}
```

辅助结构ArrayList解题

```java
class Solution {
    public int[][] merge(int[][] intervals) {
            // 自定义排序
	    	Arrays.sort(intervals,(a,b)->(a[0]-b[0]));
	    	// 存储结构
	    	ArrayList<int[]> res = new ArrayList<>();
	    	// 遍历
	    	for(int i=0;i<intervals.length;i++) {
	    		int L = intervals[i][0];
	    		int R = intervals[i][1];
	    		
	    		if(res.size()==0 || L>res.get(res.size()-1)[1]) {
	    			// 不合并
	    			res.add(new int[] {L,R});
	    		}else {
	    			// 合并
	    			res.get(res.size()-1)[1] = Math.max(res.get(res.size()-1)[1], R);
	    		}	    		
	    	}
	    	// 转成数组
	    	return res.toArray(new int[res.size()][]);
    }
}
```

### [2.Leetcode169多数元素](https://leetcode-cn.com/problems/majority-element/)

给定一个大小为 *n* 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 **大于** `⌊ n/2 ⌋` 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

**示例 1：**

```
输入：[3,2,3]
输出：3
```

**示例 2：**

```
输入：[2,2,1,1,1,2,2]
输出：2
```



> 解题思路：理解多数元素是什么？排序之后直接返回n/2下标对应的元素就是了。要不然就是辅助结构hashmap来解题

```java
class Solution {
    public int majorityElement(int[] nums) {
            Arrays.sort(nums);
	    	return nums[nums.length/2];
    }
}
```

> 解题思路二、用HashMap来解题 此题用来熟悉HashMap的遍历方式

```java
public int majorityElement(int[] nums) {
	    	// 用hashmap来解题
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	for(int i=0;i<nums.length;i++) {
	    		if(hashMap.containsKey(nums[i])) {
	    			// hashMap包含其值 更新频率
	    			hashMap.put(nums[i],hashMap.get(nums[i])+1);
	    		}else {
	    			// hashMap不含其值
	    			hashMap.put(nums[i],1);
	    		}
	    	}	
	    	int length = nums.length;
	    	int frequency = length >> 1;
	    	// 结果存放
	    	Map.Entry<Integer, Integer> res = null;
	    	// 对hashMap进行遍历
	    	for(Map.Entry<Integer, Integer> entry:hashMap.entrySet()) {
	    		if(res==null || entry.getValue()>res.getValue()) {
	    			res = entry;
	    		}
	    	}
	    	return res.getKey();
	    	
	    }
```

> 解题思路三、摩尔投票法 时间复杂度为O(n) 
>
> 核心就是对拼消耗，假如一个诸侯争霸的游戏，假设你方超过总人口一半以上，并且能保证每个人出去干仗都能一对一同归浴巾，最后能剩下的必定是你自己人。
>
> 程序实现就是：维护一个候选的众数candidate和一个出现的次数count。
>
> 遍历整个数组，如果count值为0，那么赋值candidate，之后判断x。如果x与candidate相等，则count自增1，如果不相等则减一。

```java
 public int majorityElement(int[] nums) {
	    	int res = nums[0];
	    	int count = 1;
	    	for(int i=1;i<nums.length;i++) {
	    		if(count==0) {
	    			res = nums[i];
	    		}
	    		count += nums[i]==res?1:-1;
	    	}
	    	return res;
	    }
```



## 排序+两指针辅助解题

### 1.[Leetcode015三数之和](https://leetcode-cn.com/problems/3sum/)

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。

**示例：**

给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]



> 解题思路一、暴力解法三种不同的值
>
> 解题思路二、排序+双指针的解法  三数的时候 确定一个数，剩余两数就可以确定。
>
> 因为定下一个值，剩下两值就可以确定。而通过排序的时候我们就可以通过剪枝来加快速度，但注意剪枝的时候要考虑越界问题。

```java
// 解法一、暴力解法
	    public List<List<Integer>> threeSum(int[] nums) {
	            HashSet res = new HashSet();
	            Arrays.sort(nums);
		    	for(int i=0;i<nums.length-2;i++) {
		    		for(int j=i+1;j<nums.length-1;j++) {
		    			for(int k=j+1;k<nums.length;k++) {
		    				if(nums[i]+nums[j]+nums[k]==0) {
		    					List<Integer> res_temp = new ArrayList<>();
	                            res_temp.add(nums[i]);
	                            res_temp.add(nums[j]);
	                            res_temp.add(nums[k]);
	                            res.add(res_temp);
		    				}else if(nums[i]>0 || nums[i]+nums[j]>0 || nums[i]+nums[j]+nums[k]>0){
	                            break;
	                        }
		    			}
		    		}
		    	}
		    	
		    	return new ArrayList<>(res);
	    }
```

```java
// 解法二、排序+双指针的解法
        public List<List<Integer>> threeSum_2(int[] nums) {
        	 // 结果去重
        	HashSet res = new HashSet();
        	// 继续优化 是否可以剪枝
        	
        	// 先判断数组
        	if(nums.length<3) {
        		return new ArrayList<>(res);
        	}
        	// 排序
        	Arrays.sort(nums);
        	// 双指针解法 定一个 
        	for(int i=0;i<nums.length-2;i++) {
        		int left = i+1;
        		int right = nums.length-1;
        		
        		// 去重
        		if(i!=0&&nums[i]==nums[i-1]) {
        			continue;
        		}
        		
        		//剪枝
        		if(nums[i]>0) {
        			break;
        		}
        		// 剪枝
        		if(nums[i]+nums[i+1]+nums[i+2]>0) {
        			break;
        		}
        		// 当前
        		if(nums[i]+nums[nums.length-1]+nums[nums.length-2]<0) {
        			continue;
        		}
        		// 循环
        		while(left<right) {
            		int curNum = nums[i] + nums[left] + nums[right];
        			if(curNum>0) {
            			right--;
            		}else if(curNum<0) {
            			left++;
            		}else if(curNum==0) {
                        // 结果
            			List<Integer> res_temp = new ArrayList<>();
            			res_temp.add(nums[i]);
            			res_temp.add(nums[left]);
            			res_temp.add(nums[right]);
                        res.add(res_temp);
                        // 走步
                        left++;
                        right--;
            		}
        		}
        	}
        	return new ArrayList<>(res);
        }
```

### 2.[Leetcode018四数之和](https://leetcode-cn.com/problems/4sum/)

给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

**注意：**

答案中不可以包含重复的四元组。

**示例：**

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]

> 解题思路一：暴力破解 暴力列举这四个值
>
> 解题思路二：四个值，先定两值，剩下两值左右指针定，即排序+双指针得到解法

```java
// 解题一、暴力解法
		public List<List<Integer>> fourSum(int[] nums, int target) {
	    	// 结果
			HashSet res = new HashSet();
			if(nums.length<4) {
				return new ArrayList<>();
			}
			// 暴力
			for(int i=0;i<nums.length-3;i++) {
				for(int j=i+1;j<nums.length-2;j++) {
					for(int k=j+1;k<nums.length-1;k++) {
						for(int l=k+1;l<nums.length;l++) {
							if(nums[i]+nums[j]+nums[k]+nums[l]==target) {
								// 临时结果
								List<Integer> res_temp = new ArrayList<>();
								res_temp.add(nums[i]);
								res_temp.add(nums[j]);
								res_temp.add(nums[k]);
								res_temp.add(nums[l]);
								res.add(res_temp);
							}
						}
					}
				}
			}
			
			
			return new ArrayList<>(res);
	    }
		
```

```java
// 解题二、排序+双指针定
		public List<List<Integer>> fourSum_2(int[] nums, int target) {
			// 四个值定下两个 才能定下剩下两个
			HashSet res = new HashSet();
			if(nums.length<4) {
				return new ArrayList<>(res);
			}
			 // 排序
            Arrays.sort(nums);
			// 对其循环两值
			for(int i=0;i<nums.length-3;i++) {
				for(int j=i+1;j<nums.length-2;j++) {
					// 左右指针
					int left = j+1;
					int right = nums.length-1;
					// 剪枝
					// 最小值
					if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target) {
						break;
					}
					// 最大值
					if(nums[i]+nums[j]+nums[nums.length-1]+nums[nums.length-2]<target) {
						continue;
					}
					
					// 循环判断剩下中间的
					while(left<right) {
						int curNum = nums[i] + nums[j] + nums[left] + nums[right];
						if(curNum==target) {
							List<Integer> res_temp = new ArrayList<>();
							res_temp.add(nums[i]);
							res_temp.add(nums[j]);
							res_temp.add(nums[left]);
							res_temp.add(nums[right]);
							res.add(res_temp);
							left++;
							right--;
 						}else if(curNum>target) {
							right--;
						}else if(curNum<target) {
							left++;
						}
					}
					
				}
			}
			
			
			return new ArrayList<>(res);
		}
		 
```

### 3.[Leetcode611 有效三角形的个数](https://leetcode-cn.com/problems/valid-triangle-number/)

给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。

示例 1:

输入: [2,2,3,4]
输出: 3

解释:
有效的组合是: 
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3

> 允许重复,不需要hashset。三条边那么从中选3个。
>
> 思路一、不排序的情况下，暴力遍历。并且需要验证这三个值三次是否满足任意两边大于第三边。
>
> 思路二、排序的情况下，仅需验证一次即可。而且是选3个点，那么遍历最长的边，剩下两边，通过左右指针辅助即可解题。

```java
// 第一种方法
	    public int triangleNumber(int[] nums) {
	    	// 判断数组的长度
	    	if(nums.length<3) {
	    		return 0;
	    	}
	    	// 存储结果
	    	int res = 0;
	    	// 遍历
	    	for(int i=0;i<nums.length-2;i++) {
	    		for(int j=i+1;j<nums.length-1;j++) {
	    			for(int k=j+1;k<nums.length;k++) {
	    				if(nums[i]+nums[j]>nums[k]&&nums[i]+nums[k]>nums[j]&&nums[j]+nums[k]>nums[i]) {
	    					res++;
	    				}
	    			}
	    		}
	    	}
	    	return res;
	    }
```

```java
// 类似从数组中挑选3个数，但是可重复。
	    public int triangleNumber_4(int[] nums) {
	    	int res = 0;
	    	if(nums.length<3) {
	    		return res;
	    	}
	    	// 排序
	    	Arrays.sort(nums);
	    	// 对其遍历最长边 剩余两值左右指针
	    	for(int i=2;i<nums.length;i++) {
	    		int left = 0;
	    		int right = i-1;
	    		while(left<right) {
	    			// 三角形需满足条件2边大于第三边
		    		if(nums[left]+nums[right]>nums[i]) {
		    			// 那么从左边继续走 肯定还满足条件那么就有right-left
		    			// 即右边固定，左边都满足条件
		    			res += (right-left);
		    			// 查看别的满足条件的值
		    			right--;
		    		}else {
		    			// 此时不满足条件，调大最大值
		    			left++;
		    		}
	    		}
	    		
	    	}	    	
	    	return res;
	    }
```

### 4.[Leetcode976三角形的最大周长](https://leetcode-cn.com/problems/largest-perimeter-triangle/)

给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

如果不能形成任何面积不为零的三角形，返回 0。

示例 1：

输入：[2,1,2]
输出：5

示例 2：

输入：[1,2,1]
输出：0

示例 3：

输入：[3,2,3,4]
输出：10

示例 4：

输入：[3,6,2,3]
输出：8

> 解题思路一、暴力破解， 遍历全部的三个值
>
> 解题思路二：排序 +双指针 一个确定剩下两个也就确定。
>
> 但是双指针怎么确定呢？
>
> 先遍历最长边，那么剩下的双指针，我们直接判断剩余的最长边满不满足条件即可，满足那么这个就是最长边；不满足的话，那么看下一个。

```java
	    public int largestPerimeter(int[] A) {
	    	int res = 0;
	    	if(A.length<3) {
	    		return res;
	    	}
	    	// 排序+双指针的思路
	    	Arrays.sort(A);
	    	// 遍历一个最长边，剩余两个左右指针来决定 （复杂度过高，其实可以逆序开始，那么只要找到一个就是最大值）
	    	for(int i=2;i<A.length;i++) {
	    		int left = 0;
	    		int right = i-1;
	    		while(left<right) {
	    			// 开始找满足三角形的值
	    			if(A[left]+A[right]>A[i]) {
	    				// 那么挑选出最符合题意的最大值left的最边上即最靠近right
	    				res = Math.max(res, A[right-1]+A[right]+A[i]);
	    				// 那么此时无需看right小的了，因为肯定比res小 结束
	    				break;
	    			}else {
	    				// 如果不满足条件的话，那么移动下最小值left
	    				left++;
	    			}
	    		}
	    		
	    	}
	    	
	    	return res;
	    }
	    
```

```java
public int largestPerimeter_2(int[] A) {
	    	int res = 0;
	    	if(A.length<3) {
	    		return res;
	    	}
	    	// 排序+双指针 选3个值
	    	Arrays.sort(A);
	    	// 逆序找 只要找到一个即可
	    	for(int i=A.length-1;i>=2;i--) {
	    		// 两个指针
	    		int left = 0;
	    		int right = i-1;
	    		while(left<right) {
	    			// 开始找满足条件的三角形
	    			if(A[left]+A[right]>A[i]) {
	    				// 那么找最大值
	    				res = Math.max(res, A[right-1]+A[right]+A[i]);
	    				// 结束循环
	    				break;
	    			}else {
	    				//不满足条件  调大最大值
	    				left++;
	    			}
	    		}
	    		// 如果res有值即为最大 不需 遍历了
	    		if(res>0) {
	    			break;
	    		}
	    	}	    	
	    	return res;
	    }
```

```java
// 第三种方法 逆序遍历三个值
	    public int largestPerimeter_3(int[] A) {
	    	// 排序 + 双指针
	    	Arrays.sort(A);
	    	// 逆序 直接找其最大值，不需从最小的left开始，直接看最大的left是否满足，不满足就下一个。
	    	for(int i=A.length-1;i>=2;i--) {
	    		if(A[i-1]+A[i-2]>A[i]) {
	    			return A[i-1]+A[i-2]+A[i];
	    		}
	    	}
	    	return 0;
	    }
```

```java
// 排序+双指针
 public int largestPerimeter(int[] A) {
        // 排序+双指针
        Arrays.sort(A);
        //  双指针
        for(int i=A.length-1;i>=2;i--){
            int right = i-1;
            int left  = i-2;
            // 判断是否满足三角形的条件
            if(A[left] + A[right] >A[i]){
                return A[left]+A[right]+A[i];
            }
        }
        return 0;
    }
```

### [5.Leetcode016最接近的三数之和](https://leetcode-cn.com/problems/3sum-closest/)

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 

示例：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

> 解题思路：还是之前的排序+双指针的思路，无非就是判断标准不一样了。

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
            // 排序+双指针即可
	    	Arrays.sort(nums);
            // 初始化的方式
	    	int res  = nums[0]+nums[1]+nums[2];
	    	for(int i=0;i<nums.length-2;i++) {
	    		int left = i+1;
	    		int right = nums.length-1;
	    		// while循环
	    		while(left<right) {
	    			// 当前值
		    		int temp_target = nums[i] + nums[left] + nums[right];
                    res = (Math.abs(target-res))>(Math.abs(target-temp_target))?temp_target:res;
		    		if(temp_target==target) {
		    			return temp_target;
		    		}else if(temp_target<target) {
		    			left++;
		    		}else {
		    			right--;
		    		}
	    		}
	    	}
	    	return res;
    }
}
```



## 双指针(快慢指针)解题

### 1.[Leetcode26删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)

给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。



示例 1:

给定数组 nums = [1,1,2], 

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 

你不需要考虑数组中超出新长度后面的元素。

示例 2:

给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。

> 解题思路：因为在原数组中进行操作，那么使用双指针即快慢指针 快指针遍历整个数组 慢指针维护新数组。



```java
class Solution {
	    public int removeDuplicates(int[] nums) {
	            // 双指针问题  快慢指针 快指针遍历原数组 慢指针维护新数组的长度
	    		if(nums==null || nums.length<1) {
	    			return 0;
	    		}
	    		// left维护新数组
		    	int left  = 0;
		    	int index = 1;
		    	while(index<nums.length) {
		    		if(nums[index]==nums[left]) {
		    			// 不等
		    			index++;
		    		}else {
		    			// 相等
		    			nums[++left] = nums[index++];
		    		}
		    	}
		    	return left+1;
	    }
	    
	}
```

### 2.[Leetcode027移除元素](https://leetcode-cn.com/problems/remove-element/)

给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。

不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

 

示例 1:

给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。

示例 2:

给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。

> 解题思路：双指针解题思路，慢指针维护不等的。

```java
class Solution {
	    public int removeElement(int[] nums, int val) {
	    	// 存储长度
	    	int left = -1;
	    	int index = 0;
	    	while(index<nums.length) {
	    		if(nums[index]==val) {
	    			// 等于查看下一个
	    			index++;
	    		}else {
	    			// 不等于交换
	    			swap(nums,++left,index++);
	    		}
	    	}
	    	return left+1;
	    }
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	}
```

### [3.Leetcode283移动零](https://leetcode-cn.com/problems/move-zeroes/)

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]
输出: [1,3,12,0,0]

> 快慢 指针，快指针遍历整个数组，慢指针维护新数组

```java
class Solution {
	    public void moveZeroes(int[] nums) {
	    	// 快慢指针
	    	int left = -1;
	    	int index = 0;
	    	while(index<nums.length) {
	    		if(nums[index]==0) {
	    			// 相等怎么办
	    			index++;
	    		}else {
	    			// 不相等怎么办
	    			swap(nums,++left,index++);
	    		}
	    	}
	    }
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	}
```

### [4.Leetcode盛最多水的容器011](https://leetcode-cn.com/problems/container-with-most-water/)

给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。

 

示例 1：

![image-20210105205029626](C:\Users\LvChaoZhang\AppData\Roaming\Typora\typora-user-images\image-20210105205029626.png)

输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例 2：

输入：height = [1,1]
输出：1

示例 3：

输入：height = [4,3,2,1,4]
输出：16

示例 4：

输入：height = [1,2,1]
输出：2

> 解题思路：这道题用双指针来做，但是最关键的点是理解如何盛最多的水，即理解计算公式从最远的边来看，然后两边的高的最短值来计算。然后根据高度来依次移动。

```java
class Solution {
	    public int maxArea(int[] height) {
	    	// 双指针从两边开始移动
	    	int left = 0;
	    	int right = height.length-1;
	    	// 存储结果
	    	int res = 0;
	    	// 存储临时的值
	    	int temp = 0;
	    	while(left<right) {
	    		temp = (right-left)*Math.min(height[left], height[right]);
	    		res = Math.max(res, temp);
	    		// 更新步伐
	    		if(height[left]<height[right]) {
	    			left++;
	    		}else {
	    			right--;
	    		}
	    	}
	    	return res;
	    }
	}
```

### [5.Leetcode042接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

![image-20210105221948915](C:\Users\LvChaoZhang\AppData\Roaming\Typora\typora-user-images\image-20210105221948915.png)



输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 

示例 2：

输入：height = [4,2,0,3,2,5]
输出：9

> 解题思路：难点在于如何理解，从当前值出发，找其左边最大值和右边最大值即可。为了防止重复计算，可先用数组记录下来。

```java
public int trap(int[] height) {
	    	// 对数组判断
	    	if(height==null || height.length==0) {
	    		return 0;
	    	}
	    	// 0为左边 1为右边
	    	int[][] dp = new int[height.length][2];
	    	dp[0][0] = 0;
	    	dp[height.length-1][1] = 0;
	    	// 遍历得到当前值左右最大的高度 最左边跟最右边都没法存储水
	    	for(int i=1;i<height.length-1;i++) {
	    		// 左边
	    		for(int left=0;left<i;left++) {
	    			dp[i][0] = Math.max(dp[i][0],height[left]);
	    		}
	    		// 右边
	    		for(int right=i+1;right<height.length;right++) {
	    			dp[i][1] = Math.max(dp[i][1], height[right]);
	    		}
	    	}
	    	
	    	// 开始计算
	    	int res = 0;
	    	for(int i=1;i<height.length-1;i++) {
	    		res += Math.max((Math.min(dp[i][0], dp[i][1]))-height[i], 0);
	    	}
	    	return res;
	    }
	    
```

对其改进，其实可以直接记录当前最大值，要不就是当前值要不就是前面那个最大值

```java
public int trap_2(int[] height) {
	    	// 对数组判断
	    	if(height==null || height.length==0) {
	    		return 0;
	    	}
	    	// 获取当前值的左右最大值
	    	int[] left_max = new int[height.length];
	    	int[] right_max = new int[height.length];
	    	left_max[0] = height[0];
	    	for(int i=1;i<height.length;i++) {
	    		// 当前最大值 是前面的还是一直是
	    		left_max[i] = Math.max(left_max[i-1], height[i]);
	    	}
	    	right_max[height.length-1] = height[height.length-1];
	    	for(int i=height.length-2;i>=0;i--) {
	    		right_max[i] = Math.max(right_max[i+1], height[i]);
	    	}
	    	
	    	
	    	// 开始计算
	    	int res = 0;
	    	for(int i=1;i<height.length-1;i++) {
	    		res += Math.max((Math.min(left_max[i], right_max[i]))-height[i], 0);
	    	}
	    	return res;
	    }
```

### [6.Leetcode075颜色分类](https://leetcode-cn.com/problems/sort-colors/)

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

进阶：

- 你可以不使用代码库中的排序函数来解决这道题吗？
- 你能想出一个仅使用常数空间的一趟扫描算法吗？


示例 1：

输入：nums = [2,0,2,1,1,0]
输出：[0,0,1,1,2,2]

示例 2：

输入：nums = [2,0,1]
输出：[0,1,2]

示例 3：

输入：nums = [0]
输出：[0]

示例 4：

输入：nums = [1]
输出：[1]

> 解题思路：同荷兰国旗问题，两个左右指针同时移动，但是要注意一点就是截止条件！！！

```java
class Solution {
	    public void sortColors(int[] nums) {
	    	int index = 0;
	    	int left = -1;
	    	int right = nums.length;
	    	while(index<right) {
	    		if(nums[index]==1) {
	    			index++;
	    		}else if(nums[index]<1) {
	    			swap(nums,index++,++left);
	    		}else if(nums[index]>1) {
	    			// 右边交换
	    			swap(nums,index,--right);
	    		}
	    	}
	    }
	    // 交换函数
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	}
```

### [7.Leetcode031下一个排列](https://leetcode-cn.com/problems/next-permutation/)

实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须 原地 修改，只允许使用额外常数空间。

示例 1：

输入：nums = [1,2,3]
输出：[1,3,2]

示例 2：

输入：nums = [3,2,1]
输出：[1,2,3]

示例 3：

输入：nums = [1,1,5]
输出：[1,5,1]

示例 4：

输入：nums = [1]
输出：[1]

> 解题思路：该题解题思路 从右边出发找一个最小值，从右边接着出发找比最小值大的一个值即可，交换两个，并翻转链表即可

```java
class Solution {
	    public void nextPermutation(int[] nums) {
	    	// 解题思路 两边扫描
	    	// 先找第一个较小的数 从右边开始
	    	int i = nums.length-2;
	    	while(i>=0&&nums[i]>=nums[i+1]) {
	    		i--;
	    	}
	    	if(i>=0) {
	    		// 找第二个数 较大的数
	    		int j = nums.length-1;
	    		while(j>=0&&nums[j]<=nums[i]) {
	    			j--;
	    		}
	    		// 交换两个数
	    		swap(nums,i,j);
	    	}
	    	// 链表交换
	    	reverse(nums,i+1);
	    }
	    
	    // 交换
	    public void swap(int[] nums,int i,int j) {
	    	int temp = nums[i];
	    	nums[i]  = nums[j];
	    	nums[j]  = temp;
	    }
	    // 链表反转
	    public void reverse(int[] nums,int start) {
	    	int left = start;
	    	int right  = nums.length-1;
	    	while(left<=right) {
	    		swap(nums,left++,right--);
	    	}
	    }
	}
```

### [8.Leetcode167两数之和II-输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:

返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

示例:

输入: numbers = [2, 7, 11, 15], target = 9
输出: [1,2]
解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

> 解题思路：双指针来解题有序数组

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
	    	int right = numbers.length-1;
	    	while(left<=right) {
	    		if(numbers[left]+numbers[right]==target) {
	    			return new int[] {left+1,right+1};
	    		}else if(numbers[left]+numbers[right]>target) {
	    			right--;
	    		}else if(numbers[left]+numbers[right]<target) {
	    			left++;
	    		}
	    	}
	    	return new int[] {-1,-1};
    }
}
```

### [9.Leetcode1013将数组分成和相等的三个部分](https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/)

给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。

形式上，如果可以找出索引 i+1 < j 且满足 A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1] 就可以将数组三等分。

示例 1：

输入：[0,2,1,-6,6,-7,9,1,2,0,1]
输出：true
解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1

示例 2：

输入：[0,2,1,-6,6,7,9,-1,2,0,1]
输出：false

示例 3：

输入：[3,3,6,5,-2,2,5,1,-9,4]
输出：true
解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4

> 解题思路：双指针来解题，双指针开始从左边和右边开始。三等分 其实遍历一遍就知道总和了。

```java
class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        // 三等分，遍历完整个数组求和 就知道每份多少了。
	    	int sum = 0;
	    	for(int i=0;i<arr.length;i++) {
	    		sum += arr[i];
	    	}
	    	// 如果不能整除 直接返回
	    	if(sum%3!=0) {
	    		return false;
	    	}
	    	int count = sum/3;
	    	// 左右指针开始解题
	    	int left = 0;
	    	int left_sum = arr[left];
	    	int right = arr.length-1;
	    	int right_sum = arr[right];
	    	while(left+1<right) {
	    		if(left_sum==count&&right_sum==count) {
	    			return true;
	    		}
	    		
	    		if(left_sum!=count) {
	    			left_sum += arr[++left];
	    		}
	    		
	    		if(right_sum!=count) {
	    			right_sum += arr[--right];
	    		}
	    		
	    	}
	    	return false;
    }
}
```

### [10.Leetcode189旋转数组](https://leetcode-cn.com/problems/rotate-array/)

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

 

进阶：

尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？


示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]

示例 2:

输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]

> 解题思路：旋转数组原地旋转的解题思路 可用翻转数组来实现。
>
> 翻转数组给出左右指针 同时移动  替换即可了。无非就是确定中间位置。

```java
// 粗暴的方法 直接新建一个数组，之前i的位置变为 (i+k)%nums.length
	    public void rotate(int[] nums, int k) {
	    	int[] arr = new int[nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		arr[(i+k)%nums.length]  = nums[i];
	    	}
	    	System.arraycopy(arr,0,nums,0,nums.length);
	    }
```



```java
//通过反转数组来实现，整体先反转，前半分翻转 后半部分翻转
	    // 确定前半分k%nums.length-1 即k=3 翻转的是0-2索引
	    // 1 2 3 4 5 6 7
	    // 7 6 5 4 3 2 1
	    // 5 6 7 4 3 2 1
	    // 5 6 7 1 2 3 4
	    public void rotate_2(int[] nums, int k) {
	    	int index = k%nums.length - 1;
	    	reverse(nums,0,nums.length-1);
	    	reverse(nums,0,index);
	    	reverse(nums,index+1,nums.length-1);
	    }
	    
	    // 翻转数组 左右双指针，同时移动 替换
	    public void reverse(int[] nums,int left,int right) {
	    	while(left<right) {
	    		int temp   = nums[left];
	    		nums[left] = nums[right];
	    		nums[right]= temp;
	    		
	    		left++;
	    		right--;
	    	}
	    	
	    }
```

### [11.Leetcode287寻找重复数](https://leetcode-cn.com/problems/find-the-duplicate-number/)

给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。

 

示例 1：

输入：nums = [1,3,4,2,2]
输出：2

示例 2：

输入：nums = [3,1,3,4,2]
输出：3

示例 3：

输入：nums = [1,1]
输出：1

示例 4：

输入：nums = [1,1,2]
输出：1

> 解题思路1：用排序来帮忙

```java
// 第一种解题思路：排序
	    public int findDuplicate(int[] nums) {
	       Arrays.sort(nums);
	       for(int i=0;i<nums.length-1;i++) {
	    	   if(nums[i]==nums[i+1]) {
	    		   return nums[i];
	    	   }
	       }
	       return 0;
	    }
```

> 解题思路2：将数组看成一个循环的链表 从而用双指针来解题的思路

```java
class Solution {
    public int findDuplicate(int[] nums) {
       // 必定存在重复值， 必定相遇
	    	int low = 0;
	    	int fast = 0;
	    	while(true) {
	    		// 走两步
	    		low = nums[low];
	    		fast = nums[nums[fast]];
	    		// 相遇； 
	    		if(low==fast) {
	    			// 开始找起点 走一步
	    			fast = 0;
	    			while(nums[fast]!=nums[low]) {
	    				fast = nums[fast];
	    				low = nums[low];
	    			}
	    			return nums[low];
	    		}
	    	}
    }
}
```

### [12.Leetcode209长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

 

示例：

输入：s = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。


进阶：

如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。

> 时间复杂为O(n)，且连续的最小的，
>
> 考虑双指针，同时出发，之后慢慢缩短距离

```java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
           // 时间复杂度为O(n)思考 双指针
	    	int left = 0;
	    	int right = 0;
	    	int sum = 0;
	    	int minLength = Integer.MAX_VALUE;
	    	while(right<nums.length) {
	    		sum += nums[right];
	    		while(sum>=s) {
	    			//记录此时长度
	    			int length = right-left+1;
	    			// 开始缩短左边
	    			sum -= nums[left];
	    			left++;
	    			minLength = Math.min(minLength, length);
	    		}
	    		
	    		right++;	    		
	    	}
	    	return minLength!=Integer.MAX_VALUE?minLength:0;
    }
}
```

### [13.Leetcode977有序数组的平方](https://leetcode-cn.com/problems/squares-of-a-sorted-array/)

给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

 

示例 1：

输入：nums = [-4,-1,0,3,10]
输出：[0,1,9,16,100]
解释：平方后，数组变为 [16,1,0,9,100]
排序后，数组变为 [0,1,9,16,100]

示例 2：

输入：nums = [-7,-3,2,3,11]
输出：[4,9,9,49,121]

> 双指针 用双指针的思考 从哪里开始，如何移动

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
// 双指针解题 不从中间开始 从两边开始
	    	int left = 0;
	    	int right = nums.length-1;
	    	// 结果
	    	int[] res = new int[nums.length];
	    	int index = nums.length-1;
	    	while(left<=right) {
	    		// 开始
	    		if(nums[left]*nums[left]>=nums[right]*nums[right]) {
	    			res[index--] = nums[left]*nums[left]; 
	    			left++;
	    		}else {
	    			res[index--] = nums[right]*nums[right];
	    			right--;
	    		}
	    	}
	    	return res;
    }
}
```

### [14.Leetcode080删除排序数组中的重复项II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/)

给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。



示例 1：

输入：nums = [1,1,1,2,2,3]
输出：5, nums = [1,1,2,2,3]
解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 你不需要考虑数组中超出新长度后面的元素。

示例 2：

输入：nums = [0,0,1,1,1,1,2,3,3]
输出：7, nums = [0,0,1,1,2,3,3]
解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 你不需要考虑数组中超出新长度后面的元素。

> 解题思路：考察逻辑，考察一个遍历原数组，一个遍历新数组。

```java
class Solution {
    public int removeDuplicates(int[] nums) {
            int low = 0;
	    	int fast = 1;
	    	int fre = 0;
	    	while(fast<nums.length) {
	    		// 老数组
	    		if(nums[fast]==nums[low]) {
	    			fre++;
	    		}else {
	    			fre = 0;
	    		}
	    		
	    		// 新数组
	    		if(fre<2) {
	    			low++;
	    			nums[low] = nums[fast];
	    		}
	    		
	    		// 接着走
	    		fast++;
	    	}
	    	return low+1;
    }
}
```



## 单调栈

### [1.Leetcode084柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/)(计算最值)

给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram.png)



以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

 ![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram_area.png)



图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

示例:

输入: [2,1,5,6,2,3]
输出: 10

> 解题思路一、从当前出发，左边while连续找一个比它大的，右边找一个比它大的。

```java
// 暴力解法
	    public int largestRectangleArea(int[] heights) {
	    	// 存储结果
	    	int res = 0;
	    	// 对其整个进行遍历
	    	for(int i=0;i<heights.length;i++) {
	    		// 计算当前每个值的最大面积
	    		int curHeight = heights[i];
	    		// 左右指针
	    		int left = i-1;
	    		//开始
	    		while(left>=0&&heights[left]>=curHeight) {
	    			left--;
	    		}
	    		// 找不到
	    		left++;	    		
	    		int right = i+1;
	    		while(right<heights.length&&heights[right]>=curHeight) {
	    			right++;
	    		}
	    		// 找不到
	    		right--;
	    		
	    		// 计算面积
	    		int maxarea = (right-left+1)*(curHeight);
	    		res = Math.max(res, maxarea);
	    	}
	    	return res;
	    }
```

> 解题思路二、一个单调的辅助栈来模拟

```java
// 单调栈
	    public int largestRectangleArea_2(int[] heights) {
	    	int res = 0;
	    	// 单调栈辅助解题
	    	Deque<Integer> stack = new ArrayDeque<>();
	    	// 在其头尾补充
	    	int[] new_heights = new int[heights.length+2];
	    	//复制
	    	System.arraycopy(heights, 0, new_heights, 1,heights.length);
	    	// 开始遍历
	    	for(int i=0;i<new_heights.length;i++) {
	    		// 单调栈
	    		while(!stack.isEmpty()&&new_heights[stack.peek()]>new_heights[i]) {
	    			int curIndex = stack.pop();
	    			res = Math.max(res, (i-stack.peek()-1)*new_heights[curIndex]);
	    		}
	    		stack.push(i);
	    	}
	    	return res;
	    }
```

### [2.Leetcode739每日温度](https://leetcode-cn.com/problems/daily-temperatures/)

请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

> 解题思路：当题目有意当前比它大的，考虑单调栈的思路解题。

```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
         // 看后面几天 是否有高的 单调栈 递减栈
	    	Deque<Integer> stack = new ArrayDeque<Integer>();
	    	// 拷贝
	    	// 对其遍历
	    	// 结果 单调递减栈
	    	int[] res = new int[T.length];
	    	for(int i=0;i<T.length;i++) {
	    		// 出栈
	    		while(!stack.isEmpty()&&T[stack.peek()]<T[i]) {
	    			// 满足条件
	    			int index = stack.pop();
	    			res[index] = i-index; 
	    		}
	    		// 入栈
	    		stack.push(i);
	    	}
	    	return res;
    }
}
```

### [3.Leetcode469下一个更大元素I](https://leetcode-cn.com/problems/next-greater-element-i/)

给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。

请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

 

示例 1:

输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。

示例 2:

输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。

> 解题思路：单调栈+HashMap辅助结构来解题。

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调栈
	    	// 现在nums2中遍历 得到每一个比起大的元素
	    	// 结果用HashMap来存储
	    	HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
	    	Deque<Integer> stack = new ArrayDeque<>();
	    	// 对nums2遍历 单调栈的方法
	    	for(int i=0;i<nums2.length;i++) {
	    		// 出栈
	    		while(!stack.isEmpty()&&nums2[i]>nums2[stack.peek()]) {
	    			int curIndex = stack.pop();
	    			// 存储
	    			hashMap.put(nums2[curIndex],nums2[i]);
	    		}
	    		// 入栈
	    		stack.push(i);
	    	}
	    	// 单调栈中其实还有一些值
	    	while(!stack.isEmpty()) {
	    		hashMap.put(nums2[stack.pop()],-1);
	    	}
	    	// 结果存储
	    	int[] res = new int[nums1.length];
	    	// 开始遍历num1找其值
	    	for(int i=0;i<nums1.length;i++) {
	    		if(hashMap.containsKey(nums1[i])) {
	    			//包含该值则
	    			res[i] = hashMap.get(nums1[i]);
	    		}
	    	}
	    	return res;
    }
}
```

### [4.Leetcode042接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 

示例 1：



输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
示例 2：

输入：height = [4,2,0,3,2,5]
输出：9

> 解题思路：暴力解法

```java
// 第一种思路 找其左边最大值 找其右边最大值
		public int trap(int[] height) {
			// 当前左右两边最大值中的最小值 首位两边装不下
			int res = 0;
			
			for(int i=1;i<height.length-1;i++) {
				int leftHeight = 0;
				int rightHeight = 0;
				// 不用管是否连续
				for(int j=i;j>=0;j--) {
					leftHeight = Math.max(leftHeight, height[j]);
				}
				for(int k=i;k<=height.length-1;k++) {
					rightHeight = Math.max(rightHeight, height[k]);
				}
				// 找其最值
				res += Math.min(leftHeight, rightHeight) - height[i];
			}
			return res;
	    }
```

> 解题思路二、维护一个动态数组

```java
// 第二种思路 维护数组
		public int trap_2(int[] height) {
			if(height.length==0){
                return 0;
            }
			int res = 0;
			int[] leftMax = new int[height.length];
			int[] rightMax = new int[height.length];
			// 对其遍历赋值
			leftMax[0] = height[0];
			for(int i=1;i<height.length;i++) {
				leftMax[i] = Math.max(leftMax[i-1], height[i]);
			}
			
			rightMax[height.length-1] = height[height.length-1];
			for(int i=height.length-2;i>=0;i--) {
				rightMax[i] = Math.max(rightMax[i+1], height[i]);
			}
			// 计算
			for(int i=1;i<=height.length-2;i++) {
				res += Math.min(leftMax[i], rightMax[i])-height[i];
			}
			return res;
		}
		
```

> 单调栈的思路

```java
// 第三种思路 单调栈 需要知道后面是否有比它大的
		public int trap_3(int[] height) {
			if(height==null) {
				return 0;
			}
			Deque<Integer> stack = new ArrayDeque<>();
			int res = 0;
			// 对其遍历
			for(int i=0;i<height.length;i++) {
				while(!stack.isEmpty()&&height[i]>height[stack.peek()]) {
					int curIndex = stack.pop();
					while(!stack.isEmpty()&&height[stack.peek()]==height[curIndex]) {
						stack.pop();
					}
					if(!stack.isEmpty()) {
						int stackTop = stack.peek();
						res += (Math.min(height[stackTop], height[i])-height[curIndex])*(i-curIndex-1);
					}
					
				}
				stack.push(i);
			}
			return res;
		}
```



## 数值计算辅助解题

### 1.[Leetcode812最大三角形面积](https://leetcode-cn.com/problems/largest-triangle-area/)

给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。

示例:
输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
输出: 2
解释: 
这五个点如下图所示。组成的橙色三角形是最大的，面积为2。

![image-20210101184643880](C:\Users\LvChaoZhang\AppData\Roaming\Typora\typora-user-images\image-20210101184643880.png)

> 解题思路：暴力循环，暴力得出三个值，然后利用鞋带公式来计算多边形的面积。

```java
class Solution {
	    public double largestTriangleArea(int[][] points) {
	    	// 存储结果
	    	double res = 0;
	    	// for循环遍历 
	    	int len = points.length;
	    	for(int i=0;i<len-2;i++) {
	    		for(int j=i+1;j<len-1;j++) {
	    			for(int k=j+1;k<len;k++) {
	    				res = Math.max(res,area(points[i],points[j],points[k]));
	    			}
	    		}
	    	}
	    	return res;
	    }
	    
	    // 鞋带公式计算面积
	    public double area(int[] P,int[] Q,int[] R) {
	    	return 0.5 * Math.abs(P[0]*Q[1]+Q[0]*R[1]+R[0]*P[1]-
	    						  P[1]*Q[0]-Q[1]*R[0]-R[1]*P[0]);
	    }
	}
```

### [2.Leetcode066加1](https://leetcode-cn.com/problems/plus-one/)

给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

 

示例 1：

输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。

示例 2：

输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。

示例 3：

输入：digits = [0]
输出：[1]

> 解题思路：一开始想着将数组转为数字，但未考虑到数组转数值之后超出了其表示范围，浪费了时间。
>
> 直接对数组操作，加个余数变量。若加完余数变量，剩余余数变量单独操作即可。

```java
class Solution {
    public int[] plusOne(int[] digits) {
           // 为防止超出界限
	    	int flag = 1;
	    	for(int i=digits.length-1;i>=0;i--) {
	    		if(digits[i]+flag>=10) {
	    			digits[i] = 0;
	    			flag = 1;
	    		}else {
	    			digits[i]  = digits[i] + flag;
	    			flag = 0;
	    		}
	    	}
	    	// 查看此时的flag
	    	if(flag!=0) {
	    		// 还有余数
	    		int[] new_digits = new int[digits.length+1];
	    		new_digits[0]    = 1;
	    		return new_digits;
	    	}else {
	    		return digits;
	    	}
    }
}
```

## 逻辑解题

### [1.Leetcode048旋转图像](https://leetcode-cn.com/problems/rotate-image/)

给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

> 解题思路：从大局观来看，解决一圈之后，再调用下一圈

```java
class Solution {
	    public void rotate(int[][] matrix) {
	    	// 左上角和右下角
	    	int row_left = 0;
	    	int col_left = 0;
	    	int row_right = matrix.length-1;
	    	int col_right = matrix[0].length-1;
	    	while(row_left<row_right) {
	    		swap(matrix,row_left++,col_left++,row_right--,col_right--);
	    	}
	    }
	    
	    // 交换
	    public void swap(int[][] matrix,int row_left,int col_left,int row_right,int col_right) {
	    	int temp;
	    	int times = row_right-row_left;
	    	for(int i=0;i<times;i++) {
	    		temp 						   = matrix[row_left][col_left+i];
	    		matrix[row_left][col_left+i]   = matrix[row_right-i][col_left];
	    		matrix[row_right-i][col_left]  = matrix[row_right][col_right-i];
	    		matrix[row_right][col_right-i] = matrix[row_left+i][col_right];
	    		matrix[row_left+i][col_right]   = temp;
	    	}
	    }
	}
```

### [2.Leetcode_offer_04二维数组中的查找](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。

 

限制：

0 <= n <= 1000

0 <= m <= 1000



> 解题思路：从左上角开始，while来判断

```java
class Solution {
	    public boolean findNumberIn2DArray(int[][] matrix, int target) {
	    	// 逻辑题目
	    	// 重点就是从左上角出发
	    	if(matrix==null || matrix.length==0 || matrix[0].length==0) {
	    		return false;
	    	}
	    	int n= matrix.length;
	    	int m = matrix[0].length;
	    	// 左上角点坐标为cur_x,cur_y;
	    	int cur_x = 0;
	    	int cur_y = m-1;
	    	// 满足条件
	    	while(cur_x<n&&cur_y>=0) {
	    		if(matrix[cur_x][cur_y]==target) {
	    			return true;
	    		}else if(matrix[cur_x][cur_y]>target) {
	    			cur_y--;
	    		}else if(matrix[cur_x][cur_y]<target) {
	    			cur_x++;
	    		}
	    	}
	    	return false;
	    }
	}
```

### [3.Leetcode118杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/)

给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。



在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

> 解题思路：就是用ArrayList来辅助解题即可。

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        // 结果
	    	List<List<Integer>> res = new ArrayList<>();
	    	int i = 1;
	    	// 临时值保存上一个的结果
			List<Integer> temp_past = new ArrayList<>();
	    	while(i<=numRows) {
	    		
    			List<Integer> temp = new ArrayList<>();
	    		// 内循环
	    		for(int j=1;j<=i;j++) {
	    			if(j==1) {
	    				temp.add(1);
	    			}else if(j==i) {
	    				temp.add(1);
	    			}else {
	    				// 其余的时候
	    				temp.add(temp_past.get(j-2)+temp_past.get(j-1));
	    			}
	    		}
	    		
	    		if(i!=1) {
    				//保存结果
	    			temp_past = temp;
    			}
	    		// 添加到结果中
	    		res.add(temp);
	    		i++;
	    	}
	    	
	    	return res;
    }
}
```

### [4.Leetcode054螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)

给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]

示例 2:

输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]

> 解题思路：按层数 一层一层的处理，无非就是记得对里面剩下一行或者一列的情况处理

```java
class Solution {
		// 存储结果
    	List<Integer> res = new ArrayList<>();
	    public List<Integer> spiralOrder(int[][] matrix) {
	    	// 存储结果
 	    	int start_row = 0;
	    	int start_col = 0;
	    	int end_row   = matrix.length-1;
	    	int end_col   = matrix[0].length-1;
	    	//while循环
	    	while(start_row<=end_row&&start_col<=end_col) {
	    		printCircle(matrix,start_row++,start_col++,end_row--,end_col--);
	    	}
	    	return res;
	    }
	    // 打印图形
	    public void printCircle(int[][] matrix,int start_row,int start_col,int end_row,int end_col) {

	    	// 开始
	    	for(int i=start_col;i<=end_col;i++) {
	    		res.add(matrix[start_row][i]);
	    	}
	    	for(int i=start_row+1;i<=end_row;i++) {
	    		res.add(matrix[i][end_col]);
	    	}

            // 如果已经结束了
	    	if(start_row==end_row || start_col == end_col) {
	    		return;
	    	}
            
	    	for(int i=end_col-1;i>start_col;i--) {
	    		res.add(matrix[end_row][i]);
	    	}
	    	for(int i=end_row;i>start_row;i--) {
	    		res.add(matrix[i][start_col]);
	    	}
	    	
	    }
	}
```

### [4.剑指Offer29顺时针打印矩阵](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]

示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]

```java
class Solution {
    public int[] spiralOrder(int[][] matrix) {
         if(matrix.length==0||matrix[0].length==0){
             return new int[]{};
         }
         int start_row = 0;
	        int start_col = 0;
	        int end_row   = matrix.length-1;
	        int end_col   = matrix[0].length-1;
	        int[] res       = new int[matrix.length*matrix[0].length];
	        int index	  = 0;
	        while(start_row<=end_row&&start_col<=end_col) {
	        	// 开始遍历
	        	for(int i=start_col;i<=end_col;i++) {
	        		res[index++] = matrix[start_row][i];
	        	}
	        	for(int i=start_row+1;i<=end_row;i++) {
	        		res[index++] = matrix[i][end_col];
	        	}
	        	// 判断是否为单列
	        	if(start_row!=end_row) {
	        		for(int i=end_col-1;i>=start_col;i--) {
	        			res[index++] = matrix[end_row][i];
	        		}
	        	}
	        	// 判断是否为单行
	        	if(start_col!=end_col) {
	        		for(int i=end_row-1;i>start_row;i--) {
	        			res[index++] = matrix[i][start_col];
	        		}
	        	}
	        	start_row++;
	        	start_col++;
	        	end_row--;
	        	end_col--;
	        }
	        return res;
    }
}
```

### [5.Leetcode119 杨辉三角II](https://leetcode-cn.com/problems/pascals-triangle-ii/)

给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

![img](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif)

在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:

输入: 3
输出: [1,3,3,1]

```java
package com.lcz.leetcode;
import java.util.*;
public class Leetcode119 {
	class Solution {
	    public List<Integer> getRow(int rowIndex) {
	    	// 全部的
	    	List<List<Integer>> res_all = new ArrayList<>();
	    	for(int i=0;i<=rowIndex;i++) {
	    		// 每一行的List
	    		List<Integer> res = new ArrayList<>();
	    		//每一行的值
	    		for(int j=0;j<=i;j++) {
	    			// 头是1 尾巴是1
	    			if(j==0 || j==i) {
	    				res.add(1);
	    			}else {
	    				//值(i,j)= (i-1,j)+(i-1,j-1)
	    				int lastValue_1 = res_all.get(i-1).get(j);
	    				int ladtValue_2 = res_all.get(i-1).get(j-1);
	    				res.add(lastValue_1+ladtValue_2);
	    			}
	    		}
	    		// 添加
	    		res_all.add(res);
	    	}
	    	// 返回
	    	return res_all.get(rowIndex);
	    }
	}
}

```

> 优化思路：

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = i; j >= 1; j--) {
                list.set(j, list.get(j - 1) + list.get(j));
            }
            list.add(1);
        }
        return list;
    }
}


```

### [6.Leetcode074搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)

编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。

示例 1：

![img](https://assets.leetcode.com/uploads/2020/10/05/mat.jpg)

输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true

> 解题思路：从右上角开始搜索即可

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
	    	int n = matrix[0].length;
	    	int start_x = 0;
	    	int start_y = n-1;
	    	// 开始搜索
	    	while(start_x<m&&start_y>=0) {
	    		if(matrix[start_x][start_y]==target) {
	    			return true;
	    		}else if(matrix[start_x][start_y]>target) {
	    			start_y--;
	    		}else if(matrix[start_x][start_y]<target) {
	    			start_x++;
	    		}
	    	}
	    	return false;
    }
}
```

### [7.Leetcode628三个数的最大乘积](https://leetcode-cn.com/problems/maximum-product-of-three-numbers/)

给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

 

示例 1：

输入：nums = [1,2,3]
输出：6

示例 2：

输入：nums = [1,2,3,4]
输出：24
示例 3：

输入：nums = [-1,-2,-3]
输出：-6

> 解题思路：三个数全是正数，则就是最后三个最大的；
>
> 若有负数，那么要不就是两个最小的*一个最大的或者最后三个最大的。

```java
class Solution {
		// 解题思路：排序 + 思考 如果数组全是正数那么 就是最后三个乘积 如果数组有负数，那么要不就是三个乘积 要不就是两个最小的负数*一个正数
	    public int maximumProduct(int[] nums) {
	    	Arrays.sort(nums);
	    	int len = nums.length;
	    	int res = Math.max(nums[len-1]*nums[len-2]*nums[len-3], nums[0]*nums[1]*nums[len-1]);
	    	return res;
	    }
	}
```

### [8.Leetcode922按奇偶排序数组II](https://leetcode-cn.com/problems/sort-array-by-parity-ii/)

给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。

 

示例：

输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。


提示：

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000

> 解题思路：在原数组上进行修改，两个指针，一个奇数 一个偶数

```java
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int left = 0;
	    	int right = 1;
	    	while(left<A.length-1&&right<A.length) {
	    		// 满足条件移动到一个
	    		// 满足偶数
	    		while(left<A.length-1&&A[left]%2==0) {
	    			left += 2;
	    		}
	    		// 满足奇数
	    		while(right<A.length&&A[right]%2==1) {
	    			right += 2;
	    		}
	    		// 存在不满足的
	    		if(left<A.length-1&&right<A.length) {
	    			int temp = A[left];
	    			A[left]  = A[right];
	    			A[right] = temp;
	    		}
	    	}
	    	return A;
    }
}
```



## 动态规划辅助解题

### 1.[Leetcode120三角形最小路径和](https://leetcode-cn.com/problems/triangle/)经典动态规划入门题

给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。

相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。

 

示例 1：

输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

示例 2：

输入：triangle = [[-10]]
输出：-10



> 解题思路一、
>
> 在本题中，给定的三角形的行数为 nn，并且第 ii 行（从 00 开始编号）包含了 i+1i+1 个数。如果将每一行的左端对齐，那么会形成一个等腰直角三角形，如下所示：
>
>
> [2]
> [3,4]
> [6,5,7]
> [4,1,8,3]
>
> 动态规划解题：定义、初始化、以及转移方程。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
           // 动态规划 定义 初始化 转移方程
           int n = triangle.size();
           int[][] dp = new int[n][n];
           //初始化
           dp[0][0] = triangle.get(0).get(0);
           for(int i=1;i<n;i++){
               dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);
           }
           int i,j;
           // 遍历
           for(i=1;i<n;i++){
               for(j=1;j<i;j++){
                   dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1]) + triangle.get(i).get(j);
               }
               if(j==i){
                   dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
               }
           }

           // 结果是找最后一列
           int res = dp[n-1][0];
           for(i=1;i<n;i++){
               res = Math.min(res,dp[n-1][i]);
           }
           return res;
    }
}
```

> 解题思路二、找最小路径和。从上往下走与从下往上走是一样的，只不过转移方程不一样而已，而且由列来决定。

```java
 // 定义并将其赋值为0
			int[] dp = new int[triangle.size()+1];
			// 定义行
			int row = triangle.size();
			// 从最后一行开始走
			for(int i=row-1;i>=0;i--) {
				// 列从一开始走 
				for(int j=0;j<=i;j++) {
					// 转移方程
					dp[j] = Math.min(dp[j],dp[j+1]) + triangle.get(i).get(j);
				}				
			}
			return dp[0];
```

### [1.Leetcode62不同路径](https://leetcode-cn.com/problems/unique-paths/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

![image-20210106103050216](C:\Users\LvChaoZhang\AppData\Roaming\Typora\typora-user-images\image-20210106103050216.png)

示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右

> 解题思路：路径问题，与上述的最短不同的是，本次是统计不同路径，理解思路是一样的，但不同的是其在初始化与转移方程的不同。

```java
class Solution {
    public int uniquePaths(int m, int n) {
            // 从上往下走
	    	int[][] dp = new int[m][n];
	    	// 初始化
	    	for(int i=0;i<m;i++) {
	    		dp[i][0] = 1;
	    	}
	    	for(int j=0;j<n;j++) {
	    		dp[0][j] = 1;
	    	}
	    	// 开始遍历
	    	for(int i=1;i<m;i++) {
	    		for(int j=1;j<n;j++) {
	    			// 转移方程 求路径和
	    			dp[i][j] = dp[i-1][j] + dp[i][j-1];
	    		}
	    	}
	    	
	    	return dp[m-1][n-1];
    }
}
```

### [1.Leetcode63不同路径II](https://leetcode-cn.com/problems/unique-paths-ii/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

网格中的障碍物和空位置分别用 1 和 0 来表示。

示例 1：

![img](https://assets.leetcode.com/uploads/2020/11/04/robot1.jpg)

输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：

1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
示例 2：

> 解题思路：还是动态规划，无非就是判断数组为0还是1，为1的话则有障碍物，过不去了。

```java
class Solution {
	    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	    	// 定义 初始化 转移方程
	    	int n  = obstacleGrid.length;
	    	int m  = obstacleGrid[0].length;
	    	int[][] dp = new int[n][m];
	    	// 初始化
	    	for(int i=0;i<n;i++) {
	    		// 如果遇到一个障碍物 后面无法走到
	    		if(obstacleGrid[i][0]==1) {
	    			break;
	    		}
	    		dp[i][0] = 1;
	    	}
	    	for(int j=0;j<m;j++) {
	    		//遇到障碍物，后面无法走到
	    		if(obstacleGrid[0][j]==1) {
	    			break;
	    		}
	    		dp[0][j] = 1;
	    	}
	    	// 转移方程
	    	for(int i=1;i<n;i++) {
	    		for(int j=1;j<m;j++) {
	    			//主要看当前是否为障碍物
	    			dp[i][j] = obstacleGrid[i][j]==1?0:dp[i-1][j]+dp[i][j-1];
	    		}
	    	}
	    	return dp[n-1][m-1];
	    }
	}
```





### [1.Leetcode064最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

 

示例 1：

![image-20210107110121915](C:\Users\LvChaoZhang\AppData\Roaming\Typora\typora-user-images\image-20210107110121915.png)

输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。

示例 2：

输入：grid = [[1,2,3],[4,5,6]]
输出：12

> 解题思路：定义 初始化 转移方程

```java
class Solution {
    public int minPathSum(int[][] grid) {
            // 动态规划路径问题 定义 初始化 转移方程 从下往上走
	    	int n = grid.length;
	    	int m = grid[0].length;
	    	// 定义
	    	int[][] dp = new int[n][m];
	    	// 初始化
	    	dp[0][0] = grid[0][0];
	    	for(int i=1;i<n;i++) {
	    		dp[i][0] = dp[i-1][0] + grid[i][0];
	    	}
	    	for(int j=1;j<m;j++) {
	    		dp[0][j] = dp[0][j-1] + grid[0][j];
	    	}
	    	// 开始遍历
	    	for(int i=1;i<n;i++) {
	    		for(int j=1;j<m;j++) {
	    			dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + grid[i][j];
	    		}
	    	}
	    	return dp[n-1][m-1];
    }
}
```



### 2.[Leetcode121买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)

> **具体详情可见动态规划之买卖股票一节。**

给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

注意：你不能在买入股票前卖出股票。

 

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

示例 2:

输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

> 股票详情买卖问题可参考动态规划之股票买卖。
>
> 这里一次买卖的话。主要更新当前天数之前的最小的值，然后每天计算最大值。

```java
class Solution {
	    public int maxProfit(int[] prices) {
	        if(prices==null || prices.length<2) {
	        	return 0;
	        }
	    	int price = prices[0];
	    	int profit = 0;
	    	for(int i=1;i<prices.length;i++) {
	    		profit = Math.max(prices[i]-price, profit);
	    		price = Math.min(price, prices[i]);
	    	}
	    	return profit;
	    }
	}
```



### [3.Leetcode053最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

> 动态规划解题思路
>
> 定义 初始化 转移方程
>
> 连续的话，就是从当前开始或者当前+之前

```java
class Solution {
    public int maxSubArray(int[] nums) {
            // 动态规划解题 定义 初始化 转移方程
	    	int[] dp = new int[nums.length+1];
	    	dp[0]    = nums[0];
	    	for(int i=1;i<nums.length;i++) {
	    		// 转移方程 当前值 和 当前值以及前一个的值
	    		dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
	    	}
	    	// 从数组中寻找最大值
	    	int max_value = dp[0];
	    	for(int i=1;i<nums.length;i++) {
	    		max_value = Math.max(max_value, dp[i]);
	    	}
	    	return max_value;
    }
}
```

更近一步

```java
class Solution {
    public int maxSubArray(int[] nums) {
            // 动态规划解题 定义 初始化 转移方程
	    	int[] dp = new int[nums.length+1];
	    	dp[0]    = nums[0];
            int max_value = nums[0];
	    	for(int i=1;i<nums.length;i++) {
	    		// 转移方程 当前值 和 当前值以及前一个的值
	    		dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
                max_value = Math.max(max_value, dp[i]);
	    	}
	    	return max_value;
    }
}
```

### [4.Leetcode120三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。



示例 1：

输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
输出：11
解释：如下面简图所示：
   2
  3 4
 6 5 7
4 1 8 3
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

示例 2：

输入：triangle = [[-10]]
输出：-10

> 解题思路：动态规划

```java
package com.lcz.leetcode;
import java.util.*;
// 三角形最小路径和
// 解题思路 动态规划
public class Leetcode0120 {
	class Solution {
	    public int minimumTotal(List<List<Integer>> triangle) {
	    	// 动态规划 定义 初始化 转移方程
	    	int size = triangle.size();
	    	// 定义
	    	int[][] dp = new int[size][size];
	    	// 初始化
	    	dp[0][0] = triangle.get(0).get(0);
	    	// 转移方程
	    	for(int i=1;i<size;i++) {
	    		List<Integer> temp = triangle.get(i);
	    		// 开始接着遍历 每一行
	    		int j=0;
	    		if(j==0) {
    				dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
    			}
	    		for(j=1;j<temp.size()-1;j++) {
	    			dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);    			
	    		}
	    		if(j==temp.size()-1) {
	    			dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
	    		}
	    	}
	    	// 从最后一行找最小值
	    	int minValue = dp[size-1][0];
	    	for(int i=1;i<size;i++) {
	    		minValue = Math.min(minValue, dp[size-1][i]);
	    	}
	    	return minValue;
	    }
	}
}

```

### [5.Leetcode509 裴波那契数](https://leetcode-cn.com/problems/fibonacci-number/)

斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：

F(0) = 0，F(1) = 1
F(n) = F(n - 1) + F(n - 2)，其中 n > 1
给你 n ，请计算 F(n) 。

 

示例 1：

输入：2
输出：1
解释：F(2) = F(1) + F(0) = 1 + 0 = 1

示例 2：

输入：3
输出：2
解释：F(3) = F(2) + F(1) = 1 + 1 = 2

示例 3：

输入：4
输出：3
解释：F(4) = F(3) + F(2) = 2 + 1 = 3

> 解题思路：递归

```java
// 递归
		public int fib(int n) {
	    	if(n==0) {
	    		return 0;
	    	}
	    	if(n==1) {
	    		return 1;
	    	}
	    	return fib(n-1)+fib(n-2);
	    }
```

> 解题思路：动态规划

```java
// 动归解题
		public int fib_2(int n) {
	    	if(n==0) {
	    		return 0;
	    	}
	    	if(n==1) {
	    		return 1;
	    	}
	    	int f0 = 0;
	    	int f1 = 1;
	    	int f = 0;
	    	for(int i=2;i<=n;i++) {
	    		f  = f0 + f1;
	    		f0 = f1;
	    		f1 = f;
	    	}
	    	return f;
	    }
```

### [6.Leetcode746使用最小花费爬楼梯](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)

数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。

每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。

请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。

 

示例 1：

输入：cost = [10, 15, 20]
输出：15
解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。

 示例 2：

输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出：6
解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length];
	    	//初始化
	    	dp[0] = cost[0];
	    	dp[1] = cost[1];
	    	// 不一定非得到最后一个 倒数第二个也可以
	    	for(int i=2;i<cost.length;i++) {
	    		// 当前值=前一家 前两家
	    		dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
	    	}
	    	//返回值
	    	return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }
}
```

### [7.Leetcode152乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)

给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

 

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。

示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

> 解题思路：存在负数，那么就维护两个值，一个是最大，一个是最小，负数时候交换两数。要不就来判断乘积之后的值大小

```java
class Solution {
    public int maxProduct(int[] nums) {
        // 维护一个最大的子数组，维护一个最小的子数组
	    	int res = Integer.MIN_VALUE;
	    	int imax = 1;
	    	int imin = 1;
	    	for(int i=0;i<nums.length;i++) {
	    		// 负数的时候交换
	    		if(nums[i]<0) {
	    			int temp = imax;
	    			imax = imin;
	    			imin = temp;
	    		}
	    		// 要不就从头开始要不就
	    		imax = Math.max(imax*nums[i], nums[i]);
	    		imin = Math.min(imin*nums[i], nums[i]);
	    		res = Math.max(imax,res);
	    	}
	    	return res;
    }
}
```



## 回溯解题

```java
def backward():
    if (回朔点）：# 这条路走到底的条件。也是递归出口
        保存该结果
        return   
    else:
        for route in all_route_set :  逐步选择当前节点下的所有可能route
            if 剪枝条件：
                剪枝前的操作
                return   #不继续往下走了，退回上层，换个路再走            
            else：#当前路径可能是条可行路径
                保存当前数据  #向下走之前要记住已经走过这个节点了。例如push当前节点
                self.backward() #递归发生，继续向下走一步了。                
                回朔清理     # 该节点下的所有路径都走完了，清理堆栈，准备下一个递归。例如弹出当前节点
```

### [1.Leetcode078子集](https://leetcode-cn.com/problems/subsets/)

给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。


示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]


提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, res, new ArrayList<Integer>());
        return res;

    }

    private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
        // 因为是子集 所以不需要判断长度
        res.add(new ArrayList<>(tmp));
        for (int j = i; j < nums.length; j++) {
            tmp.add(nums[j]);
            // 注意j+1了
            backtrack(j + 1, nums, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }
}
```

### [2.Leetcode090子集II](https://leetcode-cn.com/problems/subsets-ii/)

给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

> 解题思路：回溯解题。子集只能用一次。且数组中可能包含重复的元素，所以需要对其排序，之后进行判断。
>
> 此外记得拷贝一份临时值。回溯的时候。

```java
class Solution {
	    public List<List<Integer>> subsetsWithDup(int[] nums) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> path = new ArrayList<>();
	    	// 排序
	    	Arrays.sort(nums);
	    	backtrack(nums,0,path,res);
	    	return res;
	    }
	    public void backtrack(int[] nums,int index,List<Integer> path,List<List<Integer>> res) {
	    	res.add(new ArrayList<>(path));
	    	// for循环
	    	for(int i=index;i<nums.length;i++) {
	    		// 去重
	    		if(i>index&&nums[i]==nums[i-1]) {
	    			continue;
	    		}
	    		path.add(nums[i]);
	    		backtrack(nums, i+1, path, res);
	    		path.remove(path.size()-1);
	    	}
	    }
	}
```

### [3.Leetcode046全排列](https://leetcode-cn.com/problems/permutations/)

给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

> 回溯解决方法之二，通过判断其容器的大小是否满足条件，另一方面通过一个数组判断是否访问过了。

```java
class Solution {
		public List<List<Integer>> permute(int[] nums) {
			List<List<Integer>> res = new ArrayList<>();
			List<Integer> path = new ArrayList<>();
			
			// 所有数字都需要遍历一遍
			boolean[] visited = new boolean[nums.length];
			backtrack(nums,visited, path, res);
			return res;
	    }
		
		public void backtrack(int[] nums,boolean[] visited,List<Integer> path,List<List<Integer>> res) {
			if(path.size()==nums.length) {
				res.add(new ArrayList<>(path));
				return;
			}
			// 全部加入
			for(int i=0;i<nums.length;i++) {
				// 防止一个数字使用多遍
				if(visited[i]==true) {
					continue;
				}
				visited[i]=true;
				path.add(nums[i]);
				backtrack(nums,visited, path, res);
				visited[i]=false;
				path.remove(path.size()-1);
			}
		}
	}
```

### [4.Leetcode047全排列II](https://leetcode-cn.com/problems/permutations-ii/)


给定一个可包含重复数字的序列 `nums` ，**按任意顺序** 返回所有不重复的全排列。

 

**示例 1：**

```
输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
```

**示例 2：**

```
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
```

> 解题思路：对其数组重复判断

```java
class Solution {
	    public List<List<Integer>> permuteUnique(int[] nums) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> path = new ArrayList<>();
	    	// 数组去重
	    	Arrays.sort(nums);
	    	// 统计次数
	    	boolean[] visited = new boolean[nums.length];
	    	backtrack(nums,visited,path,res);
	    	return res;
 	    }
	    public void backtrack(int[] nums,boolean[] visited,List<Integer> path,List<List<Integer>> res) {
	    	if(path.size()==nums.length) {
	    		res.add(new ArrayList<>(path));
	    		return;
	    	}
	    	//  遍历
	    	for(int i=0;i<nums.length;i++) {
	    		// 防止多次访问
	    		if(visited[i]==true) {
	    			continue;
	    		}
	    		// 防止数组重复
	    		if(i>0&&nums[i]==nums[i-1]&&visited[i-1]==false) {
	    			continue;
	    		}
	    		// 回溯
	    		visited[i] = true;
	    		path.add(nums[i]);
	    		backtrack(nums, visited, path, res);
	    		visited[i] = false;
	    		path.remove(path.size()-1);
	    		
	    	}
	    }
	}
```



### [5.Leetcode039组合总和](https://leetcode-cn.com/problems/combination-sum/)

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 
示例 1：

输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]

示例 2：

输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

> 解题思路：回溯 + 排序进行剪枝。
>
> 但本题有两个点需要注意：一个是数组中本来就没有重复的元素，所以不需要去重的代码；另外一个是数组中的数字可以无限制的选取，所以索引还是i。此外记得总和采用临时值的方式。

```java
package com.lcz.leetcode;
// 组合总和
// 回溯法解决
import java.util.*;
public class Leetcode039 {
	class Solution {
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> res_temp = new ArrayList<>();
	    	// 回溯法调用
	    	// 排序 剪枝
	    	Arrays.sort(candidates);
	    	backtrack(candidates,0,0,target,res_temp,res);
	    	return res;
	    }
	    // 回溯法
	    public void backtrack(int[] candidates,int index,int sum,int target,List<Integer> res_temp,List<List<Integer>> res) {
	    	// 判断是否满足条件
	    	if(sum==target) {
	    		res.add(new ArrayList<>(res_temp));
	    		return;
	    	}
	    	
	    	// for循环
	    	for(int i=index;i<candidates.length;i++) {
	    		// 临时值
	    		int temp = candidates[i] + sum;
	    		if(temp<=target) {
	    			res_temp.add(candidates[i]);
		    		backtrack(candidates, i, temp, target, res_temp, res);
		    		res_temp.remove(res_temp.size()-1);
	    		}else {
	    			break;
	    		}
	    	}
	    	
	    }
	}
}

```

### [6.Leetcode040组合总和II](https://leetcode-cn.com/problems/combination-sum-ii/)

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 

示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]

> 解题思路：与上述不同的是，数组一方面重复的；另外一些数字只能用一次。

```java
package com.lcz.leetcode;
// 组合总和II
import java.util.*;
public class Leetcode040 {
	class Solution {
	    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	    	List<List<Integer>> res = new ArrayList<>();
	    	List<Integer> path = new ArrayList<>();
	    	// 排序 剪枝
	    	Arrays.sort(candidates);
	    	
	    	backtrack(candidates,0,0,target,path,res);
	    	return res;
	    }
	    public void backtrack(int[] candidates,int index,int sum,int target,List<Integer> path,List<List<Integer>> res) {
	    	// 判断是否满足条件了
	    	if(sum==target) {
	    		// 拷贝一份path防止影响
	    		res.add(new ArrayList<>(path));
	    		return;
	    	}
	    	// for循环
	    	for(int i=index;i<candidates.length;i++) {
	    		// 去重
	    		if(i>index&&candidates[i]==candidates[i-1]) {
	    			continue;
	    		}
	    		// 临时值
	    		int temp = sum + candidates[i];
	    		// 判断满足条件
	    		if(temp<=target) {
	    			path.add(candidates[i]);
		    		backtrack(candidates, i+1, temp, target, path, res);
		    		path.remove(path.size()-1);
	    		}else {
	    			break;
	    		}
	    	}    	
	    }
	}
}

```

### [7.Leetcode077组合](https://leetcode-cn.com/problems/combinations/)

给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

示例:

输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

> 解题思路：类似于子集的问题，无非就是有长度的限制。

```java
class Solution {
		List<Integer> temp = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
	    public List<List<Integer>> combine(int n, int k) {
	    	// 回溯算法
	    	dfs(1,n,k);
	    	return res;
	    }
	    public void dfs(int cur,int n,int k) {
	    	// 长度限制即递归结束条件
	    	if(temp.size() + (n-cur+1) < k || temp.size()>k) {
	    		return;
	    	}
	    	// 满足条件的话
	    	if(temp.size()==k) {
	    		// 添加
	    		res.add(new ArrayList<>(temp));
	    		return;
	    	}
	    	
	    	// for循环
	    	for(int i=cur;i<=n;i++) {
	    		temp.add(i);
	    		dfs(i+1,n,k);
	    		temp.remove(temp.size()-1);
	    	}
	    	
	    }
	}
```



### [8.Leetcode216组合总和III](https://leetcode-cn.com/problems/combination-sum-iii/)

找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

说明：

所有数字都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]

> 解题思路：看成子集问题，只不过有长度限制，满足长度之后看是否满足总和的要求。

```java
class Solution {
	    // 解题思路:从9个数中挑选k个数，符合总和n则加入进去
		List<Integer> temp = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		public List<List<Integer>> combinationSum3(int k, int n) {
			//从9个数挑选k个数
			dfs(1,9,k,n);
			return res;
	    }
		// 回溯
		public void dfs(int cur,int n,int k,int sum) {
			// 递归截止条件
			if(temp.size()+(n-cur+1) < k || temp.size()>k) {
				return;
			}
			if(temp.size()==k) {
				int tempSum = 0;
				for(int num:temp) {
					tempSum+=num;
				}
				if(tempSum==sum) {
					// 加入
					res.add(new ArrayList<>(temp));
				}
				
			}
			// for循环
			for(int i=cur;i<=n;i++) {
				// 判断是否满足条件
					temp.add(i);
					dfs(i+1, n, k, sum);
					temp.remove(temp.size()-1);
			}
		}
	}
```



### [9.Leetcode79单词搜索](https://leetcode-cn.com/problems/word-search/)

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

示例:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false

> 解题思路：回溯的思路，首先不确定起始值所以两个for循环上。之后回溯
>
> 递归截止条件，遍历下一个的条件 返回

```java
class Solution {
	    public boolean exist(char[][] board, String word) {
	    	char[] words = word.toCharArray();
	    	// 其开头位置不确定
	    	for(int i=0;i<board.length;i++) {
	    		for(int j=0;j<board[0].length;j++) {
	    			if(dfs(board,words,i,j,0)) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
	    }
	    
	    // 回溯
	    public boolean dfs(char[][] board,char[] words,int i,int j,int index) {
	    	// 递归结束条件
	    	if(i>=board.length || i<0 || j>=board[0].length || j<0 || board[i][j]!=words[index]) {
	    		return false;
	    	}
	    	// 结束的话就是
	    	if(index==words.length-1) {
	    		return true;
	    	}
	    	// 看下一个
	    	char temp = board[i][j];
	    	// 记录当前已经被遍历过了
	    	board[i][j] = '0';
	    	boolean res = dfs(board,words,i,j+1,index+1) || dfs(board,words,i,j-1,index+1) 
	    			    ||dfs(board,words,i+1,j,index+1) || dfs(board,words,i-1,j,index+1);
	    	// 恢复
	    	board[i][j] = temp;
	    	return res;
	    	
	    }
	}
```

### [10.Leetcode1219黄金矿工](https://leetcode-cn.com/problems/path-with-maximum-gold/)

你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：

每当矿工进入一个单元，就会收集该单元格中的所有黄金。
矿工每次可以从当前位置向上下左右四个方向走。
每个单元格只能被开采（进入）一次。
不得开采（进入）黄金数目为 0 的单元格。
矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。


示例 1：

输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
输出：24
解释：
[[0,6,0],
 [5,8,7],
 [0,9,0]]
一种收集最多黄金的路线是：9 -> 8 -> 7。

示例 2：

输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
输出：28
解释：
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。

> 解题思路：网格搜索  上下左右 任意位置 
>
> 回溯：记录当前值 ，修改当前值 ，遍历别的方向，回溯当前值

```java
class Solution {
	    // 回溯网格问题：任意位置出发，上下左右都可以走
		public int getMaximumGold(int[][] grid) {
	    	// 结果
			int res = 0;
			// 因为从任意一点出发
			int n = grid.length;
			int m = grid[0].length;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					res = Math.max(dfs(grid,i,j),res);
				}
			}
			return res;
	    }
		
		// 回溯
		public int dfs(int[][] grid,int i,int j) {
			// 递归结束条件
			if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
				return 0;
			}
			// 网格回溯 记录当前值 当前值已访问 恢复当前值
			int temp = grid[i][j];
			// 当前值已经被访问了
			grid[i][j] = 0;
			// 沿着继续走
			int up = dfs(grid,i,j-1);
			int down = dfs(grid,i,j+1);
			int left = dfs(grid,i-1,j);
			int right = dfs(grid,i+1,j);
			int max = Math.max(up, Math.max(right, Math.max(down, left)));
			// 恢复当前值
			grid[i][j] = temp;
			return grid[i][j]+max;
		}
	}
```

### [11.Leetcode051N皇后](https://leetcode-cn.com/problems/n-queens/)

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

 

示例 1：

输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。

示例 2：

输入：n = 1
输出：[["Q"]]

> 解题思路：
>
> 1.构造一个棋盘，构造返回结果，对行进行一行一行的走
>
> 2.那么回溯中遍历列即可就需要判断能否放下去，放下去之后 回溯
>
> 3.判断是否能放下去 当前的上方 左上角 右上角

```java
class Solution {
	    public List<List<String>> solveNQueens(int n) {
	    	// 构建一个N*N的棋盘
	    	char[][] chess = new char[n][n];
	    	for(int i=0;i<n;i++) {
	    		for(int j=0;j<n;j++) {
	    			chess[i][j] = '.';
	    		}
	    	}
	    	// 结果存储
	    	List<List<String>> res = new ArrayList<>();
	    	//开始回溯解题 从棋盘第0行开始
	    	solve(res,chess,0);
	    	return res;
	    }
	    // 回溯解题
	    public void solve(List<List<String>> res,char[][] chess,int row) {
	    	// 递归截止条件
	    	if(row==chess.length) {
	    		// 将当前棋盘封装成想要的值
	    		res.add(construct(chess));
	    		return;
	    	}
	    	// 开始遍历列
	    	for(int col=0;col<chess[0].length;col++) {
	    		// 判断是否合法 合法才记录
	    		if(valid(chess,row,col)) {
	    			chess[row][col] = 'Q';
	    			solve(res,chess,row+1);
	    			chess[row][col] = '.';
	    		}
	    	}
	    	
	    }
	    // 判断是否合法
	    public boolean valid(char[][] chess,int row,int col) {
	    	// 保证其坐标上面位置没有皇后
	    	for(int i=0;i<row;i++) {
	    		if(chess[i][col]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 判断当前坐标的左上角对角线
	    	for(int i=row-1,j=col+1;i>=0&&j<chess[0].length;i--,j++) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 判断当前坐标的左上角对角线
	    	for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	// 都没问题
	    	return true;
	    }
	    
	    
	    // 棋盘封装成返回值
	    public List<String> construct(char[][] chess){
	    	List<String> path = new ArrayList<>();
	    	for(int i=0;i<chess.length;i++) {
	    		// 整行一起添加进来
	    		path.add(new String(chess[i]));
	    	}
	    	return path;
	    }
	}
```

### [12.Leetcode053	N皇后II](https://leetcode-cn.com/problems/n-queens-ii/)

n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。

 

示例 1：

![img](https://assets.leetcode.com/uploads/2020/11/13/queens.jpg)

输入：n = 4
输出：2
解释：如上图所示，4 皇后问题存在两个不同的解法。
示例 2：

输入：n = 1
输出：1

> 解题思路：同上

```java
class Solution {
        int res = 0;
	    public int totalNQueens(int n) {
	    	// 构建棋盘
	    	char[][] chess = new char[n][n];
	    	for(int i=0;i<n;i++) {
	    		for(int j=0;j<n;j++) {
	    			chess[i][j] = '.';
	    		}
	    	}
	    	// 开始回溯 第0行开始
	    	dfs(chess,0);
	    	return res;
	    }
	    
	    // 回溯
	    public void dfs(char[][] chess,int row) {
	    	// 递归结束条件
	    	if(row==chess.length) {
	    		res+=1;
	    		return;
	    	}
	    	// 回溯
	    	for(int col=0;col<chess[0].length;col++) {
	    		// 判断能否放下去
	    		if(valid(chess,row,col)) {
	    			chess[row][col] = 'Q';
	    			dfs(chess,row+1);
	    			chess[row][col] = '.';
	    		}
	    	}
	    }
	    
	    // 判断合法性
	    public boolean valid(char[][] chess,int row,int col) {
	    	// 判断其上面 其左上角 右上角
	    	
	    	for(int i=0;i<row;i++) {
	    		if(chess[i][col]=='Q') {
	    			return false;
	    		}
	    	}
	    	
	    	for(int i=row-1,j=col+1;i>=0&&j<chess[0].length;i--,j++) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	
	    	for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--) {
	    		if(chess[i][j]=='Q') {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	}
```

## 深度优先搜索(与回溯类似但不回去)

### [1.Leetcode695岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)

给定一个包含了一些 0 和 1 的非空二维数组 grid 。

一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)

 

示例 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。

示例 2:

[[0,0,0,0,0,0,0,0]]
对于上面这个给定的矩阵, 返回 0。

> 解题思路：沉岛的解题思路，遇到1将其周围都置为0，将其添加求和即可。或者类似于朋友圈问题

```java
class Solution {
		// 解题思路：DFS与回溯略微有所不同，回溯是路径下去 然后再回来 DFS是周围全部 的
		// 解题用沉岛思想，也可以用类似朋友圈思想
	    public int maxAreaOfIsland(int[][] grid) {
	    	// 结果
	    	int res  = 0;
	    	// 出发点任意
	    	for(int i=0;i<grid.length;i++) {
	    		for(int j=0;j<grid[0].length;j++) {
	    			if(grid[i][j]==1) {
		    			res = Math.max(res, dfs(grid,i,j));
	    			}
	    		}
	    	}
	    	return res;
	    }
	    // 深度优先搜索
	    public int dfs(int[][] grid,int i,int j) {
	    	// 递归截止条件
	    	if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
	    		return 0;
	    	}
	    	// 统计结果 当前结果
	    	int num = 1;
	    	// 已访问置为0
	    	grid[i][j] = 0;
	    	// 别的点
	    	num += dfs(grid,i+1,j);
	    	num += dfs(grid,i-1,j);
	    	num += dfs(grid,i,j+1);
	    	num += dfs(grid,i,j-1);
	    	return num;	    	
	    }
	}
```





## 贪心辅助解题

### [1.Leetcode605种花问题](https://leetcode-cn.com/problems/can-place-flowers/)

假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

示例 1:

输入: flowerbed = [1,0,0,0,1], n = 1
输出: True

示例 2:

输入: flowerbed = [1,0,0,0,1], n = 2
输出: False

> 种花问题：从左向右遍历，在可以种花的地方就种一朵，能种就种(因为在任意一种花)，而这是一种贪心思想
>
> 关键点在于如何判断是否能种花：当前为0,左0(或者当前为0) 右边为0(或者当前为左右边)

```java
public boolean canPlaceFlowers(int[] flowerbed, int n) {
	    	// 只要能种 那么都种上 贪心思想
	    	// 什么情况下能种花？ 本身为空 左为空(当前为首) 右为空(当前为尾巴)
	    	// 遍历
	    	int res =  0;
	    	for(int i=0;i<flowerbed.length;i++) {
	    		if(flowerbed[i]==0 && (i==0 || flowerbed[i-1]==0) && (i==flowerbed.length-1 || flowerbed[i+1]==0)){
	    			// 可以种花
	    			flowerbed[i]=1;
	    			res++;
	    		}
	    	}
	    	return res>=n;
	    }
```

 ### [2.Leetcode055跳跃游戏](https://leetcode-cn.com/problems/jump-game/)

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个位置。

示例 1:

输入: [2,3,1,1,4]
输出: true
解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。

示例 2:

输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。

> 解题思路：贪心算法。对于整个数组遍历，但是需要判断该i的值是否能达到，即是否小于最大的能够到达的值，记录整个数组能够到达的最大索引，看是否达到了。

```java
class Solution {
    public boolean canJump(int[] nums) {
            // 贪心算法 计算每一个能够达到的最长长度度
	    	// 遍历全部值 记录最长长度
	    	int rightMax = 0;
	    	for(int i=0;i<nums.length;i++) {
	    		// 其最大长度可以遍历到
	    		if(i<=rightMax) {
	    			rightMax = Math.max(i+nums[i], rightMax);
	    			if(rightMax>=nums.length-1) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
    }
}
```

### [2.Leetcode045跳跃游戏II](https://leetcode-cn.com/problems/jump-game-ii/)

给定一个非负整数数组，你最初位于数组的第一个位置。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

你的目标是使用最少的跳跃次数到达数组的最后一个位置。

示例:

输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
说明:

假设你总是可以到达数组的最后一个位置。

> 贪心算法

```java
class Solution {
    public int jump(int[] nums) {
        int rightMax = 0;
	    	int end  = 0;
	    	// 结果
	    	int step = 0;
	    	// 开始遍历
	    	for(int i=0;i<nums.length-1;i++) {
	    		// 增加判断条件而已
	    		rightMax = Math.max(rightMax, i+nums[i]);
	    		if(i==end) {
	    			end = rightMax;
	    			step++;
	    		}
	    	}
	    	return step;
    }
}
```



### [3.Leetcode1431拥有最多糖果的孩子](https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/)

给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。

对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。

 

示例 1：

输入：candies = [2,3,5,1,3], extraCandies = 3
输出：[true,true,true,false,true] 
解释：
孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。

示例 2：

输入：candies = [4,2,1,1,2], extraCandies = 1
输出：[true,false,false,false,false] 
解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。

示例 3：

输入：candies = [12,1,12], extraCandies = 10
输出：[true,false,true]

> 解题思路：找到其最大值，之后每个值加上额外的值看与最大值比较

```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new ArrayList<>();
	    	// 先遍历找到其最大值
	    	int max = candies[0];
	    	for(int i=1;i<candies.length;i++) {
	    		max = Math.max(max, candies[i]);
	    	}
	    	// 之后统计结果 贪心算法
	    	for(int i=0;i<candies.length;i++) {
	    		if(candies[i]+extraCandies>=max) {
	    			res.add(true);
	    		}else {
	    			res.add(false);
	    		}
	    	}	    	
	    	return res;
    }
}
```



## 二分查找解题

### 1.[Leetcode035搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

示例 1:

输入: [1,3,5,6], 5
输出: 2

示例 2:

输入: [1,3,5,6], 2
输出: 1

示例 3:

输入: [1,3,5,6], 7
输出: 4

示例 4:

输入: [1,3,5,6], 0
输出: 0

> 解题思路：二分查找法

```java
class Solution {
	    public int searchInsert(int[] nums, int target) {
	    	return binarySearch(nums, 0, nums.length-1, target);
	    }
	    public int binarySearch(int[] nums,int left,int right,int target) {
	    	// 求左边
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]<target) {
	    			left = mid + 1;
	    		}else if(nums[mid]>=target) {
	    			right = mid-1;
	    		}
	    	}
	    	return left;
	    }
	}
```

### [2.Leetcode004寻找两个正序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/)

给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。

进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？

 

示例 1：

输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
示例 2：

输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
示例 3：

输入：nums1 = [0,0], nums2 = [0,0]
输出：0.00000
示例 4：

输入：nums1 = [], nums2 = [1]
输出：1.00000
示例 5：

输入：nums1 = [2], nums2 = []
输出：2.00000

> 求解中位数，中间的数。
>
> 比如k=7 第7个数，7/2=3 两边各取3个来看一下，然后从7里面减去3.继续

```java
class Solution {
	    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int n = nums1.length;
	        int m = nums2.length;
	        int left  = (n+m+1)/2;
	        int right = (n+m+2)/2;
	        // k为第几个数，而不是索引
	        return ((getKth(nums1,0,n-1,nums2,0,m-1,left))+(getKth(nums1,0,n-1,nums2,0,m-1,right)))*0.5;
	    }
	    
	    public int getKth(int[] nums1,int start1,int end1,int[] nums2,int start2,int end2,int k) {
	    	int len1 = end1 - start1 + 1;
	    	int len2 = end2 - start2 + 1;
	    	if(len1>len2) {
	    		return getKth(nums2,start2,end2,nums1,start1,end1,k);
	    	}
	    	// 递归截止条件
	    	if(len1==0) {
	    		// 索引
	    		return nums2[start2+k-1];
	    	}
	    	if(k==1) {
	    		return Math.min(nums1[start1], nums2[start2]);
	    	}
	    	// 继续递归 索引
	    	int i = start1 + Math.min(len1, k/2) - 1;
	    	int j = start2 + Math.min(len2, k/2) - 1;
	    	// 比较当前值
	    	if(nums1[i]>nums2[j]) {
	    		// nums2的去掉
	    		return getKth(nums1,start1,end1,nums2,j+1,end2,k-(j-start2+1));
	    	}else {
	    		// nums1的去掉
	    		return getKth(nums1,i+1,end1,nums2,start2,end2,k-(i-start1+1));
	    	}	    	
	    }
	}
```

### [3.Leetcode034在排序数组中查找元素的的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？


示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]

示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]

示例 3：

输入：nums = [], target = 0
输出：[-1,-1]

> 二分查找法

```java
class Solution {
	    public int[] searchRange(int[] nums, int target) {
	    	int left = binaryLeft(nums, 0, nums.length-1, target);
	    	int right = binrayRight(nums, 0, nums.length-1, target);
	    	return new int[] {left,right};
	    }
	    
	    // 二分查找其左边界
	    public int binaryLeft(int[] nums,int left,int right,int target) {
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]>=target) {
	    			right = mid-1;
	    		}else if(nums[mid]<target) {
	    			left = mid+1;
	    		}
	    		
	    	}
            
            // 判断是否越界
            if(left>=nums.length||nums[left]!=target){
                return -1;
            }
	    	return left;
	    }
	    
	    // 二分查找其右边界
	    public int binrayRight(int[] nums,int left,int right,int target) {
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]>target) {
	    			right = mid - 1;
	    		}else if(nums[mid]<=target) {
	    			left = mid + 1;
	    		}
	    	}
            // 判断是否越界
            if(right<0 || nums[right]!=target){
                return -1;
            } 
	    	return right;
	    }
	}
```

### [3.剑指Offer53在排序数组中查找数组I](https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)

统计一个数字在排序数组中出现的次数。

 

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: 0


限制：

0 <= 数组长度 <= 50000

 

```java
class Solution {
	    // 找到一个数字在排序数组出现的次数
		public int search(int[] nums, int target) {
	    	// 解题思路找其左右边界
			int leftIndex = binarySearch_Left(nums,target);
			if(leftIndex==-1) {
				return 0;
			}
			int rightIndex = binarySearch_Right(nums,target);
			return rightIndex-leftIndex+1;
	    }
		// 找左边界
		public int binarySearch_Left(int[] nums,int target) {
			int left = 0;
			int right = nums.length-1;
			while(left<=right) {
				int mid = left + ((right-left)>>1);
				if(nums[mid]>=target) {
					right--;
				}else {
					left++;
				}
			}
			if(left>=nums.length||nums[left]!=target) {
				return -1;
			}
			return left;
		}
		// 找右边界
		public int binarySearch_Right(int[] nums,int target) {
			int left = 0;
			int right = nums.length-1;
			while(left<=right) {
				int mid = left + ((right-left)>>1);
				if(nums[mid]<=target) {
					left++;
				}else {
					right--;
				}
			}
			if(right<0||nums[right]!=target) {
				return -1;
			}
			return right;
		}
	}
```



### [4.Leetcode033搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。

请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

 

示例 1：

输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4

示例 2：

输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1

示例 3：

输入：nums = [1], target = 0
输出：-1

> 解题思路：二分查找法来解题，多了一步来判断哪个序列是有序的。

```java
class Solution {
	    public int search(int[] nums, int target) {
	    	// 可用二分查找的办法
	    	return binarySearch(nums, 0, nums.length-1, target);
	    }
	    public int binarySearch(int[] nums,int left,int right,int target) {
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]==target) {
	    			return mid;
	    		}
	    		// 判断mid哪个区间是有序的
	    		if(nums[mid]>=nums[left]) {
	    			// 从left到mid之间是有序的
	    			// 再接着判断target在哪里
	    			if(target>=nums[left]&&target<nums[mid]) {
	    				right = mid-1;
	    			}else {
	    				left =  mid+1;
	    			}
	    		}else {
	    			// 从mid到right之间是有序的
	    			if(target>nums[mid]&&target<=nums[right]) {
	    				left =  mid + 1;
	    			}else {
	    				right = mid - 1;
	    			}
	    		}
	    	}
	    	
	    	return -1;
	    }
	}
```

### [5.Leetcode153寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。

请找出其中最小的元素。

 

示例 1：

输入：nums = [3,4,5,1,2]
输出：1
示例 2：

输入：nums = [4,5,6,7,0,1,2]
输出：0
示例 3：

输入：nums = [1]
输出：1

> 二分查找的变形题

```java
class Solution {
    public int findMin(int[] nums) {
// 二分查找
	    	int left = 0;
	    	int right = nums.length-1;
	    	// 剪枝
	    	
	    	while(left<=right) {
	    		// 单调递增时直接返回
	    		if(nums[left]<=nums[right]) {
		    		return nums[left];
		    	}
	    		
	    		// 判断哪边有序
	    		int mid = left + ((right-left)>>1);
	    		if(nums[left]<=nums[mid]) {
	    			// 从left到mid之间有序
	    			left = mid+1;
	    		}else if(nums[left]>nums[mid]) {
	    			// 无序
	    			right = mid;
	    		}
	    	}
	    	return -1;
    }
}
```



### [5.Leetcode剑指Offer53 -II 0~N-1中缺失的数字](https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/)

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

 

示例 1:

输入: [0,1,3]
输出: 2

示例 2:

输入: [0,1,2,3,4,5,6,7,9]
输出: 8

> 解题思路：看到有序数组则考虑二分查找

```java
class Solution {
    public int missingNumber(int[] nums) {
        int left = 0;
	    	int right = nums.length-1;
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]==mid) {
	    			left = mid+1;
	    		}else {
	    			right = mid-1;
	    		}
	    	}
	    	return left;
    }
}
```

### [6.Leetcode268丢失的数字](https://leetcode-cn.com/problems/missing-number/)

给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。

 

进阶：

你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?


示例 1：

输入：nums = [3,0,1]
输出：2
解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。

示例 2：

输入：nums = [0,1]
输出：2
解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
示例 3：

输入：nums = [9,6,4,2,3,5,7,0,1]
输出：8
解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。

> 解题思路：排序 + 不一样的二分

```java
class Solution {
    public int missingNumber(int[] nums) {
Arrays.sort(nums);
	    	int left = 0;
	    	int right = nums.length-1;
	    	while(left<=right) {
	    		int mid = left + ((right-left)>>1);
	    		if(nums[mid]==mid) {
	    			left = mid+1;
	    		}else {
	    			right = mid-1;
	    		}
	    	}
	    	return left;
    }
}
```

## 排序辅助解题

### [1.Leetcode1375有多少小于当前数字的数字](https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/)

给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

以数组形式返回答案。



示例 1：

输入：nums = [8,1,2,2,3]
输出：[4,0,1,1,3]
解释： 
对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
对于 nums[1]=1 不存在比它小的数字。
对于 nums[2]=2 存在一个比它小的数字：（1）。 
对于 nums[3]=2 存在一个比它小的数字：（1）。 
对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。

示例 2：

输入：nums = [6,5,4,8]
输出：[2,1,0,3]

示例 3：

输入：nums = [7,7,7,7]
输出：[0,0,0,0]

> 解题思路一、暴力破解法

```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 对每个数都对其统计
	    	int[] res = new int[nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		int count = 0;
	    		for(int j=0;j<nums.length;j++) {
	    			if(nums[i]>nums[j]) {
	    				count++;
	    			}
	    		}
	    		res[i] = count;
	    	}
	    	return res;
    }
}
```

> 解题思路二、排序+一个辅助的空间结构

```java
// 排序来解题 但需一个辅助结构来记录之前的索引
	    public int[] smallerNumbersThanCurrent_2(int[] nums) {
	    	// 第一维度记录原来数据 第二维度记录之前的索引
	    	int[][] new_nums = new int[nums.length][nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		new_nums[i][0] = nums[i];
	    		new_nums[i][1] = i;
	    	}
	    	// 对其按照第一维度排序
	    	Arrays.sort(new_nums,(a,b)->(a[0]-b[0]));
	    	
	    	//结果存储
    		int prev = -1;
    		int[] res = new int[nums.length];
	    	// 接下来统计可
	    	for(int i=0;i<new_nums.length;i++) {
	    		// 第一个数或者两个数同
	    		if(prev==-1 || new_nums[i][0]!=new_nums[i-1][0]) {
	    			prev = i;
	    		}
	    		res[new_nums[i][1]] = prev;
	    	}
	    	return res;
	    }
```

> 解题思路三、最优 计数排序

```java
// 计数排序 桶排序
	    public int[] smallerNumbersThanCurrent_3(int[] nums) {
	    	// 观察数据范围为0-100之间
	    	int[] bucket = new int[101];
	    	// 统计每个数出现的频率
	    	for(int num:nums) {
	    		bucket[num]++;
	    	}
	    	// 之后对其相加
	    	for(int i=1;i<101;i++) {
	    		bucket[i] = bucket[i] + bucket[i-1];
	    	}
	    	// 结果
	    	int[] res = new int[nums.length];
	    	for(int i=0;i<nums.length;i++) {
	    		// 这里需考虑0
	    		res[i] = nums[i]==0?0:bucket[nums[i]-1];
	    	}
	    	return res;
	    }
```

### [2.Leetcode1122数组的相对排序](https://leetcode-cn.com/problems/relative-sort-array/)

给你两个数组，arr1 和 arr2，

arr2 中的元素各不相同
arr2 中的每个元素都出现在 arr1 中
对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

 

示例：

输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
输出：[2,2,2,1,4,3,3,9,6,7,19]


提示：

1 <= arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
arr2 中的元素 arr2[i] 各不相同
arr2 中的每个元素 arr2[i] 都出现在 arr1 中

> 解题思路1：自定义排序。在的话就是索引排序，要不就大小排序。
>
> 但注意一点是 int类型无法lambda自定义，二维数组的一维就可以
>
> Arrays.sort()只能对包装类型。
>
> Collections.sort就可以

```java
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // HashSet
	    	HashMap<Integer,Integer> hashMap = new HashMap<>();
	    	for(int i=0;i<arr2.length;i++) {
	    		hashMap.put(arr2[i], i);
	    	}
	    	// 自定义排序
	    	List<Integer> list = new ArrayList<>();
	    	for(int i=0;i<arr1.length;i++) {
	    		list.add(arr1[i]);
	    	}
	    	Collections.sort(list,(a,b)->{
	    		if(hashMap.containsKey(a) || hashMap.containsKey(b)) {
	    			return hashMap.getOrDefault(a, 1001)-hashMap.getOrDefault(b, 1001);
	    		}
	    		// 大小排序
	    		return a-b;
	    	});
	    	int[] res = new int[arr1.length];
	    	// 重新赋值
	    	for(int i=0;i<arr1.length;i++) {
	    		res[i] = list.get(i);
	    	}
	    	return res;
    }
}
```

> 解题思路二、计数排序
>
> 具体地，我们使用一个长度为 10011001（下标从 00 到 10001000）的数组frequency,，记录每一个元素在数组arr1 中出现的次数。随后我们遍历数组arr2,当遍历到元素 xx 时，我们将 frequency[x]个 xx加入答案中，并将 frequency[x] 清零。当遍历结束后，所有在 arr2 中出现过的元素就已经有序了。

```java
// 数据范围为1000 考虑计数排序
			// 先从arr1中找到最大值
			int max = arr1[0];
			for(int i=1;i<arr1.length;i++) {
				max = Math.max(max, arr1[i]);
			}
			// 开辟空间
			int[] bucket = new int[max+1];
			// 存取出现频率
			for(int num:arr1) {
				bucket[num]++;
			}
			// 结果
			int[] res = new int[arr1.length];
			int index = 0;
			// 遍历arr2
			for(int num:arr2) {
				// 肯定有
				for(int i=0;i<bucket[num];i++) {
					res[index++] = num;
				}
				bucket[num] = 0;
			}
			// 遍历剩余的
			for(int i=0;i<bucket.length;i++) {
				if(bucket[i]!=0) {
					// 可能存在多个
					for(int j=0;j<bucket[i];j++) {
						res[index++] = i;
					}
				}
			}
			return res;
```

### [3.Leetcode057插入区间](https://leetcode-cn.com/problems/insert-interval/)

给出一个无重叠的 ，按照区间起始端点排序的区间列表。

在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

 

示例 1：

输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
输出：[[1,5],[6,9]]

示例 2：

输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
输出：[[1,2],[3,10],[12,16]]
解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

> 开辟一个空间，将全部值加入，重新排序。
>
> 之后重新添加，先添加一个。
>
> 后添加的判断是否需要合并，不需要合并直接添加，需要合并，找到最终的终点即可了。

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
	    	// 对其遍历
	    	for(int[] interval:intervals) {
	    		list.add(new int[] {interval[0],interval[1]});
	    	}
	    	list.add(new int[] {newInterval[0],newInterval[1]});
	    	// 排序
	    	Collections.sort(list,(a,b)->(a[0]-b[0]));
	    	// 之后找寻结果
	    	List<int[]> res = new ArrayList<>();
	    	for(int[] cur:list) {
	    		// 如果为空添加
	    		if(res.isEmpty()) {
	    			res.add(cur);
	    		}else {
	    			// 里面有值了判断是否需要合并
	    			int[] last = res.get(res.size()-1);
	    			if(cur[0]>last[1]) {
	    				res.add(cur);
	    			}else {
	    				res.get(res.size()-1)[1] = Math.max(last[1], cur[1]);
	    			}
	    		}
	    	}
	    	return res.toArray(new int[res.size()][]);
    }
}
```







## 归并排序解题

### [1.Leetcode面试题10.01合并排序的数组](https://leetcode-cn.com/problems/sorted-merge-lcci/)

定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。

初始化 A 和 B 的元素数量分别为 m 和 n。

示例:

输入:
A = [1,2,3,0,0,0], m = 3
B = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]

> 归并的思路：重新创建一个临时数组



```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
            // 开辟一个临时的空间来存储
	    	int[] temp = new int[m+n];
	    	int index  = 0;
	    	// 两个指针
	    	int p1 = 0;
	    	int p2 = 0;
	    	while(p1<m&&p2<n) {
	    		//开始比较
	    		if(A[p1]>B[p2]) {
	    			temp[index++] = B[p2++];
	    		}else {
	    			temp[index++] = A[p1++];
	    		}
	    	}
	    	while(p1<m) {
	    		temp[index++] = A[p1++];
	    	}
	    	while(p2<n) {
	    		temp[index++] = B[p2++];
	    	}
	    	// 最后重新赋值给A
	    	for(int i=0;i<temp.length;i++) {
	    		A[i] = temp[i];
	    	}
    }
}
```

 不需要创建临时数组

```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        // 不用临时数组 逆序
        int p1 = m-1, p2 = n-1;
        int k  = m+n-1;
        while(p1>=0&&p2>=0){
            if(A[p1]<B[p2]){
                A[k--] = B[p2--];
            }else{
                A[k--] = A[p1--];
            }
        }
        while(p2>=0){
            A[k--] = B[p2--];
        }
    }
}
```

## 快速排序  归并排序 优先级队列(小顶堆)