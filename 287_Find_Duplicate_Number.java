/*Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

Solution:
Floyd's Fast and Slow Pointer Approach:
Duplicate means finding the cycle
fast will have 

*/



class find_duplicate_number {

    static int findDuplicate(int[] nums) {

        //arr = [3(0),1(1),3(2),4(3),2(4)]
        // start from here:
        int fast = nums[0]; //3
        int slow = nums[0]; //3

        while (true) {
            fast = nums[fast]; //fast = nums[3] = 4
            slow = nums[nums[slow]]; //nums[nums[4]] = nums[2] = 3
            if (fast == slow) {
                    break;
            }
        }

        int ptr1 = nums[0]; //3 //Starting VALUE
        int ptr2 = fast; //place where break was done means where both met at duplicate number //3 //ENDING VALUE
        //BUT USING WE KNOW WHATS THE DUPLICATE NUMBER, NOW CHECK FOR ITS PARTNER BELOW:


        //first array both ptr comes 3 so exit return

        //second array case used: {{1(0),3(1),4(2),2(3),2(4)}}
        //fast = ptr2 = 2
        //ptr1 = starting point = 1

        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1]; //ptr1 = nums[1] = 3
            ptr2 = nums[ptr2]; //ptr2 = nums[fast] = nums[ptr2] = 2
        }
        return ptr1;
    }

        /*

2nd METHOD:

Approach: The basic idea is to use a HashMap to solve the problem. But there is a catch, the numbers in the array are from 0 to n-1, and the input array has length n. So, the input array can be used as a HashMap. While Traversing the array, if an element ‘a’ is encountered then increase the value of a%n‘th element by n. The frequency can be retrieved by dividing the a % n’th element by n.
Algorithm:
Traverse the given array from start to end.
For every element in the array increment the arr[i]%n‘th element by n.
Now traverse the array again and print all those indexes i for which arr[i]/n is greater than 1. Which guarantees that the number n has been added to that index
This approach works because all elements are in the range from 0 to n-1 and arr[i] would be greater than n only if a value “i” has appeared more than once.

*/
    static int findDuplicates_2(int[] numRay){
        for (int i = 0; i < numRay.length; i++) { 
            numRay[numRay[i] %  numRay.length] = numRay[numRay[i] %  numRay.length] + numRay.length; 
        } 
        System.out.println("The repeating elements are : "); 
        for (int i = 0; i < numRay.length; i++) { 
            if (numRay[i] >= numRay.length*2) { 
                System.out.println(i + " ");
                return i; 
            } 
        }
        return -1; 
    }




    public static void main(String args[]){
        int[] arr = {3,1,3,4,2};
        System.out.println(findDuplicate(arr));

        int[] arr2 = {1,3,4,2,2};
        System.out.println(findDuplicate(arr2));

        int[] arr3 = {0, 4, 3, 2, 7, 8, 2, 3, 1}; 
        System.out.println(findDuplicates_2(arr3));
    }
}