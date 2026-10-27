import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        backtrack(result, s, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(List<String> result, String s, int start, List<String> current) {
        if (current.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) {
                break;
            }

            String part = s.substring(start, start + len);
            if ((part.length() > 1 && part.charAt(0) == '0') || Integer.parseInt(part) > 255) {
                continue;
            }

            current.add(part);
            backtrack(result, s, start + len, current);
            current.remove(current.size() - 1);
        }
    }
}