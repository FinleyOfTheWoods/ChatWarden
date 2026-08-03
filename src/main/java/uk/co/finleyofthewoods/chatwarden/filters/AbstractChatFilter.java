package uk.co.finleyofthewoods.chatwarden.filters;

import lombok.extern.slf4j.Slf4j;
import net.minecraft.server.level.ServerPlayer;

@Slf4j
public abstract class AbstractChatFilter {
    public boolean allowMessage(String message, ServerPlayer player) {
        log.debug("{} filtering message send by {}: {}", this.getClass().getSimpleName(), player.getDisplayName().getString(), message);
        if (filter(message, player)) {
            log.warn("Message filtered by {}", this.getClass().getSimpleName());
            log.warn("Sender: {}, Message: {}", player.getDisplayName().getString(), message);
            return false;
        }
        log.debug("Message allowed by {}", this.getClass().getSimpleName());
        return true;
    }

    public abstract boolean filter(String message, ServerPlayer player);
}
