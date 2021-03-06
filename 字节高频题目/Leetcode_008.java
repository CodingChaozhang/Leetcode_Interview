class Solution {
    public int myAtoi(String s) {
        int index = 0;
        int n = s.length();
        // 转成数组
        char[] arr = s.toCharArray();

        // 开始丢弃空格
        while(index<n&&arr[index]==' '){
            index++;
        }
        // 如果到头了
        if(index==n){
            return 0;
        }
        // 判断符号
        boolean neg = false;
        if(arr[index]== '-'){
            neg = true;
            index++;
        }else if(arr[index]=='+'){
            index++;
        }else if(!Character.isDigit(arr[index])){
            //遇到不是数字的字符
            return 0;
        }

        // 存储结果
        int res = 0;
        // 判断长度和是否是数字以及是否超出
        while(index<n&&Character.isDigit(arr[index])){
            int digit = arr[index]-'0';
            //判断是否溢出 res*10+digit>Integer.MAX_VALUE 即res>
            if(res>(Integer.MAX_VALUE-digit)/10){
                return neg?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }

            res = res*10 + digit;
            index++;
            
        }
        return neg?-res:res;

    }
}