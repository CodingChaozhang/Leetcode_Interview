class Solution {
    public boolean exist(char[][] board, String word) {
       
        // dfs走法
        char[] arr = word.toCharArray();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,i,j,arr,0)){
                    return true;
                }
            }
        }
        return false;
    }

    // dfs
    public boolean dfs(char[][] board,int i,int j,char[] dict,int index){
        // 下一步走到边界都找不到 已访问过了
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=dict[index]){
            return false;
        }
        if(index==dict.length-1){
            return true;
        }
        // 走过的处理
        char temp = board[i][j];
        board[i][j] = '1';

        // 接着走
        boolean res = dfs(board,i+1,j,dict,index+1) || 
                      dfs(board,i-1,j,dict,index+1) || 
                      dfs(board,i,j-1,dict,index+1) ||
                      dfs(board,i,j+1,dict,index+1);

        // 恢复
        board[i][j] = temp;

        return res;

    }
}