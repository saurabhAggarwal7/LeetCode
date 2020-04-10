/*Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
] */

import java.util.*;
class Fizz_Buzz_solution{

    //NAIVE Solution:
    public List<String> fizzBuzz_simple(int n) {
        // ans list
        List<String> ans = new ArrayList<String>();
    
        for (int num = 1; num <= n; num++) {
    
          boolean divisibleBy3 = (num % 3 == 0);
          boolean divisibleBy5 = (num % 5 == 0);
    
          String numAnsStr = "";
    
          if (divisibleBy3) {
            // Divides by 3, add Fizz
            numAnsStr += "Fizz";
          }
    
          if (divisibleBy5) {
            // Divides by 5, add Buzz
            numAnsStr += "Buzz";
          }
    
          if (numAnsStr.equals("")) {
            // Not divisible by 3 or 5, add the number
            numAnsStr += Integer.toString(num);
          }
    
          // Append the current answer str to the ans list
          ans.add(numAnsStr);
        }
    
        return ans;
      }

      
}