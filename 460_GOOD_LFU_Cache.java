/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Note that the number of times an item is used is the number of calls to the get and put functions for that item since it was inserted. This number is set to zero when the item is removed.

 

Follow up:
Could you do both operations in O(1) time complexity?

 

Example:

LFUCache cache = new LFUCache( 2 /* capacity) */
/*

MAX CAPACITY is 2
When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. 
For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.


cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1

<CACHE = 1, 2>

cache.put(3, 3);    // evicts key 2, bcz most recent frequency of use of 2 was 0 and of 1 is 1

<CACHE = <3, 1>

cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.

cache.put(4, 4);    // evicts key 1, bcz most recent frequency =1 and also 3=1. Tie is there so in this least recently used means old one is evicted

<CACHE = <4,3>

cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
 */

import java.util.*;

//WHY LINKEDLIST :: (all nodes connected in same double linked list has same frequency)
class LFU_cache {

    class ListNode {
        int key;
        int val;
        int frequency; // frequency count of current node
        ListNode prev;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.frequency = 1;
        }
    } // end of class listNode

    // Generic Data Structure of a DL Linked List
    class DoubleLinkedList {

        int listSize; // current size of double linked list
        ListNode head; // head node of double linked list
        ListNode tail; // tail node of double linked list

        public DoubleLinkedList() {
            this.listSize = 0;

            // head and tail init with is a new node with key and value {0,0}
            this.head = new ListNode(0, 0);
            this.tail = new ListNode(0, 0);

            // relations defined with pointers back and front
            head.next = tail;
            tail.prev = head;
        }

        // Utility method-1: addNode
        /** add new node into head of list and increase list size by 1 **/
        public void addNode(ListNode currNode) {

            // what is head's next currently ?
            ListNode nextNode = head.next;

            currNode.next = nextNode;
            currNode.prev = head;

            // now that we have moved forward so do the head sould as well
            head.next = currNode;

            // it means the pointer which was earlier prev is now currNode
            nextNode.prev = currNode;

            //increase the size of list as well on new added node
            listSize++;
        }

        // Utility method-2: removeNode
        /** remove input node and decrease list size by 1 **/
        public void removeNode(ListNode currNode) {

            // who are next and previous nodes of the current nodes
            ListNode prevNode = currNode.prev;
            ListNode nextNode = currNode.next;

            // remove in between node so the prev's next is current's next
            prevNode.next = nextNode;

            // next's prev is now current's prev
            nextNode.prev = prevNode;
            listSize--;
        }

        // Utility method-3: removeTail:
        /** remove tail node **/
        public ListNode removeTail() {
            // check for listSize it's very important:
            if (listSize > 0) {

                // TAIL NODE IS SOMETHING TAIL'S PREVIOUS NODE
                ListNode tailNode = tail.prev;
                removeNode(tailNode);
                return tailNode;
            }
            return null;
        }

    }

    // capacity: total capacity of LFU Cache
    final int capacity;

    // curSize: current size of LFU cache
    int currSize;

    // minFrequency: frequency of the last linked list (the minimum frequency of
    // entire LFU cache)
    int min_frequency;

    // a hash map that has key to Node mapping, which used for storing all nodes by
    // their keys
    Map<Integer, ListNode> cache;

    // a hash map that has key to linked list mapping, which used for storing all
    // double linked list by their frequencies
    Map<Integer, DoubleLinkedList> frequencyMap;

    public LFU_cache(int capacity) {
        this.capacity = capacity;
        this.currSize = 0;
        this.min_frequency = 0;

        // cache and frequency map::
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    // METHOD-1:: PUT Method:
    /**
     * add new node into LFU cache, as well as double linked list condition 1: if
     * LFU cache has input key, update node value and node position in list
     * condition 2: if LFU cache does NOT have input key - sub condition 1: if LFU
     * cache does NOT have enough space, remove the Least Recent Used node in
     * minimum frequency list, then add new node - sub condition 2: if LFU cache has
     * enough space, add new node directly
     **/
    public void put(int key, int value) {
        // corner case: check cache capacity initilaziation:

        //itr-1: key and value = 1
        //itr-2: key and value = 2
        if (capacity == 0)
            return;

        if (cache.containsKey(key)) {
            ListNode currNode = cache.get(key);
            currNode.val = value;
            updateNode(currNode);

        } else {

            //itr-1: key and value = 1 CurSize++
            //itr-2: key and value = 2 CurSize++
            currSize++;

            // Sub-condition if overflow occurs:
            if (currSize > capacity) {

                //itr-3: remove {tail node of list with min_frequency}
                //List 0->1->2[TAIL]

                // LinkedList will have key as frequencies Remember that:
                // Get {value} from map which has the {key} = min_Frequency
                DoubleLinkedList minFrequencyList = frequencyMap.get(min_frequency);

                // whatever {value} from map or the LinkedList we get we tend to remove it's
                // tail {as per least frequently used removed node logic, less frequency go out
                // !!}
                ListNode deleteNode = minFrequencyList.removeTail();

                // Now that the listnode is removed, remove this key from the cache value as
                // well;
                cache.remove(deleteNode.key);
                currSize--;
            }

            // reset min frequency to 1 because of adding new node

            //itr-1: key and value = 1 
            //itr-2: key and value = 2 
            min_frequency = 1;

            // {key and value} from input
            ListNode newNode = new ListNode(key, value);

            // get the list with frequency 1, and then add new node into the list, as well
            // as into LFU cache

            //itr-1: key and value = 1 DEFAULT LIST KEY=0, NEXT AND PREV=0 

            //itr-2: key and value = 2 currlist already has 1 in it add 2 also
            //CURR LIST NOW IS 0-> 2-> 1

            //FREQUENCY MAP CONTAINS DIFFERENT KEYS WITH EACH AS NEW FREQUENCY LIKE 1, 2 ETC
            //PULL OUT THE LINKEDLIST WITH FREQUENCY EQUALS 1 ELSE BY DEFAULT PULL OUT 
            DoubleLinkedList currList = frequencyMap.getOrDefault(1, new DoubleLinkedList());
            currList.addNode(newNode);

            //{key=1} put the list along side this
            //itr-2: Frequency-map: {1, 0->1->2}
            frequencyMap.put(1, currList);
            cache.put(key, newNode);
        }
    }

    // METHOD-0:: GET METHOD:
    /**
     * get node value by key, and then update node frequency as well as relocate
     * that node
     **/
    public int get(int key) {
        ListNode curNode = cache.get(key);
        if (curNode == null) {
            return -1;
        }
        updateNode(curNode);
        return curNode.val;
    }

    // METHOD-2:: UPDATE METHOD:
    public void updateNode(ListNode currNode) {
        int currNode_frequency = currNode.frequency;

        // Retrive the currNode using Kye as it's frequency from the LinkedList
        DoubleLinkedList currList = frequencyMap.get(currNode_frequency);
        currList.removeNode(currNode);

        // BOUNDRY CONDITION:
        // if current list is the last list which has lowest frequency and current node
        // is the only node in that list
        // we need to remove the entire list and then increase min frequency value by 1
        if (currNode_frequency == min_frequency && currList.listSize == 0) {
            min_frequency++;
        }
        currNode_frequency++;

        // add current node to another list has current frequency + 1,
        // if we do not have the list with this frequency, initialize it
        DoubleLinkedList newList = frequencyMap.getOrDefault(currNode.frequency, new DoubleLinkedList());
        newList.addNode(currNode);
        frequencyMap.put(currNode.frequency, newList);
    }

}

class LFU_cache_implement {
    public static void main(String args[]) {
        LFU_cache cache = new LFU_cache(2);
        cache.put(1, 1); //min_frequency of key-1 = 1
        cache.put(2, 2); //min_frequency of key-2 = 1
        cache.get(1); // returns 1 //it's frequency is updated to 2 in the List (UpdateNode function)

        cache.put(3, 3); // evicts key 2, bcz most recent frequency of use of 2 was less(min_freq=1) VS 1 is 2

        cache.get(2); // returns -1 (not found)
        cache.get(3); // returns 3. //Frequency of 3 is now 2 {min_frequency=1}

        cache.put(4, 4); // evicts key 1, bcz most recent frequency =2 and also 3=2. Tie is there so in
                         // this least recently used means old one is evicted

        System.out.println(cache.get(1)); // returns -1 (not found)
        System.out.println(cache.get(3)); // returns 3 //updates the frequcny also now to 2 {min_frequency was 1}
        System.out.println(cache.get(4)); // returns 4 //updates the frequency also now equals 2 {min_freq was 1}

    }
}
