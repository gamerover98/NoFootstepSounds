package it.gamerover.nfps.support.legacy.packet;

import com.comphenix.packetwrapper.WrapperPlayServerNamedSoundEffect;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory;
import it.gamerover.nfps.reflection.ServerVersion;
import it.gamerover.nfps.packet.SoundPacketAdapter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import it.gamerover.nfps.support.interfaces.ISoundChecker;

public class LegacySoundPacketAdapter extends SoundPacketAdapter {

    /**
     * 1.8 protocol wiki: https://wiki.vg/index.php?title=Protocol&oldid=7368#Sound_Effect
     * <b>The first field of the Sound Effect packet is the sound name.</b>
     */
    private static final int PACKET_SOUND_PROPERTY_INDEX_V1_8 = 0;

    /**
     * The SoundChecker implementation.
     */
    private final ISoundChecker<?> soundChecker;

    public LegacySoundPacketAdapter(@NotNull Plugin plugin, @NotNull ServerVersion currentVersion) {

        super(plugin, currentVersion);

        if (ServerVersion.is1_8_8(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_8.SoundChecker();
        } else if (ServerVersion.is1_9(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_9.SoundChecker();
        } else if (ServerVersion.is1_10(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_10.SoundChecker();
        } else if (ServerVersion.is1_11(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_11.SoundChecker();
        } else if (ServerVersion.is1_12(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_12.SoundChecker();
        } else {
            throw new IllegalStateException("Your current legacy server version is not supported!");
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean isFootStepSound(@NotNull Player player, @NotNull PacketContainer packet) {

        if (ServerVersion.is1_8_8(currentVersion)) {

            // missed PacketWrapper class for 1.8
            StructureModifier<String> stringStructureModifier = packet.getStrings();
            String sound = stringStructureModifier.read(PACKET_SOUND_PROPERTY_INDEX_V1_8);

            return ((ISoundChecker<String>) soundChecker).isFootstepSound(sound);

        }

        WrapperPlayServerNamedSoundEffect wrapperPacket = new WrapperPlayServerNamedSoundEffect(packet);
        SoundCategory soundCategory = wrapperPacket.getSoundCategory();

        if (soundCategory != SoundCategory.PLAYERS) {
            return false;
        }

        Sound sound = wrapperPacket.getSoundEffect();
        return ((ISoundChecker<Sound>) soundChecker).isFootstepSound(sound);

    }

}
