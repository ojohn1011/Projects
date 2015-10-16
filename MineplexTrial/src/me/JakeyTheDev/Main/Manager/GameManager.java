package me.JakeyTheDev.Main.Manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import me.JakeyTheDev.Main.Engine;
import me.JakeyTheDev.Main.Format.ChatFormat;
import me.JakeyTheDev.Main.Game.Game;
import me.JakeyTheDev.Main.Game.Games.Runner;
import me.JakeyTheDev.Main.Game.Games.Spleef;
import me.JakeyTheDev.Main.Game.Settings.GameState;
import me.JakeyTheDev.Main.Game.Settings.GameType;

public class GameManager
{

	private Engine _engine;

	public GameManager(Engine engine)
	{
		this._engine = engine;
	}

	protected GameType type;
	private Game _selectedGame;
	private boolean _gameLoaded;
	private int _task, _time;
	public String map;
	public int minTime = 3, maxTime = 12;
	private List<Player> _spectators = new ArrayList<>();
	private List<Player> _alivePlayers = new ArrayList<>();
	private HashMap<Integer, Player> _possition = new HashMap<>();

	public List<Game> games = new ArrayList<>();

	public void setGames()
	{
		games.add(new Spleef(_engine));
		games.add(new Runner(_engine));
	}

	public void start()
	{
		this.load();
	}

	public void stop()
	{
		stopCountdown();
	}

	public void restartGame()
	{
		this.load();
	}

	public void finishGame()
	{
		_selectedGame.unLoad();

		_engine.module.deleteWorld(false);

		GameState.setGameState(GameState.ENDING);

		_alivePlayers.clear();

		this._possition.clear();

		Bukkit.getScheduler().runTaskLater(_engine, new Runnable()
		{
			@Override
			public void run()
			{
				start();
			}
		}, 20L);
	}

	public void load()
	{

		for (Player all : Bukkit.getOnlinePlayers())
		{
			all.teleport(Bukkit.getWorld("world").getSpawnLocation());
		}

		if (!_gameLoaded)
		{

			int random = new Random().nextInt(_engine.manager.games.size());
			_selectedGame = _engine.manager.games.get(random);
			_selectedGame.preLoad();

			map = getRandomWorld();

			_engine.module.loadWorld(GameType.getGameType().toString().toLowerCase(), map.toString());

			this._gameLoaded = true;

			startCountdown();
		}
	}

	public void startCountdown()
	{

		this._time = 30;

		_task = Bukkit.getScheduler().scheduleSyncRepeatingTask(_engine, new Runnable()
		{

			@Override
			public void run()
			{
				for (Player all : Bukkit.getOnlinePlayers()) 
				{
					_engine.Scoreboard.giveCountdownScoreboard(all, "MINEPLEX", _time);
				}
				if (_time == 0)
				{
					for (Player all : Bukkit.getOnlinePlayers())
					{
						all.teleport(Bukkit.getWorld(_engine.module.GameWorld).getSpawnLocation());
						_engine.Scoreboard.giveGameScoreboard(all, _selectedGame.getName());
						_engine.manager._alivePlayers.add(all);
					}
					GameState.setGameState(GameState.IN_PROGRESS);
					ChatFormat.broadcastMessage(
		                    "The game, " + ChatColor.GREEN + _selectedGame.getName() + ChatColor.BLUE + " has started!",
		                    ChatFormat.GAME);
					_engine.manager._selectedGame.Register();
					Bukkit.getScheduler().cancelTask(_time);
				}
				else if (_time >= 30)
				{
					_engine.module.createWorld(_engine.module.GameWorld);
					ChatFormat.broadcastMessage("The game is starting in " + _time, ChatFormat.GAME);
					clearPosition();
				}
				else if (_time == 20)
				{
					ChatFormat.broadcastMessage("", ChatFormat.LINE);
					ChatFormat.broadcastMessage("", ChatFormat.NONE);
					ChatFormat.broadcastMessage("Game: " + ChatColor.GREEN + _selectedGame.getName(), ChatFormat.NONE);
					ChatFormat.broadcastMessage("", ChatFormat.NONE);
					ChatFormat.broadcastMessage(
		                    "Map: " + ChatColor.GREEN + getMapName().toString().toUpperCase().replace("_", " "),
		                    ChatFormat.NONE);
					ChatFormat.broadcastMessage("", ChatFormat.NONE);
					for (String s : _selectedGame.getDescription())
					{
						ChatFormat.broadcastMessage(ChatColor.GREEN + s, ChatFormat.NONE);
					}
					ChatFormat.broadcastMessage("", ChatFormat.NONE);
					ChatFormat.broadcastMessage("", ChatFormat.LINE);
				}
				else if (_time == 10)
				{
					ChatFormat.broadcastMessage("The game is starting in " + ChatColor.GREEN + _time, ChatFormat.GAME);
				}
				else if (_time >= 1 && _time <= 5)
				{
					ChatFormat.broadcastMessage("The game is starting in " + ChatColor.GREEN + _time, ChatFormat.GAME);
					for (Player all : Bukkit.getOnlinePlayers())
					{
						all.playSound(all.getLocation(), Sound.ANVIL_LAND, 1, 1);
					}
				}
				_time--;
			}
		}, 0L, 20L);
	}

	public void stopCountdown()
	{
		Bukkit.getScheduler().cancelTask(_task);
	}

	public void preFinishGame()
	{

		GameState.setGameState(GameState.ENDING);

		if (_possition.size() == 2)
		{
			ChatFormat.broadcastMessage("", ChatFormat.LINE);
			ChatFormat.broadcastMessage("First Winner: " + ChatColor.GREEN + _possition.get(1).getName(),
			        ChatFormat.GAME);
			ChatFormat.broadcastMessage("Second winner: " + ChatColor.GREEN + _possition.get(2).getName(),
			        ChatFormat.GAME);
			ChatFormat.broadcastMessage("", ChatFormat.LINE);

		}
		else
		{

			ChatFormat.broadcastMessage("", ChatFormat.LINE);
			ChatFormat.broadcastMessage("First Winner: " + ChatColor.GREEN + _possition.get(1).getName(),
			        ChatFormat.GAME);
			ChatFormat.broadcastMessage("Second winner: " + ChatColor.GREEN + _possition.get(2).getName(),
			        ChatFormat.GAME);
			ChatFormat.broadcastMessage("Third winner: " + ChatColor.GREEN + _possition.get(3).getName(),
			        ChatFormat.GAME);
			ChatFormat.broadcastMessage("", ChatFormat.LINE);
		}

		Bukkit.getScheduler().runTaskLater(_engine, new Runnable()
		{
			@Override
			public void run()
			{
				restartGame();
			}
		}, 5 * 20);
	}

	public void addAlive(Player player)
	{
		this._alivePlayers.add(player);
	}

	public void removeAlive(Player player)
	{
		this._alivePlayers.remove(player);
	}

	public void addSpectator(Player player)
	{
		player.setAllowFlight(true);
		player.setFlying(true);
		player.teleport(player.getWorld().getSpawnLocation());

		ChatFormat.sendMessage(player, "Spectator added!", ChatFormat.GAME);

		if (!this._spectators.contains(player))
		{
			this._spectators.add(player);
		}

		for (Player all : Bukkit.getOnlinePlayers())
		{
			all.hidePlayer(player);
			_engine.Scoreboard.giveGameScoreboard(all, _selectedGame.getName());
		}
	}

	public void removeSpectator(Player player)
	{
		for (Player all : Bukkit.getOnlinePlayers())
		{
			all.showPlayer(player);
		}

		player.setAllowFlight(false);
		player.setFlying(false);

		ChatFormat.sendMessage(player, "Spectator removed!", ChatFormat.GAME);

		if (this._spectators.contains(player)) this._spectators.remove(player);
	}

	public List<Player> getSpectators()
	{
		return this._spectators;
	}

	public Game getSelectedGame()
	{
		return this._selectedGame;
	}

	public List<Player> getAlive()
	{
		return this._alivePlayers;
	}

	public void setPosition(int slot, Player player)
	{
		this._possition.put(slot, player);
	}

	public void clearPosition()
	{
		this._possition.clear();
	}

	public String getRandomWorld()
	{
		File directory = null;
		try
		{
			directory = new File(this._engine.getDataFolder().getCanonicalPath(),
			        "Games/" + GameType.getGameType().toString().toLowerCase());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		List<String> worlds = Lists.newArrayList();

		for (File f : directory.listFiles())
		{
			if (!f.isHidden() && f.isDirectory())
			{
				worlds.add(f.getName());
			}
		}
		return worlds.get((new Random()).nextInt(worlds.size()));
	}

	public String getMapName()
	{
		return this.map;
	}
}
