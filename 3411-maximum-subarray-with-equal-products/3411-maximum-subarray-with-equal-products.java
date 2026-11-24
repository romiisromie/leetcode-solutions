class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLcm = 1;
        int maxVal = 0;
        for (int x : nums) {
            maxLcm = lcm(maxLcm, x);
            if (x > maxVal) {
                maxVal = x;
            }
        }
        long maxProd = (long) maxLcm * maxVal;

        int ans = 1;
        for (int i = 0; i < n; i++) {
            long prod = 1;
            int g = 0;
            int l = 1;
            for (int j = i; j < n; j++) {
                prod *= nums[j];
                if (prod > maxProd) {
                    break;
                }
                g = gcd(g, nums[j]);
                l = lcm(l, nums[j]);
                if (prod == (long) l * g) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}