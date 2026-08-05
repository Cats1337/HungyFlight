package io.github.cats1337.hungryflight.utils;

import io.github.cats1337.hungryflight.HungryFlight;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public final class ConfigUtils {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private ConfigUtils() {
    }

    public static String getStringFromConfig(String path) {
        return HungryFlight.getInstance().getConfig().getString(path, "");
    }

    public static Component getMessageFromConfig(String path) {
        return MINI_MESSAGE.deserialize(
                HungryFlight.getInstance().getConfig().getString(path, "")
        );
    }

    public static int getIntegerFromConfig(String path) {
        return HungryFlight.getInstance().getConfig().getInt(path, 0);
    }

    public static boolean getBooleanFromConfig(String path) {
        return HungryFlight.getInstance().getConfig().getBoolean(path, false);
    }

    public static void sendActionBar(Player player, Component message) {
        player.sendActionBar(message);
    }

    public static void reloadConfig() {
        HungryFlight.getInstance().reloadConfig();
    }
}