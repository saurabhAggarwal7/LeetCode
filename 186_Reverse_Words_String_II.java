/**
 * reverse-words-in-a-string-ii
 * 
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

 */

 //SOLUTION:
 //https://www.geeksforgeeks.org/print-words-string-reverse-order/

 /*Input : I AM A GEEK
Output : GEEK A AM I

Input : GfG IS THE BEST
Output : BEST THE IS GfG*/


/*
Approach: Traverse the string from the last character, and move towards the first character. While traversing, if a space character is encountered, put a NULL in that position and print the remaining string just after the NULL character. Repeat this until the loop is over and when the loop ends, print the string, the %s will make the printing of characters until it encounters the first NULL character.
step 1: Traverse from the last character until it encounters a space character .
Step 2: Put a NULL character at the position of space character and print the string after it.
Step 3: At the end, the loop ends when it reaches the first character, so print the remaining characters, it will be printed the first NULL character, hence the first word will be pprinted.
*/


class reverse_string_blanks_II{
    static String word_reverse(String str){
        
        //traverse from end
        int i=str.length() -1; //i=15
        int start;
        int end = i+1; //16
        String result ="";

        while(i>=0){
            if(str.charAt(i) == ' '){

                //Initially:: i=str.length() -1; end = i+1;

                //itr-1: //i=10 //start=11, end=16
                //itr-2: //i=5  //start=6, end=10
                start = i+1;
                while(start != end){
                    result+= str.charAt(start++);
                }

                //itr-1: WORLD<space>
                //itr-2: WORLD<space>GOOD<space>
                result += ' '; 
                end = i; //end = 10 with respect to last reversed word
            }
            i--; //no space found traverse towards starting
        }

        //After this while loop ends we get the result as WORLD<space>GOOD<space>
        //Now we need to add HELLO also so, loop through using one more while loop as well:

        //for HELLO:
        start =0;
        while(start != end){
            result+= str.charAt(start++);
        }

        //WORLD GOOD HELLO
        return result;
    }

    public static void main(String args[]){
        String str = "HELLO GOOD WORLD"; //WORLD GOOD HELLO
        System.out.println(word_reverse(str));

    }
}