class Solution {
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        // 临时存储结果
        int[] arr = new int[4];
        // 一个是s的开端 一个是arr的开端
        dfs(s,arr,0,0);
        return res;
    }
    // 回溯
    public void dfs(String s,int[] arr,int s_start,int arr_start){
        // 如果 都遍历到最后了
        if(arr_start==arr.length){
            if(s_start==s.length()){
                // 开始转换
                StringBuilder temp = new StringBuilder();
                //
                for(int i=0;i<arr.length;i++){
                    temp.append(arr[i]);
                    if(i!=arr.length-1){
                        temp.append(".");
                    }
                }
                res.add(temp.toString());
            }
            return;
        }
        // 其他截至条件
        if(s_start==s.length()){
            return;
        }

        // 如果是0的话
        if(s.charAt(s_start)=='0'){
            arr[arr_start] = 0;
            dfs(s,arr,s_start+1,arr_start+1);
        }        
        // 遍历
        // 数字
        int digit = 0;
        for(int i=s_start;i<s.length();i++){
            digit = digit*10 + (s.charAt(i)-'0');
            // 符合条件
            if(digit>0&&digit<=0xFF){
                arr[arr_start] = digit;
                dfs(s,arr,i+1,arr_start+1);
            }else{
                break;
            }
        }

    }
}