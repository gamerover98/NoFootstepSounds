package it.gamerover.nfps.support.v1_16;

import it.gamerover.nfps.support.interfaces.ISoundChecker;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public class SoundChecker  implements ISoundChecker<Sound> {

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
