
class Search_Matrix{
    	/* Searches the element x in mat[][]. If the 
element is found, then prints its position 
and returns true, otherwise prints "not found" 
and returns false */
	private static void search(int[][] mat, int n, int x) 
	{ 

		int i = 0, j = n - 1; // set indexes for top right 
		// element 

		while (i < n && j >= 0) { 
			if (mat[i][j] == x) { 
				System.out.print("n Found at " + i + " " + j); 
				return; 
			} 
			if (mat[i][j] > x) 
				j--; 
			else // if mat[i][j] < x 
				i++; 
		} 

		System.out.print("n Element not found"); 
		return; // if ( i==n || j== -1 ) 
	} 
	// driver program to test above function 
	public static void main(String[] args) 
	{ 
		int mat[][] = { { 10, 20, 30, 40 }, 
						{ 15, 25, 35, 45 }, 
						{ 27, 29, 37, 48 }, 
						{ 32, 33, 39, 50 } }; 

		search(mat, 4, 29); 
	} 
}