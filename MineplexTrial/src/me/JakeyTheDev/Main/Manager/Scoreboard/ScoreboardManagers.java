package me.JakeyTheDev.Main.Manager.Scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardManagers 
{
	private static ScoreboardManager manager;
	public static Scoreboard mainScoreboard;

	public ScoreboardManagers() {
		manager = Bukkit.getScoreboardManager();
		mainScoreboard = manager.getMainScoreboard();
	}

	public static Scoreboard createScoreboard() {
		Scoreboard board = manager.getNewScoreboard();
		return board;
	}

	public static Objective createObjective(Scoreboard board, DisplaySlot slot,
			String name)
	{
		Objective objective = board.registerNewObjective("Board", "dummy");
		objective.setDisplayName(name);
		objective.setDisplaySlot(slot);

		return objective;
	}

	public static void setScores(Objective o, String message, int rowNumber) {
		if (message == null) {
			int spaces = Integer.parseInt((rowNumber + "").replace("-", ""));
			message = ChatColor.WHITE + "";
			for (int x = 0; x < spaces; x++) {
				message = message + " ";
			}
		}

		Score score = o.getScore(message);
		score.setScore(rowNumber);
	}
}	