package me.JakeyTheDev.Hub.Inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.JakeyTheDev.Hub.Utils.ItemUtil;

public class SettingsInventory {

	public static Inventory Settings;

	public static void openSettingsMenu(Player player) {

		Settings = Bukkit.createInventory(null, 3*9, ChatColor.AQUA + "Settings");

		Settings.setItem(13, ItemUtil.createItem(Material.NETHER_STAR, 1, 
				ChatColor.LIGHT_PURPLE + "Enable/Disable Vanishing Players",
				Arrays.asList(ChatColor.RED + "Click to enable/disable Vanishing Players!")));

		player.openInventory(Settings);
	}
}
