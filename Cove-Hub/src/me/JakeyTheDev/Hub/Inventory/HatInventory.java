package me.JakeyTheDev.Hub.Inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.JakeyTheDev.Hub.Utils.ItemUtil;

public class HatInventory
{

	public static Inventory Wardrobe;

	public static void openHatsMenu(Player player)
	{

		Wardrobe = Bukkit.createInventory(null, 2*9, ChatColor.AQUA + "Wardrobe");

		Wardrobe.setItem(0, ItemUtil.createSkull(ChatColor.GOLD + "JakeyTheDev's Hat", "JakeyTheDev", 
				Arrays.asList(ChatColor.GREEN + "Click me to wear me as a hat till you leave!")));
		Wardrobe.setItem(2, ItemUtil.createSkull(ChatColor.GOLD + "Breezie_'s Hat", "Breezie_", 
				Arrays.asList(ChatColor.GREEN + "Click me to wear me as a hat till you leave!")));
		Wardrobe.setItem(4, ItemUtil.createSkull(ChatColor.GOLD + "West_Phoenix's Hat", "West_Phoenix", 
				Arrays.asList(ChatColor.GREEN + "Click me to wear me as a hat till you leave!")));
		Wardrobe.setItem(6 , ItemUtil.createSkull(ChatColor.GOLD + "EthanSparkles's Hat", "EthanSparkles", 
				Arrays.asList(ChatColor.GREEN + "Click me to wear me as a hat till you leave!")));
		Wardrobe.setItem(8 , ItemUtil.createSkull(ChatColor.GOLD + "Funkeypigeon's Hat", "funkeypigeon", 
				Arrays.asList(ChatColor.GREEN + "Click me to wear me as a hat till you leave!")));
		
		Wardrobe.setItem(13, ItemUtil.createItem(Material.BED, 1, ChatColor.DARK_GREEN + "Clear Hat", 
				Arrays.asList(ChatColor.RED + "Click to clear your current hat!")));
		player.openInventory(Wardrobe);
	}
} 
