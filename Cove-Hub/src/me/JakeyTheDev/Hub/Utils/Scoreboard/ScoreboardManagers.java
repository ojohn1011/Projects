package me.JakeyTheDev.Hub.Utils.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardManagers
{

	public static void createScoreboard(Player player) 
	{

		Scoreboard _board;
		ScoreboardManager _manager;

		_manager = Bukkit.getScoreboardManager();
		_board = _manager.getNewScoreboard();

		Objective objective = _board.registerNewObjective("Test", "dummy");
		objective.setDisplayName(ChatColor.WHITE.toString() + ChatColor.BOLD + "Welcome to Combat Cove");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score s9 = objective.getScore("    ");
		s9.setScore(9);
		Score s8 = objective.getScore(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Rank:");
		s8.setScore(8);
		Score s7 = objective.getScore(ChatColor.WHITE.toString() + "Member");
		s7.setScore(7);
		Score s6 = objective.getScore("  ");
		s6.setScore(6);
		Score s5 = objective.getScore(ChatColor.RED.toString() + ChatColor.BOLD + "Online Players:");
		s5.setScore(5);
		Score s4 = objective.getScore(ChatColor.WHITE.toString()
				+ Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
		s4.setScore(4);
		Score s3 = objective.getScore(" ");
		s3.setScore(3);
		Score s2 = objective.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "Crystals:");
		s2.setScore(2);
		Score s1 = objective.getScore(ChatColor.WHITE.toString() + 0);
		s1.setScore(1);

		player.setScoreboard(_board);
	}
}