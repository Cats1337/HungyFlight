package io.github.cats1337.hungryflight.utils;

import org.bukkit.entity.Player;

public final class Food {

    private Food() {
    }

    public static void takeFood(Player Player, int Amount) {
        int FoodLevel = Player.getFoodLevel();
        int DisableThreshold = ConfigUtils.getIntegerFromConfig(
                "settings.features.Costing.auto-disable"
        );

        int NewFoodLevel = Math.max(FoodLevel - Amount, 0);
        Player.setFoodLevel(NewFoodLevel);

        if (NewFoodLevel <= DisableThreshold) {
            FlyToggle.flyDisable(Player);
            Player.sendMessage(
                    ConfigUtils.getMessageFromConfig("message.auto-disable")
            );
        }
    }
}