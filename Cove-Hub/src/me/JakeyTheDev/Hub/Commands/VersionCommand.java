package me.JakeyTheDev.Hub.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JakeyTheDev.Hub.Core;
import me.JakeyTheDev.Hub.Bungee.BungeeMethods;
import me.JakeyTheDev.Hub.Utils.ChatUtil;

@SuppressWarnings("all")
public class VersionCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		
		Player player = (Player) sender;
		
		if(args.length <= 0 || args.length >= 3)
		{
			ChatUtil.sendMessage(player, ChatUtil.WARNING,
					"Correct Arguments: " + ChatColor.GREEN + "/Hub <Args>");
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("Version"))
			{
				if(player.isOp())
				{
					ChatUtil.sendMessage(player, ChatUtil.STAFF, "This plugin is running at version: " 
							+ ChatColor.GREEN + Core.getInstance().getDescription().getVersion() + " Beta!");
				} else 
				{

				}
			}
		} else if(args.length == 2) 
		{
			if(args[0].equalsIgnoreCase("SendTo")) 
			{
				try 
				{
					BungeeMethods.sendToServer(player, args[1]);
					ChatUtil.sendMessage(player, ChatUtil.HUB, "You have been sent to " 
							+ ChatColor.GREEN + args[1].toUpperCase());
				} catch(Exception e) 
				{
					ChatUtil.sendMessage(player, ChatUtil.WARNING,
							"This is not a server!");
				}
			}
		}
		return false;
	}
}
