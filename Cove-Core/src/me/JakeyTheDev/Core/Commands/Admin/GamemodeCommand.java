package me.JakeyTheDev.Core.Commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JakeyTheDev.Core.Commands.AbstractCommand;
import me.JakeyTheDev.Core.Utils.ChatUtil;
import me.JakeyTheDev.Core.Utils.MathUtils;

public class GamemodeCommand extends AbstractCommand implements CommandExecutor
{

	public GamemodeCommand(int rank)
	{
		super(rank);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if ((sender instanceof Player)) 
		{
			Player p = (Player) sender;
			
			if (args.length < 1)
			{
				ChatUtil.sendMessage(p, ChatUtil.ERROR, "Your gamemode is " + p.getGameMode());
				return true;
			}
			
			int gmArg = 0;
			Player target = p;
			
			if (!MathUtils.isNumeric(args[gmArg]))
			{
				if ((args.length > 1) && (Bukkit.getPlayerExact(args[0]) != null)) 
				{
					gmArg = 1;
					target = Bukkit.getPlayerExact(args[0]);
				} else 
				{
					ChatUtil.sendMessage(p, ChatUtil.ERROR, "This is not a number!");
					return true;
				}
			}
			
			@SuppressWarnings("all")
			GameMode gm = GameMode.getByValue(Integer.parseInt(args[gmArg]));
			if (gm == null) 
			{
				ChatUtil.sendMessage(p, ChatUtil.ERROR, "This is an invalid gamemode!");
				return true;
			}
			
			target.setGameMode(gm);
			
			if (target != p)
			{
				ChatUtil.sendMessage(p, ChatUtil.ERROR, target.getName() + "'s gamemode was changed!");
			}
			ChatUtil.sendMessage(p, ChatUtil.ERROR, "Yeay! My Gamemode was changed by me!");
		}
		
		return true;
	}
}
