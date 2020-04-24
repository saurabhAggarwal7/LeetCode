/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,

add(1); 
add(3); 
add(5);
find(4) -> true
find(7) -> false
 */

 import java.util.*;
 class two_sum_design_III {


    static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    static int add(int num){
        if(map.containsKey(num)){
            //increment the value +1 becuase valus is the count here
            
            //same number inserted twice, increment the count.
            //duplicates: (3,3)
            map.put(num, map.get(num)+ 1); 
        } else{
            map.put(num, 1);
        }
        return num;
    }

    static boolean find(int num){
        for(Integer i: map.keySet()){

            //find - Find if there exists any pair of numbers which sum is equal to the value.
            //Since it's two sum so get the target by subtracting its partner

            //TO-DO:::are there two such keys in map whose sum is equak to value to be found ??
            //First we need to get those two numbers {one will be by traversing all keys and opther by subtracting from the sum}
            
            //Check for duplicates case, i-e 3,3 then count has to be more than 1 i-e i==Traget (3, 3) and count>2 for this case specially

            int target = num - i;
            if(map.containsKey(target)){
                if(i == target && map.get(target) < 2){
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    public static void main (String args[]){
        System.out.print(add(1));
        System.out.print(add(3));
        System.out.print(add(5));
        System.out.print(find(4)); //True becuase theree are two elemensts 3+1 = 4 so it shoudl exists
        System.out.print(find(7));
    }

 }