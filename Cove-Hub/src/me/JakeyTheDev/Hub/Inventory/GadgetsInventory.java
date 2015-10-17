package me.JakeyTheDev.Hub.Inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.JakeyTheDev.Hub.Utils.ItemUtil;

public class GadgetsInventory
{

	public static Inventory Gadgets;

	public static void openGadgetsMenu(Player player) 
	{

		Gadgets = Bukkit.createInventory(null, 3*9, ChatColor.AQUA + "Gadgets");

		Gadgets.setItem(11, ItemUtil.createItem(Material.DIAMOND_HOE, 1, 
				"Firework Launcher", Arrays.asList(ChatColor.YELLOW + "Credits: 3 (Each time you want to use it!)")));

		player.openInventory(Gadgets);
	}
}
