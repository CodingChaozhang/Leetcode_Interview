public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        // n与n-1进行与运算，即可 
        while(n!=0){
            count++;
            n &= (n-1);
        }
        return count;
    }
}