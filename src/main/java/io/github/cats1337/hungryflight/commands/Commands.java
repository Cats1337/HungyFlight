package io.github.cats1337.hungryflight.commands;

import io.github.cats1337.hungryflight.utils.ConfigUtils;
import io.github.cats1337.hungryflight.utils.FlyToggle;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String @NonNull [] args) {

        return switch (command.getName().toLowerCase()) {
            case "fly" -> handleFly(sender, args);
            case "flyadmin" -> handleFlyAdmin(sender, args);
            default -> false;
        };
    }

    private boolean handleFly(CommandSender sender, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.notplayer"));
                return true;
            }

            if (!player.hasPermission("hungryflight.fly")) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.no-permission"));
                return true;
            }

            int minimumFood = ConfigUtils.getIntegerFromConfig("settings.features.Costing.auto-disable");

            if (player.getFoodLevel() <= minimumFood) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.lowhunger"));
                return true;
            }

            toggleFlight(player);

            if (FlyToggle.isFlying(player)) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.fly-on"));
            } else {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.fly-off"));
            }

            return true;
        }

        if (args.length == 1) {
            if (!sender.hasPermission("hungryflight.fly.other")) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.no-permission"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.invalidplayer"));
                return true;
            }

            int minimumFood = ConfigUtils.getIntegerFromConfig("settings.features.Costing.auto-disable");

            if (target.getFoodLevel() <= minimumFood) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.targetlowhunger"));
                return true;
            }

            toggleFlight(target);

            if (FlyToggle.isFlying(target)) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.flyother-on"));
            } else {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.flyother-off"));
            }

            return true;
        }

        sendHelp(sender);
        return true;
    }

    private boolean handleFlyAdmin(CommandSender sender, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("hungryflight.admin.reload")) {
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.no-permission"));
                return true;
            }

            ConfigUtils.reloadConfig();
            sender.sendMessage(ConfigUtils.getMessageFromConfig("message.adminhelp-reloadconfig"));
            return true;
        }

        sendHelp(sender);
        return true;
    }

    private void toggleFlight(Player player) {
        if (FlyToggle.isFlying(player)) {
            FlyToggle.flyDisable(player);
        } else {
            FlyToggle.flyEnable(player);
        }
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.top"));
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.plugin"));
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.fly"));

        if (sender.hasPermission("hungryflight.fly.other")) {
            sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.flyother"));
        }

        if (sender.hasPermission("hungryflight.admin.reload")) {
            sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.reload"));
        }

        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.bottom"));
    }
}