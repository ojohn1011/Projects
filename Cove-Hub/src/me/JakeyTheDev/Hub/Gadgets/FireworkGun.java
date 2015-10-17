package me.JakeyTheDev.Hub.Gadgets;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import me.JakeyTheDev.Hub.Core;
import me.JakeyTheDev.Hub.Utils.ChatUtil;

public class FireworkGun implements Listener 
{

	ArrayList<Player> cooldown = new ArrayList<Player>();

	@EventHandler
	public void onInteract(final PlayerInteractEvent e) 
	{
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(e.getItem().getType() == Material.DIAMOND_HOE) 
			{
				if(e.getPlayer().isOp()) 
				{
					shootFireworks(e.getPlayer());
				} else 
				{
					if (cooldown.contains(e.getPlayer())) 
					{
						ChatUtil.sendMessage(e.getPlayer(), ChatUtil.COOLDOWN, "You can only use this every 5 seconds.");
						return;
					}
					shootFireworks(e.getPlayer());
					cooldown.add(e.getPlayer());
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), new Runnable() {
						public void run()
						
						{
							cooldown.remove(e.getPlayer());
						}
					}, 5*20);
					return;
				}
			}
		}
	}
	public void shootFireworks(Player player)
	{

		Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
		FireworkMeta meta = fw.getFireworkMeta();

		meta.addEffect(FireworkEffect.builder().
				flicker(true).trail(true).withColor
				(Color.BLUE, Color.RED, Color.AQUA, Color.GREEN, Color.PURPLE).build());

		fw.setFireworkMeta(meta);
		fw.setVelocity(player.getLocation().getDirection().setY(+0.1));

	}
}
