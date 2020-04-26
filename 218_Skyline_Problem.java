/**
 * SKY LINE PROBLEM 
 * YOUTUBE: https://www.youtube.com/watch?v=GSBLe8cKu0s
 * Solution: https://www.educative.io/page/11000001/70001
 * 
 * 
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * 
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 * Time complexity is O(nlogn)
 * Space complexity is O(n)
 */

 /**
  * 
  SOLUTION IN A NUTT Shell:

  problem is to draw the skyline drawing.
  we are given the start and end points and height of the building as the inputs [x, y, height]
  output will be the list of points required to draw the skyline. Note any buildings that are short are covered by the big tall buildings and they wont be included in the skylie  drawing
  
  Code wise::
  --T(start point) and F(end point) are the status of the points
  --create a TreeMap<> and put the highest height there initially 0
  -- we check if that 'H' is already available in map if yes increase the count+1
  --if the heght of any point replaces the max_height as stored in treeMap then that T(type) point is included in the result

  --Main change comes when we have F type point::
  2 cases::


  Apart from this there will be 3 boundry cases as well that are Tie btreaker cases if we have same startpoint or same end points
  */

import java.util.*;

/**
 * Date 01/07/2016
 * @author Tushar Roy
 *
 * Given skyline of a city merge the buildings
 *
 * Time complexity is O(nlogn)
 * Space complexity is O(n)
 *
 * References
 * https://leetcode.com/problems/the-skyline-problem/
 * https://leetcode.com/discuss/67091/once-for-all-explanation-with-clean-java-code-nlog-time-space
 */
class BuildingPoints{
    int x;
    int y;
}

class SkylineDrawing {

    /**
     * Represents either start or end of building
     */
    static class BuildingPoint implements Comparable<BuildingPoint> {
        int x;
        boolean isStart;
        int height;

        @Override
        public int compareTo(BuildingPoint o) {
            //first compare by x.


            //BASE CONDITIONS----->>
            //CONDITION-1::If they are same then use this logic
            //CONDITION-2:::if two starts are compared then higher height building should be picked first
            //CONDITION-3:::if two ends are compared then lower height building should be picked first
            //CONDITION-4:::if one start and end is compared then start should appear before end

            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
            }
        }
     }

    public List<int[]> getSkyline(int[][] buildings) {

        //for all start and end of building put them into List of BuildingPoint
        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length*2];
        int index = 0;
        for(int building[] : buildings) {
            buildingPoints[index] = new BuildingPoint();
            buildingPoints[index].x = building[0];
            buildingPoints[index].isStart = true;
            buildingPoints[index].height = building[2];

            buildingPoints[index + 1] = new BuildingPoint();
            buildingPoints[index + 1].x = building[1];
            buildingPoints[index + 1].isStart = false;
            buildingPoints[index + 1].height = building[2];
            index += 2;
        }
        Arrays.sort(buildingPoints);

        /**
         * BUILDING POINTS:: SORT BY X ASCENDING::
         * 
         * height: 4, isStart=T, x:1
         * height:2, x=2
         * h=4, F, x3
         * h4, F,x4
         * h2, F, x6
         * h3, T, x7
         * h4, T, x8
         * h3, F, x9
         * h2, T, x10
         * h2, F, x11
         * h4, F, x11
         */

        //using TreeMap because it gives log time performance.
        //PriorityQueue in java does not support remove(object) operation in log time.
        TreeMap<Integer, Integer> queue = new TreeMap<>();
        //PriorityQueue<Integer> queue1 = new PriorityQueue<>(Collections.reverseOrder());
        queue.put(0, 1);
        //queue1.add(0);
        int prevMaxHeight = 0;
        List<int[]> result = new ArrayList<>();
        for(BuildingPoint buildingPoint : buildingPoints) {
            //if it is start of building then add the height to map. If height already exists then increment
            //the value

            //START (T) CASE::::
            if (buildingPoint.isStart) {
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value != null) {
                        //HEIGHT ALREADY EXISTS SO JUST INCREMENT THE VALUE
                        return value + 1;
                    }
                    return 1;
                });
              //  queue1.add(cp.height);

            //END CASE (F) CASE:::::
            } else { 
                //if it is end of building then decrement or remove the height from map.
                queue.compute(buildingPoint.height, (key, value) -> {
                    if (value == 1) {
                        //IF HEIGHT COUNT IS 1 SO YOU CANT DECREMENT FURTHER FROM THERE, SO JUST RETURN NULL FORM HERE:
                        return null;
                    }

                    //IF THERE ARE MULTIPLE COUNT OIF TEH SMAE HEIGHT AVAILABLE JUST DECREMNT THE HEIGT COUNT FORM THERE:
                    return value - 1;
                });
               // queue1.remove(cp.height);
            }

            //peek the current height after addition or removal of building x.
            //JAVA: lastKey() is used to retrieve the last or the highest key present in the map.
            int currentMaxHeight = queue.lastKey();
            //int currentMaxHeight = queue1.peek();

            //if height changes from previous height then this building x becomes critcal x.
            // So add it to the result.

            //MAINTAIN THE PREVIOUS HEIGHT AND COURRENT HEIGHT AND IF HEIGHT CHANGES THE ADD THIS TO THE RESULT.
            if (prevMaxHeight != currentMaxHeight) {
                result.add(new int[]{buildingPoint.x, currentMaxHeight});
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int [][] buildings = {{1,3,4},{3,4,4},{2,6,2},{8,11,4}, {7,9,3},{10,11,2}};
        SkylineDrawing sd = new SkylineDrawing();
        List<int[]> criticalPoints = sd.getSkyline(buildings);
        criticalPoints.forEach(cp -> System.out.println(cp[0] + " " + cp[1]));

        /**OUTPUT::::
         *  1 4
            4 2
            6 0
            7 3
            8 4
            11 0
         */

    }
}