/*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 

Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3 */

//TRICK:
//Here accepted solution based on recursion. 
//To save memory I decuded to apply bit mask for every visited cell. Please check board[y][x] ^= 256;

class word_search_exists {
    static boolean exist(char[][] board, String word) {
        char[] word_char_array = word.toCharArray();

        // wor_char_array = {0A /1B /2C/ 3C/4E/5D}
        /*
        3 Rows and 4 columns
         * board = [ ['A','B','C','E'], 
         * ['S','F','C','S'], 
         * ['A','D','E','E'] ]
         */

        // traverse the row and columns of the board:
        for (int row = 0; row < word_char_array.length; row++) {
            for (int column = 0; column < word_char_array.length; column++) {
                if (exist(board, row, column, word_char_array, 0))
                    return true;
            }
        }
        return false;
    }

    static boolean exist(char[][] board, int row, int column, char[] word, int i) {

        //COMPARE BETWEEN BOARD[ROW] AND BOARD[COLUMN VS WORD[I] INDEX]
        //ALL 4 RESULT_1..... RESULT4 ARE ADJACENT TO THE CURRENT CHARACTER
        //TO PREVENT VISITING AGAIN SAME CELL WE MASK THE VISITED CELL

        // Boundry conditions:

        //
        if (i == word.length)
            return true;

        //
        if (row < 0 || column < 0 || row == board.length || column == board.length || column == board[row].length)
            return false;

        // check for word being equal or not:
        if (board[row][column] != word[i])
            return false;

        // To save memory applied bit mask for every visited cell. Please check
        // board[y][x] ^= 256;
        //board[0][0] = now masked to some character
        board[row][column] ^= 256;

        // RECURSION: ADJACENT CONDITIONS:
        // boolean result = exist(board, row, column + 1, word, i + 1) || 
                            //exist(board, row, column - 1, word, i + 1)
        //                  || exist(board, row + 1, column, word, i + 1) 
        //                  || exist(board, row - 1, column, word, i + 1);

        //row=0, column = 0, 1, 2, 3 and i=0,1,2,3
        //row, column = 1,2,i=3

        //RIGHT:
        boolean result_1 = exist(board, row, column + 1, word, i + 1);

        //row=0, column = 2, i=2
        //row=0, column =1, i=3

        //LEFT
        boolean result_2 = exist(board, row, column - 1, word, i + 1);

        //row, column 0, 2, i=2
        //row=, colum = 1, 2, i=3

        //TOP:
        boolean result_3 = exist(board, row + 1, column, word, i + 1);

        //BOTTOM:
        boolean result_4 = exist(board, row - 1, column, word, i + 1);

        //CHECK FOR ADJACENT FOR CHARACTERS:
        boolean result = result_1 || result_2 || result_3 || result_4;

        board[row][column] ^= 256;

        return result;
    }

    public static void main(String args[]) {

        // define input:
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };

        //TRUE:
        System.out.println(exist(board, "ABCCED"));
        
        //FALSE
        //System.out.println(exist(board, "XYZP"));

    }
}