class Solution {
    public boolean exist(char[][] board, String word) {
        // string转数组
        char[] word_arr = word.toCharArray();
        // 对其遍历
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,word_arr,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    // dfs 上下左右走
    public boolean dfs(char[][] board,char[] word_arr,int i,int j,int index){
        // 递归截止条件
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word_arr[index]){
            return false;
        }
        // 遍历到最后一个了
        if(index==word_arr.length-1){
            return true;
        }
        // 记录当前值已被遍历到了
        char temp = board[i][j];
        board[i][j] = '0';

        // 结果值
        boolean res = dfs(board,word_arr,i-1,j,index+1) || 
                      dfs(board,word_arr,i+1,j,index+1) || 
                      dfs(board,word_arr,i,j-1,index+1) || 
                      dfs(board,word_arr,i,j+1,index+1);
        // 回溯
        board[i][j] = temp;

        return res;
    }
}