package io.github.cats1337.hungyflight.runnable;

import io.github.cats1337.hungyflight.HungyFlight;
import io.github.cats1337.hungyflight.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBar extends BukkitRunnable {
  public void run() {
    for (Player p : Bukkit.getOnlinePlayers()) {
      boolean enable = ConfigUtils.getBooleanFromConfig("message.Actionbar.enable");
      if (!enable)
        return; 
      if (!p.getAllowFlight())
        return; 
      if (p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR))
        return; 
      if (p.hasPermission(ConfigUtils.getStringFromConfig("settings.nohunger-permission"))) {
        Bukkit.getScheduler().runTaskLater((Plugin)HungyFlight.getInstance(), () -> ConfigUtils.sendActionBar(p, ConfigUtils.getMessageFromConfig("message.Actionbar.flying-nocosthunger")), 100L);
        ConfigUtils.sendActionBar(p, "");
        continue;
      } 
      ConfigUtils.sendActionBar(p, ConfigUtils.getMessageFromConfig("message.Actionbar.flying"));
      Bukkit.getScheduler().runTaskLater((Plugin)HungyFlight.getInstance(), () -> ConfigUtils.sendActionBar(p, ""), 100L);
    } 
  }
}
