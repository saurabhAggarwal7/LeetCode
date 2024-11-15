import java.util.Stack;

/*
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
**/

/*Exlaination:

Approach #2: Stack [Accepted]

Intuition

Consider trying to find the next warmer occurrence at T[i]. What information (about T[j] for j > i) must we remember?

Say we are trying to find T[0]. If we remembered T[10] = 50, knowing T[20] = 50 wouldn't help us, as any T[i] that has its next warmer ocurrence at T[20] would have it at T[10] instead. However, T[20] = 100 would help us, since if T[0] were 80, then T[20] might be its next warmest occurrence, while T[10] couldn't.

Thus, we should remember a list of indices representing a strictly increasing list of temperatures. For example, [10, 20, 30] corresponding to temperatures [50, 80, 100]. When we get a new temperature like T[i] = 90, we will have [5, 30] as our list of indices (corresponding to temperatures [90, 100]). The most basic structure that will satisfy our requirements is a stack, where the top of the stack is the first value in the list, and so on.

Algorithm

As in Approach #1, process indices i in descending order. 
We'll keep a stack of indices such that T[stack[-1]] < T[stack[-2]] < ..., where stack[-1] is the top of the stack, stack[-2] is second from the top, and so on; 
and where stack[-1] > stack[-2] > ...; and we will maintain this invariant as we process each temperature.

After, it is easy to know the next occurrence of a warmer temperature: it's simply the top index in the stack.

Here is a worked example of the contents of the stack as we work through T = [73, 74, 75, 71, 69, 72, 76, 73] in reverse order, 
at the end of the loop (after we add T[i]). 
For clarity, stack only contains indices i, but we will write the value of T[i] beside it in brackets, such as 0 (73).

When i = 7, stack = [7 (73)]. ans[i] = 0.
When i = 6, stack = [6 (76)]. ans[i] = 0.
When i = 5, stack = [5 (72), 6 (76)]. ans[i] = 1.
When i = 4, stack = [4 (69), 5 (72), 6 (76)]. ans[i] = 1.
When i = 3, stack = [3 (71), 5 (72), 6 (76)]. ans[i] = 2.
When i = 2, stack = [2 (75), 6 (76)]. ans[i] = 4.
When i = 1, stack = [1 (74), 2 (75), 6 (76)]. ans[i] = 1.
When i = 0, stack = [0 (73), 1 (74), 2 (75), 6 (76)]. ans[i] = 1.

*/

class Daily_Temperature{

    static int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();

        //traverse array of temp backwards:

        //i=7, ans[7] = 0 because stack is empty, stack push the so stack(7)
        //i=6, T[stack.peek()] = 73, T[6] = 76, made stack empty,  ans[6] = 0 because stack is made empty now. stack push now so stack(6) now
        //i=5, T[stack.peek()] = 76, T[5] = 72, condition false no need to make stack empty now. ans[i] = stack.peek() - i, so ans[5] = 1 and stack(5)

        //Stack is also used to store, 1st higher, 2nd higher and Top always reflect the highest most element:
        for (int i = T.length - 1; i >= 0; --i) {

            //Stack top is lesser than this element under consideration hence empty this stack, as this is not useful anyway going forward:
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }

            //ans[i] will store the value for the index the distance it has to travel across between Higher one and itslef thats why it's stack.peek() - i
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;

            //Stack will store the index value so that we will compare the T[i] from there accordingly
            stack.push(i);
        }
        return ans;
    }


    public static void main(String args[]){
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] arr = dailyTemperatures(T);
        System.out.println(arr);
    }
}

