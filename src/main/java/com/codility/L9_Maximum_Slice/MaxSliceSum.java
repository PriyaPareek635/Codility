package com.codility.L9_Maximum_Slice;

/*
* A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called A slice of array A. The sum of A slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].

Write A function:

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns the maximum sum of any slice of A.

For example, given array A such that:

A[0] = 3  A[1] = 2  A[2] = -6
A[3] = 4  A[4] = 0
the function should return 5 because:

(3, 4) is A slice of A that has sum 4,
(2, 2) is A slice of A that has sum −6,
(0, 1) is A slice of A that has sum 5,
no other slice of A has sum greater than (0, 1).
Assume that:

N is an integer within the range [1..1,000,000];
each element of array A is an integer within the range [−1,000,000..1,000,000];
the result will be an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

/**
 * Created by Chaklader on 6/24/18.
 */
public class MaxSliceSum {




    /*
     * solution - a
     */
    public static int solution(int[] A) {

        int N = A.length;

        int max = 0;
        int result = 0;

        int value = Integer.MIN_VALUE;

        if( N == 1){
            return  A[0];
        }


        for (int i = 0; i < N; i++) {

            value = A[i] > value? A[i]: value;

            max = (max + A[i]) > 0 ? (max + A[i]) : 0;
            result = result > max ? result : max;
        }

        // all of the values are negatives 
        if(value <=0){
            return value;
        }

        return result;
    }





    /*
     * solution - a
     */
    public static int solution(int[] A) {


//        int max = Integer.MIN_VALUE;
        int max = (1 << 31);

        for (int i = 0; i < A.length; i++) {
            max = A[i] > max ? A[i] : max;
        }

        if (max <= 0) {
            return max;
        }

        int result = 0;
        int sum = 0;

        for (int i = 0; i < A.length; i++) {
                                    
            sum = (sum + A[i]) > 0 ? (sum + A[i]) : 0;
            result = sum > result ? sum : result;
        }

        return result;
    }


    /*
     * solution - b
     */
    public static int solution1(int[] A) {

        int N = A.length;

        int maxEnding = A[0];
        int maxSlice = A[0];

        for (int i = 1; i < N; i++) {

            maxEnding = Math.max(A[i], maxEnding + A[i]);
            maxSlice = Math.max(maxEnding, maxSlice);
        }

        return maxSlice;
    }


    /*
     * solution - c
     */
    public static int solution2(int[] A) {

        int N = A.length;

        int[] lms = new int[N];
        int[] rms = new int[N];

        int minSum = 0;
        int sum = 0;

        for (int i = 0; i < A.length; i++) {

            lms[i] = minSum;
            sum += (int) A[i];

            if (sum < minSum) {
                minSum = sum;
            }
        }

        int total = sum;

        minSum = 0;
        sum = 0;

        for (int i = A.length - 1; i >= 0; i--) {

            rms[i] = minSum;
            sum += (int) A[i];

            if (sum < minSum) {
                minSum = sum;
            }
        }

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {

            sum = total - lms[i] - rms[i];

            if (sum > result) {
                result = sum;
            }
        }

        return result;
    }


    /*
     * solution - d
     * */
    public static int solution3(int[] A) {


        int end = A[0];
        int start = A[0];

        for (int i = 1; i < A.length; i++) {

            end = Math.max(A[i], end + A[i]);
            start = Math.max(start, end);
        }

        return start;
    }
}
