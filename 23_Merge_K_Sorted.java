
/*Divide and Conquer approach. 
//This approach doesn’t require extra space for heap and works in O(nk Log k)

// merging of two linked lists can be done in O(n) time and O(1) space (For arrays O(n) space is required). 
//The idea is to pair up K lists and merge each pair in linear time using O(1) space. 
//After first cycle, K/2 lists are left each of size 2*N. After second cycle, K/4 lists are left each of size 4*N and so on. 
//We repeat the procedure until we have only one list left.*/



class mergeKSortedList{

    //Sort two lists here using recursion:
    //O(logn) extra space due to recursion

    public static Node SortedMerge(Node a, Node b){
        Node result = null;
        if (a==null) return b;
        if(b== null) return a;

        if(a.data<=b.data){
            result = a;
            result.next = SortedMerge(a.next, b);
        } else{
            result = b;
            result.next = SortedMerge(a, b.next);
        }
        return result;
    }
    
    
    //Array of lists and get a sorted output:
    public static Node mergeKLists(Node arr[], int last){
        
        //repeat until one list is left, thats why we took (k-1) 
        // k is the total number of lists in array
        while(last !=0){
            int i=0, j= last;

            //(i,j) forms a pair:
            while(i<j){

				// merge List i with List j and store 
                // merged list in List i 
                arr[i] = SortedMerge(arr[i],arr[j]);

                //once merged move to next pair:
                i++;
                j--;

                //if all pairs are merged, update last:
                if(i>=j){
                    last = j;
                }
            }
        }

        return arr[0];
    }

    //print the current list with Node as parameter
    public static void printList(Node node){
        while(node != null){
            System.out.print(node.data + " ");

            //go forward:
            node = node.next;
        }
    }




    public static void main(String args[]){
        int k=3; //no of LLs
        //int n=4; //numbet of elements in List

        //Create an array to store the head nodes of the LL
        
        //array of Nodes Type:
        //Every Index will have its own Linked List
        //The array of LL is created because we dont know in advance that how many LL will be created and added:
        Node arr[] = new Node[k];

        //List-1:
        arr[0] = new Node(1); 
		arr[0].next = new Node(3); 
		arr[0].next.next = new Node(5); 
		arr[0].next.next.next = new Node(7); 

        //List-2
        arr[1] = new Node(2); 
		arr[1].next = new Node(4); 
		arr[1].next.next = new Node(6); 
		arr[1].next.next.next = new Node(8); 
        
        //List-3
		arr[2] = new Node(0); 
		arr[2].next = new Node(9); 
		arr[2].next.next = new Node(10); 
        arr[2].next.next.next = new Node(11); 

        //Merge all the lists:
        Node head = mergeKLists(arr, k-1);
        printList(head);
        
    }
}

class Node{
    int data;
    Node next;
    Node(int data){
        this.data = data;
    }
}