/**
 * 
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */

 //SOLUTION:
 /**
  * 1. TRAVERSE THE ARRAY IN BATCHES OF 10 STARTING FROM 0TH INDEX TILL END<last 10 batch length
    2. add every string combination in Set
    3. If the combination akready rxists sin teh Set add it tto the result list
  */

 import java.util.*;
 class find_repeated_DNA_pattern{

    static List<String> find_Repeated_DNA_Sequence(String s){

        //Boundry COnditions for DNA Sequence Length:
        if(s== null || s.length() < 10){
            return Collections.emptyList();
        }

        int start = 0;
        int end =10;

        Set<String> set = new HashSet<>();
        Set<String> result = new HashSet<>();
        StringBuilder builder = new StringBuilder(s);

        //itr-1: 10<32
        while(end <= s.length()){ 
            String curr = builder.substring(start, end); //AAAAACCCCC
            if(set.contains(curr)) {
                //start=10-end20: curr : AAAAACCCCC which is already there in set so add it to the result
				result.add(curr);
			}
			set.add(curr); //add AAAAACCCCC to set
			start++; //start =1
			end++; //end =11
        }
        return new ArrayList<>(result);
    }
     public static void main(String args[]){
         System.out.println(find_Repeated_DNA_Sequence("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
     }
 }

