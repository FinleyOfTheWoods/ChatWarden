package uk.co.finleyofthewoods.chatwarden;

import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import uk.co.finleyofthewoods.chatwarden.handlers.ServerMessageEventsHandler;

import java.io.File;

@Slf4j
public class Chatwarden implements ModInitializer {
    private static final String MOD_ID = "chatwarden";
    private static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow();
    private static final String MOD_NAME = MOD_CONTAINER.getMetadata().getName();
    private static final String MOD_VERSION = MOD_CONTAINER.getMetadata().getVersion().getFriendlyString();

    @Override
    public void onInitialize() {
        log.info("{} {} initialising...", MOD_NAME, MOD_VERSION);

        File directory = FabricLoader.getInstance().getConfigDir().resolve("chatwarden").toFile();
        if (!directory.exists() && !directory.mkdirs()) {
            log.error("Failed to create directory {}", directory.getAbsolutePath());
            log.error("{} is disabled", MOD_NAME);
        }

        ServerMessageEventsHandler.init();
        log.info("{} {} initialised", MOD_NAME, MOD_VERSION);

        ServerMessageEvents.ALLOW_CHAT_MESSAGE.register(ServerMessageEventsHandler::handle);
    }
}
