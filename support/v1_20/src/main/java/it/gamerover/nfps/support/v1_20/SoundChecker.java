package it.gamerover.nfps.support.v1_20;

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
            case BLOCK_NETHER_GOLD_ORE_STEP:
            case BLOCK_MOSS_STEP:
            case BLOCK_AZALEA_STEP:
            case BLOCK_CALCITE_STEP:
            case BLOCK_CANDLE_STEP:
            case BLOCK_COPPER_STEP:
            case BLOCK_DEEPSLATE_STEP:
            case BLOCK_TUFF_STEP:
            case BLOCK_AMETHYST_BLOCK_STEP:
            case BLOCK_AMETHYST_CLUSTER_STEP:
            case BLOCK_AZALEA_LEAVES_STEP:
            case BLOCK_BIG_DRIPLEAF_STEP:
            case BLOCK_CAVE_VINES_STEP:
            case BLOCK_DEEPSLATE_BRICKS_STEP:
            case BLOCK_DEEPSLATE_TILES_STEP:
            case BLOCK_DRIPSTONE_BLOCK_STEP:
            case BLOCK_FLOWERING_AZALEA_STEP:
            case BLOCK_HANGING_ROOTS_STEP:
            case BLOCK_MOSS_CARPET_STEP:
            case BLOCK_POINTED_DRIPSTONE_STEP:
            case BLOCK_POLISHED_DEEPSLATE_STEP:
            case BLOCK_POWDER_SNOW_STEP:
            case BLOCK_ROOTED_DIRT_STEP:
            case BLOCK_SCULK_SENSOR_STEP:
            case BLOCK_SMALL_DRIPLEAF_STEP:
            case BLOCK_SPORE_BLOSSOM_STEP:
            case BLOCK_FROGLIGHT_STEP:
            case BLOCK_FROGSPAWN_STEP:
            case BLOCK_MANGROVE_ROOTS_STEP:
            case BLOCK_MUD_BRICKS_STEP:
            case BLOCK_MUD_STEP:
            case BLOCK_MUDDY_MANGROVE_ROOTS_STEP:
            case BLOCK_PACKED_MUD_STEP:
            case BLOCK_SCULK_CATALYST_STEP:
            case BLOCK_SCULK_SHRIEKER_STEP:
            case BLOCK_SCULK_STEP:
            case BLOCK_SCULK_VEIN_STEP:
            case BLOCK_BAMBOO_WOOD_HANGING_SIGN_STEP:
            case BLOCK_BAMBOO_WOOD_STEP:
            case BLOCK_CHISELED_BOOKSHELF_STEP:
            case BLOCK_HANGING_SIGN_STEP:
            case BLOCK_NETHER_WOOD_HANGING_SIGN_STEP:
            case BLOCK_NETHER_WOOD_STEP:
            case BLOCK_CHERRY_LEAVES_STEP:
            case BLOCK_CHERRY_SAPLING_STEP:
            case BLOCK_CHERRY_WOOD_STEP:
            case BLOCK_DECORATED_POT_STEP:
            case BLOCK_PINK_PETALS_STEP:
            case BLOCK_SUSPICIOUS_GRAVEL_STEP:
            case BLOCK_SUSPICIOUS_SAND_STEP: return true;
            default: return false;
        }

    }

}
