/**
 * 
 * Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.
 */

 class is_Isomorphic{

    static boolean is_Isomorphic(String s, String t){
        
        //Initially charCode 128 spaces marked as 0
        //<1-128 is 0
        int[] map = new int[128];
        int[] book = new int[128];


        for(int i=0; i< s.length(); i++){

            //paper:
            //i=0, 112, i=1: 97, i:2: 101, i:3: 114
            int cs = (int)s.charAt(i);
            
            //title:
            //i=0, 116, i=1: 105, i:2: 108, i:3: 101
            int ts = (int)t.charAt(i); 

            if(map[cs] == 0 && book[ts] ==0){

                //map[s1CharCode] = s2CharCode
                //map[112] = 116, 
                //map[97] = 105
                //map[101] = 108
                //map[114] = 101
                map[cs] = ts; 

                //book[s2CharCode] = 1
                //book[116] = 1, 
                //book[105] = 1
                //book[108] = 1
                //map[101] = 1
                book[ts] = 1; 

            } else if(map[cs] != ts){

                //FOO BAR CASE:
                //here debug will come because of oo
                //f-> t, <<o->a, o-> r>> Here issue will come due to map mismatch value
                return false;
            }
        }

        //while loop completely traversed without any issue so TRUE return::
        return true;
    }
     public static void main(String args[]){
        //System.out.println(is_Isomorphic("paper", "title")); //TRUE
        System.out.println(is_Isomorphic("foo", "bar")); //FALSE

     }
 }