package it.gamerover.nfps.support.flat.packet;

import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory;
import it.gamerover.nfps.ServerVersion;
import it.gamerover.nfps.packet.SoundPacketAdapter;
import it.gamerover.nfps.support.interfaces.ISoundChecker;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import com.comphenix.packetwrapper.WrapperPlayServerNamedSoundEffect;

public class FlatSoundPacketAdapter extends SoundPacketAdapter {

    /**
     * The SoundChecker implementation.
     */
    private final ISoundChecker<Sound> soundChecker;

    public FlatSoundPacketAdapter(@NotNull Plugin plugin, @NotNull ServerVersion currentVersion) {

        super(plugin, currentVersion);
        soundChecker = new LatestFlatSoundChecker();

    }

    @Override
    protected boolean handleSoundPacketSending(@NotNull Player player, @NotNull PacketContainer packet) {

        WrapperPlayServerNamedSoundEffect wrapperPacket = new WrapperPlayServerNamedSoundEffect(packet);
        SoundCategory soundCategory = wrapperPacket.getSoundCategory();

        if (soundCategory != SoundCategory.PLAYERS) {
            return false;
        }

        Sound sound = wrapperPacket.getSoundEffect();
        return soundChecker.isFootstepSound(sound);

    }

    /**
     * 1.16.5 implementation.
     */
    private static class LatestFlatSoundChecker implements ISoundChecker<Sound> {

        @Override
        public boolean isFootstepSound(@NotNull Sound sound) {

            switch (sound) {
                case BLOCK_ANVIL_STEP:
                case BLOCK_GLASS_STEP:
                case BLOCK_GRASS_STEP:
                case BLOCK_GRAVEL_STEP:
                case BLOCK_LADDER_STEP:
                case BLOCK_METAL_STEP:
                case BLOCK_SAND_STEP:
                case BLOCK_SNOW_STEP:
                case BLOCK_STONE_STEP:
                case BLOCK_WOOD_STEP:
                case ENTITY_CHICKEN_STEP:
                case ENTITY_COW_STEP:
                case ENTITY_ENDERMITE_STEP:
                case ENTITY_HORSE_STEP:
                case ENTITY_HUSK_STEP:
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
                case ENTITY_ZOMBIE_VILLAGER_STEP:
                case BLOCK_CORAL_BLOCK_STEP:
                case BLOCK_SLIME_BLOCK_STEP:
                case BLOCK_WET_GRASS_STEP:
                case BLOCK_WOOL_STEP:
                case ENTITY_DROWNED_STEP:
                case ENTITY_IRON_GOLEM_STEP:
                case ENTITY_HORSE_STEP_WOOD:
                case ENTITY_SKELETON_HORSE_STEP_WATER: return true;
                default: return false;
            }

        }

    }

}
