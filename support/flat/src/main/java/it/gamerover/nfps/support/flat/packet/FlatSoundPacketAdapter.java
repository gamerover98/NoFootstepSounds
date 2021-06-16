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

        if (ServerVersion.is1_13(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_13.SoundChecker();
        } else if (ServerVersion.is1_14(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_14.SoundChecker();
        } else {
            soundChecker = new LatestFlatSoundChecker();
        }

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
                case BLOCK_CORAL_BLOCK_STEP:
                case BLOCK_SLIME_BLOCK_STEP:
                case BLOCK_WET_GRASS_STEP:
                case BLOCK_WOOL_STEP:
                case BLOCK_BAMBOO_STEP:
                case BLOCK_BASALT_STEP:
                case BLOCK_CHAIN_STEP:
                case BLOCK_FUNGUS_STEP:
                case BLOCK_LANTERN_STEP:
                case BLOCK_LODESTONE_STEP:
                case BLOCK_NETHERRACK_STEP:
                case BLOCK_NYLIUM_STEP:
                case BLOCK_ROOTS_STEP:
                case BLOCK_SCAFFOLDING_STEP:
                case BLOCK_SHROOMLIGHT_STEP:
                case BLOCK_STEM_STEP:
                case BLOCK_VINE_STEP:
                case BLOCK_ANCIENT_DEBRIS_STEP:
                case BLOCK_BONE_BLOCK_STEP:
                case BLOCK_GILDED_BLACKSTONE_STEP:
                case BLOCK_HONEY_BLOCK_STEP:
                case BLOCK_NETHER_BRICKS_STEP:
                case BLOCK_NETHER_ORE_STEP:
                case BLOCK_NETHER_SPROUTS_STEP:
                case BLOCK_NETHERITE_BLOCK_STEP:
                case BLOCK_SOUL_SAND_STEP:
                case BLOCK_SOUL_SOIL_STEP:
                case BLOCK_WART_BLOCK_STEP:
                case BLOCK_WEEPING_VINES_STEP:
                case BLOCK_NETHER_GOLD_ORE_STEP: return true;
                default: return false;
            }

        }

    }

}
