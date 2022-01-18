class Solution {
    public boolean isPalindrome(int x) {
        // 判断回文数字->翻转数字 来判断值
        if(x<0){
            return false;
        }
        // 对其翻转
        int verse_digit = 0;
        int temp = x;
        while(temp!=0){
            verse_digit = verse_digit*10 + temp%10;
            temp = temp/10;
        }

        return verse_digit==x?true:false;

    }
}