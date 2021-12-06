package it.gamerover.nfps;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import it.gamerover.nfps.command.PluginCommand;
import it.gamerover.nfps.config.ConfigManager;
import it.gamerover.nfps.packet.SoundPacketAdapter;
import it.gamerover.nfps.reflection.ReflectionContainer;
import it.gamerover.nfps.reflection.ReflectionException;
import it.gamerover.nfps.reflection.ServerVersion;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tozymc.spigot.api.command.CommandController;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class CoreHandler {

    @Getter @Nullable
    private static CoreHandler instance;

    /**
     * Gets the current server version.
     * <p>
     *     If the server version is not supported, it will be the latest
     *     available version supported by the plugin.
     * </p>
     */
    @Getter
    private static ServerVersion serverVersion;

    /**
     * Gets reflections.
     */
    @Getter
    private static ReflectionContainer reflectionContainer;

    /**
     * NoFootstepSound plugin instance.
     */
    @Getter @NotNull
    private final Plugin plugin;

    /**
     * Gets the ProtocolManager instance of ProtocolLib.
     */
    @Getter
    private ProtocolManager protocolManager;

    /**
     * Gets the command controller instance.
     */
    @Getter
    private CommandController commandController;

    /**
     * Gets the main plugin command.
     */
    @Getter
    private PluginCommand pluginCommand;

    @SuppressWarnings("squid:S3010") // SonarLint: Remove this assignment of "instance".
    protected CoreHandler(@NotNull Plugin plugin) {

        this.plugin = plugin;
        CoreHandler.instance = this;

    }

    /**
     * @return The not null Sound packet adapter implementation instance.
     */
    @NotNull
    protected abstract SoundPacketAdapter getSoundPacketAdapter();

    /**
     * Invoked when the plugin is loading.
     */
    protected void pluginLoading() {

        this.protocolManager = ProtocolLibrary.getProtocolManager();
        ConfigManager.reload(plugin);

    }

    /**
     * Invoked when the plugin is enabling.
     */
    protected void pluginEnabling() {

        if (protocolManager == null) {
            return;
        }

        Logger logger = plugin.getLogger();

        protocolManager.addPacketListener(getSoundPacketAdapter());

        commandController = new CommandController((JavaPlugin) plugin);
        pluginCommand     = new PluginCommand(commandController);

        logger.info(plugin.getName() + " successful enabled!");

    }

    /**
     * Invoked when the plugin is disabling.
     */
    protected void pluginDisabling() {

        if (protocolManager == null) {
            return;
        }

        Logger logger = plugin.getLogger();

        protocolManager.removePacketListeners(plugin);

        if (commandController != null && pluginCommand != null) {

            try {

                commandController.removeCommand(pluginCommand);

            } catch (IllegalStateException isEx) {
                // nothing to do.
            }

        }

        logger.info(plugin.getName() + " successful disabled!");

    }

    /**
     * Initialize the CoreHandler by loading static stuff like reflections and server version.
     *
     * @param plugin The not-null plugin instance.
     * @throws ReflectionException Thrown due to a reflection's error.
     */
    static void init(@NotNull Plugin plugin) throws ReflectionException {

        if (reflectionContainer == null) {
            reflectionContainer = new ReflectionContainer();
        }

        ServerVersion runningVersion = ServerVersion.getRunningServerVersion(reflectionContainer);
        Logger logger = plugin.getLogger();

        // The server running with an unknown server version.
        if (runningVersion == null) {

            ServerVersion latestVersion = ServerVersion.getLatest(false);
            String message = "Cannot find the current server version, "
                    + "attempting to start the plugin with the latest ("
                    + latestVersion.getVersion() + ") supported version ...";

            logger.log(Level.WARNING, message);
            runningVersion = latestVersion;

        } else {

            String message = "Detected " + runningVersion.getVersion() + " server version";
            logger.log(Level.INFO, message);

        }

        serverVersion = runningVersion;

    }

}
