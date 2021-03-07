class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        // 解题思路：递归
        getParenthesis("",n,n);
        return res;
    }
    // left表示左括号 right表示右括号
    public void getParenthesis(String str,int left,int right){
        if(left==0&&right==0){
            res.add(str);
            return;
        }
        // 左括号不能剩余的右括号多
        if(left>right){
            return;
        }
        // 其余递归
        if(left>0){
            getParenthesis(str+"(",left-1,right);
        }

        if(right>0){
            getParenthesis(str+")",left,right-1);
        }

    }
}