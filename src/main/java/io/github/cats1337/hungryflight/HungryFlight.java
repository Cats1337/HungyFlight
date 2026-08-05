package io.github.cats1337.hungryflight;

import io.github.cats1337.hungryflight.commands.Commands;
import io.github.cats1337.hungryflight.events.Interact;
import io.github.cats1337.hungryflight.runnable.ActionBar;
import io.github.cats1337.hungryflight.runnable.HungerCheck;
import io.github.cats1337.hungryflight.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class HungryFlight extends JavaPlugin {

    private static HungryFlight Instance;

    public static HungryFlight getInstance() {
        return Instance;
    }

    @Override
    public void onEnable() {
        Instance = this;

        saveDefaultConfig();
        getLogger().info("Plugin loaded!");

        new HungerCheck().runTaskTimer(
                this,
                0L,
                20L * ConfigUtils.getIntegerFromConfig("settings.features.Costing.Timer.time")
        );

        new ActionBar().runTaskTimer(this, 0L, 20L);

        Bukkit.getPluginManager().registerEvents(new Interact(), this);

        Commands Commands = new Commands();

        Objects.requireNonNull(getCommand("fly")).setExecutor(Commands);
        Objects.requireNonNull(getCommand("flyadmin")).setExecutor(Commands);
    }

    @Override
    public void onDisable() {
        getLogger().info("HungryFlight disabled!");
    }
}
