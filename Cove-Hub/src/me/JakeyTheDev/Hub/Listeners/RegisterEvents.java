package me.JakeyTheDev.Hub.Listeners;

import org.bukkit.Bukkit;

import me.JakeyTheDev.Hub.Core;
import me.JakeyTheDev.Hub.CommandBlocker.PluginBlocker;
import me.JakeyTheDev.Hub.Gadgets.FireworkGun;
import me.JakeyTheDev.Hub.Listeners.GlobalListeners.DeathListener;
import me.JakeyTheDev.Hub.Listeners.GlobalListeners.ItemDropAndMoveListener;
import me.JakeyTheDev.Hub.Listeners.GlobalListeners.JoinListener;
import me.JakeyTheDev.Hub.Listeners.GlobalListeners.PlaceAndMineBlockListener;
import me.JakeyTheDev.Hub.Listeners.GlobalListeners.QuitListener;
import me.JakeyTheDev.Hub.Listeners.GlobalListeners.RespawnEvent;

public class RegisterEvents 
{
	
	public static void registerEvents() 
	{
		
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new InteractEvent(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new JoinListener(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new QuitListener(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new DeathListener(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new ItemDropAndMoveListener(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new RespawnEvent(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new FireworkGun(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new PluginBlocker(), Core.getInstance());
		Bukkit.getPluginManager().registerEvents(new PlaceAndMineBlockListener(), Core.getInstance());
	}
}
