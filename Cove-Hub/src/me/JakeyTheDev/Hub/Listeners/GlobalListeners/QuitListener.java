package me.JakeyTheDev.Hub.Listeners.GlobalListeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.JakeyTheDev.Hub.Utils.ChatUtil;
import me.JakeyTheDev.Hub.Utils.Packets.ActionBar;

public class QuitListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {

		Player player = e.getPlayer();
		
		e.setQuitMessage("");

		for (Player all : Bukkit.getOnlinePlayers()) {
			ActionBar.sendActionbar(all,ChatColor.GOLD +  player.getName() + ChatColor.GREEN + " has left the game!");
			ChatUtil.sendMessage(all, ChatUtil.LEAVE, player.getName());
		}
	}
}
