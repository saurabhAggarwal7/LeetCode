/*Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , determine if a person could attend all meetings.

For example,
Given [ [0, 30], [5, 10], [15, 20] ],
return false.

*/

//Realted Questions:
//Meeting roms-II
//Merge Intervals

//If a person can attend all meetings, there must not be any overlaps between any meetings. After sorting the intervals, we can compare the current end and next start.

import java.util.*;
class Meeting_Room_1 {

    static class Interval {
        int start;
        int end;
    }

    static boolean canAttendMeetings(Interval[] intervals) {

         //create your own sorting function:
         Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b){
                return a.start - b.start;
            }
        });

        //compute
        for(int i=0; i< intervals.length; i++){
            if(intervals[i].end > intervals[i+1].start)
                return false;
        }
        return true;
    }
}
