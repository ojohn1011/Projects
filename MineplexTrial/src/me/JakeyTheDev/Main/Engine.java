package me.JakeyTheDev.Main;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.JakeyTheDev.Main.Format.ChatFormat;
import me.JakeyTheDev.Main.Game.Games.Runner;
import me.JakeyTheDev.Main.Game.Games.Spleef;
import me.JakeyTheDev.Main.Game.Settings.GameState;
import me.JakeyTheDev.Main.Game.Settings.GameType;
import me.JakeyTheDev.Main.Listeners.ChatListener;
import me.JakeyTheDev.Main.Listeners.ConnectionListener;
import me.JakeyTheDev.Main.Listeners.HubListener;
import me.JakeyTheDev.Main.Manager.GameManager;
import me.JakeyTheDev.Main.Manager.InventoryManager.GemInventory;
import me.JakeyTheDev.Main.Manager.Scoreboard.ScoreboardManagers;
import me.JakeyTheDev.Main.Manager.Scoreboard.ScoreboardUtil;
import me.JakeyTheDev.Main.Manager.World.WorldGenerator;
import me.JakeyTheDev.Main.MySQL.MySQL;
import me.JakeyTheDev.Main.MySQL.PlayerData.PlayerData;

public class Engine extends JavaPlugin implements Listener
{

	public Spleef spleef;
	public Runner runner;
	public GameManager manager;
	public ChatFormat chat;
	public ScoreboardUtil Scoreboard;
	public ScoreboardManagers scoreboardManager;
	public WorldGenerator module;
	public PlayerData playerData;
	public MySQL sql;
	public GemInventory gemInv;
	
	@SuppressWarnings("all")
	
	public void onEnable() 
	{
		this.spleef = new Spleef(this);
		this.runner = new Runner(this);
		this.manager = new GameManager(this);
		this.Scoreboard = new ScoreboardUtil(this);
		this.scoreboardManager = new ScoreboardManagers();
		this.module = new WorldGenerator(this);
		this.playerData = new PlayerData(this);
		this.gemInv = new GemInventory(this);
		
		manager.setGames();
		
		GameState.setGameState(GameState.NOT_READY);
		GameType.setGameType(GameType.NONE);
		this.manager._alivePlayers.clear();
		
		for (Player all : Bukkit.getOnlinePlayers()) 
		{
			this.manager.removeSpectator(all);
		}
		this.manager._possition.clear();
		
		//TODO LOGIN TO SQL DATABASE (MAY USE A CONFIGURATION FILE)
		
		sql = new MySQL(this, "IP", "3306", "mc1080", "USERNAME", "PASS");
		
		sql.register();
		
		Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
		Bukkit.getPluginManager().registerEvents(new HubListener(this), this);
		Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
		Bukkit.getPluginManager().registerEvents(new GemInventory(this), this);

		try {
			if(sql.checkConnection() == false) {

				System.out.println("Not Connected to the SQL Servers!");

			} else {

				getConfig().options().copyDefaults(true);

				saveConfig();


				Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

					@Override
					public void run() {

						try {
							if(sql.openConnection().isClosed()) {
								sql.openConnection();
							} else {
								System.out.println("SQL connection still stable!"); 
							}
						} catch (SQLException e) {
							System.out.println("SQL connection disabled by SQL Exception in Main");
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							System.out.println("SQL connection disabled by ClassNotFound Exception in Main");
							e.printStackTrace();
						}

					}
				}, 500*20, 500*20);
			}
		} catch (SQLException e) {
			System.out.println("Could not connect to servers for SQL!");
			e.printStackTrace();
		}
		
		for(Player online : Bukkit.getOnlinePlayers())
			playerData.players.put(online, new PlayerData(this, online));
		
		System.out.println("MINEPLEX TRIAL ENABLED!");
	}
	
	@Override
	public void onDisable()
	{
		for(Player online : Bukkit.getOnlinePlayers())
			playerData.players.get(online).saveData(online); 
	}
}
