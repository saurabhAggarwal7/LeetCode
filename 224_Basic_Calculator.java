/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/

//SOLUTION::::

/**
 * Principle:
 * 
 * (Sign before '+'/'-') = (This context sign); (Sign after '+'/'-') = (This
 * context sign) * (1 or -1); Algorithm:
 * 
 * Start from +1 sign and scan s from left to right; if c == digit: This number
 * = Last digit * 10 + This digit; if c == '+': Add num to result before this
 * sign; This sign = Last context sign * 1; clear num; if c == '-': Add num to
 * result before this sign; This sign = Last context sign * -1; clear num; if c
 * == '(': Push this context sign to stack; if c == ')': Pop this context and we
 * come back to last context; Add the last num. This is because we only add
 * number after '+' / '-'.
 */

import java.util.*;
class calc {

    static int calculate(String s) {
        if (s == null)
            return 0;

        int result = 0;

        int sign = 1;

        int num = 0;

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(sign);

        for (int i = 0; i < s.length(); i++) {

            //(
            //1
            //+
            //(
            //4
            //+
            //5
            //+
            //2
            //)        
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {

                //1, num = 0 + 1- '0' = 1, stack=1,1
                num = num * 10 + (c - '0');

            } else if (c == '+' || c == '-') {

                //+, result=1, sign=1, back to num=0, acual calcualte the result here by addition::
                result += sign * num; 
                sign = stack.peek() * (c == '+' ? 1 : -1);
                num = 0;

            } else if (c == '(') {

                //(, stack = 1, sign =1
                stack.push(sign);

            } else if (c == ')') {
                stack.pop();
            }
        }

        result += sign * num;
        return result;
    }

    public static void main(String args[]){
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}