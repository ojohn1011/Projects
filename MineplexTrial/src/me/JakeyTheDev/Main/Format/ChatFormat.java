package me.JakeyTheDev.Main.Format;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum ChatFormat
{

	NONE("", ChatColor.BLUE.toString(), ChatColor.BLUE.toString()), GAME("Game>", ChatColor.BLUE.toString(),
	        ChatColor.BLUE.toString()), ADMIN("Admin>", ChatColor.RED.toString(), ChatColor.BLUE.toString()), LINE(
	                "=======================================",
	                ChatColor.DARK_GREEN.toString() + ChatColor.STRIKETHROUGH,
	                ChatColor.DARK_GREEN.toString() + ChatColor.BOLD);

	public String prefix, pColour, cColour;

	ChatFormat(String prefixText, String prefixColour, String chatColour)
	{
		this.prefix = prefixText;
		this.pColour = prefixColour;
		this.cColour = chatColour;
	}

	public String getPrefix()
	{
		return this.prefix;
	}

	public String getPrefixColour()
	{
		return this.pColour;
	}

	public static void broadcastMessage(String arguments, ChatFormat format)
	{
		Bukkit.broadcastMessage(format.pColour + format.prefix + " " + format.cColour + arguments);
	}

	public static void sendMessage(Player player, String arguments, ChatFormat format)
	{
		player.sendMessage(format.pColour + format.prefix + " " + format.cColour + arguments);
	}
}
