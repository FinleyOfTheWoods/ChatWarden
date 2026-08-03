package uk.co.finleyofthewoods.chatwarden.filters;

public class SubStringChatFilter extends AbstractChatFilter {
    private static final String FILE_NAME = "wildcard_bad_words.json";

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    public boolean filter(String message) {
        if (message == null || message.isBlank()) return false;
        if (badWords.isEmpty()) return false;

        for (String word : badWords) {
            if (message.contains(word)) return true;
        }
        return false;
    }
}
