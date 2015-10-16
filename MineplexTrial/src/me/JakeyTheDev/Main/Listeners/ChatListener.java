package me.JakeyTheDev.Main.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.JakeyTheDev.Main.Engine;

public class ChatListener implements Listener
{

	private Engine _engine;

	public ChatListener(Engine engine)
	{
		this._engine = engine;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		if (e.getPlayer().isOp())
		{
			e.setFormat(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "LT" + " " + ChatColor.YELLOW
					+ e.getPlayer().getName() + " " + ChatColor.WHITE + e.getMessage());
		} else 
		{
			e.setFormat(ChatColor.RED.toString() + ChatColor.BOLD + "JR.DEV" + " " + ChatColor.YELLOW
					+ e.getPlayer().getName() + " " + ChatColor.WHITE + e.getMessage());
		}
	}
}
