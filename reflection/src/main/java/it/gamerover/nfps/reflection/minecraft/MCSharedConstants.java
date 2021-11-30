package it.gamerover.nfps.reflection.minecraft;

import it.gamerover.nfps.reflection.ReflectionException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;

/**
 * The SharedConstants class from net.minecraft.* is available from Spigot 1.8, but
 * it is used from Spigot 1.17!
 */
public final class MCSharedConstants extends MCReflection {

    private static final String SHARED_CONSTANTS_CLASS_NAME  = "SharedConstants";
    private static final String GET_GAME_VERSION_METHOD_NAME_N1 = "getGameVersion";
    private static final String GET_GAME_VERSION_METHOD_NAME_N2 = "b";

    /**
     * Gets the GameVersion instance from the current running server.
     * This field is null prior to Spigot 1.17.
     */
    @Getter @Nullable
    private final Object gameVersionInstance;

    @SuppressWarnings("squid:S2637")
    public MCSharedConstants(@NotNull String completeServerVersion) throws ReflectionException {

        super(completeServerVersion);

        Class<?> sharedConstantsClass = super.getMinecraftClass(SHARED_CONSTANTS_CLASS_NAME);

        Method getGameVersionMethod;
        Object gameVersion;

        try {

            getGameVersionMethod = super.getMethod(sharedConstantsClass, GET_GAME_VERSION_METHOD_NAME_N1);
            gameVersion = getGameVersionMethod.invoke(null);

        } catch (Exception ex1) {

            try {

                getGameVersionMethod = super.getMethod(sharedConstantsClass, GET_GAME_VERSION_METHOD_NAME_N2);
                gameVersion = getGameVersionMethod.invoke(null);

            } catch (Exception ex2) {

                String errorMessage = getMinecraftPackage() + "." + SHARED_CONSTANTS_CLASS_NAME
                        + "." + GET_GAME_VERSION_METHOD_NAME_N1 + "() method";
                throw new ReflectionException(errorMessage, ex2);

            }

        }

        this.gameVersionInstance = gameVersion;

    }

}
