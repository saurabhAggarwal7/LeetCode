import java.util.*;

/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

 
Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]*/

class queue_reconstruction{

    static int[][] reconstructionQueue(int[][] people){
        
        //create a list of Type Array (Not Integer or String or anything else)

        //[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
        List<int[]> list=new ArrayList();
        for(int[] a: people)
            list.add(a);
        
        //sort by h for unequal heights:
        //if two people have equal height then compare their k values thats why its [1] and [0] taken:
        Collections.sort(list, (a,b)-> (a[0] !=b[0]) ? -Integer.compare(a[0], b[0]): Integer.compare(a[1], b[1]));
        
        //after sorting [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]] becomes following:
        /*[[7,0], [7,1], [6,1], [5,0]. [5,2], [4,4]]*/
        //result is : [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]] 
        //remember h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h
        for(int i=0;i<list.size();i++){

            //// k is number of people in front of peole with height greater than or equal to h
            int[] e = list.get(i); // e= [4,4] h-->>e[0] = 4 k-->>e[1]=4 ; 
            if(e[1] < i){ //k--> e[1] = 1, i = 2 then this consition is true
                list.add(e[1], e);
                list.remove(i+1);
            }
        }
        return list.toArray(people);
    }

    //utility function to print the list of arrays like the following:
    //[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
    static void print(List<int[]> list){
        //loop an array a:
        for(int[] a: list){
            System.out.println(Arrays.toString(a) + ",");
        }
    }

    public static void main(String args[]){
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] arr = reconstructionQueue(people);
        System.out.println(arr);
    }

}