package io.github.cats1337.hungryflight.runnable;

import io.github.cats1337.hungryflight.utils.ConfigUtils;
import io.github.cats1337.hungryflight.utils.FlyToggle;
import io.github.cats1337.hungryflight.utils.Food;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HungerCheck extends BukkitRunnable {

    @Override
    public void run() {
        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Timer.enable")) {
            return;
        }

        int DefaultCost = ConfigUtils.getIntegerFromConfig("settings.groups.default.FoodCost");
        int SecondCost = ConfigUtils.getIntegerFromConfig("settings.groups.second.FoodCost");
        String SecondPermission = ConfigUtils.getStringFromConfig("settings.groups.second.Permission");

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!FlyToggle.isFlying(player)) {
                continue;
            }

            if (player.hasPermission("hungryflight.nohunger")) {
                continue;
            }

            if (player.getGameMode() == GameMode.CREATIVE
                    || player.getGameMode() == GameMode.SPECTATOR) {
                continue;
            }

            if (!player.isFlying()
                    && !player.isSwimming()
                    && !player.isGliding()) {

                Food.takeFood(
                        player,
                        player.hasPermission(SecondPermission)
                                ? SecondCost
                                : DefaultCost
                );
            }
        }
    }
}