package io.github.cats1337.hungyflight.runnable;

import io.github.cats1337.hungyflight.HungyFlight;
import io.github.cats1337.hungyflight.utils.ConfigUtils;
import io.github.cats1337.hungyflight.utils.FlyToggle;
import io.github.cats1337.hungyflight.utils.Food;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class HungerCheck extends BukkitRunnable {
  public void run() {
    for (Iterator<Player> iterator = Bukkit.getOnlinePlayers().iterator(); iterator.hasNext(); ) {
      Player p = iterator.next();
      if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Timer.enable"))
        continue; 
      if (!FlyToggle.isFlying(p))
        continue; 
      if (p.hasPermission("hungyflight.nohunger"))
        continue; 
      if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR)
        continue; 
      int foodcost1 = ConfigUtils.getIntegerFromConfig("settings.groups.default.FoodCost");
      int foodcost2 = ConfigUtils.getIntegerFromConfig("settings.groups.second.FoodCost");
      String permission2 = ConfigUtils.getStringFromConfig("settings.groups.second.Permission");
      boolean perm2 = p.hasPermission(permission2);
      Bukkit.getScheduler().runTask((Plugin)HungyFlight.getInstance(), () -> {
            if (!p.isOnGround() || !p.isSwimming() || !p.isGliding()) {
              if (!perm2)
                Food.takeFood(p, foodcost1); 
              if (perm2)
                Food.takeFood(p, foodcost2); 
            } 
          });
    } 
  }
}
