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

