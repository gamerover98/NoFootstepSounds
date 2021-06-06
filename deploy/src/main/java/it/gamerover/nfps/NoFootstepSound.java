package it.gamerover.nfps;

import it.gamerover.nfps.flat.FlatHandler;
import it.gamerover.nfps.legacy.LegacyHandler;
import it.gamerover.nfps.reflection.ReflectionException;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class NoFootstepSound extends JavaPlugin {

    /**
     * Gets the NoFootstepSound plugin instance.
     */
    @Getter
    private static NoFootstepSound instance;

    /**
     * Gets the plugin handler.
     *
     * > If the server is a legacy version (1.8.8 - 1.12.2) the handler will be LegacyHandler.
     * > If the server is a flat version (1.13+) the handler will be FlatHandler.
     */
    @Getter
    private CoreHandler handler;

    /**
     * If true, the plugin will execute the onEnable method.
     */
    private boolean isPluginStartable = true;

    @Override
    @SuppressWarnings("squid:S2696") // Make the enclosing method "static" or remove this set.
    public void onLoad() {

        NoFootstepSound.instance = this;

        try {

            CoreHandler.init(false);

        } catch (ReflectionException firstEx) {

            String serverVersion = firstEx.getMessage();
            ServerVersion latestVersion = ServerVersion.getLatest(false);

            String message = "Cannot find " + serverVersion + " server version." +
                    " Attempting to start it with " + latestVersion.getVersion();
            getLogger().warning(message);

            try {

                CoreHandler.init(true);

            } catch (ReflectionException secondEx) {

                getLogger().severe("Cannot start " + getName() + " with the current server version");
                isPluginStartable = false;

            }

        }

        if (!isPluginStartable) {
            return;
        }

        ServerVersion serverVersion = CoreHandler.getServerVersion();
        
        if (serverVersion.isLegacy()) {
            handler = new LegacyHandler(this);
        } else {
            handler = new FlatHandler(this);
        }

        handler.pluginLoading();

    }

    @Override
    public void onEnable() {

        if (!isPluginStartable) {

            disablePlugin();
            return;

        }

        handler.pluginEnabling();

    }

    @Override
    public void onDisable() {
        handler.pluginDisabling();
    }

    /**
     * Disable the plugin with a warning message.
     */
    private void disablePlugin() {

        getLogger().warning("Disabling " + getName() + " - " + super.getDescription().getVersion());
        super.setEnabled(false);

    }

}
