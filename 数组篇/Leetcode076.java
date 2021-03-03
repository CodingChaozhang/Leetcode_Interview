class Solution {
    public String minWindow(String s, String t) {
        if(s==""||s==null||t==""||t==null||s.length()<t.length()){
            return "";
        }

        // 记录其值
        int[] need = new int[128];
        for(int i=0;i<t.length();i++){
            need[t.charAt(i)]++;
        }
        int[] windows = new int[128];
        int left = 0, right = 0;
        int count = 0;
        int minLength = s.length()+1;
        //  结果
        String res = "";
        // 开始
        while(right<s.length()){
            char ch = s.charAt(right);
            windows[ch]++;
            if(need[ch]>0&&need[ch]>=windows[ch]){
                count++;
            }

            // 收缩左侧
            while(count==t.length()){
                ch = s.charAt(left);
                if(need[ch]>0&&need[ch]>=windows[ch]){
                    count--;
                }
                //记录结果
                if(right-left+1<minLength){
                    minLength = right-left+1;
                    res = s.substring(left,right+1);
                }

                windows[ch]--;
                left++;
            }
            right++;
        }
        return res;
    }
}