/*Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
*/

//SOLUTION:
/*
The problem is similar to our old post Segregate 0s and 1s in an array, and both of these problems are variation of famous Dutch national flag problem.

The problem was posed with three colours, here `0′, `1′ and `2′. The array is divided into four sections:

a[1..Lo-1] zeroes (red)
a[Lo..Mid-1] ones (white)
a[Mid..Hi] unknown
a[Hi+1..N] twos (blue)
The unknown region is shrunk while maintaining these conditions

Lo := 1; Mid := 1; Hi := N;
while Mid <= Hi do
Invariant: a[1..Lo-1]=0 and a[Lo..Mid-1]=1 and a[Hi+1..N]=2; a[Mid..Hi] are unknown.
case a[Mid] in
0: swap a[Lo] and a[Mid]; Lo++; Mid++
1: Mid++
2: swap a[Mid] and a[Hi]; Hi–
*/

class sort_colors{

    static void sort012(int[] a, int arr_size){

        int lo=0;
        int hi=arr_size -1;
        int mid=0;
        int temp =0;

        /*Examine a[Mid]. There are three possibilities: 
        //a[Mid] is (0) red, 
        //(1) white or 
        //(2) blue.*/

        while(mid <=hi){
            switch(a[mid]){

                /*Case (0) a[Mid] is red, 
                //swap a[Lo] and a[Mid]; Lo++; Mid++
                [0, 0, 0, 1 (lo), 1, 1, ? (mid), ? , ?, ?(hi), 2, 2, 2]*/

                //RED (0) SWAP LO and MID
                case 0: {

                    //swap lo and mid
                    temp = a[lo];
                    a[lo] = a[mid];
                    a[mid] = temp;

                    //forward:
                    lo++;
                    mid++;

                    break;
                }

                /*Case (1) a[Mid] is white, Mid++
                [0, 0, 0, 0, 1 (lo), 1, 1, ? (mid), ? , ?(hi), 2, 2, 2]*/

                //WHITE(1): nothing mid++
                case 1:{

                    mid++;
                    break;

                }
                /*
                Case (2) a[Mid] is blue, swap a[Mid] and a[Hi]; Hi—
                [0, 0, 0, 1 (lo), 1, 1, ? (mid), ? , ?(hi), 2, 2, 2]
                Continue until Mid>Hi. 
                */

                //BLUE(2) MID and HI SWAP. Hi--
                case 2:{

                    //swap mid and hi
                    temp = a[mid];
                    a[mid] = a[hi];
                    a[hi] = temp;

                    //move backwards:
                    hi--;
                    break;

                }
            } //end of switch
        } //end of while
    }

        /* Utility function to print array arr[] */
	static void printArray(int arr[], int arr_size) 
	{ 
		int i; 
		for (i = 0; i < arr_size; i++) 
			System.out.print(arr[i] + " "); 
		System.out.println(""); 
	} 

    public static void main(String args[]){
        int[] arr = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        int arr_size = arr.length; 
		sort012(arr, arr_size); 
		System.out.println("Array after seggregation "); 
		printArray(arr, arr_size); 
    }
}