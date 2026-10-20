import java.util.Arrays;
import java.util.Random;

class Solution {
    private static final long MOD1 = 1000000007L;
    private static final long MOD2 = 1000000009L;
    private static final long BASE1;
    private static final long BASE2;

    static {
        Random rand = new Random();
        BASE1 = 100000 + rand.nextInt(100000);
        BASE2 = 100000 + rand.nextInt(100000);
    }

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        long[] p1 = new long[2 * n + 1];
        long[] p2 = new long[2 * n + 1];
        p1[0] = 1;
        p2[0] = 1;
        for (int i = 1; i <= 2 * n; i++) {
            p1[i] = (p1[i - 1] * BASE1) % MOD1;
            p2[i] = (p2[i - 1] * BASE2) % MOD2;
        }

        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            h1[i + 1] = (h1[i] * BASE1 + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + nums[i]) % MOD2;
        }

        long[] hs1 = new long[n + 1];
        long[] hs2 = new long[n + 1];
        for (int i = 0; i < n; i++) {
            hs1[i + 1] = (hs1[i] * BASE1 + sorted[i]) % MOD1;
            hs2[i + 1] = (hs2[i] * BASE2 + sorted[i]) % MOD2;
        }

        int ans = 0;
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean possible = true;
            for (int i = 0; i < n; i += k) {
                long target1 = (hs1[i + k] - (hs1[i] * p1[k]) % MOD1 + MOD1) % MOD1;
                long target2 = (hs2[i + k] - (hs2[i] * p2[k]) % MOD2 + MOD2) % MOD2;

                long segment1 = (h1[i + k] - (h1[i] * p1[k]) % MOD1 + MOD1) % MOD1;
                long segment2 = (h2[i + k] - (h2[i] * p2[k]) % MOD2 + MOD2) % MOD2;

                long combined1 = (segment1 * p1[k] + segment1) % MOD1;
                long combined2 = (segment2 * p2[k] + segment2) % MOD2;

                boolean blockMatch = false;
                for (int s = 0; s < k; s++) {
                    long current1 = (combined1 - (segment1 * p1[2 * k - s]) % MOD1 + MOD1) % MOD1;
                    current1 = (current1 * p1[s]) % MOD1;
                    current1 = (current1 + (segment1 - ( (segment1 / p1[k - s]) * p1[k - s] ) % MOD1)) % MOD1; 
                    
                    long rem1 = (segment1 - (segment1 * p1[s]) % MOD1 + MOD1) % MOD1;
                    
                    long hSub1_1 = (h1[i + s] - (h1[i] * p1[s]) % MOD1 + MOD1) % MOD1;
                    long hSub2_1 = (h1[i + k] - (h1[i + s] * p1[k - s]) % MOD1 + MOD1) % MOD1;
                    
                    long hRot1 = (hSub2_1 * p1[s] + hSub1_1) % MOD1;
                    long hRot2 = ( (h2[i + k] - (h2[i + s] * p2[k - s]) % MOD2 + MOD2) % MOD2 * p2[s] + (h2[i + s] - (h2[i] * p2[s]) % MOD2 + MOD2) % MOD2 ) % MOD2;

                    if (hRot1 == target1 && hRot2 == target2) {
                        blockMatch = true;
                        break;
                    }
                }

                if (!blockMatch) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                ans += k;
            }
        }
        return ans;
    }
}