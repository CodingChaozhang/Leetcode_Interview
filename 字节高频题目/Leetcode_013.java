class Solution {
    public int romanToInt(String s) {
        if(s==null||s.length()<1){
            return 0;
        }
        // 对其存储
        HashMap<Character,Integer> hashMap = new HashMap<>(){{
            put('I',1);
            put('V',5);
            put('X',10);
            put('L',50);
            put('C',100);
            put('D',500);
            put('M',1000);
        }};
        // 结果
        int res = 0;
        // 字符串转换
        char[] arr = s.toCharArray();
        // 对其遍历
        int preNum = hashMap.get(arr[0]);
        for(int i=1;i<arr.length;i++){
            int curNum = hashMap.get(arr[i]);
            // 比较
            if(preNum>=curNum){
                res += preNum;
            }else{
                res -= preNum;
            }
            preNum = curNum;
        }
        res += preNum;
        return res;
    }
}