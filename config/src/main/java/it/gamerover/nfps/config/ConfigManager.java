package it.gamerover.nfps.config;

import ch.jalu.configme.SettingsManager;
import it.gamerover.nfps.config.holder.ConfigHolder;
import it.gamerover.nfps.util.YamlUtil;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigManager {

    private static final String CONFIG_FILE_NAME = "config.yml";

    /**
     * The ConfigMe loading settings manager instance.
     */
    @Nullable
    private static SettingsManager settingsManager;

    private ConfigManager() {
        throw new IllegalStateException("This is a static class");
    }

    /**
     * Load/Reload the config.yml file.
     */
    public static void reload(@NotNull Plugin plugin) {

        settingsManager = YamlUtil.reloadAndGetSettings(plugin,
                settingsManager, CONFIG_FILE_NAME, ConfigHolder.class);

    }

}
