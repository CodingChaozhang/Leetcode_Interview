class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m  = matrix.length-1;
        int n = matrix[0].length;
        int row_start = 0;
        int col_start = n-1;
        while(row_start<=m&&col_start>=0){
            if(matrix[row_start][col_start]==target){
                return true;
            }else if(matrix[row_start][col_start]>target){
                col_start--;
            }else if(matrix[row_start][col_start]<target){
                row_start++;
            }
        }
        return false;
    }
}