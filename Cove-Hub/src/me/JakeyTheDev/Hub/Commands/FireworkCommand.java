package me.JakeyTheDev.Hub.Commands;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import me.JakeyTheDev.Hub.Utils.ChatUtil;

public class FireworkCommand implements CommandExecutor 
{

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{

		Player player = (Player) sender;

		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("Launch")) 
			{
				ChatUtil.sendMessage(player, ChatUtil.HUB, "FIREWORKS SIZZLE!");

				Firework fw = (Firework) player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
				FireworkMeta meta = fw.getFireworkMeta();

				meta.addEffect(FireworkEffect.builder().
						flicker(true).trail(true).withColor(Color.BLUE, Color.RED, Color.AQUA).build());

				fw.setFireworkMeta(meta);

			}
		} else 
		{
			ChatUtil.sendMessage(player, ChatUtil.WARNING, "/Firework Launch");
		}
		return false;
	}
}
