/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.

Java Solution
When a room is taken, the room can not be used for anther meeting until the current meeting is over. As soon as the current meeting is finished, the room can be used for another meeting. We can sort the meetings by start timestamps and sequentially assign each meeting to a room. Each time when we assign a room for a meeting, we check if any meeting is finished so that the room can be reused. In order to efficiently track the earliest ending meeting, we can use a min heap. Whenever an old meeting ends before a new meeting starts, we reuse the room (i.e., do not add more room). Otherwise, we need an extra room (i.e., add a room).

*/

//The time complexity is O(N*log(N)).

//HEAP OFFER:
//The java.util.PriorityQueue.offer() method is used to insert a particular element into the Priority Queue. It acts similar to the add() method of Priority Queue.
//TRUE if value is successfuly inserted in the heap

/*Theory:

ADD VS OFFER

The difference between offer and add is explained by these two excerpts from the javadocs:

From the Collection interface:

If a collection refuses to add a particular element for any reason other than that it already contains the element, it must throw an exception (rather than returning false). This preserves the invariant that a collection always contains the specified element after this call returns.

From the Queue interface

When using queues that may impose insertion restrictions (for example capacity bounds), method offer is generally preferable to method Collection.add(E), which can fail to insert an element only by throwing an exception.

PriorityQueue is a Queue implementation that does not impose any insertion restrictions. Therefore the add and offer methods have the same semantics.

*/

/**
 
POLL VS PEAK

The java.util.PriorityQueue.poll() method in Java is used to retrieve or fetch and remove the first element of the Queue or the element present at the head of the Queue. The peek() method only retrieved the element at the head but the poll() also removes the element along with the retrieval. It returns NULL if the queue is empty.

 */

 /**
  Normal QUEUE Vs Priority Queue (WITH HEAP) Difference in Using here:


If Normal Queue is Used:
i=1: {4, 9} count=1
i=2: {2, 15} count = 2
i=3: {16, 23} //reuse above room
i=4: {9, 29} //count = 3 BAD //Here the problem is This will unnecesslr increase the time and count+1 as being start time 9 it could have been staretd earlier
i=5: {36, 45} //reuse 


//{{2, 15}, {4, 9}, {9, 29}, {16, 23}, {36, 45}}

BELOW:

  **/



import java.util.*;
class meeting_rooms_II {

    static int min_meeting_rooms(int[][] intervals){

        //{{2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}};

        //Sort the arry as per the start time of the meetinsg scheduled: thats why we took [0]
        Arrays.sort(intervals, Comparator.comparing((int[] itr) -> itr[0]));

        //AFTER SORT:
        //{{2, 15}, {4, 9}, {9, 29}, {16, 23}, {36, 45}}

        //Create a priority queue to check for the end time and re use the same room if the meeting ends:

        //Heap Peek will store the latest meeting end time
        //We compare the heap Peek with the next meeting's start time 
        //Peak is to compare and Poll is used to remove from the heap
        //Sort the meeting in start time is important to schedule the structure

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int count =0;
        for(int[] itr: intervals){
            if(heap.isEmpty()){
                //i=0, heap is empty so insert end time in heap (itr[1])
                count++;
                heap.offer(itr[1]);
            } else{
                if(itr[0] >= heap.peek()){
                    //Reuse the room and no count++
                    heap.poll(); //i=3 because last meeting ends at 9 and new meeting starts at 9. itr[0] = 9 from {9, 29} and heap.peek() which stores the last meetings end time hence no new meeting room is required here
                                 //i=5: no need for new and reuse the same room: [ 23 from {16, 23} Vs 36 from {36, 45}] so we are good 
                } else{
                    count++; //i=1, {count=1 now} since it[0] = 4 and heap.peek is 15 so we need another new room
                             //i=4  {count= 2 now} new meeting room requied now as Start time itr[0] is 16 and heap peek is 29
                }
                heap.offer(itr[1]); //insert the end time
            }
        }
        
        return count;
    }

    public static void main(String args[]) {
        int[][] intervals = {{2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}};
        System.out.println(min_meeting_rooms(intervals));
    }
}