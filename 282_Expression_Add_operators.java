
/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 

Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []

This problem has a lot of edge cases to be considered:

overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.

a little trick is that we should <<save the value that is to be multiplied in the next recursion.>>


However, adding String is extremely expensive. Speed can be increased by 20% if you use StringBuilderinstead, that will be better optimization.

Before: 238ms (67.31%)
Now: 189ms (96.56%)

many people don't realize the essence of backtrack is sb.append().append() and then sb.setLength(len). 
The author of this solution avoided these operations by string "+" operation which creates a new copy of original string to proceed further recursion path and then there is no need to reset string after recursion returns.

Both your solution and yanvinci's soultion are using substring, which will create a new String every time. 

The reason is that String is immutable in Java. Also, I use char[] num instead of String num, because if you are using the latter, everytime you get the char at a certain index, 
Java will do the range check for the index which is already checked in our code and is not necessary to check again!


 */

 /**
  * TRICKS TO MAKE CODE FAST::
  -- STRING BUILDER
  --AVOID USING SUBSTRING
  --USE CHAR[] INDEXED ALREADY BY JAVA
  */
  
import java.util.*;
class Expression_Add_operators {

    static List<String> addOperators(String num, int target) {

        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        //dfs:
        dfs(res, sb, num.toCharArray(), 0, target, 0, 0);
        return res;
    
    }
    static void dfs(List<String> res, StringBuilder sb, char[] num, int pos, int target, long prev, long multi) { 
        //itr-1: res=<>, sb=<>, char num[] = [1,2, 3], pos =0, target=6, prev =0, multi=0
        //itr-2: res=<>. sb=[49, 0, ....0] becuase sb.append(1), curr was 1, num same, pos=1, traget same, prev=1 which was curr, multi is also curr

        //WHEN ITERATION ITR POS==LENGTH OF THE NUM ARR[]
        if(pos == num.length) {

            if(target == prev) {
                //AIM IS TO MAKE TARGET AND PREVIOUS SAME 

                //"1+2+3": sb == //0:49, 1:43, 2:50, 3:43, 4:51 
                res.add(sb.toString());
            }
            return;
        }

        long curr = 0;
        for(int i = pos; i < num.length; i++) {

            if(num[pos] == '0' && i != pos) 
                break;
            
            curr = 10 * curr + num[i] - '0';
            int len = sb.length();

            if(pos == 0) {
                //FIRST TIME : so that opertaor is appended afterwards 
                dfs(res, sb.append(curr), num, i + 1, target, curr, curr); 
                sb.setLength(len);
            } else {

                //itr-2: curr=2, i=1, {prev = {prev+curr}, {traget = curr}

                dfs(res, sb.append("+").append(curr), num, i + 1, target, prev + curr, curr); 
                sb.setLength(len);

                //{prev=prev-curr} {multi=-curr}
                dfs(res, sb.append("-").append(curr), num, i + 1, target, prev - curr, -curr); 
                sb.setLength(len);

                //{prev=prev - multi + multi * curr}
                //{multi=multi * curr}
                dfs(res, sb.append("*").append(curr), num, i + 1, target, prev - multi + multi * curr, multi * curr); 
                sb.setLength(len);
            }
        }
    }

    public static void main(String args[]){

        //123, target = 6 Output: ["1+2+3", "1*2*3"] 
        System.out.println(addOperators("123", 6));

    }
}