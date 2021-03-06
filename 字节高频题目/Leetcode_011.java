class Solution {
    public int maxArea(int[] height) {
        // 两个指针 
        int left = 0;
        int right = height.length-1;
        // 结果
        int res = 0;
        // 同时出发
        while(left<right){
            int temp = Math.min(height[left],height[right])*(right-left);
            res = Math.max(temp,res);
            // 如何走
            if(height[left]>=height[right]){
                right--;
            }else{
                left++;
            }

        }
        return res;


    }
}