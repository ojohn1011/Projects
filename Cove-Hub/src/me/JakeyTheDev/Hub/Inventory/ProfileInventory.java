package me.JakeyTheDev.Hub.Inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.JakeyTheDev.Hub.Utils.ItemUtil;

public class ProfileInventory {


	public static Inventory Profile;

	public static void openProfileMenu(Player player) {

		Profile = Bukkit.createInventory(null, 3*9, ChatColor.AQUA + "Profile");

		Profile.setItem(13, ItemUtil.createSkull(ChatColor.YELLOW + 
				player.getName() + "'s " + ChatColor.GREEN + "Stats", player.getName(), Arrays.asList(
						ChatColor.GREEN + "Name: " + ChatColor.YELLOW + player.getName(),
						ChatColor.GREEN + "Crystals: " + ChatColor.YELLOW + "0")));	
		Profile.setItem(11, ItemUtil.createItem(
				Material.REDSTONE_COMPARATOR, 1, 
				ChatColor.YELLOW + "Settings", Arrays.asList("", ChatColor.AQUA + "Click me to change and adapt settings.")));
		
		Profile.setItem(15, ItemUtil.createItem(
				Material.GLASS, 1, 
				ChatColor.YELLOW + "More Coming Soon!", Arrays.asList("", ChatColor.AQUA + "Coming Soon!")));
		
		player.openInventory(Profile);
	}

	/*/
	 * 		Menu.setItem(13, ItemUtil.createSkull(ChatColor.YELLOW + 
				player.getName() + "'s " + ChatColor.GREEN + "Stats", player.getName(), Arrays.asList(
				ChatColor.GREEN + "Name: " + ChatColor.YELLOW + player.getName(),
				ChatColor.GREEN + "Crystals: " + ChatColor.YELLOW + "0")));	
	 */

}