package it.gamerover.nfps.support.legacy.packet;

import com.comphenix.packetwrapper.WrapperPlayServerNamedSoundEffect;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory;
import it.gamerover.nfps.packet.SoundPacketAdapter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class LegacySoundPacketAdapter extends SoundPacketAdapter {

    public LegacySoundPacketAdapter(@NotNull Plugin plugin) {
        super(plugin);
    }

    @Override
    protected boolean handleSoundPacketSending(@NotNull Player player, @NotNull PacketContainer packet) {

        WrapperPlayServerNamedSoundEffect wrapperPacket = new WrapperPlayServerNamedSoundEffect(packet);
        SoundCategory soundCategory = wrapperPacket.getSoundCategory();

        if (soundCategory != SoundCategory.PLAYERS) {
            return false;
        }

        Sound sound = wrapperPacket.getSoundEffect();
        return isStepSound(sound);

    }

    /**
     * @param sound The not null enumeration instance of the sound.
     * @return True if the sound is a step type.
     */
    private boolean isStepSound(@NotNull Sound sound) {

        switch (sound) {
            case STEP_GRASS:
            case STEP_GRAVEL:
            case STEP_LADDER:
            case STEP_SAND:
            case STEP_SNOW:
            case STEP_STONE:
            case STEP_WOOD:
            case STEP_WOOL: return true;
            default: return false;
        }

    }

}
