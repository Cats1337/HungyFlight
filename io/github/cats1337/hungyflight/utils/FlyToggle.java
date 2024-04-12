package io.github.cats1337.hungyflight.utils;

import org.bukkit.entity.Player;

public class FlyToggle {
  public static void flyEnable(Player p) {
    p.setAllowFlight(true);
  }
  
  public static void flyDisable(Player p) {
    p.setAllowFlight(false);
    p.setFlying(false);
  }
  
  public static boolean isFlying(Player p) {
    return p.getAllowFlight();
  }
}
