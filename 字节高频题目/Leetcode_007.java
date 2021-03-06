class Solution {
    public int reverse(int x) {
        // 不允许存储64位就是不允许用long
        int res = 0;
        while(x!=0){
            // 判断是否溢出
            if(((res*10)/10)!=res){
                res = 0;
                return res;
            }
            res = res*10 + x%10;
            x   = x/10;
        }
        return res;
    }
}