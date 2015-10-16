package me.JakeyTheDev.Hub.Listeners.GlobalListeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.JakeyTheDev.Hub.Core;
import me.JakeyTheDev.Hub.Methods.GiveItems;
import me.JakeyTheDev.Hub.Methods.SendWelcomeMessage;
import me.JakeyTheDev.Hub.Utils.ChatUtil;
import me.JakeyTheDev.Hub.Utils.Packets.ActionBar;
import me.JakeyTheDev.Hub.Utils.Packets.TabTitle;
import me.JakeyTheDev.Hub.Utils.Packets.Title;
import me.JakeyTheDev.Hub.Utils.Scoreboard.ScoreboardManagers;

public class JoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		final Player player = e.getPlayer();
		
		ScoreboardManagers.createScoreboard(player);

		if(player.isOp()) {
			TabTitle.sendTabTitle(player, 
					ChatColor.GOLD + "---> " + ChatColor.AQUA + 
					"Welcome to Combat Cove!" + ChatColor.GOLD + " <---", ChatColor.GREEN + "We are currently in 1.8!");

		} else {
			TabTitle.sendTabTitle(player, 
					ChatColor.GOLD + "---> " + ChatColor.AQUA + 
					"Welcome to Combat Cove!" + ChatColor.GOLD + " <---", ChatColor.GREEN + 
					"We are currently in 1.8! \n Donate using /buy! Donating removes ads!");

		}

		e.setJoinMessage("");

		for(int i = 1; i < 100; i++) {
			ChatUtil.sendMessage(player, ChatUtil.NONE, "");
		}
		SendWelcomeMessage.sendWelcome(player);

		player.getInventory().clear();

		GiveItems.giveItems(player);

		for (Player all : Bukkit.getOnlinePlayers()) {
			ActionBar.sendActionbar(all,ChatColor.GOLD +  player.getName() + ChatColor.GREEN + " has joined the game!");
			
			ChatUtil.sendMessage(all, ChatUtil.JOIN, player.getName());
		}
		Title.sendTitle(player, ChatColor.GREEN + "Welcome to", ChatColor.AQUA + "Combat Cove");
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				
				ScoreboardManagers.createScoreboard(player);
				
			}
		}, 5*20, 5*20);
	}
}
