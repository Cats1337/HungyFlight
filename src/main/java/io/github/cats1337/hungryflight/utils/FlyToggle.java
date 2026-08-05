package io.github.cats1337.hungryflight.utils;

import org.bukkit.entity.Player;

public final class FlyToggle {

    private FlyToggle() {
    }

    public static void flyEnable(Player player) {
        player.setAllowFlight(true);
    }

    public static void flyDisable(Player player) {
        player.setFlying(false);
        player.setAllowFlight(false);
    }

    public static boolean isFlying(Player player) {
        return player.getAllowFlight();
    }
}