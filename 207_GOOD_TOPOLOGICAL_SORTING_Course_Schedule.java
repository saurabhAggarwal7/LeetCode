/**
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 

Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
 */

//THEORY:
/**
 * DEFINATION OF TOPLOGICAL SORTING:::::
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the graph is not a DAG.
For example, a topological sorting of the following graph is “5 4 2 3 1 0?. There can be more than one topological sorting for a graph. For example, another topological sorting of the following graph is “4 5 2 0 3 1″. The first vertex in topological sorting is always a vertex with in-degree as 0 (a vertex with no in-coming edges).
In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. In topological sorting, we need to print a vertex before its adjacent vertices. 
*/

//https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
//FACT: A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
/**
 * 
 * TOPOLOGICAL SORTING:::::
 * 
 * Kahn’s algorithm for Topological Sorting


Algorithm:
Steps involved in finding the topological ordering of a DAG:

Step-1: Compute in-degree (number of incoming edges) for each of the vertex present in the DAG and initialize the count of visited nodes as 0.

Step-2: Pick all the vertices with in-degree as 0 and add them into a queue (Enqueue operation)

Step-3: Remove a vertex from the queue (Dequeue operation) and then.

Increment count of visited nodes by 1.
Decrease in-degree by 1 for all its neighboring nodes.
If in-degree of a neighboring nodes is reduced to zero, then add it to the queue.
Step 5: Repeat Step 3 until the queue is empty.

Step 5: If count of visited nodes is not equal to the number of nodes in the graph then the topological sort is not possible for the given graph.

How to find in-degree of each node?
There are 2 ways to calculate in-degree of every vertex:
Take an in-degree array which will keep track of
1) Traverse the array of edges and simply increase the counter of the destination node by 1.
2) Traverse the list for every node and then increment the in-degree of all the nodes connected to it by 1.
 * 
 */

/**
 * 
 SOLUTION:::::
 BFS Solution: (Topological sorting)

The basic idea is to use Topological algorithm: put NODE with 0 indgree into the queue, then make indegree of NODE's successor dereasing 1. Keep the above steps with BFS.
Finally, if each node's indgree equals 0, then it is validated DAG (Directed Acyclic Graph), which means the course schedule can be finished.
 */

/**
 * To be done here is  prove that Course Schedule Structure is a DAG [Directed Acyclic Graph]
 * **/

import java.util.*;

class course_schedule {

    static boolean canFinish(int num_courses, int[][] prereqs) {

        // boundry condition:
        if (num_courses == 0 || prereqs.length == 0)
            return true;

        // graph --> Indegrees Adjancy List:
        int[] indegree = new int[num_courses];
        //indgree[] = [0, 0, 0, 0]
        
        //Question::         
        // 0--> Pre(1, 2)
        //1-> Pre(3)
        //2-> Pre(3) 
        //SO create a indegree array to store number of Prerequisites:::::::

        for (int i = 0; i < prereqs.length; i++) {
            // value at indegree[prereqa[1][0]] ++
            indegree[prereqs[i][0]]++;
        }

        //Couse 0 is the prerequisetes to 0 Courses
        //Course1 is prerequiseite to 1 Cousrse
        //Course2 is prerequisite to 1 course
        //Cousre 3 is the prerequiste to 2 course
        //indgree[] = {0-> 0, 1->1, 2->1, 3-> 2 }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < num_courses; i++) {
            // whenever the indegrees reach 0, add them to queue for sorting/processing
            // becuse ut's good as per topological sort algorithm
            if (indegree[i] == 0) {
                //When Prereqs are done !!
                queue.add(i);
            }
        }

        //preres[i][0]---> PREREQUISITE COURSE
        //prereq[i][1]---> ACTUAL COURSE WHICH HAS SOME PREREQ

        //BASIC IDEA IS THE QUEUE WILL HAVE ALL THE PREREQUISITES. FOR EACH PREREQUISITE FROM THE QUEUE RUN THE FORLOOP FOR ALL THE COURSES
        //STEP-1: TAKE INITIAL PREREQ FROM QUUEUE
        //-2: RUN EVERY COURSE FOR THIS REREQ, IF SAME PREREQ IS FOUND FOR ANY COURSE THEN DECREMENT INDGREE AND CHECK IF THAT PREREQ [ IS 0 SO COURSES++]
        //-3 MOVE TO THE NEXT PREREQ COURSE

        int canFinishCount = queue.size();
        while (!queue.isEmpty()) {

            //itr-1: preReQ=0
            //itr-2: preReq=1
            //itr-3: preReq=2
            
            int preReQ = queue.remove();
            for (int i = 0; i < prereqs.length; i++) {

                //itr-1: preReQ: 0, prereqs[0][1] =0 soame sp go inside
                //itr-2: preReq Still 0, prereqs[1][1] = 0 so go inside {second record course is also 0 but it's prerq is differebt}
                if (preReQ == prereqs[i][1]) {
                    // Reduce the Indgree count or the prereq count
                    // here intention is to make it to 0

                    //PREPREQ=0
                    //itr-1: i=0: prereqs[i][0] = 1 / indegree[1] =1 => 0 now
                    //itre-2: i=1, prereqs[i][0] = 2/indgree[2] = 1 => 0 now
                    //itr-3 and itr-4 <> 

                    //PREREQ=1:
                    //itr: when i=2 .....

                    //PREREQ=2
                    //itr-1<> itr-2<> 
                    //itr-3 : i= 3 prereqs[i][0] = 3, indgeree[3] =1 which becomes 0 now

                    indegree[prereqs[i][0]]--;
                    
                    //If we have completed the prereqs already 

                    //PREPREQ=0
                    //itr-1: yes it becomes 0
                    //itr-2: yes it becomes 0
                    //itr-3 and itr-4 <>

                    if (indegree[prereqs[i][0]] == 0) {

                        //PREPREQ=0
                        //itr-1: canFinishCount =2 now //priginally it was 1 because of queue.size() =1 by default
                        //itr-2: canFinishCount =3 now
                        //itr-3 and itr-4 <>


                        canFinishCount++; //can finish 1 course and so on

                        //PREPREQ=0
                        //itr-1: prereqs[0][0] = 1 so now queue will have queue {1}
                        //ir-2: prereqs[1][0] = 2 so now queue will have queue {1, 2}
                        //itr-3 and itr-4 <>
                        
                        queue.add(prereqs[i][0]); //add in queue the preq has been finished so that we can reduce it's indegree
                    }
                }
            }
        }

        // Queue size should be same as num_courses
        return (canFinishCount == num_courses);
    }

    public static void main(String args[]) {
        // int[][] prereqs = {{1,0},{0,1}}; //NO
        int[][] prereqs = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }; //TRUE

        //To finish course 0 you should first fininsh 1
        //to finish course 0, first finish 2
        //to finish course 1 finish 3
        //to finish 2 first finish 3
        // 0--> Pre(1, 2)
        //1-> Pre(3)
        //2-> Pre(3)

        //Question here is can you complete 4 courses with these Pre req requirements ??

        int num_courses = 4;
        System.out.println(canFinish(num_courses, prereqs)); // TRUE
    }
}
