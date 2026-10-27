public class Solution {
    private int count = 0;
    private String result = "";

    public String getHappyString(int n, int k) {
        backtrack(new StringBuilder(), n, k);
        return result;
    }

    private void backtrack(StringBuilder sb, int n, int k) {
        if (sb.length() == n) {
            count++;
            if (count == k) {
                result = sb.toString();
            }
            return;
        }

        for (char ch = 'a'; ch <= 'c'; ch++) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ch) {
                continue;
            }

            sb.append(ch);
            backtrack(sb, n, k);
            if (!result.isEmpty()) {
                return;
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}