//Given two strings S and T, determine if they are both one edit distance apart.

/*abstractInput:  s1 = "geeks", s2 = "geek"
Output: yes
Number of edits is 1

Input:  s1 = "geeks", s2 = "geeks"
Output: no
Number of edits is 0

Input:  s1 = "geaks", s2 = "geeks"
Output: yes
Number of edits is 1

Input:  s1 = "peaks", s2 = "geeks"
Output: no
Number of edits is 2

*/

//SOLUTION::
/**
 * An Efficient Solution is to simultaneously traverse both strings and keep track of count of different characters. Below is complete algorithm.

Let the input strings be s1 and s2 and lengths of input 
strings be m and n respectively.

1) If difference between m an n is more than 1, 
     return false.
2) Initialize count of edits as 0.
3) Start traversing both strings from first character.
    a) If current characters don't match, then
       (i)   Increment count of edits
       (ii)  If count becomes more than 1, return false
       (iii) If length of one string is more, then only
             possible  edit is to remove a character.
             Therefore, move ahead in larger string.
       (iv)  If length is same, then only possible edit
             is to  change a character. Therefore, move
             ahead in both strings. 
    b) Else, move ahead in both strings. 
 */

 class one_Edit_distance{
    // Returns true if edit distance 
    // between s1 and s2 is one, else false 

    static boolean isEditDistanceOne(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        if(Math.abs(m-n) > 1)
            return false;
        
        //result couny of ediits:    
        int count = 0;

        int i=0;
        int j=0;
        while(i<m && j<n){
            if(s1.charAt(i) != s2.charAt(j)){
                if(count ==1)
                    return false;

                // If length of one string is more, then only possible edit is to remove a character 
                
                //s1 is greater string thab s2
                if(m> n)
                    i++;
                //s2 is greater string than s2:
                else if(m<n)
                    j++;
                else
                    i++; j++;

                //whatever maybe but it incraese the counts
                count++;
            } else{
                //if characters match then :
                i++;
                j++;
            }
        }

        if(i<m || j<n)
            count++;

        return count ==1;
    }
    public static void main(String args[]){
        String s1 = "geaks";
        String s2 = "geeks";

        System.out.println(isEditDistanceOne(s1, s2));
    }
    
 }