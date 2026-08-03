package uk.co.finleyofthewoods.chatwarden.handlers;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import uk.co.finleyofthewoods.chatwarden.filters.ExactChatFilter;
import uk.co.finleyofthewoods.chatwarden.filters.SubStringChatFilter;

@Slf4j
public class ServerMessageEventsHandler {
    private static final ExactChatFilter EXACT_BAD_WORD_FILTER = new ExactChatFilter();
    private static final SubStringChatFilter WILDCARD_BAD_WORD_FILTER = new SubStringChatFilter();

    public static void init() {
        EXACT_BAD_WORD_FILTER.load();
        WILDCARD_BAD_WORD_FILTER.load();
    }

    public static boolean handle(PlayerChatMessage message, ServerPlayer player, ChatType.Bound type) {
        String messageContent = message.decoratedContent().getString().toLowerCase();

        boolean filterMessage = EXACT_BAD_WORD_FILTER.allowMessage(messageContent, player)
                && WILDCARD_BAD_WORD_FILTER.allowMessage(messageContent, player);

        return filterMessage;
    }
}
