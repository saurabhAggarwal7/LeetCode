

/**
 * Implement an iterator to flatten a 2d vector.
For example, Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
Attention:
filter out empty list when saving the incoming data;
in hasNext() function, make sure both pointers are valid;
after retrieve data in next(), update pointers.
 */

 /**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

 import java.util.*;

class Vector2D implements Iterator<Integer> {
    List<List<Integer>> vector = new ArrayList<>();

    int p =0;// which list;
    int q =0;// which number in list;

    public Vector2D(List<List<Integer>> vec2d) {
        for(List<Integer> l : vec2d){
            if(l.size()>0){
                vector.add(l);
            }
        }
    }

    @Override
    public Integer next() {
        int val = vector.get(p).get(q);
        q++;
        if(q == vector.get(p).size()){
            p++;
            q=0;
        }
        return val;
    }

    @Override
    public boolean hasNext() {

        if(p >= vector.size()) 
            return false;
        if(p == (vector.size()-1) && q >= vector.get(p).size()) 
            return false;
        else 
            return true;
    }
}

class vector_implement{
    public static void main(String args[]){
        // int[][] arr = {{1,2}, {3}, {4, 5, 6}};
        // ArrayList<ArrayList<Integer>> lst = new ArrayList<ArrayList<Integer>>(Arrays.asList(arr));
        // Vector2D obj = new Vector2D(lst);
    }
}


