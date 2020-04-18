/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

//This is a search problem, and breath-first search guarantees the optimal solution.

import java.util.*;

class WordNode{
    String word;
    int num_steps;

    public WordNode(String word, int num_steps){
        this.word = word;
        this.num_steps = num_steps;
    }
}

class word_ladder{

    static int ladderLength(String beginWord, String endWord, Set<String> wordDict){
        
        //queue: LL
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));

        //wordDict.add(endWord);
        //Boundry Condition: dont compute uneccessary:
        if(!wordDict.contains(endWord))
            return 0;
        
        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
            int num_steps = top.num_steps;

            if(word.equals(endWord)){
                return num_steps;
            }

            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<= 'z'; c++){
                    char temp = arr[i];
                    if(arr[i] != c){
                        arr[i] = c;
                    }
                    String newWord = new String(arr);
                    if(wordDict.contains(newWord)){
                        queue.add(new WordNode(newWord, num_steps+1));
                        wordDict.remove(newWord);
                    }
                    arr[i] = temp;
                }
            }
            //for loop ends here
            
        }
        return 0;
    }




    public static void main(String args[]){
        /**
         * start = "hit"
            end = "cog"
            dict = []
            One shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the program should return its length 5.
         */
        String [] arr = {"hot","dot","dog","lot","log","cog"};
        Set<String> dict = new HashSet<String>(Arrays.asList(arr));
        System.out.println(ladderLength("hit", "cog", dict));
    }
}