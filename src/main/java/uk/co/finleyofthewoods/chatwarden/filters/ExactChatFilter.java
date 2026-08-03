package uk.co.finleyofthewoods.chatwarden.filters;

import net.minecraft.server.level.ServerPlayer;

public class ExactChatFilter extends AbstractChatFilter {
    @Override
    public boolean allowMessage(String message, ServerPlayer player) {
        return super.allowMessage(message, player);
    }

    @Override
    public boolean filter(String message, ServerPlayer player) {
        return message.contains("exact");
    }
}
