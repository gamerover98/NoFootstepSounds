package it.gamerover.nfps;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import it.gamerover.nfps.config.ConfigManager;
import it.gamerover.nfps.packet.SoundPacketAdapter;
import it.gamerover.nfps.reflection.ReflectionContainer;
import it.gamerover.nfps.reflection.ReflectionException;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    protected void pluginLoading() {

        this.protocolManager = ProtocolLibrary.getProtocolManager();
        ConfigManager.reload(plugin);

    }

    protected void pluginEnabling() {

        if (protocolManager == null) {
            return;
        }

        Logger logger = plugin.getLogger();

        protocolManager.addPacketListener(getSoundPacketAdapter());
        logger.info(plugin.getName() + " successful enabled!");

    }

    protected void pluginDisabling() {

        if (protocolManager == null) {
            return;
        }

        Logger logger = plugin.getLogger();

        protocolManager.removePacketListeners(plugin);
        logger.info(plugin.getName() + " successful disabled!");

    }

    /**
     * Initialize the CoreHandler by loading static stuff like reflections and server version.
     * @throws ReflectionException Thrown due to a reflections error.
     */
    static void init(boolean force) throws ReflectionException {

        if (reflectionContainer == null) {
            reflectionContainer = new ReflectionContainer();
        }

        String stringVersion = reflectionContainer.getMinecraft().getMinecraftServer().getVersion();
        serverVersion = ServerVersion.getVersion(stringVersion);

        if (serverVersion == null) {

            if (force) {
                serverVersion = ServerVersion.getLatest(false);
            } else {
                throw new ReflectionException(stringVersion);
            }

        }

    }

}
