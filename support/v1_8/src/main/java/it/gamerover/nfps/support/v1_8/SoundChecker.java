package it.gamerover.nfps.support.v1_8;

import it.gamerover.nfps.support.interfaces.ISoundChecker;
import org.jetbrains.annotations.NotNull;

/**
 * Why String instead of Sound class?
 * Read the isFootstepSound(String sound) method comment.
 */
public class SoundChecker implements ISoundChecker<String> {

    private static final String STEP_VALUE_NAME = "step";

    /**
     * Due to the textual sound name in the Sound Effect packet,
     * You can't use the org.bukkit.Sound enum class.
     *
     * For instance:
     *  Packet textual sound: org.bukkit.sound.step.grass
     *  Sound class doesn't have an enumeration reference for this sound type.
     *
     * @param sound The not null textual sound.
     * @return True if the sound contains the word "step".
     */
    @Override
    public boolean isFootstepSound(@NotNull String sound) {
        return sound.contains(STEP_VALUE_NAME);
    }

}
