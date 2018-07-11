package com.codility.L11_Sieve_Of_Eratosthenes;

/*
* A prime is A positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.

A semiprime is A natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.

You are given two non-empty arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.

Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.

For example, consider an integer N = 26 and arrays P, Q such that:

    P[0] = 1    Q[0] = 26
    P[1] = 4    Q[1] = 10
    P[2] = 16   Q[2] = 20
The number of semiprimes within each of these ranges is as follows:

(1, 26) is 10,
(4, 10) is 4,
(16, 20) is 0.
Write A function:

class Solution { public int[] solution1(int N, int[] P, int[] Q); }

that, given an integer N and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.

For example, given an integer N = 26 and arrays P, Q such that:

    P[0] = 1    Q[0] = 26
    P[1] = 4    Q[1] = 10
    P[2] = 16   Q[2] = 20
the function should return the values [10, 4, 0], as explained above.

Assume that:

N is an integer within the range [1..50,000];
M is an integer within the range [1..30,000];
each element of arrays P, Q is an integer within the range [1..N];
P[i] ≤ Q[i].
Complexity:

expected worst-case time complexity is O(N*log(log(N))+M);
expected worst-case space complexity is O(N+M) (not counting the storage required for input arguments).
* */

/**
 * Created by Chaklader on 6/25/18.
 */
public class CountSemiprimes {

    /*
     * sieve of Eratosthenes is A simple, ancient algorithm for finding all prime numbers up
     * to any given limit.It does so by iteratively marking as composite (i.e., not prime)
     * the multiples of each prime, starting with the first prime number, 2. The multiples
     * of A given prime are generated as A sequence of numbers starting from that prime, with
     * constant difference between them that is equal to that prime. This is the sieve's
     * key distinction from using trial division to sequentially test each candidate number
     * for divisibility by each prime
     * */

    /*
     * A semiprime is A natural number that is the product
     * of two (not necessarily distinct) prime numbers.
     * */


    // Count the semiprime numbers in the given range [a..b]
    /*
     * solution1 - a
     */
    public static int[] solution(int[] A, int[] B, int N) {

        int[] factArray = sieve(N);

        int[] semiPrimes = new int[factArray.length];

        for (int i = 0; i < semiPrimes.length; i++) {

            if (factArray[i] != 0 && factArray[i / factArray[i]] == 0) {
                semiPrimes[i] = 1;
            }
        }

        int[] semiPrimesPreSum = prefixSum(semiPrimes);
        int[] res = new int[A.length];

        for (int i = 0; i < B.length; i++) {
            res[i] = semiPrimesPreSum[B[i]] - semiPrimesPreSum[A[i] - 1];
        }

        return res;
    }

    //preparing array for factorization (array with primes)
    // [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5, 2]
    public static int[] sieve(int n) {

        int[] F = new int[n + 1];

        for (int i = 2; i * i <= n; i++) {

            if (F[i] == 0) {

                for (int k = i * i; k <= n; k += i) {

                    if (F[k] == 0) {
                        F[k] = i;
                    }
                }
            }
        }

        return F;
    }

    public static int[] prefixSum(int[] A) {

        int[] prefSum = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                prefSum[i] = A[i];
            } else {
                prefSum[i] = prefSum[i - 1] + A[i];
            }
        }

        return prefSum;
    }
    // ENd of solution1 - a


    /*
     * solution1 - b
     */
    public int[] solution1(int N, int[] P, int[] Q) {

        int length = P.length;
        int[] prime = sieve1(N);

        boolean[] semiprime = semiprime1(prime);
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {

            int primeNumber = countSemiprimes(P[i], Q[i], semiprime, N);
            result[i] = primeNumber;
        }

        return result;
    }


    /*
     * use the sieve to get factorized numbers
     */
    public int[] sieve1(int N) {

        int[] prime = new int[N + 1];

        for (int i = 2; i <= (double) Math.sqrt(N); i++) {

            if (prime[i] == 0) {

                int k = i * i;

                while (k <= N) {

                    if (prime[k] == 0) {
                        prime[k] = i;
                    }

                    k += i;
                }
            }
        }
        return prime;
    }


    public boolean[] semiprime1(int[] prime) {

        boolean semiprime[] = new boolean[prime.length];

        for (int i = 0; i < prime.length; i++) {

            if (prime[i] == 0) {
                continue;
            }

            int firstFactor = prime[i];

            if (prime[i / firstFactor] == 0) {
                semiprime[i] = true;
            }
        }

        return semiprime;
    }


    public int countSemiprimes(int P, int Q, boolean[] semiprime, int N) {

        int count = 0;

        if (P > Q || P > N || Q > N) {
            return 0;
        }

        for (int i = P == 1 ? 2 : P; i <= Q; i++) {
            if (semiprime[i]) {
                count++;
            }
        }

        return count;
    }


    /*
     * solution1 - c
     */
    public int[] solution2(int N, int[] P, int[] Q) {

        int length = P.length;

        int[] prime = sieve2(N);
        int[] semiprime = semiprime(prime);

        int[] result = new int[length];
        int[] semiprimesAggreation = new int[N + 1];


        for (int i = 1; i < N + 1; i++) {

            semiprimesAggreation[i] = semiprime[i];
            semiprimesAggreation[i] += semiprimesAggreation[i - 1];
        }

        for (int i = 0; i < length; i++) {

            result[i] = semiprimesAggreation[Q[i]]
                    - semiprimesAggreation[P[i]]
                    + semiprime[P[i]];
        }

        return result;
    }

    public int[] sieve2(int N) {

        int[] prime = new int[N + 1];

        for (int i = 2; i <= (double) Math.sqrt(N); i++) {

            if (prime[i] == 0) {

                int k = i * i;

                while (k <= N) {
                    if (prime[k] == 0) {
                        prime[k] = i;
                    }
                    k += i;
                }
            }
        }
        return prime;
    }

    public int[] semiprime(int[] prime) {

        int semiprime[] = new int[prime.length];

        for (int i = 0; i < prime.length; i++) {

            if (prime[i] == 0) {
                continue;
            }

            int firstFactor = prime[i];

            if (prime[i / firstFactor] == 0) {
                semiprime[i] = 1;
            }
        }
        return semiprime;
    }


    /*
     * solution1 - d
     */
    public int[] solution(int n, int[] p, int[] q) {

        final int[] m = new int[n];
        int i = 2;

        // mark all non-prime numbers with minimal prime factor
        while ((long) i * i <= n) {

            int k = i * i;

            while (k <= n) {

                if (m[k - 1] == 0) {
                    m[k - 1] = i;
                }

                k += i;
            }

            i++;
        }

        // if number / it's minimal prime factor is not a prime number, unmark
        for (i = m.length - 1; i >= 0; i--) {

            if (m[i] > 0 && m[(i + 1) / m[i] - 1] != 0) {
                m[i] = 0;
            }
        }

        // memorize number semiprimes from 0 to i
        int c = 0;

        for (i = 0; i < m.length; i++) {

            if (m[i] > 0) {
                c++;
            }

            m[i] = c;
        }

        // calculate result for ranges
        int[] result = new int[p.length];

        for (i = 0; i < p.length; i++) {

            int from = p[i] - 1;
            int to = q[i] - 1;

            if (from == 0) {
                result[i] = m[to];
            } else {
                result[i] = m[to] - m[from - 1];
            }
        }

        return result;
    }
}
