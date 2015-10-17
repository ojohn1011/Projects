package me.JakeyTheDev.Core.Commands.Admin;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JakeyTheDev.Core.Core;
import me.JakeyTheDev.Core.Commands.AbstractCommand;
import me.JakeyTheDev.Core.PlayerData.PlayerData;
import me.JakeyTheDev.Core.Ranks.PermissionsManager;
import me.JakeyTheDev.Core.Utils.ChatUtil;

public class SetRankCommand extends AbstractCommand
{
	
	public SetRankCommand(int rank) 
	{
		super(rank);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if ((sender instanceof Player)) {
			Player s = (Player) sender;
			if (args.length < 2) {
				ChatUtil.sendMessage(s, ChatUtil.PERMISSIONS, "Wrong amount of arguments!");
				return true;
			}
			
			Player target = Bukkit.getPlayerExact(args[0]);
			if (target == null) {
				ChatUtil.sendMessage(s, ChatUtil.PERMISSIONS, "This player is not online.");
				return true;
			}
			PlayerData t = PlayerData.players.get(target);
			if (PlayerData.players.get(s).rank.rank < t.rank.rank)
			{
				ChatUtil.sendMessage(s, ChatUtil.PERMISSIONS, "You cannot set the rank of a player higher than you!");
				return true;
			}
			
			String rank = args[1].toUpperCase();
			if (!PermissionsManager.isRank(rank))
			{
				ChatUtil.sendMessage(s, ChatUtil.PERMISSIONS, rank + " is not a rank?");
				return true;
			}
			t.updateRank(PermissionsManager.valueOf(rank));
			ChatUtil.sendMessage(s, ChatUtil.PERMISSIONS, "The player has recieved their rank!");
			
		} else {
			if (args.length < 2)
			{
				Player player = (Player) sender;
				ChatUtil.sendMessage(player, ChatUtil.PERMISSIONS, "Wrong amount of arguments!");
				return true;
			}
			
			if (Bukkit.getPlayerExact(args[0]) != null)
			{
				
				Player target = Bukkit.getPlayerExact(args[0]);
				
    			PlayerData t = PlayerData.players.get(target);
    			
    			String rank = args[1].toUpperCase();
    			
    			if (!PermissionsManager.isRank(rank)) 
    			{
    				Player player = (Player) sender;
    				ChatUtil.sendMessage(player, ChatUtil.PERMISSIONS, "This is an invalid rank!");
    				return true;
    			}
    			t.updateRank(PermissionsManager.valueOf(rank));
    			Player player = (Player) sender;
    			ChatUtil.sendMessage(player, ChatUtil.PERMISSIONS, "Player has recieved their rank!");
    			
			} else {
				
				String rank = args[1].toUpperCase();
    			Player player = (Player) sender;
    			if (!PermissionsManager.isRank(rank))
    			{
    				ChatUtil.sendMessage(player, ChatUtil.PERMISSIONS, rank + " is not a rank?");
    				return true;
    			}
				
				try {
					if(Core.sql.checkConnection()) 
					{
						if(Core.sql.accountExists(Bukkit.getOfflinePlayer(args[0]).getUniqueId(), "PlayerInfo")) {
							Core.sql.setValue(Bukkit.getOfflinePlayer(args[0]).getUniqueId(), "PlayerInfo", "RANK", rank);
							System.out.println("COMPLETE");
						} else
						{
							System.out.println("DOSNT EXIST");
						}
					}
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}
