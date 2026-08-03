package uk.co.finleyofthewoods.chatwarden.mixin;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ChatEventMixin {
    @Inject(method = "sendChatMessage", at = @At("HEAD"))
    public void onPlayerChatEvent(OutgoingChatMessage message, boolean filtered, ChatType.Bound chatType, CallbackInfo ci)
    {

    }
}
