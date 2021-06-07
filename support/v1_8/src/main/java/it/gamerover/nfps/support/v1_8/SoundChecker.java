package it.gamerover.nfps.support.v1_8;

import it.gamerover.nfps.support.interfaces.ISoundChecker;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public class SoundChecker implements ISoundChecker<Sound> {

    @Override
    public boolean isFootstepSound(@NotNull Sound sound) {

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
