package io.github.cats1337.hungyflight.commands;

import io.github.cats1337.hungyflight.utils.ConfigUtils;
import io.github.cats1337.hungyflight.utils.FlyToggle;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
  @ParametersAreNonnullByDefault
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("fly")) {
      if (args.length == 0)
        if (sender instanceof Player) {
          if (sender.hasPermission("hungyflight.fly")) {
            Player sp = (Player)sender;
            if (sp.getFoodLevel() > ConfigUtils.getIntegerFromConfig("settings.features.Costing.auto-disable")) {
              if (!FlyToggle.isFlying(sp)) {
                FlyToggle.flyEnable(sp);
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.fly-on"));
                return true;
              } 
              if (FlyToggle.isFlying(sp)) {
                FlyToggle.flyDisable(sp);
                sender.sendMessage(ConfigUtils.getMessageFromConfig("message.fly-off"));
                return true;
              } 
            } else {
              sender.sendMessage(ConfigUtils.getMessageFromConfig("message.lowhunger"));
              return true;
            } 
          } else {
            sender.sendMessage(ConfigUtils.getMessageFromConfig("message.no-permission"));
            return true;
          } 
        } else {
          sender.sendMessage(ConfigUtils.getMessageFromConfig("message.notplayer"));
          return true;
        }  
      if (sender.hasPermission("hungyflight.fly.other")) {
        Player p = Bukkit.getPlayerExact(args[0]);
        if (p == null) {
          sender.sendMessage(ConfigUtils.getMessageFromConfig("message.invalidplayer"));
          return true;
        } 
        if (p.getFoodLevel() > ConfigUtils.getIntegerFromConfig("settings.features.Costing.Timer.time")) {
          if (!FlyToggle.isFlying(p)) {
            FlyToggle.flyEnable(p);
            sender.sendMessage(ConfigUtils.getMessageFromConfig("message.flyother-on"));
            return true;
          } 
          if (FlyToggle.isFlying(p)) {
            FlyToggle.flyDisable(p);
            sender.sendMessage(ConfigUtils.getMessageFromConfig("message.flyother-off"));
            return true;
          } 
        } else {
          sender.sendMessage(ConfigUtils.getMessageFromConfig("message.targetlowhunger"));
          return true;
        } 
      } else {
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.no-permission"));
        return true;
      } 
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.top"));
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.plugin"));
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.fly"));
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.flyother"));
      if (sender.hasPermission("hungyflight.admin.reload"))
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.reload")); 
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.bottom"));
      return true;
    } 
    if (cmd.getName().equalsIgnoreCase("flyadmin")) {
      if (args.length == 1 && args[0]
        .equalsIgnoreCase("reload")) {
        if (sender.hasPermission("hungyflight.admin.reload")) {
          ConfigUtils.reloadConfig();
          sender.sendMessage(ConfigUtils.getMessageFromConfig("message.adminhelp-reloadconfig"));
          return true;
        } 
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.no-permission"));
        return true;
      } 
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.top"));
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.plugin"));
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.fly"));
      if (sender.hasPermission("hungyflight.fly.other"))
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.flyother")); 
      if (sender.hasPermission("hungyflight.admin.reload"))
        sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.reload")); 
      sender.sendMessage(ConfigUtils.getMessageFromConfig("message.help.bottom"));
      return true;
    } 
    return false;
  }
}
