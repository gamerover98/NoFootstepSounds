package it.gamerover.nfps.support.legacy.packet;

import com.comphenix.packetwrapper.WrapperPlayServerNamedSoundEffect;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory;
import it.gamerover.nfps.ServerVersion;
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
        } else {
            soundChecker = new LatestLegacySoundChecker();
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean handleSoundPacketSending(@NotNull Player player, @NotNull PacketContainer packet) {

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


    /**
     * 1.12.2 implementation.
     */
    private static class LatestLegacySoundChecker implements ISoundChecker<Sound> {

        @Override
        public boolean isFootstepSound(@NotNull Sound sound) {

            switch (sound) {
                case BLOCK_ANVIL_STEP:
                case BLOCK_CLOTH_STEP:
                case BLOCK_GLASS_STEP:
                case BLOCK_GRASS_STEP:
                case BLOCK_GRAVEL_STEP:
                case BLOCK_LADDER_STEP:
                case BLOCK_METAL_STEP:
                case BLOCK_SAND_STEP:
                case BLOCK_SLIME_STEP:
                case BLOCK_SNOW_STEP:
                case BLOCK_STONE_STEP:
                case BLOCK_WOOD_STEP:
                case ENTITY_CHICKEN_STEP:
                case ENTITY_COW_STEP:
                case ENTITY_ENDERMITE_STEP:
                case ENTITY_HORSE_STEP:
                case ENTITY_HUSK_STEP:
                case ENTITY_IRONGOLEM_STEP:
                case ENTITY_LLAMA_STEP:
                case ENTITY_PARROT_STEP:
                case ENTITY_PIG_STEP:
                case ENTITY_POLAR_BEAR_STEP:
                case ENTITY_SHEEP_STEP:
                case ENTITY_SILVERFISH_STEP:
                case ENTITY_SKELETON_STEP:
                case ENTITY_SPIDER_STEP:
                case ENTITY_STRAY_STEP:
                case ENTITY_WITHER_SKELETON_STEP:
                case ENTITY_WOLF_STEP:
                case ENTITY_ZOMBIE_STEP:
                case ENTITY_ZOMBIE_VILLAGER_STEP: return true;
                default: return false;
            }

        }

    }

}
