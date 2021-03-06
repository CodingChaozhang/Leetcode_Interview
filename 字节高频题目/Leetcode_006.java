class Solution {
    public String convert(String s, int numRows) {
        char[] arr = s.toCharArray();
        int n = s.length();
        
        StringBuilder res = new StringBuilder();
        int cycle = 2*numRows-2;
        // 按行
        for(int i=0;i<numRows;i++){
            // 按列找
            for(int j=0;j+i<n;j+=cycle){
                res.append(arr[j+i]);
                // 不在第一行和最后一行存在了中间值
                if(i!=0&&i!=numRows-1&&j+cycle-i<n){
                    res.append(arr[j+cycle-i]);
                }

            }
        }
        return res.toString();

    }
}