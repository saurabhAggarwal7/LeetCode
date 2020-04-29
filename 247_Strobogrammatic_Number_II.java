
/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.
For example, Given n = 2, return ["11","69","88","96"].
 */

import java.util.*;
class Strobogrammatic_Number_II {
        int target;

        public List<String> findStrobogrammatic(int n) {
            target = n;
            return find(n);
        }
    
        List<String> find(int n){
            List<String> res = new ArrayList<>();
            if(n == 0){
                res.add("");
                return res;
            }
            if(n == 1){
                //n=3 so n-2 =1 recursion it will run
                res.add("1");
                res.add("0");
                res.add("8");
                return res;
            }
    
            List<String> prev = find(n-2); //n=3 so recursive 

            //after recursion we get res = ['1', '0', '8']
    
            for(String s : prev){
                if(n != target) res.add("0" + s + "0");

                //run 1st itr for 1
                //run 2nd itr for 0
                //run 3rd itr for 8

                //12 numbers will be created like this
                res.add("1" + s + "1");
                res.add("8" + s + "8");
                res.add("6" + s + "9");
                res.add("9" + s + "6");
            }
            return res;
        }
}

class strobogrammatic_implement{
    public static void main(String args[]){
        Strobogrammatic_Number_II obj = new Strobogrammatic_Number_II();
        System.out.println(obj.findStrobogrammatic(3));
    }
}