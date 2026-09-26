import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                StringBuilder key = new StringBuilder();
                i++;
                while (i < n && s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }
                String val = map.get(key.toString());
                sb.append(val != null ? val : "?");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}