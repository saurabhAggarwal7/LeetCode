/*Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length*/

//Example- Find the 4th highest element in the array:
import java.util.PriorityQueue;
import java.util.Queue;

//Min Heap Solution using Priority Queue:

class PriorityQueueHighestElem{

    public static int findKthLargest(int[] nums, int k) {

        //create a priortiy queue and add numbers in the queue:
        Queue<Integer> minHeap = new PriorityQueue<>();

        //let k=2, means we need to find 2nd highest element in array:
        // fill minheap with only first 2 elements
        //create batch of elemeents like 5th Highest so, 5 elements will be in the queue then
        for(int i=0; i<k; ++i){
            minHeap.add(nums[i]);
        }

        //skipping first 2 bcz they are already in heap loop over to entire array now
        //compare the top of the queue with all the other elements and if anyone is more than that then remove the smaller element and the highest one will become the peek now
        for(int i=k; i<nums.length; ++i){
            if(nums[i]> minHeap.peek()){
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }

        //at the last only result is left in queue
        return minHeap.remove();
    }


    public static void main(String[] args){
        int arr [] = {3,2,3,1,2,4,5,5,6};
        int k = 2;
        System.out.println("Highest " + k + "th value in array is:" + findKthLargest(arr, k));

        //Result is <<t stri>>
    }
}