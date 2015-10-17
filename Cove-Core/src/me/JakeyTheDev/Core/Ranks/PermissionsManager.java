package me.JakeyTheDev.Core.Ranks;

import org.bukkit.ChatColor;

public enum PermissionsManager
{
	
	LEADERSHIP("Leadership", "LT", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 10),
	OWNER("Owner", "OWNER", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 9),
	DEVELOPER("Dev", "DEV", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 8),
	ADMIN("Admin", "ADMIN", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 7),
	SRMOD("Sr.Mod", "SR.MOD", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 6),
	MOD("Mod", "MOD", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 5),
	HELPER("Helper", "HELPER", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 4),
	BUILDER("Builder", "BUILDER", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 3),
	YOUTUBER("YouTuber", "YOUTUBE", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 2),
	TWITCH("Twitch", "TWITCH", ChatColor.RED.toString() + ChatColor.BOLD, ChatColor.RED, 1),
	PLAYER("Player", "", ChatColor.GREEN.toString() + ChatColor.BOLD, ChatColor.YELLOW, 0);
	
	
	public String Name, gamePrefix, nameColor;
	public ChatColor textColor;
	public int rank;
	public int donor = 0;
	
	PermissionsManager(String Name, String gamePrefix, String nameColor, ChatColor textColor, int rank)
	{
		this.Name = Name;
		this.gamePrefix = gamePrefix;
		this.nameColor = nameColor;
		this.textColor = textColor;
		this.rank = rank;
	}
	
	PermissionsManager(String Name, String gamePrefix, String nameColor, ChatColor textColor, int rank, int donor) 
	{
		this.Name = Name;
		this.gamePrefix = gamePrefix;
		this.nameColor = nameColor;
		this.textColor = textColor;
		this.rank = rank;
		this.donor = donor;
	}
	
	public static boolean isRank (String s) {
		for (PermissionsManager p : PermissionsManager.values()) {
			if(p.name().equalsIgnoreCase(s)) return true;
		}
		return false;
	}
}
