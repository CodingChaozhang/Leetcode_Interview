### [1.剑指Offer 03 数组中重复的数字](https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 

> 解题思路：注意长度为n，数字为0-n-1之间。
>
> 采用的思路：原地排序+指针 for循环 + while指针

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        // 原地排序 + 指针
        for(int i=0;i<nums.length;i++){
            while(i!=nums[i]){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                // 交换
                int temp = nums[i];
                nums[i]   = nums[temp];
                nums[temp] = temp;
            }

        }
        return -1;
    }
}
```

### [2.剑指offer 04二维数组中的查找](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

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

 

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length<1||matrix[0].length<1){
            return false;
        }
        // 思路就是从左上角开始找
        int row = matrix.length;
        int col = matrix[0].length;
        int start_r = 0;
        int start_c = col-1;
        int end_r = row-1;
        int end_c = 0;
        // 都要满足
        while(start_r<=end_r&&start_c>=end_c){
            if(matrix[start_r][start_c]==target){
                return true;
            }else if(matrix[start_r][start_c]>target){
                start_c--;
            }else if(matrix[start_r][start_c]<target){
                start_r++;
            }
        }
        return false;
    }
}
```

