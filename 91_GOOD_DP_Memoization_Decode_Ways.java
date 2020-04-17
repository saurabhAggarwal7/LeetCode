//DP + Memoization

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

//SIMILAR QUESTIONS:

/*
62. Unique Paths
70. Climbing Stairs
509. Fibonacci Number
*/

//THEORY:
/*
public String substring(int begIndex, int endIndex)
Parameters : 
beginIndex :  the begin index, inclusive.
endIndex :  the end index, exclusive.

226 substring(1,3) is 26 which is first 2 elements excluding the last one
*/

class decode_ways {

    // memoization
    static int decode_ways(String s){
        int n = s.length(); //3
        if(n==0)
            return 0;
        
        int[] memo = new int[n+1]; //{-, -, -, -}
        memo[n] = 1; //memo[3] = 1 {-, -, -, 1}

        //boundry condition: memo[3-1] = 0/1 just in case its 0
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        //memo[2] = s.charAt(2) //226 so its not 0 so it is 1

        //At this time is  memo={-, -, 1, 1}

        //DP : we try to find the previous states number of ways
        //we already know i=n-1 so we start with i=n-2
        
        //i=n-2=0 for us here

        //{this loop wil run for i=1, i=0 } we already know the values at [i=2 and i=3]
        for(int i=n-2; i>=0;i--){
            if(s.charAt(i) == '0')  //it's not 0 we are good
                continue;
            else{

                if(Integer.parseInt(s.substring(i, i+2)) <=26){
                    //s.substring(1, 3) => 26 from string 226
                    //s.subtstring(0, 2) => 22 which is less tha 26
                    memo[i] = memo[i+1]+memo[i+2];
                    //itr-1: memo[1] = memo[2] + memo[3] = 2
                    //itr-2: memo[0] = memo[1] + memo[2] = 3
                } else{
                    //Not a vlaid case so give the same value as previous value
                    memo[i] = memo[i+1];
                } 
        }
    }
    return memo[0]; //After traversing from back you get all the results as the combination from the previous states so we return memo[0 here]
    }

    public static void main(String args[]) {
        System.out.println(decode_ways("226"));
    }
}