package uk.co.finleyofthewoods.chatwarden.handlers;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import uk.co.finleyofthewoods.chatwarden.filters.ExactChatFilter;
import uk.co.finleyofthewoods.chatwarden.filters.WildCardChatFilter;


public class ServerMessageEventsHandler {
    private static final ExactChatFilter EXACT_BAD_WORD_FILTER = new ExactChatFilter();
    private static final WildCardChatFilter WILDCARD_BAD_WORD_FILTER = new WildCardChatFilter();

    public static void init() {
        EXACT_BAD_WORD_FILTER.load();
        WILDCARD_BAD_WORD_FILTER.load();
    }

    public static boolean handle(PlayerChatMessage message, ServerPlayer player, ChatType.Bound type) {
        String messageContent = message.decoratedContent().getString().toLowerCase();

        return EXACT_BAD_WORD_FILTER.allowMessage(messageContent, player)
                && WILDCARD_BAD_WORD_FILTER.allowMessage(messageContent, player);
    }
}
