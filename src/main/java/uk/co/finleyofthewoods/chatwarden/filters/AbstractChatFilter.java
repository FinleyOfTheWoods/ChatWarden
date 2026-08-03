package uk.co.finleyofthewoods.chatwarden.filters;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.level.ServerPlayer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Slf4j
public abstract class AbstractChatFilter {
    private static final Gson GSON = new Gson();

    public boolean allowMessage(String message, ServerPlayer player) {
        log.debug("{} filtering message send by {}: {}", this.getClass().getSimpleName(),
                player.getDisplayName().getString(), message);
        if (filter(message)) {
            log.warn("Message filtered by {}", this.getClass().getSimpleName());
            log.warn("Sender: {}, Message: {}", player.getDisplayName().getString(), message);
            return false;
        }
        log.debug("Message allowed by {}", this.getClass().getSimpleName());
        return true;
    }

    public abstract boolean filter(String message);

    protected abstract String getFileName();

    public void load() {

        File directory = FabricLoader.getInstance().getConfigDir().resolve("chatwarden").toFile();
        if (!directory.exists() && !directory.mkdirs()) {
            log.error("Failed to create directory {}", directory.getAbsolutePath());
            return;
        }
        File file = new File(directory, getFileName());
        if (!file.exists()) {
            createConfigFile(file);
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            Set<String> loadedWords = GSON.fromJson(reader, new TypeToken<Set<String>>(){}.getType());

            if (loadedWords == null) {
                log.warn("{} file is empty", file.getName());
                return;
            }
            setBadWords(loadedWords);
            log.info("Loaded {} words from {}", loadedWords.size(), file.getName());
        } catch (IOException e) {
            log.error("Error loading words file {}: {}", file.getName(), e.getMessage());
        }
    }

    protected abstract void setBadWords(Set<String> loadedWords);

    private void createConfigFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(Collections.emptySet(), writer);
        } catch (IOException e) {
            log.error("Error creating bad words file: {}", e.getMessage());
        }
    }
}
