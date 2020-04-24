/**
 * Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */

import java.util.*;
 class happy_numbers_set{
    static boolean isHappy(int n) {
        //n=19
        Set<Integer> inLoop = new HashSet<Integer>();
        int squareSum,remain;

        //use outer while loop to make sure yoe exit teh infinite condition it will return false when value (n) is already in Set
        //means we already traversed for this case and we didnt get result so Lets break the loop

        //HashSet<> when add it returns the value is added
        //HasSet.add(1) returns 1
        //HashSet.add(1) return false

        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n%10; //9 //1
                squareSum += remain*remain; //81 //82
                n /= 10; 

                //Loop-1:
                //itr-1: now n=1 bcz ::19/10=1 
                //itr-2: now n=0 bcz 1/1= is 0

                //Loop-2:

            }
            if (squareSum == 1)
                return true; 
            else
                n = squareSum; //square sum is 82
    
        }
        return false;
    
    }
    public static void main(String args[]){
        System.out.println(isHappy(19));
    }
 }