
/**
 * points = [[3,3],[5,-1],[-2,4]], K = 2
 */
import java.util.*;
class Simple_Solution_K_Closest_points_to_origin {
    static int[][] kClosest(int[][] points, int K) {
        
        Queue<int[]> queue=new PriorityQueue<>((a, b) -> (a[0]*a[0]+a[1]*a[1])-(b[0]*b[0]+b[1]*b[1]));
        int[][] res=new int[K][2];
        int index=0;

        for(int[] arr:points) {
            queue.add(arr);
        }
        while(index<K) {
            res[index]=queue.poll();
            index++;
        }
        return res;
    }

    public static void main(String args[]){
        //points = [[3,3],[5,-1],[-2,4]], K = 2
        int[][] points = {{3,3}, {5, -1}, {-2, 4}};
        int K = 2;
        kClosest(points, K);
    }
}