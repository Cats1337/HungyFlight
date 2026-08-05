package io.github.cats1337.hungryflight.runnable;

import io.github.cats1337.hungryflight.utils.ConfigUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBar extends BukkitRunnable {

    @Override
    public void run() {
        if (!ConfigUtils.getBooleanFromConfig("message.Actionbar.enable")) {
            return;
        }

        Component Flying = ConfigUtils.getMessageFromConfig("message.Actionbar.flying");
        Component NoCost = ConfigUtils.getMessageFromConfig("message.Actionbar.flying-nocosthunger");
        String Permission = ConfigUtils.getStringFromConfig("settings.nohunger-permission");

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!player.getAllowFlight()) {
                continue;
            }

            if (player.getGameMode() == GameMode.CREATIVE
                    || player.getGameMode() == GameMode.SPECTATOR) {
                continue;
            }

            if (player.hasPermission(Permission)) {
                ConfigUtils.sendActionBar(player, NoCost);
            } else {
                ConfigUtils.sendActionBar(player, Flying);
            }
        }
    }
}