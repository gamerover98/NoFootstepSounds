package it.gamerover.nfps.support.legacy;

import it.gamerover.nfps.CoreHandler;
import it.gamerover.nfps.support.legacy.packet.LegacySoundPacketAdapter;
import it.gamerover.nfps.packet.SoundPacketAdapter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused") // This class is used from the deploy module into the main class.
public class LegacyHandler extends CoreHandler {

    private final LegacySoundPacketAdapter legacySoundPacketAdapter;

    public LegacyHandler(@NotNull Plugin plugin) {

        super(plugin);
        legacySoundPacketAdapter = new LegacySoundPacketAdapter(plugin);

    }

    @NotNull
    @Override
    protected SoundPacketAdapter getSoundPacketAdapter() {
        return legacySoundPacketAdapter;
    }

}