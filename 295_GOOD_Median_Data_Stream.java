/**
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * 
 * For example, [2,3,4], the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure. double findMedian() - Return the median of all elements so far.
 * 
 * 
 * Example:
 * 
 * addNum(1) addNum(2) findMedian() -> 1.5 addNum(3) findMedian() -> 2
 * 
 * 
 * Follow up:
 * 
 * If all integer numbers from the stream are between 0 and 100, how would you
 * optimize it? If 99% of all integer numbers from the stream are between 0 and
 * 100, how would you optimize it?
 */

 /**
  * 
  I keep two heaps (or priority queues):

Max-heap small has the smaller half of the numbers.
Min-heap large has the larger half of the numbers.
This gives me direct access to the one or two middle values (they're the tops of the heaps), so getting the median takes O(1) time.

And adding a number takes O(log n) time.

Supporting both min- and max-heap is more or less cumbersome, depending on the language, 

****so I simply negate the numbers in the heap in which I want the reverse of the default order. 

To prevent this from causing a bug with -231 (which negated is itself, when using 32-bit ints), I use integer types larger than 32 bits.
Using larger integer types also prevents an overflow error when taking the mean of the two middle numbers. I think almost all solutions posted previously have that bug.

Update: These are pretty short already, but by now I wrote even shorter ones.
  */

/**
 * THEORY:
 * The peek() method of Queue Interface returns the element at the front the container. It does not deletes the element in the container. This method returns the head of the queue. The method does not throws an exception when the Queue is empty, it returns null instead.
 */

 /**
  * Queue poll() method in Java. The poll() method of Queue Interface returns and removes the element at the front the container. It deletes the element in the container. The method does not throws an exception when the Queue is empty, it returns null instead.
  */

  /**
   * Note small queue contains negative numbers so in median we do -ve so that it becomes + ie up--down/2 ---> up+Down/2
   */

   //BY DEFAULT THE PRIORITY QUEUE HAS NUMBERS ARRANGED IN ASCENDING ORDER OR MIN HEAP<>

import java.util.*;
class Median_Data_Stream {

    static Queue<Long> small = new PriorityQueue();
    static Queue<Long> large = new PriorityQueue();

    //add(2), add(3), add(4)
    static void addNum(int num) {

        //large<2>
        //large<2, 3>
        //large<3, 4> whcih was large<3> previously
        large.add((long) num);

        //large<>, small(-2)
        //large<3>, which was small<> now becomes small<-2>

        //Ideally it should become -->>>large<4>, small <-2, -3>
        //But since it is a min heap or a priority queue so, it rearranges and becomes--> large<4> and small <-3, -2> instead of <-2, -3>
        small.add(-large.poll());

        //large<> 0, small<-2>, 1 so small is more in size
        //large<3>, small<-2> size equal so nothing
        //small is more than large becuse large<4> size=1, small<-3,-2> size is 2
        if (large.size() < small.size()){
            //large<+2>, small <>

            //ideally it should become --->>large<4, 3>, small<-2>
            //but due to min heap it will become large<3,4> and small <-2>
            large.add(-small.poll());
        }
    }

    static double findMedian() {
        double result;
        if(large.size() > small.size()){
            result = large.peek();
        } else{
            result = (large.peek() - small.peek()) / 2.0;
        }
        return result;
        //return large.size() > small.size() ? large.peek() : (large.peek() - small.peek()) / 2.0;
    }
}

class implement_median_Data_Stream{
    public static void main(String args[]){
        //For example, [2,3,4], the median is 3
        Median_Data_Stream obj = new Median_Data_Stream();
        obj.addNum(2);
        obj.addNum(3);
        obj.addNum(4);
        System.out.println(obj.findMedian());
        
    }
}