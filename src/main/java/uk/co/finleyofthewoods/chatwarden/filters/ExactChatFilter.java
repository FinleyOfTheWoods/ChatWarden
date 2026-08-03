package uk.co.finleyofthewoods.chatwarden.filters;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ExactChatFilter extends AbstractChatFilter {
    private static final String FILE_NAME = "exact_bad_words.json";
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\W+");

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    public boolean filter(String message) {
        if (message == null || message.isBlank()) return false;
        if (badWords.isEmpty()) return false;

        String[] words = WHITESPACE_PATTERN.split(message);
        for (String word : words) {
            if (word.isBlank()) continue;
            if (badWords.contains(word)) return true;
        }
        return false;
    }
}
