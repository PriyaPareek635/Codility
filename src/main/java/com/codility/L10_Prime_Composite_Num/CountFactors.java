package com.codility.L10_Prime_Composite_Num;

/*
* A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.

For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).

Write a function:

class Solution { public int solution(int N); }

that, given a positive integer N, returns the number of its factors.

For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.

Assume that:

N is an integer within the range [1..2,147,483,647].
Complexity:

expected worst-case time complexity is O(sqrt(N));
expected worst-case space complexity is O(1).
* */

/**
 * Created by Chaklader on 6/25/18.
 */
public class CountFactors {


    /*
     * solution - a
     */
    public static int solution(int N) {

        int res = 0;

        /*
         * to reduece the number of iterations
         * we will iterate till, i * i <= N
         * */
        for (int i = 1; (long) i * i <= N; i++) {

            if (i * i == N) {
                return ++res;
            }

            /*
             * we get 2 factors/ divisors
             * */
            else if (N % i == 0) {
                res += 2;
            }
        }

        return res;
    }


    /*
     * solution - b
     */
    public int solution1(int N) {

        int result = 0;

        for (int i = 1; i <= (double) Math.sqrt(N); i++) {

            if (i == (double) Math.sqrt(N)) {
                result++;
            } else if (N % i == 0) {
                result += 2;
            }
        }

        return result;
    }


    /*
     * solution - c
     */
    public int solution2(int N) {

        int i = 1;
        int result = 0;

        /*
        * we only check behind the N
        * */
        while ((long) i * i < N) {

            if (N % i == 0) {
                result += 2;
            }

            i++;
        }

        if (i * i == N) {
            result++;
        }
        return result;
    }


}
