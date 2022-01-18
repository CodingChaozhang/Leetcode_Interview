class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        // 1.维护一个排过序的滑动窗口数组
        // 2.使用二分查找检索删除的索引
        // 3.将需要删除的值替换为需要插入的值
        // 4.使用局部冒泡排序保证数组顺序
        int len = nums.length;
        int[] windows = new int[k];
        // 结果
        double[] res = new double[len-k+1];
        //给初始化的滑动窗口赋值
        for(int i=0;i<k;i++){
            windows[i] = nums[i];
        }
        // 对其排序
        Arrays.sort(windows);
        // 二分查找中间的值
        res[0] = getMid(windows);
        // 遍历其它的
        for(int i=0;i<len-k;i++){
            // 获取要删除的索引
            int index = binarySearch(windows,nums[i]);
            // 删除即替换
            windows[index] = nums[i+k];
            // 重新排序
            while(index<windows.length-1&&windows[index]>windows[index+1]){
                // 交换
                swap(windows,index,index+1);
                index++;
            }
            while(index>0&&windows[index]<windows[index-1]){
                swap(windows,index,index-1);
                index--;
            }
            // 重新寻找中位数
            res[i+1] = getMid(windows);
        }
        // 返回其值
        return res;
    }

    // 交换
    public void swap(int[] windows,int left,int right){
        int temp = windows[left];
        windows[left] = windows[right];
        windows[right] = temp;
    }
    // 二分查找
    public int binarySearch(int[] nums,int target){
        int l = 0;
        int r = nums.length-1;
        while(l<=r){
            int mid = l + ((r-l)>>1);
            // 判断
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                r = mid-1;
            }else if(nums[mid]<target){
               l = mid+1; 
            }
        }
        return -1;
    }

    // 求数组的中位数
    public double getMid(int[] windows){
        int len = windows.length;
        if(len%2==0){
            // 避免溢出
            return windows[len/2]/2.0 +windows[len/2-1]/2.0;
        }else{
            return windows[len/2];
        }
    }
}