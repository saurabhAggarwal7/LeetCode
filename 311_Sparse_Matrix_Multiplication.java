
/**
 * Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

From the formula: Sum(A_ik * B_kj) -> C_ij

We can see that when A_ik is 0, there is no need to compute B_kj. So we switch the inner two loops and add a 0-checking condition.
 */
class Sparse_Matrix_Multiplication {

    static int[][] multiply(int[][] A, int[][] B) {
        //validity check
     
        int[][] C = new int[A.length][B[0].length];
     
        for(int i=0; i<C.length; i++){
            for(int k=0; k<A[0].length; k++){
                if(A[i][k]!=0){  //Optimized --->> check for it else the product will be 0 because mostly the matrix is sparsed and has most entries as 0's
                    for(int j=0; j<C[0].length; j++){
                        C[i][j] += A[i][k]*B[k][j];
                    }
                }
            }
        }
     
        return C;
    }
}