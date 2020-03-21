/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1*/

/*More Examples:
Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
         key = 3
Output : Found at index 8

Input  : arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
         key = 30
Output : Not found

Input : arr[] = {30, 40, 50, 10, 20}
        key = 10   
Output : Found at index 3*/

//Solution https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/

/*Input arr[] = {3, 4, 5, 1, 2}
Element to Search = 1
  1) Find out pivot point and divide the array in two
      sub-arrays. (pivot = 2) /*Index of 5
      2) Now call binary search for one of the two sub-arrays.
      (a) If element is greater than 0th element then
             search in left array
      (b) Else Search in right array
          (1 will go in else as 1 < 0th element(3))
  3) If element is found in selected sub-array then return index
     Else return -1.
     */

class SearchRotatedSorted{

    //step-1: Find the pivot in the Sorted Rotated Array:

    //example- {3, 4, 5, 6, 1, 2}
    //pivot will be value (6) that is index (3)
    //actual array is {1, 2, 3, 4, 5, 6}
    //Turning point will be the point just before 
    static int findPivot(int arr[], int low, int high){

        //Base Conditions:
        if (low>high) return -1;
        if (high == low) return low;

        //calculate mid value:
        //mid = 4
        //low = 0
        //high = 8
        int mid = (low+high)/2;

        //case-1:
        //{3, 4, 5, 6, 1, 2}
        //arr[mid 9 ] < arr[mid+1] 10 (this case will not run)

        //arr[mid] has to be more than arr[mid+1] to detect a big change moving to right side
        if(mid < high && arr[mid]> arr[mid+1])
            return mid;
        
        //case-2: 
        //arr[mid] has to be less than arr[mid+1] to detect a big change moving to left side
        if(mid > low && arr[mid]< arr[mid-1]){
            return mid-1;
        }

        //case-3 : Not found, Traverse further Go Left <-------
        if(arr[low] >= arr[mid])
            return findPivot(arr, low, mid-1);
        
        //case-4: Not found, Traverse, Go right you will find something ------>
        return findPivot(arr, mid+1, high);
    }

    //step-2: binary search the value once you get the pivot:
    static int binarySearch(int arr[], int low, int high, int key){

        //Low Index: 6
        //High Index: 8

        //Actual Key is at index 8
       if(high< low){
           return -1;
       }

       //mid is 7
       int mid = (low+high)/2;

       //mid:

       //mid index value is found yeah !! after recusrsion finally break condition:
       if(key== arr[mid]){
           return mid;
       }

       //RHS:
       //not found move further to the right hand side:
       if(key > arr[mid]){
           return binarySearch(arr, mid+1, high, key);
       }
       //LHS:
       //not found move further to the left:
       return binarySearch(arr, low, (mid-1), key);
    }

    static int pivotedBinarySearch(int arr[], int n, int key)
    {
        int pivot = findPivot(arr, 0, n-1);

        //this array is not rotated so do simple binary search:
        if(pivot == -1)
            return binarySearch(arr, 0, n-1, key);
        
        // If we find the pivot, 
        //1) Compare with pivot
        // 2) Then, Search in Two Sub-Arrays around the pivot

        //Pivot is same as Key
        if(arr[pivot] == key){
            return pivot;
        }

        //LHS
        if(arr[0] <= key)
            return binarySearch(arr, 0, pivot-1, key);
        
            //RHS
        return binarySearch(arr, pivot+1, n-1, key);


    }

    // main function 
	public static void main(String args[]) 
	{ 
	// Let us search 3 in below array 
	int arr1[] = {5, 6, 7, 8, 9, 10, 1, 2, 3}; 
	int n = arr1.length; 
	int key = 3; 
	System.out.println("Index of the element is : "
					+ pivotedBinarySearch(arr1, n, key)); 
	} 

}

    

