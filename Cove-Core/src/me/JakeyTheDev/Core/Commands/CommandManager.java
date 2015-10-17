package me.JakeyTheDev.Core.Commands;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.GameModeCommand;
import org.bukkit.command.defaults.HelpCommand;
import org.bukkit.command.defaults.KillCommand;
import org.bukkit.command.defaults.TimeCommand;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.JakeyTheDev.Core.Core;
import me.JakeyTheDev.Core.Commands.Admin.EcoCommand;
import me.JakeyTheDev.Core.Commands.Admin.GamemodeCommand;
import me.JakeyTheDev.Core.Commands.Admin.SetRankCommand;
import me.JakeyTheDev.Core.Ranks.PermissionsManager;
import net.md_5.bungee.api.ChatColor;

public class CommandManager
{

	private static Core core;

	public CommandManager() 
	{
		core = Core.getInstance();
	}

	public static HashMap<AbstractCommand, String> cmd = new HashMap<AbstractCommand, String>();

	public static void register(JavaPlugin pl, AbstractCommand c, String name)
	{
		cmd.put(c, name);
		pl.getCommand(cmd.get(c)).setExecutor(c);
		pl.getCommand(cmd.get(c)).setPermission(c.rank + "");
		loadPerms();
	}

	public void registerAll()
	{
		cmd.put(new EcoCommand(9), "eco");
		cmd.put(new GamemodeCommand(10), "gamemode");
		cmd.put(new SetRankCommand(9), "setrank");

		for (AbstractCommand c : cmd.keySet())
		{
			core.getCommand(cmd.get(c)).setExecutor(c);
			core.getCommand(cmd.get(c)).setPermission(c.rank + "");
		}
	}

	public static void loadPerms()
	{

		try 
		{
			for (Command c : getAllCommands()) 
			{
				String perm = c.getPermission();
				switch (Integer.valueOf(perm)) 
				{
				case 0:
					c.setPermissionMessage(ChatColor.GRAY + "This requires the Rank: " + ChatColor.BLUE
							+ PermissionsManager.PLAYER.toString() + ChatColor.GRAY + ".");
					break;
				case 1:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.YOUTUBER.gamePrefix
							+ ChatColor.GRAY + " or " + PermissionsManager.TWITCH.gamePrefix + ChatColor.GRAY + ".");
					break;
				case 2:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.YOUTUBER.gamePrefix);
					break;
				case 3:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.DEVELOPER.gamePrefix);
					break;
				case 4:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.BUILDER.gamePrefix);
					break;
				case 5:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.HELPER.gamePrefix);
					break;
				case 6:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.MOD.gamePrefix);
					break;
				case 7:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.SRMOD.gamePrefix);
					break;
				case 8:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.ADMIN.gamePrefix);
					break;
				case 9:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.OWNER.gamePrefix);
					break;
				case 10:
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.LEADERSHIP.gamePrefix);
					break;
				default:
					c.setPermission("99");
					c.setPermissionMessage(ChatColor.GRAY + "This requires Permission Rank: " + PermissionsManager.OWNER.gamePrefix
							+ ChatColor.GRAY + ".");
				}
			}
		} catch (Exception e) 
		{

		}

	}

	public static Collection<Command> getAllCommands() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
	IllegalAccessException {
		SimplePluginManager spm = (SimplePluginManager) Bukkit.getServer().getPluginManager();

		Field f = null;
		f = SimplePluginManager.class.getDeclaredField("commandMap");
		f.setAccessible(true);

		SimpleCommandMap scm = (SimpleCommandMap) f.get(spm);

		Collection<Command> coll = scm.getCommands();

		return coll;
	}
}
