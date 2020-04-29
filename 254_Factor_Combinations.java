/**
 * Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
 */

 import java.util.*;
 class factor_combinations{

    List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        //start=2, product=1, n=n, result=List<List<Integer>>, curr= List<Integer>
        helper(2, 1, n, result, list);
        return result;
    }
     
    void helper(int start, int product, int n, List<List<Integer>> result, List<Integer> curr){
        if(start>n || product > n )
            return;
     
        if(product==n) {
            ArrayList<Integer> t = new ArrayList<Integer>(curr);
            result.add(t);
            //Once the product reaches the number we are good so return from the recursion
            return;
        }   
     
        for(int i=start; i<n; i++){
            
            //overflow:
            if(i*product>n)
                break;
            
            //check if i is the factor of n then only it will be added:    
            if(n%i==0){
                curr.add(i);
                
                //recursion: for the next iteration:
                //product = i*product, start = i
                helper(i, i*product, n, result, curr);
                curr.remove(curr.size()-1);
            }
        }
    }
    
 }