package me.JakeyTheDev.Core.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum ChatUtil {
	
	NONE("", ChatColor.WHITE.toString(), ChatColor.WHITE.toString()),
	ERROR("ERROR", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED.toString()),
	CRYSTALS("COINS", ChatColor.DARK_RED.toString() + ChatColor.BOLD, ChatColor.RED.toString()),
	PERMISSIONS("PERMISSIONS", ChatColor.BLUE.toString(), ChatColor.BLUE.toString());
	
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
