/*

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

*/

//No Need to use Heap or priority queue here like the meetings room question:
//Related Questions:
//>> Meeting Rooms - I and II

//Hre we convert the complex [][] 2 D array structure to essy class structure so that acess to [0] and [1] is not difficult and easy to understand

import java.util.*;
class merge_interval{

    static class Interval{
        int start;
        int end;

        //constructor:
        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    static public List<Interval> merge(List<Interval> intervals) {

        //Boundry Case: If the inetrvals list is null/empty:
        if(intervals == null || intervals.size()<=1){
            return intervals;
        }
        
        //collections sort: sort by start interval 
        Collections.sort(intervals, Comparator.comparing((Interval itl)->itl.start));
        
        //After Sort: {{1,3},{2,6},{8,10},{15,18}};

        List<Interval> result = new ArrayList<>();
        //First Set: {1,3}
        Interval temp = intervals.get(0);
     
        for(int i=1; i<intervals.size(); i++){
            Interval item = intervals.get(i);
            if(item.start <= temp.end){
                temp.end = Math.max(temp.end, item.end); //Update the previous t (reuse no need to add new room or no need t add new item)
            }else{
                result.add(temp); //add new item in the list we dont have anything in this range:
                temp = item; 
            }
        }
        
        //anyway add the first element (0) to give it a start
        result.add(temp);
     
        return result;
    }

    public static void main(String args[]){
        int[][] arr = {{1,3},{2,6},{8,10},{15,18}};
        List<Interval> lst = new ArrayList<Interval>();

        //fill the list with array items of the type Interval:
        for(int[] arr_item : arr){
            lst.add(new Interval(arr_item[0], arr_item[1]));
        }

        List<Interval> result = merge(lst);
        //Output: [[1,6],[8,10],[15,18]]
        
    }
}