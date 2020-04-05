/*Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]*/

class pow_x_n{

    static double calc_pow(double x, int n){
        //conditions boundry basic:
        if(n==0) return 1;
        if(x==1) return x;

        //POSITIVE POWERS
        if(n>0){
            //even power:
            //(2, 10) so that you can easily do n/2
            if(n %2==0)

            //Recursion pairs of (2x2) 5 times
                return calc_pow(x*x, n/2);
            else
            //odd power: (2, 3)
            //4, 1
            // 1time recursion and then finally in last 2 * (4)

            //Odd numbers need one extra for recursion :
            //2x2 hadled by recursion then we need one more extra 2 so thats why x* (x*x)
                return x* calc_pow(x*x, n/2);
        } else{
            //NEGATIVE POWERS

            if(n<Integer.MIN_VALUE +1) 
                return (1/x) * calc_pow(1/x, -(n+1));
            else
                // IMPORTANT:
                //For 2.00 and -2 becomes ---->> //0.50, 2
                // i-e pow(x, -1) is equal to pow(1/x, 1)
                return calc_pow(1/x, -n);

        }
    }
    public static void main(String args[]) 
	{ 
        System.out.println(calc_pow(2.00, 3));
        System.out.println(calc_pow(2.00, -2));
        System.out.println(calc_pow(2.00, 10));
    }
}