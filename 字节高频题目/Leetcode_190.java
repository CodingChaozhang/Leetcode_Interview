public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // 颠倒二进制位 输入的最后一个开始
        int res = 0;
        // 进行32次
        for(int i=0;i<32;i++){
            // res左移
            res = res<<1;
            // res的值为=n的最后一位|res
            res = n&1|res;
            // n右移
            n = n>>1;
        }
        return res;
    }
}