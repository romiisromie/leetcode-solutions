import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int kthFactor(int n, int k) {
        List<Integer> divisors = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);

        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
                k--;
                if (k == 0) {
                    return i;
                }
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }

        if (k <= divisors.size()) {
            return divisors.get(divisors.size() - k);
        }

        return -1;
    }
}