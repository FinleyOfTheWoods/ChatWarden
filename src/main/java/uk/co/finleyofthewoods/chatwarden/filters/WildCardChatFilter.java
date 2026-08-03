package uk.co.finleyofthewoods.chatwarden.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class WildCardChatFilter extends AbstractChatFilter {
    private static final String FILE_NAME = "wildcard_bad_words.json";
    private List<Pattern> BAD_WORD_PATTERNS = Collections.emptyList();

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    public boolean filter(String message) {
        if (message == null || message.isBlank()) return false;
        if (BAD_WORD_PATTERNS.isEmpty()) return false;

        for (Pattern pattern : BAD_WORD_PATTERNS) {
            if (pattern.matcher(message).find()) return true;
        }
        return false;
    }

    @Override
    protected void setBadWords(Set<String> loadedWords) {
        List<Pattern> patterns = new ArrayList<>(loadedWords.size());
        for (String word : loadedWords) {
            if (word == null || word.isEmpty()) continue;
            String regex = Pattern.quote(word.trim().toLowerCase());
            patterns.add(Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
        BAD_WORD_PATTERNS = patterns;
    }
}
