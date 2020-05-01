/**
 * Given two 1d vectors, implement an iterator to return their elements alternately.For example, given two 1d vectors:
v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
My First solution is pretty straightforward: create a queue implemented by linkedlist, then add each element from vectors alternatively. By calling next(), we simply return element in the queue one by one.
The basic idea is to store the iterator of each list in a queue. If the current list is not ending (iterator.hasNext()), we simply get the current element using iterator.next(), and then put it back to the queue for next time operation.
 */

import java.util.*;
class Zig_zag_iterator {

    Queue<Integer> queue;

    //constructor:
    public Zig_zag_iterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<Integer>();
        int i = 0;
        for (i = 0; i < Math.min(v1.size(), v2.size()); i++){
            queue.add(v1.get(i));
            queue.add(v2.get(i));
        }
        if (v1.size() == v2.size()) {
            return;
        } else if (i == v1.size()) {
            queue.addAll(v2.subList(v1.size(), v2.size()));
            return;
        } else {
            queue.addAll(v1.subList(v2.size(), v1.size()));
            return;
        }
    }

    //next:
    public int next() {
        return queue.remove();
    }

    //hasNext:
    public boolean hasNext() {
        if (queue.isEmpty()) {
        return false;
        } else {
        return true;
        }
    }

    public static void main(String args[]){
        List<Integer> v1 = new ArrayList<Integer>();
        List<Integer> v2 = new ArrayList<Integer>();

        //v1:
        v1.add(1);
        v1.add(2);

        //v2:
        v2.add(3);
        v2.add(4);
        v2.add(5);
        v2.add(6);

        Zig_zag_iterator i = new Zig_zag_iterator(v1, v2);
        while(i.hasNext()) {
            System.out.println(i.next());
        }
        
    }
}