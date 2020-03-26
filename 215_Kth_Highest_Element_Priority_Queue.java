import java.util.PriorityQueue;
import java.util.Queue;

//Min Heap Solution using Priority Queue:

class PriorityQueueHighestElem{
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        
        for(int i = 0 ; i < k ; ++i)
        {
            minHeap.add(nums[i]);
        }
        
        for(int i = k ; i < nums.length ; ++i)
        {
            if(nums[i] > minHeap.peek())
            {
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }
        
        return minHeap.remove();
    }
}