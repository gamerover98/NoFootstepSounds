package it.gamerover.nfps;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class NoFootstepSound extends JavaPlugin {

    /**
     * Gets the NoFootstepSound plugin instance.
     */
    @Getter
    private static NoFootstepSound instance;

    /**
     * If true, the plugin will execute the onEnable method.
     */
    private boolean isPluginStartable = true;

    @Override
    @SuppressWarnings("squid:S2696") // Make the enclosing method "static" or remove this set.
    public void onLoad() {

        NoFootstepSound.instance = this;

    }

    @Override
    public void onEnable() {

        if (!isPluginStartable) {

            disablePlugin();
            return;

        }

        // nothing to do at the moment.

    }

    @Override
    public void onDisable() {
        // nothing to do at the moment.
    }

    /**
     * Disable the plugin with a warning message.
     */
    private void disablePlugin() {

        getLogger().warning("Disabling " + getName() + " - " + super.getDescription().getVersion());
        super.setEnabled(false);

    }

}
