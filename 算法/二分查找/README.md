# 二分算法

在一个已排序或者已排序经过旋转的数组中，找到target或者最小值。



## 一、排序中的数组搜索值

### [1.Leetcode035搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)

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

> 解题思路：二分查找的算法

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums,target);
    }

    // 二分查找
    public int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            // 判断
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid + 1;
            }
        }
        // 不存在返回顺序插入的位置
        return left;
    }
}
```

### [2.Leetcode034在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

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

> 解题思路：二分查找

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length<1){
            return new int[]{-1,-1};
        }
        // 找左边界和右边界
        int left = binaryLeft(nums,target);
        int right = binaryRight(nums,target);
        return new int[]{left,right};
    }
    // 左边界
    public int binaryLeft(int[] nums,int target){
        int left =  0;
        int right = nums.length-1;
        while(left<=right){
             int mid = left + ((right-left)>>1);
            // 左边界与其不相等但是中间相等了
            if(nums[mid]==target){
                right = mid-1;
            }else if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }
        }
        // 判断
        if(left>=nums.length || nums[left]!=target){
            return -1;
        }
        return left;
    }

    // 右边界
    public int binaryRight(int[] nums,int target){
        int left =  0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            // 右边界与其不相等但是中间相等了
            if(nums[mid]==target){
                left = mid+1;
            }else if(nums[mid]>target){
                right = mid-1;
            }else if(nums[mid]<target){
                left = mid+1;
            }
        }
        // 判断是否越界
        if(right<0 || nums[right]!=target ){
            return -1;
        }
        return right;
    }
}
```



## 二、排序经过旋转的数组中搜索值

### [1.Leetcode189旋转数组](https://leetcode-cn.com/problems/rotate-array/)

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

> 解题思路：全部旋 左旋 右旋

```java
class Solution {
    public void rotate(int[] nums, int k) {
        // 旋转数组 先整个旋转，再旋转前面k个 再旋转后面k个
        // 防止越界
        k = k % nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    // 翻转
    public void reverse(int[] nums,int left,int right){
        while(left<=right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;    
        }
    }
}
```

### [2.Leetcode153寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)


假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 `[0,1,2,4,5,6,7]` 可能变为 `[4,5,6,7,0,1,2]` 。

请找出其中最小的元素。

 

**示例 1：**

```
输入：nums = [3,4,5,1,2]
输出：1
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2]
输出：0
```

**示例 3：**

```
输入：nums = [1]
输出：1
```

> 解题思路：无重复元素，与最右边相比较。

```java
class Solution {
    public int findMin(int[] nums) {
        // 查找最小值 与最右边比较
        return binarySearch(nums);
    }
    public int binarySearch(int[] nums){
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while(left<=right){
            mid = left + ((right-left)>>1);
            // 查看
            if(nums[mid]>=nums[right]){
                left = mid+1;
            }else if(nums[mid]<nums[right]){
               right = mid;
            }
        }
        // 返回最小值
        return nums[mid];
    }
}
```

### [3.Leetcode154寻找旋转排序数组中的最小值II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

请找出其中最小的元素。

注意数组中可能存在重复的元素。

示例 1：

输入: [1,3,5]
输出: 1
示例 2：

输入: [2,2,2,0,1]
输出: 0

> 找最小值，且数组中有重复元素。
>
> 注意：最小值一般是存在右边，所以跟右边慢慢的比较

```java
class Solution {
    public int findMin(int[] nums) {
        return binarySearch(nums);
    }

    public int binarySearch(int[] nums){
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while(left<=right){
            mid = left + ((right-left)>>1);
            //比较
            if(nums[mid]>nums[right]){
                left = mid + 1;
            }else if(nums[mid]<nums[right]){
                right = mid;
            }else if(nums[mid]==nums[right]){
                right--;
            }
        }
        return nums[mid];
    }
}
```

### [4.Leetcode033搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)


整数数组 `nums` 按升序排列，数组中的值 **互不相同** 。

在传递给函数之前，`nums` 在预先未知的某个下标 `k`（`0 <= k < nums.length`）上进行了 **旋转**，使数组变为 `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`（下标 **从 0 开始** 计数）。例如， `[0,1,2,4,5,6,7]` 在下标 `3` 处经旋转后可能变为 `[4,5,6,7,0,1,2]` 。

给你 **旋转后** 的数组 `nums` 和一个整数 `target` ，如果 `nums` 中存在这个目标值 `target` ，则返回它的索引，否则返回 `-1` 。



**示例 1：**

```
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```

**示例 3：**

```
输入：nums = [1], target = 0
输出：-1
```

```java
class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums,target);
    }

    // 二分查找
    public int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            int mid = left + ((right-left)>>1);
            if(nums[mid]==target){
                return mid;
            }

            if(nums[mid]>=nums[left]){
                // 左边有序
                if(nums[left]<=target&&target<nums[mid]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{
                // 右边有序
                if(nums[mid]<target&&target<=nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

### [5.Leetcode081搜索旋转排序数组II](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

示例 1:

输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true
示例 2:

输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false

> 可能含有重复元素

```java
class Solution {
    public boolean search(int[] nums, int target) {
        return binarySearch(nums,target);
    }

    // 二分查找
    public boolean binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            // 去重
            while(left+1<nums.length&&nums[left]==nums[left+1]){
                left++;
            }
            while(right-1>=0&&nums[right]==nums[right-1]){
                right--;
            }

            int mid = left + ((right-left)>>1);
            // 返回值
            if(nums[mid]==target){
                return true;
            }
            // 继续查找
            if(nums[mid]>=nums[left]){
                // 左边有序
                if(nums[left]<=target&&target<nums[mid]){
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else{
                // 右边有序
                if(nums[mid]<target&&target<=nums[right]){
                    left = mid + 1;
                }else{
                    right = mid-1;
                }
            }
        }
        return false;
    }
}
```

### [6.面试题10.03 搜索旋转数组](https://leetcode-cn.com/problems/search-rotate-array-lcci/)

搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。

示例1:

 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 输出: 8（元素5在该数组中的索引）
示例2:

 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 输出：-1 （没有找到）

> k次旋转，多个相同的找寻索引值最小的。

```java
class Solution {
    public int search(int[] arr, int target) {
        return binarySearch(arr,target);
    }
    // 二分查找
    public int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length-1;
        while(left<=right){
            // 左边界
            if(nums[left]==target){
                return left;
            }

            int mid = left + ((right-left)>>1);
            // 接着判断
            if(nums[mid]==target){
                right = mid;
            }else if(nums[mid]>nums[left]){
                // 左边有序
                if(nums[left]<target&&target<nums[mid]){
                    right = mid -1;
                }else{
                    left = mid + 1;
                }
            }else if(nums[mid]<nums[left]){
                // 右边有序
                if(nums[mid]<target&&target<=nums[right]){
                    left = mid + 1;
                }else{
                    right = mid-1;
                }
            }else if(nums[mid]==nums[left]){
                left++;
            }
        }
        return -1;

    }
}
```



## 三、应用

### [1.Leetcode069 x的平方根](https://leetcode-cn.com/problems/sqrtx/)-牛顿迭代法

实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:

输入: 4
输出: 2
示例 2:

输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。

> 解题思路一、用二分查找的方法
>
> 从0-x之间 找mid使得mid*mid小于x

```java
class Solution {
    public int mySqrt(int x) {
        // 排除0和1的选项
        if(x==0 || x==1){
            return x;
        }
        return binarySearch(x);
    }
    // 二分
    public int binarySearch(int x){
        // 不需要全部从left到right
        double left = 0;
        double right = x;
        double mid = left + (right-left)/2;
        // 防止超出时间限制
        while((Math.abs(mid*mid-x)>(Math.pow(10,-6)))){
            mid = left + (right-left)/2;
            if(mid*mid>x){
                right = mid;
            }else if(mid*mid<x){
                left = mid;
            }
        }
        return (int)mid;
    }
}
```

> 数学公式法：牛顿迭代法

```java
class Solution {
    public int mySqrt(int x) {
        long n = x;
        // 防止超时
        while(n*n>x){
            n = (n+x/n)/2;
        }
        return (int)n;
    }
   
}
```

### [2.Leetcode367有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)

给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

说明：不要使用任何内置的库函数，如  sqrt。

示例 1：

输入：16
输出：True
示例 2：

输入：14
输出：False

> 解题思路：平方根的变形用法

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        // 实现sqrt算法
        long res = num;
        while(res*res>num){
            res = (res+num/res)/2;
        }
        return (int)res*(int)res == num;

    }
}
```

### [3.Leetcode232 2的幂](https://leetcode-cn.com/problems/power-of-two/)

给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:

输入: 1
输出: true
解释: 20 = 1
示例 2:

输入: 16
输出: true
解释: 24 = 16
示例 3:

输入: 218
输出: false

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        // nlogn的方法
        if(n==0){
            return false;
        }
        while(n%2==0){
            n = n/2;
        }
        return n==1;
    }
}
```

### [4.Power(x,n)](https://leetcode-cn.com/problems/powx-n/)-快速幂算法



实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。

 

示例 1：

输入：x = 2.00000, n = 10
输出：1024.00000
示例 2：

输入：x = 2.10000, n = 3
输出：9.26100
示例 3：

输入：x = 2.00000, n = -2
输出：0.25000
解释：2-2 = 1/22 = 1/4 = 0.25

> 解题思路：若是直接用for循环，会直接超时

```java
class Solution {
    public double myPow(double x, int n) {
        // 先将负号转正
        long power = n;
        if(n<0){
            x = 1/x;
            power = (-1)*power;
        }
        // 结果
        double res = 1;
        // 用for循环
        for(int i=1;i<=power;i++){
            res *= x;
        }
        return res;
    }
}
```

> 对上述的改进，算法用快速幂
>
> 即像3 * 3 * 3 * 3 * 3 = （3 * 3）* (3 * 3) * (3)

```java
class Solution {
    public double myPow(double x, int n) {
        long power = n;
        if(n<0){
            x = 1/x;
            power = (-1)*power;
        }
        // 快速幂
        double res = 1;
        while(power!=0){
            if(power%2==1){
                res *= x;
            }
            power = power/2;
            x = x*x;
        }
        return res;
    }
}
```

