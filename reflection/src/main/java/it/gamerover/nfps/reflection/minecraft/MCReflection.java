package it.gamerover.nfps.reflection.minecraft;

import it.gamerover.nfps.reflection.Reflection;
import it.gamerover.nfps.reflection.ReflectionException;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Specific reflection class for the Native Minecraft package.
 */
public class MCReflection extends Reflection {

    /**
     * The number of the 1.17 Caves & Cliffs update.
     */
    private static final int CAVES_AND_CLIFFS_UPDATE_NUMBER = 17;

    /**
     * The not compliant Native Minecraft package.
     * This string will be concatenated with the complete server version.
     */
    private static final String BEFORE_1_17_MINECRAFT_PACKAGE = "net.minecraft.server";

    /**
     * The not compliant Native Minecraft package.
     * This string will be concatenated with the complete server version.
     */
    private static final String MINECRAFT_PACKAGE = "net.minecraft";

    /**
     * The complete Native Minecraft package (Ex: net.minecraft.server.v1_8_R3.ClassName).
     *
     * <p>
     *     From 1.17 the Minecraft package doesn't contain
     *     the revision version (Ex: net.minecraft.server.ClassName)
     * </p>
     */
    @Getter(AccessLevel.PROTECTED)
    private final String minecraftPackage;


    /**
     * @param completeServerVersion The NMS complete server version like V1_8_R3
     */
    public MCReflection(@NotNull String completeServerVersion) {
        this(completeServerVersion, null);
    }

    /**
     * @param completeServerVersion The NMS complete server version like V1_8_R3
     */
    public MCReflection(@NotNull String completeServerVersion, @Nullable String subPackage) {

        if (subPackage == null) {
            subPackage = "";
        } else {
            subPackage = subPackage.trim();
        }

        if (isBeforeCaveAndCliffsUpdate(completeServerVersion)) {

            if (subPackage.isEmpty()) {
                this.minecraftPackage = BEFORE_1_17_MINECRAFT_PACKAGE + '.' + completeServerVersion + '.' + subPackage;
            } else {
                this.minecraftPackage = BEFORE_1_17_MINECRAFT_PACKAGE + '.' + completeServerVersion;
            }

        } else {

            if (subPackage.isEmpty()) {
                this.minecraftPackage = MINECRAFT_PACKAGE;
            } else {
                this.minecraftPackage = MINECRAFT_PACKAGE + '.' + subPackage;
            }

        }

    }

    /**
     * Gets a Native Minecraft class from net.minecraft.server package.
     *
     * @param className The not null class name.
     * @return The not null Native class.
     * @throws ReflectionException If the class doesn't exist.
     */
    @NotNull
    public Class<?> getMinecraftClass(@NotNull String className) throws ReflectionException {
        return getMinecraftClass("", className);
    }

    /**
     * Gets a Native Minecraft class from net.minecraft.server package.
     *
     * @param packageName If in the future Spigot decide to add another sub-package, use it.
     *                    By default, this value is "".
     * @param className The not null class name.
     * @return The not null Native class.
     * @throws ReflectionException If the class doesn't exist.
     */
    @NotNull
    public Class<?> getMinecraftClass(@NotNull String packageName,
                                      @NotNull String className) throws ReflectionException {
        return super.getClass(this.minecraftPackage + packageName, className);
    }

    /**
     * @param version The not null complete server version: v1_16_R3
     * @return True if the server version is before 1.17 caves & cliffs update.
     */
    private static boolean isBeforeCaveAndCliffsUpdate(@NotNull String version) {

        if (!version.startsWith("v")) {
            throw new IllegalArgumentException("The minecraft version needs to start with 'v'");
        }

        version = version.substring(1);
        String[] split = version.split("_");

        int firstNumber  = Integer.parseInt(split[0]);
        int secondNumber = Integer.parseInt(split[1]);

        if (firstNumber != 1) {
            throw new IllegalArgumentException("The minecraft versions needs to start with 1.*");
        }

        return secondNumber < CAVES_AND_CLIFFS_UPDATE_NUMBER;

    }

}