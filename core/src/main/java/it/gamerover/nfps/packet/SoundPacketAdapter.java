package it.gamerover.nfps.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import it.gamerover.nfps.reflection.ServerVersion;
import it.gamerover.nfps.config.ConfigManager;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class SoundPacketAdapter extends PacketAdapter {

    /**
     * Gets the not null current server version enum instance.
     */
    @Getter @NotNull
    protected final ServerVersion currentVersion;

    protected SoundPacketAdapter(@NotNull Plugin plugin, @NotNull ServerVersion currentVersion) {

        super(new AdapterParameteters()
                .plugin(plugin)
                .listenerPriority(ListenerPriority.HIGHEST)
                .types(PacketType.Play.Server.NAMED_SOUND_EFFECT));

        this.currentVersion = currentVersion;

    }

    @Override
    public void onPacketSending(PacketEvent event) {

        // this is the sound receiver and not the emitter!
        Player player = event.getPlayer();

        if (player == null || !player.isOnline()) {
            return;
        }

        PacketContainer packet = event.getPacket();
        boolean result = isFootStepSound(player, packet);

        if (thereAreNearbyPlayers(player)) {

            if (!ConfigManager.isAlwaysEnabled()) {

                World world = player.getWorld();
                String worldName = world.getName();

                // if the world is not listed, the sound will be sent to the player.
                if (!ConfigManager.containsWorld(worldName)) {
                    return;
                }

            }

            event.setCancelled(result);

        }

    }

    /**
     *
     * @param player The not null instance of the player.
     * @param packet The not null instance of the packet container.
     * @return True if the packet contains a footstep sound.
     */
    protected abstract boolean isFootStepSound(@NotNull Player player, @NotNull PacketContainer packet);

    /**
     * @param player The not null player instance.
     * @return True if there is at least one player near him.
     */
    @SuppressWarnings("squid:S1854") // SonarLint: Remove this useless assignment to local variable "nearbyPlayers".
    private boolean thereAreNearbyPlayers(@NotNull Player player) {

        double radius = ConfigManager.getRadius();
        List<Entity> nearbyEntities = player.getNearbyEntities(radius, radius, radius);

        for (Entity entity : nearbyEntities) {

            if (entity instanceof Player) {

                Player current = (Player) entity;

                if (current != player) {
                    return true;
                }

            }

        }

        return false;

    }

}
