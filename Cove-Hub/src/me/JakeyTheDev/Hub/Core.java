package me.JakeyTheDev.Hub;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.JakeyTheDev.Hub.Commands.FireworkCommand;
import me.JakeyTheDev.Hub.Commands.VersionCommand;
import me.JakeyTheDev.Hub.Listeners.RegisterEvents;
import me.JakeyTheDev.Hub.Utils.Arrays;

public class Core extends JavaPlugin implements Listener
{
	
	private static Core Instance;
	
	public void onEnable()
	{
		
		Instance = this;
		
		getServer().getMessenger().registerOutgoingPluginChannel(getInstance(), "BungeeCord");
		
		RegisterEvents.registerEvents();
		
		getCommand("Hub").setExecutor(new VersionCommand());
		getCommand("firework").setExecutor(new FireworkCommand());
		
		System.out.println("Cove-Hub Enabled!");
		
	}
	public void onDisable()
	{
		
		for (Player all : Bukkit.getOnlinePlayers())
		{
		Arrays.Vanish.remove(all);
		}
	}
	public static Core getInstance()
	{
		return Instance;
	}
}
