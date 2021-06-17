package it.gamerover.nfps.config;

import ch.jalu.configme.SettingsManager;
import it.gamerover.nfps.config.holder.ConfigHolder;
import it.gamerover.nfps.util.YamlUtil;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ConfigManager {

    /**
     * The configuration file name.
     */
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

        double radius = getRadius();

        if (radius < 0) {

            String message = "The radius property cannot be a negativa value (" + radius + ")";

            plugin.getLogger().warning(message);
            setRadius(ConfigHolder.DEF_RADIUS);

        }

    }

    //
    // GETTER
    //

    /**
     * @return The radius.
     * @throws IllegalStateException If the configuration is not loaded.
     */
    public static double getRadius() {

        checkSettings();

        assert settingsManager != null;
        return settingsManager.getProperty(ConfigHolder.RADIUS);

    }

    /**
     * @return True if all normal worlds must be fixed by default
     * @throws IllegalStateException If the configuration is not loaded.
     */
    public static boolean isAlwaysEnabled() {

        checkSettings();

        assert settingsManager != null;
        return settingsManager.getProperty(ConfigHolder.ALWAYS_ENABLED);

    }

    /**
     * @return An unmodifiable set of strings that contains the worlds' name.
     * @throws IllegalStateException If the configuration is not loaded.
     */
    public static Set<String> getWorlds() {

        checkSettings();

        assert settingsManager != null;
        return Collections.unmodifiableSet(settingsManager.getProperty(ConfigHolder.WORLDS));

    }

    /**
     * @param worldName The world name.
     * @return True if the world name is already contained into the config list.
     */
    public static boolean containsWorld(@NotNull String worldName) {
        return getWorlds().stream().anyMatch(name -> name.equals(worldName));
    }

    //
    // SETTER
    //

    /**
     * @param radius A positive value of the radius.
     * @throws IllegalArgumentException If the radius isn't a positive value.
     * @throws IllegalStateException    If the configuration is not loaded.
     */
    public static void setRadius(double radius) {

        checkSettings();

        if (radius < 0) {
            throw new IllegalArgumentException("The radius argument must be a positive number");
        }

        assert settingsManager != null;
        settingsManager.setProperty(ConfigHolder.RADIUS, radius);

    }

    /**
     * @param enabled A boolean value.
     * @throws IllegalArgumentException If the radius isn't a positive value.
     * @throws IllegalStateException    If the configuration is not loaded.
     */
    public static void setAlwaysEnabled(boolean enabled) {

        checkSettings();

        assert settingsManager != null;
        settingsManager.setProperty(ConfigHolder.ALWAYS_ENABLED, enabled);

    }

    /**
     * @param worldName The world name.
     * @return True if the world name is added into the config file,
     *         False if it is already contained.
     */
    public static boolean addWorld(@NotNull String worldName) {

        if (worldName.isEmpty()) {
            throw new IllegalArgumentException("The world name argument cannot be empty");
        }

        if (containsWorld(worldName)) {
            return false;
        }

        assert settingsManager != null;
        Set<String> worlds = new HashSet<>(getWorlds());
        worlds.add(worldName);

        settingsManager.setProperty(ConfigHolder.WORLDS, worlds);
        settingsManager.save();

        return true;

    }

    /**
     * @param worldName The world name.
     * @return True if the world name is removed from the config file,
     *         False if isn't contained.
     */
    public static boolean removeWorld(@NotNull String worldName) {

        if (worldName.isEmpty()) {
            throw new IllegalArgumentException("The world name argument cannot be empty");
        }

        if (!containsWorld(worldName)) {
            return false;
        }

        assert settingsManager != null;
        Set<String> worlds = new HashSet<>(getWorlds());
        worlds.remove(worldName);

        settingsManager.setProperty(ConfigHolder.WORLDS, worlds);
        settingsManager.save();

        return true;

    }

    //
    // PRIVATE METHODS
    //

    /**
     * @throws IllegalStateException If the settings manager instance is null,
     *                               so if the configuration is not loaded.
     */
    private static void checkSettings() {

        if (settingsManager != null) {
            return;
        }

        throw new IllegalStateException("the config " + CONFIG_FILE_NAME + " is not loaded");

    }

}
