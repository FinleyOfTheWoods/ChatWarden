package uk.co.finleyofthewoods.chatwarden.filters;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ExactChatFilter extends AbstractChatFilter {
    private static final String FILE_NAME = "exact_bad_words.json";
    private Set<String> BAD_WORDS = Collections.emptySet();

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    public boolean filter(String message) {
        if (message == null || message.isBlank()) return false;
        if (BAD_WORDS.isEmpty()) return false;

        String[] words = message.split("\\W+");
        for (String word : words) {
            if (word.isBlank()) continue;
            if (BAD_WORDS.contains(word)) return true;
        }
        return false;
    }

    @Override
    protected void setBadWords(Set<String> loadedWords) {
        Set<String> cleanedExact = new HashSet<>(loadedWords.size());
        for (String word : loadedWords) {
            if (word == null || word.isBlank()) continue;
            cleanedExact.add(word.trim().toLowerCase());
        }
        BAD_WORDS = cleanedExact;
    }
}
