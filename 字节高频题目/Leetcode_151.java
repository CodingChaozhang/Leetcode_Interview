class Solution {
    public String reverseWords(String s) {
        // 1.先去除前后的空格
        s = s.trim();
        // 2.正则表达式来分割字符串
        List<String> list = Arrays.asList(s.split("\\s+"));
        // 3.反转
        Collections.reverse(list);
        // 4.数组转成字符串
        return String.join(" ",list);
    }
}