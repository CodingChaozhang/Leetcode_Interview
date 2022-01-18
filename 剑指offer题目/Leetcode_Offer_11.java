class Solution {
    public int minArray(int[] numbers) {
        // 之前有序，二分查找最小值
        int left = 0;
        int right = numbers.length-1;
        int mid =0;
        while(left<=right){
            mid = left + ((right-left)>>1);
            // 先看在哪个区间
            if(numbers[mid]>numbers[right]){
                // 左边有序
                left = mid+1;
            }else if(numbers[mid]<numbers[right]){
                // 右边有序
                right = mid;
            }else if(numbers[mid]==numbers[right]){
                // 存在重复值
                right--;
            }
        }
        return numbers[mid];
    }
}