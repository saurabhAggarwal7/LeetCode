/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

// First Pass all four corners
//Second Pass--> Proceed to One index near 
//Third Pass--> Just near by elemenst
//Last Pass--> Inner 2x2 matrix once all the corners are rotated:

class rotate_image {

    static int[][] rotate(int[][] matrix) {
        int l = matrix.length;

        for (int i = 0; i < l / 2; i++) {
            //l=2, so will run from i=0/1
            //int test=1;
            for (int j = i; j < l - i - 1; j++) {
                //j=0 to 3 for i=0
                //j=0 to 3 for i=1
                rotate(matrix, i, j, l - 1);
            }
        }
        return matrix;
    }

    static void rotate(int[][] matrix, int i, int j, int l) {
        int one = matrix[i][j]; //5
        int two = matrix[j][l - i];//11
        int three = matrix[l - i][l - j];//16
        int four = matrix[l - j][i]; //15

        // swap the places:
        matrix[i][j] = four; //matrix[0][0] //matrix[0][1] //matrix[0][2]//matrix[1][1]
        matrix[j][l - i] = one; //matrix[0][3] //matrix[1][3] //matrix[2][3]//matrix[1][2]
        matrix[l - i][l - j] = two; //matrix[3][3] //matrix[3][2]//matrix[3][1]/matrix[2][2]
        matrix[l - j][i] = three;//matrix[3][0] //matrix[2][0]//matrix[1][0]/matrix[2][1]

        /*

  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]

  [ 5, X, X,11],
  [ X, X, X,X],
  [X, X, X, X],
  [15,X, X,16]

    [ X, 13, X,X],
  [ X, X, X,1],
  [12, X, X, X],
  [X,7, X,X]

  

        */
    }

    // public static void main(String args[]) {
    //     int[][] arr = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };

    //     int[][] matrix = rotate(arr);
    //     System.out.println(matrix);
    // }

}