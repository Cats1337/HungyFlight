package io.github.cats1337.hungyflight.utils;

import org.bukkit.entity.Player;

public class Food {
  public static void takeFood(Player p, int amount) {
    int pfood = p.getFoodLevel();
    int disable = ConfigUtils.getIntegerFromConfig("settings.features.Costing.auto-disable");
    if (pfood - amount <= disable) {
      p.setFoodLevel(Math.max(pfood - amount, 0));
      FlyToggle.flyDisable(p);
      p.sendMessage(ConfigUtils.getMessageFromConfig("message.auto-disable"));
    } else {
      p.setFoodLevel(Math.max(pfood - amount, 0));
    } 
  }
}
