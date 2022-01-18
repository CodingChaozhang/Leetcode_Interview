class Solution {
    public int[] printNumbers(int n) {
        int sum = (int)Math.pow(10,n);
        sum = sum-1;
        // 开始存储
        int[] arr = new int[sum];
        for(int i=1;i<=sum;i++){
            arr[i-1] = i;
        }
        return arr;
    }
}