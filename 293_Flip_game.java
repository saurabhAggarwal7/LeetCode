/**
 * /*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
Write a function to compute all possible states of the string after one valid move.
For example, given s = "++++", after one move, it may become one of the following states:
[
  "--++",
  "+--+",
  "++--"
]
*/

import java.util.*;
class Flip_Game {
    static List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<String>();
     
        if(s==null)
            return result;
     
        char[] arr = s.toCharArray();
        for(int i=0; i<arr.length-1; i++){
            if(arr[i]==arr[i+1] && arr[i]=='+'){

                //SET
                arr[i]='-';
                arr[i+1]='-';

                //RESULT:
                result.add(new String(arr));

                //RESET
                arr[i]='+';
                arr[i+1]='+';
            }
        }
     
        return result;
    }

    public static void main(String args[]){
        System.out.println(generatePossibleNextMoves("++++"));
    }
}