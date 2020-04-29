/**
 * This is a follow-up problem of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.
 */

 class word_shortest_distance_duplicates{

    
     static int shortestWordDistance_2(String[] words, String word1, String word2) {
        if(words==null||words.length==0)
            return -1;
     
        if(word1==null || word2==null)
            return -1;
     
        boolean isSame = false;
     
        if(word1.equals(word2))
            isSame = true;
     
        int shortest= Integer.MAX_VALUE;
     
        int prev=-1;
        int i1=-1;
        int i2=-1;
     
        for(int i=0; i<words.length; i++){
            if(isSame){
                if(words[i].equals(word1)){
                    if(prev!=-1){
                        shortest=Math.min(shortest, i-prev);
                    }
                    prev = i; 
                    //i=1 makes so prev=1 first reference
                    // and after that i=4 so shortest becomes 1-4 = 3
                    //CASE: makes and makes
                    //{"practice", "makes", "perfect", "coding", "makes"};
                }
            }else{
                //CASE: makes and coding
                //{"practice", "makes", "perfect", "coding", "makes"};
                if(word1.equals(words[i])){
                    i1=i; //i1= 1
                    if(i2!=-1){
                        shortest = Math.min(shortest, i-i2); //here also it comes to find the min value
                    }
                }else if(word2.equals(words[i])){
                    i2=i; //i2 = 3
                    if(i1!=-1){
                        shortest = Math.min(shortest, i-i1); //here also it comes to find teh min value
                    }
                }
            }
        }   
     
        return shortest;
    }

    static int shortestWordDistance_1(String[] words, String word1, String word2) {
        if(words==null || words.length<1 || word1==null || word2==null)
             return 0;
      
        int m=-1;
        int n=-1;
      
        int min = Integer.MAX_VALUE;
        int turn=0;
        if(word1.equals(word2))
             turn = 1;
      
        for(int i=0; i<words.length; i++){
             String s = words[i];
             if(word1.equals(s) && (turn ==1 || turn==0)){
                 m = i;
                 if(turn==1) turn=2;
                 if(n!=-1)
                     min = Math.min(min, m-n);
             }else if(word2.equals(s) && (turn==2 || turn==0)){
                 n = i;
                 if(turn==2) turn =1;
                 if(m!=-1)
                     min = Math.min(min, n-m);
             }
        }
      
        return min;
     }


     public static void main(String args[]){
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        shortestWordDistance_2(words, "makes", "makes");

        shortestWordDistance_2(words, "makes", "coding");
     }
 }