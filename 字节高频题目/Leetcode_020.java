class Solution {
    public boolean isValid(String s) {
        // 考察栈
        HashMap<Character,Character> hasHMap = new HashMap<>();
        hasHMap.put(')','(');
        hasHMap.put('}','{');
        hasHMap.put(']','[');
        Stack<Character> stack = new Stack<>();
        // 字符串
        char[] arr = s.toCharArray();
        // 对其遍历
        for(int i=0;i<arr.length;i++){
            /// 如果是右括号，
            if(hasHMap.containsKey(arr[i])){
                // 栈为空
                if(stack.isEmpty() || stack.pop()!=hasHMap.get(arr[i])){
                    return false;
                }
            }else{
                // 如果是左括号直接入栈
                stack.push(arr[i]);
            }
        }
        // 判断
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
}