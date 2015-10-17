package me.JakeyTheDev.Hub.Listeners.GlobalListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.JakeyTheDev.Hub.Methods.GiveItems;

public class RespawnEvent implements Listener 
{
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) 
	{
	
		GiveItems.giveItems(e.getPlayer());
	}
}
