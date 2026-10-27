import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> builtWords = new HashSet<>();
        String result = "";

        for (String word : words) {
            if (word.length() == 1 || builtWords.contains(word.substring(0, word.length() - 1))) {
                builtWords.add(word);
                if (word.length() > result.length()) {
                    result = word;
                }
            }
        }

        return result;
    }
}