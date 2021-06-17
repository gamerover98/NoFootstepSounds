package it.gamerover.nfps.command;

import it.gamerover.nfps.CoreHandler;
import it.gamerover.nfps.config.ConfigManager;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.tozymc.spigot.api.command.CombinedCommand;
import xyz.tozymc.spigot.api.command.Command;
import xyz.tozymc.spigot.api.command.result.CommandResult;
import xyz.tozymc.spigot.api.command.result.TabResult;
import xyz.tozymc.spigot.api.util.bukkit.permission.PermissionWrapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddWorldCommand extends CombinedCommand {

    public static final String PERMISSION = PluginCommand.PERMISSION + ".add";

    private static final String COMMAND = "add";

    public AddWorldCommand(@NotNull Command parent) {
        super(parent, COMMAND);
    }

    @NotNull
    @Override
    public CommandResult onCommand(@NotNull CommandSender sender, @NotNull String[] params) {

        if (params.length == 0) {
            return CommandResult.WRONG_SYNTAX;
        }

        String worldName = params[0];
        boolean result = ConfigManager.addWorld(worldName);

        if (result) {

            CoreHandler coreHandler = CoreHandler.getInstance();
            assert coreHandler != null;

            String logMessage = "Added " + worldName + " into the config list";
            coreHandler.getPlugin().getLogger().info(logMessage);

            if (sender instanceof Player) {
                sender.sendMessage("§aAdded world §e" + worldName + "§a into the config list");
            }

        } else {
            sender.sendMessage("§cCannot add §4" + worldName + "§c because of it already added");
        }

        return CommandResult.SUCCESS;

    }

    @NotNull
    @Override
    public TabResult onTab(@NotNull CommandSender sender, @NotNull String[] params) {

        int length = params.length;

        if (length > 1) {
            return TabResult.EMPTY_RESULT;
        }

        CoreHandler coreHandler = CoreHandler.getInstance();
        assert coreHandler != null;

        List<World> worlds = coreHandler.getPlugin().getServer().getWorlds();
        Set<String> names = new HashSet<>();

        worlds.forEach(world -> names.add(world.getName()));

        String token = "";

        if (length != 0) {
            token = params[0];
        }

        return TabResult.of(token, names);

    }

    @NotNull
    @Override
    public PermissionWrapper getPermission() {
        return PermissionWrapper.of(PERMISSION);
    }

    @NotNull
    @Override
    public String getSyntax() {

        assert getParent() != null;
        Command parent = getParent();
        String parentCommand = parent.getAliases().stream().findAny().orElse(parent.getName());

        return "/" + parentCommand + " " + COMMAND + " <world-name>";

    }

    @NotNull
    @Override
    public String getDescription() {
        return "Add a world to the world list";
    }

}