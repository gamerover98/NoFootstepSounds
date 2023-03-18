package it.gamerover.nfps.reflection.craftbukkit;

import it.gamerover.nfps.reflection.RawServerVersion;
import it.gamerover.nfps.reflection.Reflection;
import it.gamerover.nfps.reflection.ReflectionException;

public class CBReflection extends Reflection {

    private static final String CRAFTBUKKIT_PACKAGE = "org.bukkit.craftbukkit";

    private final String craftBukkitPackage;

    public CBReflection(RawServerVersion rawServerVersion) {
        this.craftBukkitPackage = CRAFTBUKKIT_PACKAGE + ".v" + rawServerVersion.getRaw();
    }

    public Class<?> getCraftBukkitClass(String className) throws ReflectionException {
        return getCraftBukkitClass("", className);
    }

    public Class<?> getCraftBukkitClass(String packageName, String className) throws ReflectionException {
        return super.getClass(this.craftBukkitPackage + packageName, className);
    }

}

