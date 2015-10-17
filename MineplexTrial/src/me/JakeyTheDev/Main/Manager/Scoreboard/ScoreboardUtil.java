package me.JakeyTheDev.Main.Manager.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import me.JakeyTheDev.Main.Engine;
import me.JakeyTheDev.Main.Game.Settings.GameType;

public class ScoreboardUtil
{
	private Engine _engine;

	public ScoreboardUtil(Engine engine)
	{
		this._engine = engine;
	}

	private Objective _games;

	public void giveLobbyScoreboard(int timer, Player player, String game)
	{
		player.setScoreboard(ScoreboardManagers.createScoreboard());
		Objective o = ScoreboardManagers.createObjective(player.getScoreboard(), DisplaySlot.SIDEBAR,
		        "" + ChatColor.WHITE + ChatColor.BOLD + "STARTING IN " + ChatColor.GREEN + ChatColor.BOLD + timer);
		ScoreboardManagers.setScores(o, " ", 8);
		ScoreboardManagers.setScores(o, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Players" + ChatColor.WHITE + ":", 7);
		ScoreboardManagers.setScores(o, ChatColor.WHITE.toString() + Bukkit.getOnlinePlayers().size() + "/12",6);
		ScoreboardManagers.setScores(o, "  ", 5);
		ScoreboardManagers.setScores(o, "" + ChatColor.YELLOW + ChatColor.BOLD + "Game" + ChatColor.WHITE + ":", 4);
		ScoreboardManagers.setScores(o, ChatColor.WHITE + GameType.getGameType().toString().toUpperCase(), 3);
		ScoreboardManagers.setScores(o, "   ", 2);
		ScoreboardManagers.setScores(o, "" + ChatColor.YELLOW + ChatColor.BOLD + "Server" + ChatColor.WHITE + ":", 1);
		ScoreboardManagers.setScores(o, "JakeyTheDev-1", 0);
	}
	public void giveWaitingScoreboard(Player player, String game)
	{
		player.setScoreboard(ScoreboardManagers.createScoreboard());
		Objective o = ScoreboardManagers.createObjective(player.getScoreboard(), DisplaySlot.SIDEBAR,
		        "" + ChatColor.WHITE + ChatColor.BOLD + game);
		ScoreboardManagers.setScores(o, " ", 8);
		ScoreboardManagers.setScores(o, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Players" + ChatColor.WHITE + ":", 7);
		ScoreboardManagers.setScores(o, ChatColor.WHITE.toString() + Bukkit.getOnlinePlayers().size() + "/12",6);
		ScoreboardManagers.setScores(o, "  ", 5);
		ScoreboardManagers.setScores(o, "" + ChatColor.YELLOW + ChatColor.BOLD + "Game" + ChatColor.WHITE + ":", 4);
		ScoreboardManagers.setScores(o, ChatColor.WHITE + GameType.getGameType().toString().toUpperCase(), 3);
		ScoreboardManagers.setScores(o, "   ", 2);
		ScoreboardManagers.setScores(o, "" + ChatColor.YELLOW + ChatColor.BOLD + "Server" + ChatColor.WHITE + ":", 1);
		ScoreboardManagers.setScores(o, "JakeyTheDev-1", 0);
	}

	public void giveGameScoreboard(Player player, String game)
	{
		player.setScoreboard(ScoreboardManagers.createScoreboard());
		_games = ScoreboardManagers.createObjective(player.getScoreboard(), DisplaySlot.SIDEBAR,
		        "" + ChatColor.WHITE + ChatColor.BOLD + game.toUpperCase());
		int i = 0;
		for (Player all : Bukkit.getOnlinePlayers())
		{
			if (!_engine.manager.getSpectators().contains(all))
			{
				ScoreboardManagers.setScores(_games, ChatColor.GREEN + all.getName(), i);
				i++;

			}
			else
			{
				ScoreboardManagers.setScores(_games, "" + ChatColor.GRAY + ChatColor.ITALIC + all.getName(), i);
				i++;
			}
		}
	}
}
