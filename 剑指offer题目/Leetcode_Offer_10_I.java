class Solution {
    public int fib(int n) {
        // 如果n小于2
        if(n<2){
            return n;
        }
        int f0 = 0;
        int f1 = 1;
        // 结果
        int f  = f0+f1;
        while(n>2){
            f0 = f1;
            f1 = f;
            f = f0+f1;
            f = f%(1000000007);
            n--;
        }
        return f;
    }
}