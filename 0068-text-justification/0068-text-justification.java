import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;

        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();

            while (j < n && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length();
                j++;
            }

            StringBuilder sb = new StringBuilder();
            int diff = j - i - 1;

            if (j == n || diff == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        sb.append(" ");
                    }
                }
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } else {
                int totalSpaces = maxWidth - (lineLength - diff);
                int spacesBetween = totalSpaces / diff;
                int extraSpaces = totalSpaces % diff;

                for (int k = i; k < j - 1; k++) {
                    sb.append(words[k]);
                    int spacesToApply = spacesBetween + (k - i < extraSpaces ? 1 : 0);
                    for (int s = 0; s < spacesToApply; s++) {
                        sb.append(" ");
                    }
                }
                sb.append(words[j - 1]);
            }

            result.add(sb.toString());
            i = j;
        }

        return result;
    }
}