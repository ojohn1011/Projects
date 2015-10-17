package me.JakeyTheDev.Hub.Methods;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.JakeyTheDev.Hub.Utils.ItemUtil;
import me.JakeyTheDev.Hub.Utils.TitleUtil;

public class GiveItems
{
	
	public static void giveItems(Player player) 
	{
		
		player.getInventory().setItem(0, 
				ItemUtil.createItem(Material.COMPASS, 1, 
						TitleUtil.Selector, Arrays.asList("", ChatColor.BLUE + "Right click me to choose a game!")));
		
		player.getInventory().setItem(4, 
				ItemUtil.createItem(Material.CHEST, 1, 
						TitleUtil.Cosmetics, Arrays.asList("", ChatColor.BLUE + "Right click me to open cosmetics!")));
		
		player.getInventory().setItem(8, 
				ItemUtil.createItem(Material.REDSTONE_COMPARATOR, 1, 
						TitleUtil.Profiles, Arrays.asList("", ChatColor.BLUE + "Right click me to open your profile!")));
		
		player.getInventory().setItem(7, 
				ItemUtil.createItem(Material.BLAZE_POWDER, 1, 
						"§c§lOFF §f/ §3VANISH PLAYERS", Arrays.asList("", ChatColor.BLUE + "Right click me to Vanish everyone!")));
		
	}

}
