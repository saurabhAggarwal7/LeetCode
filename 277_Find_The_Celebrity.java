// Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
//The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

// Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

// You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

// Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

/**EXPLAINATION:
 * knows(a, b): it means person a knowns person b
 * That means:
 * 
 * 1. if a knows b then a cant be a celebrity
 * 2. if b doesnt know a then b can be celebrity
 */

class Relation{
    boolean knows(int a, int b){
        return true;
    }
}

class Find_The_Celebrity extends Relation{
    int findCelebrity(int n) {

        //initialize candidate to 0
        int candidate = 0;
        
        //find viable candidate
        for(int i = 1; i < n; i++) {
            if(knows(candidate, i)) {
                //if candidate knows i then i can be the celebrity
                candidate = i;
            }
        }

        //Now candidate is (i)--> this may be a celebrity now check if it's really a celebrity ??

        //FOR THE FOLLOWING LOOP THE CELEBRITY IS THE CANDIDATE THAT IS CELEBRITY --> <CANDIDATE>
        
        //check that everyone else knows the candidate
        for(int j = 0; j < n; j++) {
            //if the candidate knows the current person or the current person does not know the candidate, return -1 (candidate is not a celebrity)
            if(j != candidate && knows(candidate, j) || !knows(j, candidate)) {
                //NO POINT IN COMPARING ITSLEF (j!=cadidate)
                //AND celebrity knows person J ---->> (knows(candidate, j))

                //OR

                //Any person doesnot know celebrity ---->>  !knows(j, candidate)
                //then return -1 that there is no celebrity

                return -1;
            }
        }
        
        //return the celebrity
        return candidate;
    }
}