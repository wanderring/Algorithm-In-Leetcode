/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border
of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not
connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if
they are adjacent cells connected horizontally or vertically.

本来一直没思路，想了一段时间决定换一种方法，从边上开始把O的字符搜索出来写入protect，成功beat 100%
*/
class Solution {
    public void solve(char[][] board) {
        if (board.length==0 || board[0].length==0) return;
        boolean[][] protect = new boolean[board.length][board[0].length];
        for (int i=0; i<board[0].length; ++i) {
            if (board[0][i]=='O') {
                protect[0][i] = true;
                solveCore(board,protect,0,i);
            }
        }
        for (int i=1; i<board.length; ++i) {
            if (board[i][0]=='O') {
                protect[i][0] = true;
                solveCore(board,protect,i,0);
            }
        }
        for (int i=1; i<board[0].length; ++i) {
            if (board[board.length-1][i]=='O') {
                protect[board.length-1][i] = true;
                solveCore(board,protect,board.length-1,i);
            }
        }
        for (int i=1; i<board.length-1; ++i) {
            if (board[i][board[0].length-1]=='O') {
                protect[i][board[0].length-1] = true;
                solveCore(board,protect,i,board[0].length-1);
            }
        }
        for (int i=0; i<protect.length; ++i) {
            for (int j=0; j<protect[0].length; ++j) {
                if (protect[i][j]) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void solveCore(char[][] board, boolean[][] protect, int row, int col) {
        if (row>0 && !protect[row-1][col] && board[row-1][col]=='O') {
            protect[row-1][col] = true;
            solveCore(board, protect, row-1, col);
        }
        if (col>0 && !protect[row][col-1] && board[row][col-1]=='O') {
            protect[row][col-1] = true;
            solveCore(board, protect, row, col-1);
        }
        if (row<board.length-1 && !protect[row+1][col] && board[row+1][col]=='O') {
            protect[row+1][col] = true;
            solveCore(board, protect, row+1, col);
        }
        if (col<board[0].length-1 && !protect[row][col+1] && board[row][col+1]=='O') {
            protect[row][col+1] = true;
            solveCore(board, protect, row, col+1);
        }
    }
}
