package me.JakeyTheDev.Core.PlayerData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import me.JakeyTheDev.Core.Core;
import me.JakeyTheDev.Core.Ranks.PermissionsManager;


public class PlayerData {


	public static HashMap<Player, PlayerData> players = new HashMap<Player, PlayerData>();

	Player player; 

	protected String name, uuid;

	public PermissionAttachment attachment;

	public int Crystals; 

	public PermissionsManager rank = PermissionsManager.PLAYER;

	public void updateRank(PermissionsManager pm) {
		int temps = pm.rank;

		if (temps > this.rank.rank) {

			for (int i = 0; i <= temps; i++) {
				if (i > this.rank.rank) this.attachment.setPermission(i + "", true);
			}

		} else if (temps < this.rank.rank) {
			for (int i = 0; i <= this.rank.rank; i++) {
				if (i > temps) this.attachment.setPermission(i + "", false);
			}
		}

		this.rank = pm;
	}


	public PlayerData(final Player p) {

		player = p;

		this.name = p.getName(); 
		this.uuid = p.getUniqueId().toString();

		this.attachment = this.player.addAttachment(Core.getInstance());

		Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), new Runnable() {
			@Override
			public void run() {

				ResultSet set = Core.sql.query("SELECT * FROM `" + "Crystals" + "` WHERE UUID = '" + p.getUniqueId().toString() + "';"); 

				try {
					if (set.next()) { 

						PlayerData.this.uuid = set.getString("UUID"); 
						PlayerData.this.name = set.getString("NAME");
						PlayerData.this.Crystals = set.getInt("CRYSTALS");  

						PlayerData.this.rank = PermissionsManager.valueOf(set.getString("RANK"));

					} else {

						PlayerData.this.name = p.getName();
						PlayerData.this.uuid = p.getUniqueId().toString(); 
						PlayerData.this.Crystals = 0; 

						PlayerData.this.rank = PermissionsManager.PLAYER;

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (int i = PlayerData.this.rank.rank; i >= 0; i--) {
					if (!PlayerData.this.attachment.getPermissions().containsKey(i + "")) PlayerData.this.attachment.setPermission(i + "", true);
				}
			}
		});
	}


	public void saveData() {

		final String s = player.getUniqueId().toString();
		final String s1 = player.getName();

		Core.getInstance();
		ResultSet set = Core.sql.query("SELECT * FROM `" + "Crystals" + "` WHERE UUID = '" + s + "';");

		try {
			if (set.next()) { 

				Core.sql.update("UPDATE `" + "Crystals" + "` SET `NAME` = '" + s1 + "', `CRYSTALS` = '" + this.Crystals + "', `RANK` = '" + this.rank.toString() +"' + WHERE `UUID` = '" + s + "';"); 

			} else { 

				Core.sql.update("INSERT INTO `" + "ExileNetwork" + "` (`UUID`, `NAME`, `CRYSTALS`, `RANK`) VALUES ('" + s + "', '" + s1 + "', '" + this.Crystals + "', '" + this.rank.toString() + "');"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveDataAsync() {
		Bukkit.getScheduler().runTaskAsynchronously(Core.getInstance(), new Runnable() {
			@Override
			public void run() {
				saveData();
			}
		});
	}
}	