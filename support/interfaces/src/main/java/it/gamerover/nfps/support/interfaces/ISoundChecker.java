package it.gamerover.nfps.support.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * @param <S> The Sound class of the current Bukkit version.
 */
public interface ISoundChecker<S> {

    /**
     * @param sound The not null instance of the sound class.
     * @return True if it is a footstep sound.
     */
    boolean isFootstepSound(@NotNull S sound);

}
