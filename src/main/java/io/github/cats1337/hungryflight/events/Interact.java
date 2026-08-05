package io.github.cats1337.hungryflight.events;

import io.github.cats1337.hungryflight.utils.ConfigUtils;
import io.github.cats1337.hungryflight.utils.FlyToggle;
import io.github.cats1337.hungryflight.utils.Food;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Interact implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.enable")) {
            return;
        }

        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.BlockBreak.enable")) {
            return;
        }

        Player player = event.getPlayer();

        if (FlyToggle.isFlying(player)) {
            Food.takeFood(
                    player,
                    ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.BlockBreak.cost")
            );
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.enable")) {
            return;
        }

        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.BlockPlace.enable")) {
            return;
        }

        Player player = event.getPlayer();

        if (FlyToggle.isFlying(player)) {
            Food.takeFood(
                    player,
                    ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.BlockPlace.cost")
            );
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.enable")) {
            return;
        }

        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.Attack.enable")) {
            return;
        }

        if (FlyToggle.isFlying(player)) {
            Food.takeFood(
                    player,
                    ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.Attack.cost")
            );
        }
    }

    @EventHandler
    public void onDamaged(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.enable")) {
            return;
        }

        if (!ConfigUtils.getBooleanFromConfig("settings.features.Costing.Interaction.features.Damaged.enable")) {
            return;
        }

        if (FlyToggle.isFlying(player)) {
            Food.takeFood(
                    player,
                    ConfigUtils.getIntegerFromConfig("settings.features.Costing.Interaction.features.Damaged.cost")
            );
        }
    }
}