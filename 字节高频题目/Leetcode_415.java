class Solution {
    public String addStrings(String num1, String num2) {
        // 第一步转换成数组
        char[] num1_arr = num1.toCharArray();
        char[] num2_arr = num2.toCharArray();
        int len1 = num1_arr.length;
        int len2 = num2_arr.length;
        // 第二步结果拼接
        StringBuilder res = new StringBuilder();
        // 进位
        int mod = 0;
        // 开始
        int index_1 = len1-1;
        int index_2 = len2-1;
        while(index_1>=0||index_2>=0){
            // 当前数值
            int cur_1 = index_1<0?0:num1_arr[index_1] - '0';
            int cur_2 = index_2<0?0:num2_arr[index_2] - '0';
            // 相加
            int temp_sum = cur_1 + cur_2 + mod;
            res.append(temp_sum%10);
            // 更新进位
            mod = temp_sum/10;
            // 继续移动
            index_1--;
            index_2--;
        }
       // 如果最后余数不为0
       if(mod!=0){
           res.append(mod);
       }
        // 翻转

        return  res.reverse().toString();
    }
}