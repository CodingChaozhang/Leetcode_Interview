class Solution {
    public boolean isNumber(String s) {
        // 表示数值的字符串
        // 优先状态机的字符串5个状态：数字0-9 标点.  e   正负号  空格
        // 空格可以先去掉
        char[] str = s.trim().toCharArray();
        // 标记情况数字 标点 e号
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        // 开始判断
        for(int i=0;i<str.length;i++){
            // 数字出现
            if(str[i]>='0'&&str[i]<='9'){
                numSeen = true;
            }else if(str[i]=='.'){
                // .之前不能出现.或者e
                if(dotSeen || eSeen){
                    return false;
                }
                dotSeen = true;
            }else if(str[i]=='e' || str[i]=='E'){
                // e之前不能出现e，且必须出现数字
                if(eSeen || !numSeen){
                    return false;
                }
                eSeen = true;
                // 出现之后
                numSeen = false;
            }else if(str[i]=='-' || str[i]=='+'){
                // +出现在0位置或者e/E的后面第一个位置才是合法的
                if(i!=0&&str[i-1]!='e'&&str[i-1]!='E'){
                    return false;
                }
            }else{
                // 其他不合法
                return false;
            }
        }
        return numSeen;
    }
}