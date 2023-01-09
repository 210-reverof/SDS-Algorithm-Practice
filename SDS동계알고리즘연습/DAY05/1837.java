import java.io.*;
import java.util.*;

class Main {
    static int MAX = 1000000 + 1;
    static int[] isNotPrime;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);

        int K = Integer.parseInt(st.nextToken());

        isNotPrime = new int[MAX];


    }

    static void eratosthenes(int K) {
        minFactor[0] = minFactor[1] = -1;
        for (int i = 2; i < K; i++) {
            minFactor[i] = i;
        }
        for (int i = 2; i*i < K; i++) {
            if (minFactor[i] == i) {
                for (int j = i * i; j < K; j += i) {
                    if (minFactor[j] == j) {
                        minFactor[j] = i;
                    }
                }
            }
        }
        for (int i = 2; i < K; i++) {
            if (minFactor[i] == i) { primeNumbers.push_back(i);
            }
        }
    }


}