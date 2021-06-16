package it.gamerover.nfps.support.v1_13;

import it.gamerover.nfps.support.interfaces.ISoundChecker;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public class SoundChecker  implements ISoundChecker<Sound> {

    @Override
    @SuppressWarnings("DuplicatedCode") // same code with internal legacy SoundChecker impl.
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
            case BLOCK_WOOL_STEP: return true;
            default: return false;
        }

    }

}
