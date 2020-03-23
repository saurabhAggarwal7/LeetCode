/*Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]*/
// Java program to find triplets in a given 
// array whose sum is zero 

//2 pointers Approach Left and Right pouinter:
import java.util.Arrays; 
import java.io.*; 

class GFG { 
	// function to print triplets with 0 sum 
static void findTriplets(int arr[], int n) 
{ 
	boolean found = false; 

	// sort array elements 
	Arrays.sort(arr); 

	for (int i=0; i<n-1; i++) 
	{ 
		// initialize left and right 
		int l = i + 1; 
		int r = n - 1; 
		int x = arr[i]; 
		while (l < r) 
		{ 
			if (x + arr[l] + arr[r] == 0) 
			{ 
				// print elements if it's sum is zero 
					System.out.print(x + " "); 
					System.out.print(arr[l]+ " "); 
					System.out.println(arr[r]+ " "); 
	
				l++; 
				r--; 
				found = true; 
			} 

			// If sum of three elements is less 
			// than zero then increment in left 
			else if (x + arr[l] + arr[r] < 0) 
				l++; 

			// if sum is greater than zero than 
			// decrement in right side 
			else
				r--; 
		} 
	} 

	if (found == false) 
			System.out.println(" No Triplet Found"); 
} 

// Driven source 
	public static void main (String[] args) { 

	int arr[] = {0, -1, 2, -3, 1}; 
	int n =arr.length; 
	findTriplets(arr, n); 
	} 
//This code is contributed by Tushil..	 
} 
