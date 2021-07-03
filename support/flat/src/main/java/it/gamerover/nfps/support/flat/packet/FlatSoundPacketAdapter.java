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

    private static final String STEP_SOUND_BLOCK_START_NAME = "BLOCK";
    private static final String STEP_SOUND_BLOCK_END_NAME   = "STEP";

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
        } else if (ServerVersion.is1_15(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_15.SoundChecker();
        } else if (ServerVersion.is1_16(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_16.SoundChecker();
        } else if (ServerVersion.is1_17(currentVersion)) {
            soundChecker = new it.gamerover.nfps.support.v1_17.SoundChecker();
        } else {
            soundChecker = new FutureFlatSoundChecker();
        }

    }

    @Override
    protected boolean isFootStepSound(@NotNull Player player, @NotNull PacketContainer packet) {

        WrapperPlayServerNamedSoundEffect wrapperPacket = new WrapperPlayServerNamedSoundEffect(packet);
        SoundCategory soundCategory = wrapperPacket.getSoundCategory();

        if (soundCategory != SoundCategory.PLAYERS) {
            return false;
        }

        Sound sound = wrapperPacket.getSoundEffect();
        return soundChecker.isFootstepSound(sound);

    }

    /**
     * Future versions and not supported yet implementations.
     */
    private static class FutureFlatSoundChecker implements ISoundChecker<Sound> {

        /**
         * The latest supported version by the plugin.
         */
        private final ISoundChecker<Sound> latestSupportedChecker = new it.gamerover.nfps.support.v1_17.SoundChecker();

        @Override
        public boolean isFootstepSound(@NotNull Sound sound) {

            boolean result = latestSupportedChecker.isFootstepSound(sound);

            if (!result) {

                /*
                 * if the name starts with BLOCK and ends with STEP.
                 * result will be true if: BLOCK_..._STEP
                 */
                String name = sound.name();
                result = name.startsWith(STEP_SOUND_BLOCK_START_NAME) && name.endsWith(STEP_SOUND_BLOCK_END_NAME);

            }

            return result;

        }

    }

}
