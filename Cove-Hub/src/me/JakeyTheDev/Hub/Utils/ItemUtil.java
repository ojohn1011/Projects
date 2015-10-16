package me.JakeyTheDev.Hub.Utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemUtil
{
	public static ItemStack createItem(Material material, int amount,
			String displayName, List<String> lore) {
		ItemStack item = new ItemStack(material, amount, (short) 0);
		ItemMeta meta = (ItemMeta) item.getItemMeta();
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}
	public static ItemStack createSkull(String displayName, String owner , List<String> lore) {
		ItemStack items = new ItemStack(Material.SKULL_ITEM , 1, (short) 3); 
		SkullMeta meta = (SkullMeta) items.getItemMeta();
		meta.setOwner(owner); 
		meta.setDisplayName(displayName);
		meta.setLore(lore);
		items.setItemMeta(meta);

		return items; 
	}
}
