class Solution {
    public int[] beautifulArray(int n) {
        int[] res = new int[n];
        if (n == 1) {
            res[0] = 1;
            return res;
        }
        
        int[] odd = beautifulArray((n + 1) / 2);
        int[] even = beautifulArray(n / 2);
        
        int idx = 0;
        for (int x : odd) {
            res[idx++] = 2 * x - 1;
        }
        for (int x : even) {
            res[idx++] = 2 * x;
        }
        
        return res;
    }
}