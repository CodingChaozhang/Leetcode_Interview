class Solution {
    public String decodeString(String s) {
        // 数据编码 记住就会，记不住就不会
        // 两个栈 一个栈存储数据，另外一个栈存储字符串
        Stack<Integer> stack_num = new Stack<>();
        Stack<StringBuilder> stack_str = new Stack<>();
        // 数字
        int curNum = 0;
        StringBuilder resChar = new StringBuilder();
        // 对其遍历
        for(Character c:s.toCharArray()){
            // 判断是数字的话
            if(Character.isDigit(c)){
                curNum = curNum*10 + c-'0';
            }else if(Character.isAlphabetic(c)){
                resChar.append(c);
            }else if(c=='['){
                // 入栈
                stack_num.push(curNum);
                stack_str.push(resChar);
                // 重新赋值
                curNum = 0;
                resChar = new StringBuilder();
            }else if(c==']'){
                // 出栈计算
                int tempNum = stack_num.pop();
                StringBuilder tempChar = stack_str.pop();
                // 计算
                for(int i=0;i<tempNum;i++){
                    tempChar.append(resChar);
                }
                resChar = tempChar;
            }
        }
        return resChar.toString();
    }
}