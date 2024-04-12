package io.github.cats1337.hungyflight.events;

import io.github.cats1337.hungyflight.utils.ConfigUtils;
import io.github.cats1337.hungyflight.utils.FlyToggle;
import io.github.cats1337.hungyflight.utils.Food;
import java.util.Objects;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Interact implements Listener {
  Boolean enable = Boolean.valueOf(ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.enable"));
  
  Boolean BlockBreak = Boolean.valueOf(ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.BlockBreak.enable"));
  
  Boolean BlockPlace = Boolean.valueOf(ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.BlockPlace.enable"));
  
  Boolean Attack = Boolean.valueOf(ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.Attack.enable"));
  
  Boolean Damaged = Boolean.valueOf(ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.BlockBreak.enable"));
  
  int BlockBreakAmount = ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.BlockBreak.cost");
  
  int BlockPlaceAmount = ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.BlockPlace.cost");
  
  int AttackAmount = ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.Attack.cost");
  
  int DamagedAmount = ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.Damaged.cost");
  
  @EventHandler
  public void onBreak(BlockBreakEvent e) {
    if (this.enable.booleanValue() && this.BlockBreak
      .booleanValue() && 
      FlyToggle.isFlying(e.getPlayer()))
      Food.takeFood(e.getPlayer(), this.BlockBreakAmount); 
  }
  
  @EventHandler
  public void onPlace(BlockPlaceEvent e) {
    if (this.enable.booleanValue() && this.BlockPlace
      .booleanValue() && 
      FlyToggle.isFlying(e.getPlayer()))
      Food.takeFood(e.getPlayer(), this.BlockPlaceAmount); 
  }
  
  @EventHandler
  public void onAttack(EntityDamageByEntityEvent e) {
    if (e.getDamager() instanceof Player && this.enable
      .booleanValue() && this.Attack
      .booleanValue() && 
      FlyToggle.isFlying(Objects.<Player>requireNonNull(((Player)e.getDamager()).getPlayer())))
      Food.takeFood(((Player)e.getDamager()).getPlayer(), this.AttackAmount); 
  }
  
  @EventHandler
  public void onDamaged(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player && this.enable
      .booleanValue() && this.Damaged
      .booleanValue() && 
      FlyToggle.isFlying((Player)e.getEntity()))
      Food.takeFood((Player)e.getEntity(), this.DamagedAmount); 
  }
}
