import java.util.*;

/*Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity )*/ 
/*
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4*/

/*
add is part of List interface as well as the Stack, but you should to notice the further readability of your code and your intentions in it by other programmers. push method will give them a clue that they're using the Stack object, 
*/

//Technique:

/*
create map (key, value)
create stack which will always keep the key for the latest entry so that when capacity is reached it is easy to get the key to be removed quickly
so main work is done in map and the quick reference is done from the stack
*/

class LRUCache {

    //-1
    //1
    //capacity reached, evits recent entry

    private int capacity = 0;
    private int current_capacity = 0;
    private Stack<Integer> stack = null;
    private Map<Integer, Integer> map = null;

    public LRUCache(int capacity) {
        this.capacity   =   capacity;
        this.stack      =   new Stack<>();
        this.map        =   new HashMap<>();
        //no need to do for curre capacity else how would you maintain the count there
    }
    
    public int get(int key) {
        if(this.map.containsKey(key)){
            stack.remove(key);
            stack.push(key);
        }
        int value  = print_value(key);
        return value;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)){
            stack.remove(key);
            stack.push(key);
            map.put(key, value);
        } else{
            if (current_capacity == capacity){
                int recent_key = stack.firstElement();
                map.remove(recent_key);
                stack.remove(key);
                stack.add(key);
            } else{
                current_capacity ++;
                stack.add(key);
            }
            map.put(key, value);
            print_value(key);
        }
    }

    public int print_value(int key){
        int value = this.map.getOrDefault(key, -1);
        System.out.print(value);
        return value;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4*/
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
