class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length<1){
            return "";
        }
        // 第一个作为模板
        int count = strs.length;
        // 第一个的长度
        int length = strs[0].length();
        // 对第一个进行遍历
        for(int i=0;i<length;i++){
            char c = strs[0].charAt(i);
            // 与另外两个对比
            for(int j=1;j<count;j++){
                // 不相等就返回
                if(i==strs[j].length()||c!=strs[j].charAt(i)){
                    return strs[0].substring(0,i);
                }
            }
        }
        // 只有一个数
        return strs[0];
    }
}