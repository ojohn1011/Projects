package me.JakeyTheDev.Hub.Listeners.GlobalListeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInventoryEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.JakeyTheDev.Hub.Utils.ChatUtil;

@SuppressWarnings("deprecation")
public class ItemDropAndMoveListener implements Listener
{
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) 
	{
		Player player = e.getPlayer();
		if(player.isOp()) 
		{
			
		} else 
		{
			
	        e.getItemDrop().remove();
			e.setCancelled(true);
			ChatUtil.sendMessage(player, ChatUtil.WARNING, "You cannot drop items in the hub!");
		}
	}
	@EventHandler
	public void onItemMove(PlayerPickupItemEvent e) 
	{
		Player player = e.getPlayer();
		if(player.isOp())
		{
			
		} else 
		
		{
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onItemMove(InventoryClickEvent e) 
	{
		HumanEntity player = e.getWhoClicked();
		if(player.isOp()) 
		{
			
		} else 
		{
			e.setResult(Result.DENY);
		}
	}
}
