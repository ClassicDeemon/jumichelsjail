package de.jumichel.jail;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandJail implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length != 1) {
            commandSender.sendMessage(ChatColor.GOLD + "" + MySql.PREFIX + ChatColor.WHITE + "Verwendung: /jail <Spielernamen>");
            return true;
        }

        String playerName = strings[0];
        Player target = Bukkit.getPlayer(playerName);

        if (target == null) {
            commandSender.sendMessage(ChatColor.GOLD + "" + MySql.PREFIX + ChatColor.WHITE + "Der Spieler " + playerName + " ist nicht online.");
            return true;
        }

        String uuid = getUUID(playerName);

        if(JailData.isInDatabase(uuid) == true) {
            target.removePotionEffect(PotionEffectType.CONFUSION);
            target.removePotionEffect(PotionEffectType.SLOW);
            JailData.setPlayerOutJail(uuid);
            commandSender.sendMessage(ChatColor.GOLD + "" + MySql.PREFIX + ChatColor.WHITE + target.getName() + " wurde entjailt.");
            target.sendMessage(ChatColor.GOLD + "" + MySql.PREFIX + ChatColor.WHITE + "Du wurdest von " + commandSender.getName() + " gejailt.");
        } else if(JailData.isInDatabase(uuid) == false) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 10, false, false));
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1000, false, false));
            JailData.setPlayerinJail(uuid);
            commandSender.sendMessage(ChatColor.GOLD + "" + MySql.PREFIX + ChatColor.WHITE + target.getName() + " wurde gejailt.");
        } else {
            commandSender.sendMessage(ChatColor.GOLD + "" + MySql.PREFIX + ChatColor.WHITE + "Ein Fehler ist aufgetreten.");
        }
        return true;
    }

    private String getUUID(String playerName) {
        return Bukkit.getOfflinePlayer(playerName).getUniqueId().toString();
    }
}
