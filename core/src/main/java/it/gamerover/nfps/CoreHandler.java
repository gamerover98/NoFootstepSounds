package it.gamerover.nfps;

import it.gamerover.nfps.reflection.ReflectionContainer;
import it.gamerover.nfps.reflection.ReflectionException;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @SuppressWarnings("squid:S3010") // SonarLint: Remove this assignment of "instance".
    protected CoreHandler(@NotNull Plugin plugin) {

        this.plugin = plugin;
        CoreHandler.instance = this;

    }

    protected void pluginLoading() {
        // plugin loading.
    }

    protected void pluginEnabling() {
        // plugin enabling.
    }

    protected void pluginDisabling() {
        // plugin disabling.
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
