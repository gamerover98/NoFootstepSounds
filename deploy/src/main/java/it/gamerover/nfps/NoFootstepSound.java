package it.gamerover.nfps;

import it.gamerover.nfps.support.flat.FlatHandler;
import it.gamerover.nfps.support.legacy.LegacyHandler;
import it.gamerover.nfps.reflection.ReflectionException;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class NoFootstepSound extends JavaPlugin {

    private static final String PROTOCOL_LIB_PLUGIN_NAME = "ProtocolLib";

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

            boolean canThrowException = false;

            ServerVersion latestVersion = ServerVersion.getLatest(false);
            String message = "Cannot find the current server version, "
                    + "attempting to start the plugin with the latest ("
                    + latestVersion.getVersion() + ") supported version ...";

            getLogger().log(Level.WARNING, message);

            try {

                CoreHandler.init(true);

            } catch (ReflectionException secondEx) {

                getLogger().severe("Cannot start " + getName() + " with the current server version");

                isPluginStartable = false;
                canThrowException = true;

            }

            if (canThrowException) {

                message = "Please report this error on the issues page on GitHub or Spigot forum";
                getLogger().log(Level.SEVERE, message, firstEx);

            }

        }

        PluginManager pluginManager = getServer().getPluginManager();
        boolean protocolLibInstalled = pluginManager.getPlugin(PROTOCOL_LIB_PLUGIN_NAME) != null;

        if (!protocolLibInstalled) {

            isPluginStartable = false;
            getLogger().severe("Cannot start " + getName() + " because "
                    + PROTOCOL_LIB_PLUGIN_NAME + " isn't installed!");

        }

        if (!isPluginStartable) {
            return;
        }

        ServerVersion serverVersion = CoreHandler.getServerVersion();
        
        try {

            if (serverVersion.isLegacy()) {
                handler = new LegacyHandler(this);
            } else {
                handler = new FlatHandler(this);
            }

            handler.pluginLoading();

        } catch (Exception generalEx) {

            isPluginStartable = false;
            getLogger().log(Level.SEVERE, "Cannot initialize the handler", generalEx);

        }


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

        if (handler == null) {
            return;
        }

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
