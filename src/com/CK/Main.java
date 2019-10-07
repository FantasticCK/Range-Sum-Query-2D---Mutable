package com.CK;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }
}


class NumMatrix {
    int[][] dp;
    int[][] mat;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return;
        int r = matrix.length, c = matrix[0].length;

        mat = new int[r][c];
        dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = matrix[i][j];
                if (i == 0 && j == 0) {
                    dp[i][j] = matrix[i][j];
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = matrix[i][j] + dp[i][j - 1];
                    continue;
                }

                if (j == 0) {
                    dp[i][j] = matrix[i][j] + dp[i - 1][j];
                    continue;
                }

                dp[i][j] = matrix[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
    }

    public void update(int row, int col, int val) {
        int r = dp.length, c = dp[0].length;
        int var = val - mat[row][col];
        mat[row][col] = val;
        for (int i = row; i < r; i++) {
            for (int j = col; j < c; j++) {
                dp[i][j] += var;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int rightBottom = dp[row2][col2];
        int rightTop = row1 == 0 ? 0 : dp[row1 - 1][col2];
        int leftBottom = col1 == 0 ? 0 : dp[row2][col1 - 1];
        int leftTop = (row1 == 0 || col1 == 0) ? 0 : dp[row1 - 1][col1 - 1];
        return rightBottom - rightTop - leftBottom + leftTop;
    }
}


class NumMatrix {

    private int[][] colSums;
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        this.matrix = matrix;

        int m = matrix.length;
        int n = matrix[0].length;
        colSums = new int[m + 1][n];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                colSums[i][j] = colSums[i - 1][j] + matrix[i - 1][j];
            }
        }
    }

    //time complexity for the worst case scenario: O(m)
    public void update(int row, int col, int val) {
        for (int i = row + 1; i < colSums.length; i++) {
            colSums[i][col] = colSums[i][col] - matrix[row][col] + val;
        }

        matrix[row][col] = val;
    }

    //time complexity for the worst case scenario: O(n)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ret = 0;

        for (int j = col1; j <= col2; j++) {
            ret += colSums[row2 + 1][j] - colSums[row1][j];
        }

        return ret;
    }

}


