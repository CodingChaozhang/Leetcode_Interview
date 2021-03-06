class Solution {
    public String longestPalindrome(String s) {
        // 回文字符串  两种扩散方式
        // 一种是  aba 从b往两边
        // 一种是   abba 从bb往两边扩散
        
        // 结果字符串
        String res = "";
        for(int i=0;i<s.length();i++){
            String res_1 = check(s,i,i);
            String res_2 = check(s,i,i+1);
            
            res = res.length()>res_1.length()?res:res_1;
            res = res.length()>res_2.length()?res:res_2;
        }
        return res;
    }

    // 检查
    public String check(String s,int left,int right){
        while(left>=0&&right<=s.length()-1&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }

        return s.substring(left+1,right);

    }
}