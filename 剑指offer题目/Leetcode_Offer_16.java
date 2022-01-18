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