package io.github.cats1337.hungyflight;

import io.github.cats1337.hungyflight.commands.Commands;
import io.github.cats1337.hungyflight.events.Interact;
import io.github.cats1337.hungyflight.runnable.ActionBar;
import io.github.cats1337.hungyflight.runnable.HungerCheck;
import io.github.cats1337.hungyflight.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class HungyFlight extends JavaPlugin {
  public static HungyFlight getInstance() {
    return (HungyFlight)getPlugin(HungyFlight.class);
  }
  
  public void onEnable() {
    saveDefaultConfig();
    getLogger().info("Plugin loaded!");
    (new HungerCheck()).runTaskTimer((Plugin)getInstance(), 0L, 20L * ConfigUtils.getIntegerFromConfig("settings.features.Costing.Timer.time"));
    (new ActionBar()).runTaskTimer((Plugin)getInstance(), 0L, 20L);
    Bukkit.getPluginManager().registerEvents((Listener)new Interact(), (Plugin)getInstance());
    Bukkit.getPluginCommand("fly").setExecutor((CommandExecutor)new Commands());
    Bukkit.getPluginCommand("flyadmin").setExecutor((CommandExecutor)new Commands());
  }
  
  public void onDisable() {
    getLogger().info("Plugin unloaded!");
  }
}
