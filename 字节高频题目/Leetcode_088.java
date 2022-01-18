class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 在数组1中保存数组2，那么就逆序来看
        int index1 = m-1;
        int index2 = n-1;
        int index = m+n-1;
        // 开始遍历
        while(index1>=0&&index2>=0){
            // 开始判断
            if(nums1[index1]>=nums2[index2]){
                nums1[index] = nums1[index1];
                index1--;
                index--;
            }else if(nums1[index1]<nums2[index2]){
                nums1[index] = nums2[index2];
                index2--;
                index--;
            }
        }
        // 如果nums2还有剩余
        while(index2>=0){
            nums1[index--] = nums2[index2--];
        }
        
    }
}