# 面试常出的排序算法



## 归并排序

### [1.数组归并]

```java
public class MergeSort {
	public static void mergeSort(int[] arr) {
		// 先判断
        if(arr.length<2){
            return;
        }
        // 归并排序
        mergeProcess(arr,0,arr.length-1);
	}
    
    // 归并排序
    public static void mergeProcess(int[] arr,int L,int R){
        // 递归截止条件
        if(L==R){
            return;
        }
        int mid = L +((R-L)>>1);
        // 前半分归并排序
        mergeProcess(arr,L,mid);
        // 后半部分归并
        mergeProcess(arr,mid+1,R);
        // 两个数组合并
        merge(arr,L,mid,R);
    }
    
    // 两个数组合并
    public static void merge(int[] arr,int L,int mid,int R){
        int l1 = L;
        int l2 = mid+1;
        // 开始合并 
        int[] temp = new int[R-L+1];
        int index = 0;
        while(l1<=mid&&l2<=R){
            if(arr[l1]<=arr[l2]){
                temp[index++] = arr[l1++];
            }else{
                temp[index++] = arr[l2++];
            } 
        }
        while(l1<=mid){
            temp[index++] = arr[l1++];
        }
        while(l2<=R){
            temp[index++] = arr[l2++];
        }
        // 重新赋值
        for(int i=0;i<temp.length;i++){
            arr[L+i] = temp[i];
        }
        
    }
	

	
	// 主函数
	public static void main(String[] args) {
		int[] arr = {56,9,10,19,28,37,34};
		mergeSort(arr);
		for(int number:arr) {
			System.out.print(number + " ");
		}
	}
}

```

### [2.Leetcode148排序链表](https://leetcode-cn.com/problems/sort-list/)

给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

示例 1：

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)


输入：head = [4,2,1,3]
输出：[1,2,3,4]

> 排序算法：归并排序

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        // 最开始的判断条件
        if(head==null || head.next==null){
            return head;
        }
        return mergeSort(head,null);
    }

    // 归并排序
    public ListNode mergeSort(ListNode head,ListNode tail){
        if(head==null){
            return null;
        }
        // 递归截止条件
        if(head.next==tail){
            head.next = null;
            return head;
        }
        // 找中间的结点
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=tail&&fast.next!=tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        // 递归
        ListNode left = mergeSort(head,mid);
        ListNode right = mergeSort(mid,tail);
        // 合并
        ListNode sorted = merge(left,right);
        return sorted;
    }

    // 合并
    public ListNode merge(ListNode l1,ListNode l2){
        // 新的链表
        ListNode dummy = new ListNode(-1);
        ListNode l = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<=l2.val){
                l.next = l1;
                l1 = l1.next;
                l = l.next;
            }else{
                l.next = l2;
                l2 = l2.next;
                l = l.next;
            }
        }
        if(l1!=null){
            l.next = l1;
        }
        if(l2!=null){
            l.next = l2;
        }
        return dummy.next;
    }


}
```



## 快速排序

### [1.数组快排]

```java
package com.lcz.leetcode;

public class QuickSort {
	public static void quickSort(int[] arr) {
		// 先判断
        if(arr.length<2){
            return;
        }
        // 快速排序
        quickSort(arr,0,arr.length-1);
	}
    
    // 快速排序
    public static void quickSort(int[] arr,int L,int R){
        if(L<R){
            // 先得到结果
            int[] part = partitation(arr,L,R);
            quickSort(arr,L,part[0]-1);
            quickSort(arr,part[1]+1,R);
        }
    }
    
    // 开始
    public static int[] partitation(int[] arr,int L,int R){
        int less = L-1;
        int more = R;
        while(L<more){
            // 开始判断
            if(arr[L]<arr[R]){
                swap(arr,++less,L++);
            }else if(arr[L]>arr[R]){
                swap(arr,--more,L);
            }else if(arr[L]==arr[R]){
                // 相等
                L++;
            }
            
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
        
    }
    
    // 交换
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        
    }
    
    
	

	
	// 主函数
	public static void main(String[] args) {
		int[] arr = {56,9,10,19,28,37,34};
		quickSort(arr);
		for(int number:arr) {
			System.out.print(number + " ");
		}
	}
}

```

### [2.Leetcode148链表](https://leetcode-cn.com/problems/sort-list/)

给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。

进阶：

你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

示例 1：

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)


输入：head = [4,2,1,3]
输出：[1,2,3,4]

> 解题思路：用快速排序，相比较归并排序速度慢

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        // 哑结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        return quickSort(dummy,null);
    }

    public ListNode quickSort(ListNode head,ListNode tail){
        // 递归截止条件
        if(head==tail || head.next==tail || head.next.next==tail){
            return head;
        }

        // 小于花粉店的值存放在临时链表中
        ListNode tempHead = new ListNode(-1);
        ListNode tp = tempHead;
        // 划分点
        ListNode partition = head.next;
        // 遍历的指针
        ListNode p = head.next;

        // 开始
        while(p.next!=tail){
            // 小于
            if(p.next.val<partition.val){
                tp.next = p.next;
                tp = tp.next;
                p.next = p.next.next;
            }else{
                p = p.next;
            }
        }

        // 合并
        tp.next = head.next;
        head.next = tempHead.next;

        // 接着
        quickSort(head,partition);
        quickSort(partition,tail);
        return head.next;


    }
}
```



## 原地排序

### [1.Leetcode448找到所有数组中消失的数字](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/)

给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。

找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:

输入:
[4,3,2,7,8,2,3,1]

输出:
[5,6]

> 解题思路：hashset或者原地查找
>
> 数字范围为1-n

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 原地排序
        int len = nums.length;
        for(int i=0;i<len;i++){
            // 值不等
            while(nums[i]!=nums[nums[i]-1]){
                // 交换
                swap(nums,i,nums[i]-1);
            }
        }
        //再次遍历
        // 结果
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<len;i++){
            if(i!=nums[i]-1){
                res.add(i+1);
            }
        }
        return res;
    }
    // 交换
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }
}
```

### [2.Leetcode041缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/)

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

> 辅助结构：hashset。for循环来解题

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        // 先排序
        int len = nums.length;
        for(int i=0;i<len;i++){
            // 值不等
            while(nums[i]>0&&nums[i]<=len&&nums[i]!=nums[nums[i]-1]){
                // 交换
                swap(nums,i,nums[i]-1);
            }
        }
        // 判断
        for(int i=0;i<len;i++){
            if(i!=nums[i]-1){
                return i+1;
            }
        }
        return len+1;
    }

    // 交换
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]  = nums[j];
        nums[j]  = temp;
    }
}
```

