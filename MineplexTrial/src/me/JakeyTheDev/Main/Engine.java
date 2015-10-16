package me.JakeyTheDev.Main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.JakeyTheDev.Main.Format.ChatFormat;
import me.JakeyTheDev.Main.Game.Games.Runner;
import me.JakeyTheDev.Main.Game.Games.Spleef;
import me.JakeyTheDev.Main.Listeners.ChatListener;
import me.JakeyTheDev.Main.Listeners.ConnectionListener;
import me.JakeyTheDev.Main.Listeners.HubListener;
import me.JakeyTheDev.Main.Manager.GameManager;
import me.JakeyTheDev.Main.Manager.Scoreboard.ScoreboardManagers;
import me.JakeyTheDev.Main.Manager.Scoreboard.ScoreboardUtil;
import me.JakeyTheDev.Main.Manager.World.WorldGenerator;

public class Engine extends JavaPlugin implements Listener
{

	public Spleef spleef;
	public Runner runner;
	public GameManager manager;
	public ChatFormat chat;
	public ScoreboardUtil Scoreboard;
	public ScoreboardManagers scoreboardManager;
	public WorldGenerator module;
	
	public void onEnable() 
	{
		this.spleef = new Spleef(this);
		this.runner = new Runner(this);
		this.manager = new GameManager(this);
		this.Scoreboard = new ScoreboardUtil(this);
		this.scoreboardManager = new ScoreboardManagers();
		this.module = new WorldGenerator(this);
		
		manager.setGames();
		
		Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new HubListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
		
		System.out.println("MINEPLEX TRIAL ENABLED!");
	}
}
