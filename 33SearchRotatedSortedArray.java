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

        if (low>high) return -1;
        if (high == low) return low;

        //calculate mid value:
        int mid = (low+high)/2;

        //case-1:
        if(mid < high && arr[mid]> arr[mid+1])
            return mid;
        
        //case-2:
        


        return -1;
    }

    //step-2: binary search the value once you get the pivot:
    static int binarySearch(int arr[], int low, int high){
       
       
        return -1; 
    }

}

    

