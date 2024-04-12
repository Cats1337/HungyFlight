package io.github.cats1337.hungyflight.utils;

import io.github.cats1337.hungyflight.HungyFlight;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ConfigUtils {
  public static String getStringFromConfig(String path) {
    return HungyFlight.getInstance().getConfig().getString(path, "");
  }
  
  public static String getMessageFromConfig(String path) {
    return ChatColor.translateAlternateColorCodes('&', HungyFlight.getInstance().getConfig().getString(path, ""));
  }
  
  public static int getIntegerFromConfig(String path) {
    return HungyFlight.getInstance().getConfig().getInt(path, 0);
  }
  
  public static boolean getBooleanFromConfig(String path) {
    return HungyFlight.getInstance().getConfig().getBoolean(path, false);
  }
  
  public static void sendActionBar(Player player, String message) {
    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
  }
  
  public static void reloadConfig() {
    HungyFlight.getInstance().reloadConfig();
  }
}
