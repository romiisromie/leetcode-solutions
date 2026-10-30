import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        int leftRem = 0;
        int rightRem = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftRem++;
            } else if (ch == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        dfs(s, 0, leftRem, rightRem, 0, new StringBuilder(), result);
        return result;
    }

    private void dfs(String s, int index, int leftRem, int rightRem, int balance, StringBuilder sb, List<String> result) {
        if (balance < 0) {
            return;
        }

        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && balance == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char ch = s.charAt(index);
        int len = sb.length();

        if (ch == '(') {
            int next = index;
            while (next < s.length() && s.charAt(next) == '(') {
                next++;
            }
            int k = next - index;

            for (int rem = 0; rem <= Math.min(k, leftRem); rem++) {
                int keep = k - rem;
                for (int i = 0; i < keep; i++) {
                    sb.append('(');
                }
                dfs(s, next, leftRem - rem, rightRem, balance + keep, sb, result);
                sb.setLength(len);
            }
        } else if (ch == ')') {
            int next = index;
            while (next < s.length() && s.charAt(next) == ')') {
                next++;
            }
            int k = next - index;

            for (int rem = 0; rem <= Math.min(k, rightRem); rem++) {
                int keep = k - rem;
                for (int i = 0; i < keep; i++) {
                    sb.append(')');
                }
                dfs(s, next, leftRem, rightRem - rem, balance - keep, sb, result);
                sb.setLength(len);
            }
        } else {
            sb.append(ch);
            dfs(s, index + 1, leftRem, rightRem, balance, sb, result);
            sb.setLength(len);
        }
    }
}