package me.JakeyTheDev.Hub.Listeners.GlobalListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.JakeyTheDev.Hub.Utils.ChatUtil;

public class PlaceAndMineBlockListener implements Listener
{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e)
	{
		if(e.getPlayer().isOp())
		{
			
		} else
		{
			
			e.setCancelled(true);
			ChatUtil.sendMessage(e.getPlayer(), ChatUtil.WARNING, "You cannot break blocks!");
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) 
	{
		if(e.getPlayer().isOp())
		{
			
		} else
		{
			e.setCancelled(true);
			ChatUtil.sendMessage(e.getPlayer(), ChatUtil.WARNING, "You cannot place blocks!");
		}
	}
}
