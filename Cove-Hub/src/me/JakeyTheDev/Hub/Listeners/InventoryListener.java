package me.JakeyTheDev.Hub.Listeners;

import java.lang.reflect.Array;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.JakeyTheDev.Hub.Bungee.BungeeMethods;
import me.JakeyTheDev.Hub.Inventory.GadgetsInventory;
import me.JakeyTheDev.Hub.Inventory.HatInventory;
import me.JakeyTheDev.Hub.Inventory.MenuInventory;
import me.JakeyTheDev.Hub.Inventory.ProfileInventory;
import me.JakeyTheDev.Hub.Inventory.ServerSelectorInventory;
import me.JakeyTheDev.Hub.Inventory.SettingsInventory;
import me.JakeyTheDev.Hub.Utils.Arrays;
import me.JakeyTheDev.Hub.Utils.ChatUtil;
import me.JakeyTheDev.Hub.Utils.ItemUtil;

public class InventoryListener implements Listener
{

	@EventHandler
	public void onInventoryClick1(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();

		if (clicked == null || clicked.getType() == Material.AIR
				|| clicked.getType() == null)
			return;

		if (ServerSelectorInventory.selector != null
				&& e.getInventory().getName()
				.equals(ServerSelectorInventory.selector.getName()))
				{
			e.setCancelled(true);
			switch (clicked.getType()) 
			{
			case BLAZE_POWDER:
				player.closeInventory();
				ChatUtil.sendMessage(player, ChatUtil.HUB, "Being sent to Particle Wars!");
				BungeeMethods.sendToServer(player, "PW");
				return;
			default:
				break;
			}
		}
	}
	@EventHandler
	public void onInventoryClick2(InventoryClickEvent e) 
	
	{
		Player player = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();

		if (clicked == null || clicked.getType() == Material.AIR
				|| clicked.getType() == null)
			return;

		if (MenuInventory.Menu != null
				&& e.getInventory().getName()
				.equals(MenuInventory.Menu.getName()))
				{
			e.setCancelled(true);
			switch (clicked.getType()) 
			{
			case NETHER_STAR:
				player.closeInventory();
				GadgetsInventory.openGadgetsMenu(player);

				return;
			case LEATHER_HELMET:
				player.closeInventory();
				HatInventory.openHatsMenu(player);

				return;
			default:
				break;
			}
		}
	}
	@EventHandler
	public void onInventoryClick3(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();

		if (clicked == null || clicked.getType() == Material.AIR
				|| clicked.getType() == null)
			return;

		if (ProfileInventory.Profile != null
				&& e.getInventory().getName()
				.equals(ProfileInventory.Profile.getName()))
				{
			e.setCancelled(true);
			switch (clicked.getType())
			{
			case REDSTONE_COMPARATOR:
				player.closeInventory();
				SettingsInventory.openSettingsMenu(player);
				return;

			default:
				break;
			}	
		}
	}
	@EventHandler
	public void onInventoryClick4(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();

		if (clicked == null || clicked.getType() == Material.AIR
				|| clicked.getType() == null)
			return;

		if (SettingsInventory.Settings != null
				&& e.getInventory().getName()
				.equals(SettingsInventory.Settings.getName())) 
				{
			e.setCancelled(true);
			switch (clicked.getType()) 
			{
			case NETHER_STAR:
				player.closeInventory();
				if(Arrays.Vanish.contains(player)) 
				{
					Arrays.Vanish.remove(player);
					for (Player all : Bukkit.getOnlinePlayers())
					{
						player.showPlayer(all);
					}
					ChatUtil.sendMessage(player, ChatUtil.HUB, "Players Enabled!");
				} else {
					for (Player all : Bukkit.getOnlinePlayers()) 
					{
						player.hidePlayer(all);
					}
					ChatUtil.sendMessage(player, ChatUtil.HUB, "Players Disabled!");
					Arrays.Vanish.add(player);

				}

				return;

			default:
				break;
			}	
		}
	}
	@EventHandler
	public void onInventoryClick5(InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();

		if (clicked == null || clicked.getType() == Material.AIR
				|| clicked.getType() == null)
			return;

		if (GadgetsInventory.Gadgets != null
				&& e.getInventory().getName()
				.equals(GadgetsInventory.Gadgets.getName()))
				{
			e.setCancelled(true);
			switch (clicked.getType()) 
			{
			case DIAMOND_HOE:
				player.closeInventory();
				//WITHDRAW 500 crystals
				player.getInventory().setItem(1, ItemUtil.createItem(Material.DIAMOND_HOE, 1, ChatColor.RED 
						+ "Firework Cannon", java.util.Arrays.asList(ChatColor.AQUA + "Shoot fireworks!!!!!!!")));
				ChatUtil.sendMessage(player, ChatUtil.HUB, "Added Firework Launcher");
				return;
			}
		}
	}
	@EventHandler
	public void onInventoryClick6(InventoryClickEvent e)
	{
		
		Player player = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();

		if (clicked == null || clicked.getType() == Material.AIR
				|| clicked.getType() == null)
			return;

		if (HatInventory.Wardrobe != null
				&& e.getInventory().getName()
				.equals(HatInventory.Wardrobe.getName()))
				{
			e.setCancelled(true);
			switch (clicked.getType())
			{
			case SKULL_ITEM:
				player.getInventory().setHelmet(e.getCurrentItem());
				player.closeInventory();
				ChatUtil.sendMessage(player, ChatUtil.HUB, "Hat added!");
				ChatUtil.sendMessage(player, ChatUtil.FABULOUS, "YOU ARE FABULOUS! <3");
				return;
			case BED:
				if(player.getInventory().getHelmet() == null) 
				{
					player.closeInventory();
					ChatUtil.sendMessage(player, ChatUtil.HUB, "You have no Head Gear!");
				} else 
				{
					player.getInventory().setHelmet(null);
					player.closeInventory();
					ChatUtil.sendMessage(player, ChatUtil.HUB, "Cleared Hat!");
				}
			}
		}
	}
}
