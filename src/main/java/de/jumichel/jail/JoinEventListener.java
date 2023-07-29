package de.jumichel.jail;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

public class JoinEventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        String uuid = Bukkit.getOfflinePlayer(player.getName()).getUniqueId().toString();

        if (JailData.isInDatabase(uuid)) {
            player.removePotionEffect(PotionEffectType.CONFUSION);
            player.removePotionEffect(PotionEffectType.SLOW);
            player.sendMessage(ChatColor.GOLD + "" + MySql.PREFIX + ChatColor.WHITE + " Du bist immer noch gejailt.");
        }

    }
}
