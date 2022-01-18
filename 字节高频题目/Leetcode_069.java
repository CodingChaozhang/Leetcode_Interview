class Solution {
    public int mySqrt(int x) {
        if(x==0||x==1){
            return x;
        }
        // 二分查找
        return binarySearch(x);
    }
    // 二分查找
    public int binarySearch(int x){
        double left = 0;
        double right = x;
        double mid = left + (right-left)/2;
        while(Math.abs(mid*mid-x)>(Math.pow(10,-6))){
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