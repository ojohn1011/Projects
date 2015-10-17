package me.JakeyTheDev.Hub.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.JakeyTheDev.Hub.Core;
import me.JakeyTheDev.Hub.Inventory.MenuInventory;
import me.JakeyTheDev.Hub.Inventory.ProfileInventory;
import me.JakeyTheDev.Hub.Inventory.ServerSelectorInventory;
import me.JakeyTheDev.Hub.Methods.GiveItems;
import me.JakeyTheDev.Hub.Methods.SendAd;
import me.JakeyTheDev.Hub.Utils.Arrays;
import me.JakeyTheDev.Hub.Utils.ChatUtil;
import me.JakeyTheDev.Hub.Utils.ItemUtil;
import me.JakeyTheDev.Hub.Utils.TitleUtil;

public class InteractEvent implements Listener
{

	@EventHandler
	public void onInteract(final PlayerInteractEvent e) 
	{ 

		final Player player = e.getPlayer(); 

		if(e.getPlayer().getItemInHand().getType() == Material.COMPASS)
		{
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) 
			{
				if(!(player.isOp()))
				{
					ChatUtil.sendMessage(player, ChatUtil.HUB, "Opening Game Selector in 5 seconds!");

					for(int i = 1; i < 100; i++) {
						ChatUtil.sendMessage(player, ChatUtil.NONE, "");
					}

					SendAd.sendAds(player);

					Bukkit.getScheduler().runTaskLater(Core.getInstance(), new Runnable()
					{

						@Override
						public void run()
						{
							ServerSelectorInventory.openSelector(player);

						}
					}, 5*20);

				} else 
				{
					ServerSelectorInventory.openSelector(e.getPlayer());
				}
			}
		}
	}
	@EventHandler
	public void onInteractMainMenu(final PlayerInteractEvent e) 
	{ 

		final Player player = e.getPlayer(); 

		if(e.getPlayer().getItemInHand().getType() == Material.CHEST) 
		{
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) 
			{
				MenuInventory.openCosmeticsMenu(player);
			}
		}
	}
	@EventHandler
	public void onInteractProfileMenu(final PlayerInteractEvent e)
	{ 

		final Player player = e.getPlayer(); 

		if(e.getPlayer().getItemInHand().getType() == Material.REDSTONE_COMPARATOR) 
		{
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
			{

				ProfileInventory.openProfileMenu(player);
			}
		}
	}
	@EventHandler
	public void onInteractVanishItem(final PlayerInteractEvent e)
	{ 

		final Player player = e.getPlayer(); 

		if(e.getPlayer().getItemInHand().getType() == Material.BLAZE_POWDER) 
		{
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) 
			{
				ChatUtil.sendMessage(player, ChatUtil.HUB, "All players vanished!");
				player.getInventory().setItem(7, 
						ItemUtil.createItem(Material.BEACON, 1, 
								"§a§lON §f/ §3UNVANISH PLAYERS", java.util.Arrays.asList("", ChatColor.BLUE + "Right click me to Vanish everyone!")));
				Arrays.Vanish.add(player);
				for (Player all : Bukkit.getOnlinePlayers()) 
				{
					player.hidePlayer(all);
				}
				
			} 
		} else if(e.getPlayer().getItemInHand().getType() == Material.BEACON) 
		{
			ChatUtil.sendMessage(player, ChatUtil.HUB, "All players un vanished!");
			Arrays.Vanish.remove(player);
			player.getInventory().setItem(7, 
					ItemUtil.createItem(Material.BLAZE_POWDER, 1, 
							"§c§lOFF §f/ §3VANISH PLAYERS", java.util.Arrays.asList("", ChatColor.BLUE + "Right click me to Un Vanish everyone!")));
			for (Player all : Bukkit.getOnlinePlayers())
			{
				player.showPlayer(all);
			}
		}
	}
}

