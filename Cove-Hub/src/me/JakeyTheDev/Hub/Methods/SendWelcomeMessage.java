package me.JakeyTheDev.Hub.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SendWelcomeMessage {
	
	public static void sendWelcome(Player player) {
		
		player.sendMessage(ChatColor.AQUA.toString() + 
				ChatColor.STRIKETHROUGH + "===============================================");
		player.sendMessage(ChatColor.GREEN + "Welcome back to Combat Cove!");
		player.sendMessage(ChatColor.AQUA + "To Transport between games/servers please");
		player.sendMessage(ChatColor.YELLOW + "Have a great day from the staff team of Combat Cove!");
		player.sendMessage(ChatColor.AQUA.toString() +
				ChatColor.STRIKETHROUGH + "===============================================");
		
	}

}
