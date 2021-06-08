package it.gamerover.nfps.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import it.gamerover.nfps.ServerVersion;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

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
        event.setCancelled(handleSoundPacketSending(player, packet));

    }

    /**
     *
     * @param player The not null instance of the player.
     * @param packet The not null instance of the packet container.
     * @return True if the event is cancelled.
     */
    protected abstract boolean handleSoundPacketSending(@NotNull Player player, @NotNull PacketContainer packet);

}
