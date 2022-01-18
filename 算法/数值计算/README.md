### [14.剑指offer15 二进制中1的个数](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/)

请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

示例 1：

输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        // n与n-1进行与运算，即可 
        while(n!=0){
            count++;
            n &= (n-1);
        }
        return count;
    }
}
```

### [15.剑指offer16 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)

实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。

 

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

> 解题思路：快速幂，记得n转换为long。原来的数基础上相乘

```java
class Solution {
    public double myPow(double x, int n) {
        long power = n;
        // 计算数值的整数次方
        double res = 1;
        // 可能有表示整数
        boolean flag = true;
        if(n<0){
            flag = false;
            power = (-1)*(n);
        }
        while(power!=0){
            if(power%2!=0){
                res *= x;
            }
            power = power/2;
            x = x*x;
        }
        return flag?res:(1/res);
    }
}
```

