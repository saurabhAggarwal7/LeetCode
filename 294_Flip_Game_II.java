/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.


For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

This problem is solved by backtracking.

 */

 /*
 ++++
 --++p1
 ----p2
 --++p1
 ++++p2
 +--+p1
 p2<> nothing to do, p1 wins !!

 ++++
 
 */
class Flip_Game_II {

    static boolean canWin(String s) {
        if(s==null||s.length()==0){
            return false;
        }
     
       return canWinHelper(s.toCharArray()); 
    }
     
    static boolean canWinHelper(char[] arr){
        for(int i=0; i<arr.length-1;i++){
            if(arr[i]=='+'&& arr[i+1]=='+'){

                //P1:: after recusrsion P2
                arr[i]='-';
                arr[i+1]='-';
                
                //++++ start input

                //--++
                //then recursion so it becomes ----
                //due to this it wont now enter into if statement bcz all are ---- which results in win=false but return true, so recursion step over
                //arr values again reset to + so now arr becomes --++
                
                //backtrack to previous recusrion step and now again rest values so ++++ and win=false, so wont return and will continue in loop
                //+--+ is the new state now because i++ and the -- is made from i=1
                //this case again rescursion so arr becomes ++++ win-false, return true

                
                boolean win = canWinHelper(arr);
                
                
                arr[i]='+';
                arr[i+1]='+';
     
                //if there is a flip which makes the other player lose, the first play wins
                if(!win){
                    return true;
                }
            }
        }
     
        return false;
    }

    public static void main(String args[]){
        System.out.println(canWin("++++"));
    }
}