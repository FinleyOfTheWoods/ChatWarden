package uk.co.finleyofthewoods.chatwarden;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import uk.co.finleyofthewoods.chatwarden.filters.ExactChatFilter;
import uk.co.finleyofthewoods.chatwarden.filters.WildCardChatFilter;

@Slf4j
public class Chatwarden implements ModInitializer {
    private static final ExactChatFilter EXACT_BAD_WORD_FILTER = new ExactChatFilter();
    private static final WildCardChatFilter WILDCARD_BAD_WORD_FILTER = new WildCardChatFilter();

    @Override
    public void onInitialize() {
        log.info("Chat Warden initialised");

        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register((message, player, _) -> {
            String messageContent = message.decoratedContent().getString().toLowerCase();

            return EXACT_BAD_WORD_FILTER.allowMessage(messageContent, player)
                    && WILDCARD_BAD_WORD_FILTER.allowMessage(messageContent, player);
        });
    }
}
