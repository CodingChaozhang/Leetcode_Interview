class Solution {
    public boolean exist(char[][] board, String word) {
        // 解题思路 dfs
        char[] dict = word.toCharArray();
        // 记录被访问过
        boolean[][] visited = new boolean[board.length][board[0].length];
        // 对其遍历
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,visited,i,j,dict,0)){
                    return true;
                }
            }
        }
        return false;
    }

    // dfs
    public boolean dfs(char[][] board,boolean[][] visited,int i,int j, char[] dict,int index){
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || dict[index]!=board[i][j] || visited[i][j]){
            return false;
        }

        if(index==dict.length-1){
            return true;
        }

        // 当前值已访问
        visited[i][j] = true;
        // 访问
        boolean res = dfs(board,visited,i+1,j,dict,index+1) ||
                      dfs(board,visited,i-1,j,dict,index+1) ||
                      dfs(board,visited,i,j+1,dict,index+1) ||
                      dfs(board,visited,i,j-1,dict,index+1);
        visited[i][j] = false;
        return res;

    }
}