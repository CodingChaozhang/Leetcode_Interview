class Solution {
    public String replaceSpace(String s) {
        // 用stringbuilder来拼接字符串
        // 将其转换为数组
        char[] arr = s.toCharArray();
        // 结果
        StringBuilder res = new StringBuilder();
        // 对其遍历
        for(int i=0;i<arr.length;i++){
            // 对其遍历
            if(arr[i]==' '){
                // 空格
                res.append("%20");
            }else{
                res.append(arr[i]);
            }
        }
        return res.toString();
    }
}