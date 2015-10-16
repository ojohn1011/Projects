package me.JakeyTheDev.Core.Commands.Admin;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.JakeyTheDev.Core.Core;
import me.JakeyTheDev.Core.Commands.AbstractCommand;
import me.JakeyTheDev.Core.PlayerData.PlayerData;
import me.JakeyTheDev.Core.Utils.ChatUtil;

public class EcoCommand extends AbstractCommand implements CommandExecutor  {

	public EcoCommand(int rank) {
		super(rank);

	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((sender instanceof Player)) {
			Player player = (Player) sender;

			if (args.length < 3) {


				Player target = Bukkit.getPlayer(args[1]);
				if (target != null) {

					int amount;

					try {
						amount = Integer.parseInt(args[2]);
					} catch (Exception e) {
						return true;
					}

					switch (args[0].toLowerCase()) {
					case "give":
						PlayerData.players.get(target).Crystals += amount;
						ChatUtil.sendMessage(player, ChatUtil.CRYSTALS, "You have given " + target.getName() + amount + " Crystals");
						break;

					case "take":
						PlayerData.players.get(target).Crystals -= amount;
						ChatUtil.sendMessage(player, ChatUtil.CRYSTALS, "You have taken " + amount + " from " + target.getName());
						break;

					case "set":
						PlayerData.players.get(target).Crystals = amount;
						ChatUtil.sendMessage(player, ChatUtil.CRYSTALS, "You have set " + target.getName() + "'s balance to " + amount);
						break;

					default:
						break;
					}
				} else 

					ChatUtil.sendMessage(player, ChatUtil.CRYSTALS, target.getName() + " Is not online!");

			} else {
				
				if (Bukkit.getPlayer(args[1]) != null) {

					Player target = Bukkit.getPlayer(args[1]);
					int amount;

					try {
						amount = Integer.parseInt(args[2]);
					} catch (Exception e) {
						return true;
					}

					switch (args[0].toLowerCase()) {
					case "give":
						ChatUtil.sendMessage((Player) sender, ChatUtil.CRYSTALS, "You have given " + target.getName() + amount + " Crystals");
						break;

					case "take":
						PlayerData.players.get(target).Crystals -= amount;
						ChatUtil.sendMessage((Player) sender, ChatUtil.CRYSTALS, "You have taken " + amount + " from " + target.getName());
						break;

					case "set":
						PlayerData.players.get(target).Crystals = amount;
						ChatUtil.sendMessage((Player) sender, ChatUtil.CRYSTALS, "You have set " + target.getName() + "'s balance to " + amount);
						break;

					default:
						break;
					}
				} else {
					OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					try {
						if(Core.sql.checkConnection()) {
							if(Core.sql.accountExists(target.getUniqueId(), "Crystals")) {
								Core.sql.setValue(target.getUniqueId(), "Crystals", "CRYSTALS", Integer.parseInt(args[2]));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}
}
