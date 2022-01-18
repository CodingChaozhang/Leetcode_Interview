class Solution {
    public int climbStairs(int n) {
        // 爬楼梯
        if(n<=2){
            return n;
        }

        // 对其遍历
        int f1 = 1;
        int f2 = 2;
        int f = f1+f2;
        int index = 3;
        while(index<=n){
            f = f1+f2;
            f1 = f2;
            f2 = f;
            index++;
        }

        return f;
    }
}