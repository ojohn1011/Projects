package me.JakeyTheDev.Main.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.JakeyTheDev.Main.Engine;
import me.JakeyTheDev.Main.Format.ChatFormat;
import me.JakeyTheDev.Main.Game.Settings.GameState;
import me.JakeyTheDev.Main.Game.Settings.GameType;

public class ConnectionListener implements Listener
{
	private Engine _engine;

	public ConnectionListener(Engine engine)
	{
		this._engine = engine;
	}

	private int _task;

	@EventHandler
	public void playerConnect(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		
		for(int i = 1; i < 100; i++) {
			ChatFormat.sendMessage(player, " ", ChatFormat.NONE);
		}

		if (player.isOp())
		{
			player.setPlayerListName(
			        ChatColor.DARK_RED.toString() + ChatColor.BOLD + "LT " + ChatColor.YELLOW + player.getName());
		}
		else
		{
			player.setPlayerListName(
			        ChatColor.RED.toString() + ChatColor.BOLD + "JR.DEV " + ChatColor.YELLOW + player.getName());
		}

		if (GameState.getGameState() == null) GameState.setGameState(GameState.NOT_READY);
		if (GameType.getGameType() == null) GameType.setGameType(GameType.NONE);

		e.setJoinMessage(ChatFormat.GAME.getPrefixColour() + ChatFormat.GAME.getPrefix() + " " + ChatColor.GREEN
		        + player.getName());

		switch (GameState.getGameState())
		{
			case IN_PROGRESS:
				_engine.manager.addSpectator(player);
				break;

			case NOT_READY:
				if (Bukkit.getOnlinePlayers().size() >= _engine.manager.minTime)
				{
					_engine.manager.addAlive(player);
					_engine.manager.start();
					player.teleport(Bukkit.getWorld("world").getSpawnLocation());

					return;
				}
				_engine.Scoreboard.giveWaitingScoreboard(e.getPlayer(), GameType.getGameType().toString());
				e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0, 88, 0));
				break;

			case COUNTDOWN:

				break;

			case ENDING:
				_engine.manager.addSpectator(player);
				break;
		}
	}

	@EventHandler
	public void playerDisconnect(PlayerQuitEvent event)
	{

		event.setQuitMessage(ChatFormat.GAME.getPrefix() + event.getPlayer().getName());

		switch (GameState.getGameState())
		{
			case NOT_READY:
				for (Player all : Bukkit.getOnlinePlayers())
				{
					if (all != event.getPlayer())
					{
						_engine.Scoreboard.giveWaitingScoreboard(all, _engine.manager.getSelectedGame().getName());
					}
				}
				break;

			case COUNTDOWN:
				if (Bukkit.getOnlinePlayers().size() <= _engine.manager.minTime)
				{
					GameState.setGameState(GameState.NOT_READY);
					_engine.manager.stopCountdown();
					for (Player all : Bukkit.getOnlinePlayers())
					{
						_engine.Scoreboard.giveWaitingScoreboard(all, _engine.manager.getSelectedGame().getName());
					}
				}
				break;

			case IN_PROGRESS:
				if (_engine.manager.getAlive().size() == 3)
				{
					_engine.manager.setPosition(3, event.getPlayer());

				}
				else if (_engine.manager.getAlive().size() == 2)
				{
					_engine.manager.setPosition(2, event.getPlayer());

					for (Player alive : _engine.manager.getAlive())
					{
						if (alive != event.getPlayer())
						{
							_engine.manager.setPosition(1, alive);
						}
					}
				}

				if (!_engine.manager.getSpectators().contains(event.getPlayer()))
				    _engine.manager.addSpectator(event.getPlayer());

				if (_engine.manager.getAlive().contains(event.getPlayer()))
				    _engine.manager.removeAlive(event.getPlayer());

				if (_engine.manager.getAlive().size() < 2)
				{
					_engine.manager.preFinishGame();
				}
				break;
			default:
				break;
		}
	}
}