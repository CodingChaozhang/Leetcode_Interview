class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length<1||matrix[0].length<1){
            return false;
        }
        // 思路就是从左上角开始找
        int row = matrix.length;
        int col = matrix[0].length;
        int start_r = 0;
        int start_c = col-1;
        int end_r = row-1;
        int end_c = 0;
        // 都要满足
        while(start_r<=end_r&&start_c>=end_c){
            if(matrix[start_r][start_c]==target){
                return true;
            }else if(matrix[start_r][start_c]>target){
                start_c--;
            }else if(matrix[start_r][start_c]<target){
                start_r++;
            }
        }
        return false;
    }
}