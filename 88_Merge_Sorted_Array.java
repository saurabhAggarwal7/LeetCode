
/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

 */

class Merge_Sorted_Array {
    //A, m, i
    //B, n, j
    void merge(int A[], int m, int B[], int n) {
        int i = m-1;
        int j = n-1;
        for (int k = m+n-1; k >= 0; k--) {
            if (i < 0)              
                A[k] = B[j--]; //B[j--] use value of B[j] and then j-- //shorthand for writing 2 code lines
            else if (j < 0)         
                A[k] = A[i--];
            else if (A[i] < B[j])   
                A[k] = B[j--];
            else                    
                A[k] = A[i--];
        }
    }    
}