package me.JakeyTheDev.Hub.Inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.JakeyTheDev.Hub.Utils.ItemUtil;
import me.JakeyTheDev.Hub.Utils.TitleUtil;

public class ServerSelectorInventory 
{
	
	public static Inventory selector;
	
	public static void openSelector(Player player) 
	
	{
		
		selector = Bukkit.createInventory(null, 3*9, TitleUtil.SelectorTitle);
		
		selector.setItem(13, ItemUtil.createItem(Material.BLAZE_POWDER
				, 1
				, ChatColor.GOLD + "Particle Wars"
				, Arrays.asList("", ChatColor.GOLD + "Click to join particle wars!")));
		
		player.openInventory(selector);
		
	}
}
