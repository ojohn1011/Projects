package me.JakeyTheDev.Core;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.JakeyTheDev.Core.Commands.Admin.EcoCommand;
import me.JakeyTheDev.Core.MySQL.MySQL;
import me.JakeyTheDev.Core.PlayerData.PlayerData;
import me.JakeyTheDev.Core.Utils.ChatUtil;

public class Core extends JavaPlugin implements Listener
{

	public static MySQL sql;
	
	private ChatUtil chat;
	
	public static Core Instance;
	
	@SuppressWarnings("all")

	public void onEnable() {

		Instance = this;

		sql = new MySQL(getInstance(), "162.223.9.251", "3306", "Core", "root", "k69yg4guccimaneG42pEn");

		sql.register();

		for (Player all : Bukkit.getOnlinePlayers()) {
			PlayerData.players.put(all, new PlayerData(all));
		}

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
	}
	public void onDisable() {

		for (Player all : Bukkit.getOnlinePlayers()) {

			PlayerData.players.get(all).saveData(); 

			try {
				sql.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL Disconected on server being closed!"); 
			}
		}
	}

	public static Core getInstance() {
		return Instance;
	}
	public ChatUtil getChatManager() {
		return this.chat;
	}
}