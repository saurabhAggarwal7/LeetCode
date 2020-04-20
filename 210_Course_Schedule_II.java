
/***
 * 
 * There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

/**
 * DIFFERECE BETWWEN THIS AND PREVIOUS ONE IS HERE WE NEED TO RETURN THE ORFER NOT THE POSSIBLITY OF BEING RETURNED:
 *
 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. 
 */

/**
 * Theory:
 * QUEUE: offer method:
 * The offer(E e) method of Queue Interface inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions. This method is preferable to add() method since this method does not throws an exception when the capacity of the container is full since it returns false.
 */

import java.util.*;

class course_Schedule {

    static int[] find_order(int numCourses, int[][] prerequisites) {
        if (numCourses == 0)
            return null;

        int indegree[] = new int[numCourses];

        //Difference: Result
        int order[] = new int[numCourses];

        //Difference:
        int index = 0;

        for (int i = 0; i < prerequisites.length; i++)
            indegree[prerequisites[i][0]]++;

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0) {

                //Difference:
                //as soon as the prereqs are completed its order in the array is incremented:
                order[index++] = i;

                // Difference: here is in queue we offer the value of i:::
                queue.offer(i);
            }
        
        while (!queue.isEmpty()) {
            int prerequisite = queue.poll(); 
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == prerequisite) {
                    indegree[prerequisites[i][0]]--;
                    if (indegree[prerequisites[i][0]] == 0) {
                        
                        //Difference: 
                        order[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }

        //Difference:
        return (index == numCourses) ? order : new int[0];
    }

    public static void main(String args[]) {
        int[][] prereqs = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }; // TRUE

        // To finish course 0 you should first fininsh 1
        // to finish course 0, first finish 2
        // to finish course 1 finish 3
        // to finish 2 first finish 3
        // 0--> Pre(1, 2)
        // 1-> Pre(3)
        // 2-> Pre(3)

        // Question here is can you complete 4 courses with these Pre req requirements
        // ??

        int num_courses = 4;
        System.out.println(find_order(num_courses, prereqs)); // TRUE
    }
}
