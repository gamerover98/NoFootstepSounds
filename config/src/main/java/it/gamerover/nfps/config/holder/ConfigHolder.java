package it.gamerover.nfps.config.holder;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;
import ch.jalu.configme.properties.StringSetProperty;

import java.util.*;

import static it.gamerover.nfps.util.YamlUtil.*;
import static ch.jalu.configme.properties.PropertyInitializer.*;

@SuppressWarnings("squid:S2386") // SonarLint: Make DEF_WORLDS member "protected".
public class ConfigHolder implements SettingsHolder {

    public static final double      DEF_RADIUS         = 0.75D;
    public static final boolean     DEF_ALWAYS_ENABLED = false;
    public static final Set<String> DEF_WORLDS         = new HashSet<>(Collections.singletonList("YourWorldName"));

    @Comment("The radius of the circle between players where to prevent the sound of footsteps")
    public static final Property<Double> RADIUS;

    @Comment("True if you want to enable this feature throughout the worlds")
    public static final Property<Boolean> ALWAYS_ENABLED;

    @Comment({
            "If the always-enabled property is false, this list will prevent",
            "footstep sounds throughout the listed worlds"
    })
    public static final Property<Set<String>> WORLDS;

    static {

        RADIUS         = newProperty(createProperty("radius"),            DEF_RADIUS);
        ALWAYS_ENABLED = newProperty(createProperty("always", "enabled"), DEF_ALWAYS_ENABLED);
        WORLDS         = new StringSetProperty(createProperty("worlds"),  DEF_WORLDS);

    }

    private ConfigHolder() {
        // nothing to do
    }

}
