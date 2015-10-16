package me.JakeyTheDev.Core.Chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.JakeyTheDev.Core.PlayerData.PlayerData;
import me.JakeyTheDev.Core.Ranks.PermissionsManager;	

public class AsyncChatListener extends AbstractListener {

	@EventHandler
	public void onAsyncChat(final AsyncPlayerChatEvent event) {
		PlayerData player = PlayerData.players.get(event.getPlayer());
		PermissionsManager rank = player.rank;
		if(rank.rank >= 7) {
			Bukkit.broadcastMessage(rank.gamePrefix + " " + rank.nameColor + event.getPlayer().getDisplayName() + ChatColor.WHITE + ": " + rank.textColor + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
			event.setCancelled(true);
			return;
		}
		Bukkit.broadcastMessage(rank.gamePrefix + " " + rank.nameColor + event.getPlayer().getDisplayName() + ChatColor.WHITE + ": " + rank.textColor + event.getMessage());
		event.setCancelled(true);
		return;
	}
}