class Solution {
    public int cuttingRope(int n) {
        if(n<4){
            return n-1;
        }
        // 大于4
        int res = 1;
        while(n>4){
            res*=3;
            n-=3;
        }
        return res*(n-1);
    }
}