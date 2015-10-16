package me.JakeyTheDev.Hub.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum ChatUtil {
	
	NONE("", ChatColor.WHITE.toString(), ChatColor.WHITE.toString()),
	COOLDOWN("COOLDOWN", ChatColor.DARK_RED.toString() + ChatColor.BOLD, ChatColor.RED.toString()),
	PERMISSIONS("PERMISSIONS", ChatColor.BLUE.toString(), ChatColor.BLUE.toString()),
	JOIN("JOIN", ChatColor.DARK_GREEN.toString() + ChatColor.BOLD, ChatColor.GREEN.toString()),
	LEAVE("LEAVE", ChatColor.DARK_RED.toString() + ChatColor.BOLD, ChatColor.RED.toString()),
	AD("ADS", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED.toString()),
	WARNING("WARNING", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED.toString()),
	STAFF("STAFF", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED.toString()),
	COSMETICS("COSMETICS", ChatColor.BLUE.toString() + ChatColor.BOLD, ChatColor.BLUE.toString()),
	FABULOUS("FABULOUS", ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD, ChatColor.LIGHT_PURPLE.toString()),
	HUB("HUB", ChatColor.GREEN.toString() + ChatColor.BOLD, ChatColor.GREEN.toString()),
	DELIVERYMAN("DELIVERY", ChatColor.BLUE.toString() + ChatColor.BOLD, ChatColor.BLUE.toString());
	
	public String prefix;
	public String color;
	public String afterColor;
	
	
	private ChatUtil(String Prefix, String Color, String AfterColor) {
		
		this.prefix = Prefix;
		this.color = Color;
		this.afterColor = AfterColor;
	}
	public static void sendMessage(Player player, ChatUtil Type, String Message) {
		
		player.sendMessage(Type.color + Type.prefix + " " + Type.afterColor + Message);
	}
}
