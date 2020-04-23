import java.util.HashMap;

/**
 * 
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
 * 
 * */


//SOLUTION:::::::
/***
 * /*
     *  A line is determined by two factors,say y=mx+c
     *  
     *  If two points(x1,y1) (x2,y2) are on the same line(Of course). 

     *  Consider the gap between two points.

     *  We have ,m=(y2-y1)/(x2-x1) m is a rational, c is canceled since c is a constant

     *  If a third point (x3,y3) are on the same line. So we must have y3=mx3+c

     *  Thus,(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=m

     *  Since c is a rational, there exists y0 and x0, y0/x0=(y3-y1)/(x3-x1)=(y2-y1)/(x2-x1)=m

     *  So we can use y0&x0 to track a line;
     * 
     * :::::::::Slope of all the points lying on the SAME LINE WILL BE SAME:::::
     */
 



import java.util.*;

class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Max_Points_Same_Line {

    static int generateGCD(int a,int b){

        /***
         * The GCD (Greatest Common Divisor) of two numbers is the largest positive integer number that divides both the numbers without leaving any remainder. For example. GCD of 30 and 45 is 15. GCD also known as HCF
         */
    
        if (b==0) return a;
        else return generateGCD(b,a%b);
        
    }

    static int maxPoints(Point[] points) {
        if (points==null) 
            return 0;

        if (points.length<=2) 
            return points.length;
        
         //Map<dx, Map<dy, count>>
         //hence map.get(dx).get(dy)
         //key-> {dx} 
         //value-> <dy, count>  

         //Result is map.get(dx).get(dy)
         //That is how many have same dx/dy if yes then we increment the value in map<map<value>> there and then we tend to find maximum of them
        Map<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
        int result=0;

        for (int i=0;i<points.length;i++){ 
            map.clear();
            int overlap=0,max=0;

            for (int j=i+1;j<points.length;j++){
                int dx=points[j].x-points[i].x; //x2-x1
                int dy=points[j].y-points[i].y; //y2-y1

                if (dx==0&&dy==0){
                    overlap++;
                    continue;
                }

                int gcd=generateGCD(dx,dy);

                if (gcd!=0){
                    dx/=gcd;
                    dy/=gcd;
                }
                
                //map already has dx as key then:
                if (map.containsKey(dx)){

                    //same slope dy/dx so:
                    if (map.get(dx).containsKey(dy)){
                        //invrement <value>++
                        map.get(dx).put(dy, map.get(dx).get(dy)+1);
                    }else{
                        //just add 1 to the value
                        map.get(dx).put(dy, 1);
                    }   					
                }else{
                    Map<Integer,Integer> m = new HashMap<Integer,Integer>();
                    m.put(dy, 1);

                    //This map doesnt even have dx, forget the internal map key
                    //create a new map as value with 1 as map<map<value>>
                    map.put(dx, m);
                }
                max=Math.max(max, map.get(dx).get(dy));
            }
            result=Math.max(result, max+overlap+1);
        }
        return result;
        
        
    }

    public static void main(String args[]){
        Point[] max_points = new Point[3];
        max_points[0] = new Point(1,1);
        max_points[1] = new Point(2,2);
        max_points[2] = new Point(3,3);

        System.out.println(maxPoints(max_points)); //3
    }



}