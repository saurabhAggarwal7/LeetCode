/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */

 //IMPORTANT THING TO NOTE HERE IS HOW DO WE HANDLE NULL CASES:::

import java.util.Iterator;
import java.util.NoSuchElementException;

 class Peeking_Iterator implements Iterator<Integer> {

    Integer next;
    Iterator<Integer> itr;
    boolean noSuchElement;

    //constructor:
    public Peeking_Iterator(Iterator<Integer> itr){
        this.itr = itr;
        advance_itr();
    }

    //return the next e;lement in itr without actually advancing to the next:
    public Integer peek(){
        return next;
    }

    private void advance_itr(){
        if(itr.hasNext()){
            next = itr.next();
        } else{
            noSuchElement = true;
        }
    }

    @Override
    public Integer next(){
        if(noSuchElement)
            throw new NoSuchElementException();

        Integer res = next;
        advance_itr();
        return res;
    }

    @Override
    public boolean hasNext(){
        return !noSuchElement;
    }

    public static void main(String args[]){
        //
    }
}