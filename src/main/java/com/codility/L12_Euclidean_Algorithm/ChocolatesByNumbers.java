package com.codility.L12_Euclidean_Algorithm;


/*
* Two positive integers N and M are given. Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.

You start to eat the chocolates. After eating a chocolate you leave only a wrapper.

You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.

More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).

You stop eating when you encounter an empty wrapper.

For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2, 6.

The goal is to count the number of chocolates that you will eat, following the above rules.

Write a function:

class Solution { public int solution(int N, int M); }

that, given two positive integers N and M, returns the number of chocolates that you will eat.

For example, given integers N = 10 and M = 4. the function should return 5, as explained above.

Assume that:

N and M are integers within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(log(N+M));
expected worst-case space complexity is O(log(N+M)).
* */

/**
 * Created by Chaklader on 6/25/18.
 */
public class ChocolatesByNumbers {


    /*
     * The goal is to count the number of chocolates that you will eat
     * */

    /*
     * Greatest common divisor (gcd) of two or more integers, which are
     * not all zero, is the largest positive integer that divides each
     * of the integers.
     * */
    /*
     * solution - a
     */
    public int solution1(int N, int M) {

        int[] X = new int[N];
        int i = 0;
        int count = 0;

        while (X[i] == 0) {

            /*
            * eat the chocolate in the i-th index
            * */
            X[i] = 1;

            i = (i + M) % N;
            count++;
        }

        return count;
    }


    /*
     * solution - b
     */
    private static int solution(int N, int M) {

        /*
         * LCM(x, y) * GCD(x, y) = x * y
         * LCM(x, y) = (x * y) / GCD(x, y)
         * */

        int a = gcd(N, M, 1);
        return N / a;
    }


    private static int gcd(int a, int b, int res) {

        if (a == b) {
            return res * a;
        } else if (a % 2 == 0 && b % 2 == 0) {
            return gcd(a / 2, b / 2, res * 2);
        } else if (a % 2 == 0) {
            return gcd(a / 2, b, res);
        } else if (b % 2 == 0) {
            return gcd(a, b / 2, res);
        } else if (a > b) {
            return gcd(a - b, b, res);
        } else {
            return gcd(a, b - a, res);
        }
    }




    /*
     * solution - c
     * */
    public int solution2(int N, int M) {

        return N / gcd1(N, M);
    }

    public static int gcd1(int a, int b) {

        if (a % b == 0) {
            return b;
        }
        return gcd1(b, a % b);
    }

    private static int lcm(int a, int b) {

        return a * (b / gcd1(a, b));
    }
}
