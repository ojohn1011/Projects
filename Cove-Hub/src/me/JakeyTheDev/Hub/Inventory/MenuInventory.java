package me.JakeyTheDev.Hub.Inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.JakeyTheDev.Hub.Utils.ItemUtil;

public class MenuInventory 
{
	
	public static Inventory Menu;
	
	public static void openCosmeticsMenu(Player player)
	{
		
		Menu = Bukkit.createInventory(null, 3*9, ChatColor.AQUA + "Cosmetics");
		
		Menu.setItem(11, ItemUtil.createItem(Material.NETHER_STAR, 1, 
				"Gadgets", Arrays.asList(ChatColor.YELLOW + "Click for a list of Gadgets!")));

		Menu.setItem(15, ItemUtil.createItem(Material.LEATHER_HELMET, 1, 
				"Hats", Arrays.asList(ChatColor.YELLOW + "Click for a list of Hats!")));
		
		player.openInventory(Menu);
	}
}
