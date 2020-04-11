/*
https://leetcode.com/problems/cheapest-flights-within-k-stops/

There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles. */

//Solution is based and implementation of Diskejtras Algorithm:
//edges = [[0,1,100],[1,2,100],[0,2,500]]
//0--> 1 is 100
//1--> 2 is 100
//0 --> 2 is 500

//The price of each flight will be in the range [1, 10000].

import java.util.*;

class cheapeast_Priority_Dijkstras{

    //flights: [[0,1,100],[1,2,100],[0,2,500]]
    static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k){

        //Step-1: Create a mao with the structure of the Graph:
        //{key} = city
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();

        for(int[] f: flights){
            //{key} is the city A, B, C (0/1/2)
            // iteration-1: f= [0, 1, 100] so f= 0 which means city A (0)

            if(!prices.containsKey(f[0])){
                prices.put(f[0], new HashMap<>());
            }
            //{value}
            prices.get(f[0]).put(f[1], f[2]);

        }

        //AFTER THE MAP: {key}-->City and {value} is adjoining connections
        /*
        map-> 
        {0}-> key{0}, //CITY A
              value: key1, value100 ----- this means for CityA, to City B it takes 100$
              value: key2, value500 ----- this means for CityA, to City C
         
        {1} -> Key{1} //CITY B
               value: key2, value100 ------ this means for CITYB, to CITY C, it takes 100s
        
         */

        //Create a Priority Queue:
        //with priority given as per comparater defined:
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        
        //{price/city/stops}
        //price by default = 0
        //src= 0//CITYA
        //k-- number of stops == 0 as per us

        pq.add(new int[] {0, src, k+1});

        while(!pq.isEmpty()){

            //remove the prioity queue array which as 3 elements: {price [0], city [1], stops [2]}
            int[] top = pq.remove();

            int price = top[0];  
            int city = top[1];
            int stops = top[2];

            //if destination is reached return the computed price during the Journey:
            //if (city(0) == dst(2)) //Loop till we get city as desitinationmeans City C here then we will return the price
            if(city == dst)
                return price;
            
            //by default if k=0 even then this code block will run atleast once. beacuse of stops = k+1    
            if(stops > 0){

                //Thsi map is the multiple values stored by teh CITY map i-e the Value from the Nested map as defined above:
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                //Disjectras Algo find for the adj cities
                for (int a : adj.keySet()) {

                    //Adjacent cities distances from City (Disjectras Algo)

                    //Price = price + adj,get(a) //price + price for that city('a')
                    //city = a
                    //stops = stops -1 //every iterative stops are decremented
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }

    public static void main(String args[]){
        //Test Case: 

        //There are n cities 

        //GRAPH:
        //n = A, B, C cities
        //A-> 0, B-> 1, C->2
        //A->B takes 100 price
        //B->C takes 100 price
        //A-> C takes 500

        /*n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
        src = 0, dst = 2, k = 0
        Output: 500 */

        //Src-> City A
        //Dest City C
        //Stops (k) = 0 , no stop so no Via In this case:

        int n= 3;
        int src = 0;
        int dest = 2;
        int k =0;
        int[][] flight = {{0,1,100},{1,2,100}, {0,2,500}};
        //Output: 500

        int result = findCheapestPrice(n, flight, src, dest, k);
        System.out.println(result);
    }

}