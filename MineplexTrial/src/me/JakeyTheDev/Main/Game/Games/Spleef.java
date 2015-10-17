package me.JakeyTheDev.Main.Game.Games;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.JakeyTheDev.Main.Engine;
import me.JakeyTheDev.Main.Game.Game;
import me.JakeyTheDev.Main.Game.Settings.GameState;
import me.JakeyTheDev.Main.Game.Settings.GameType;

public class Spleef extends Game
{
	private Engine _engine;

	public Spleef(Engine engine)
	{
		super("Spleef", 1, Arrays.asList("Punch blocks to break them", "1 Hunger per block smashed! "));

		this._engine = engine;
	}

	@Override
	public void Register()
	{
		Bukkit.getPluginManager().registerEvents(this, _engine);
	}

	@Override
	public void unRegister()
	{

		HandlerList.unregisterAll(this);
	}

	@EventHandler
	public void onInstaBreak(BlockDamageEvent e)
	{
		if (_engine.manager.getSpectators().contains(e.getPlayer()))
		{
			e.setCancelled(true);
		}
		else
		{

			e.getBlock().setType(Material.AIR);
			e.getBlock().getDrops().clear();
			if (!(e.getPlayer().getFoodLevel() > 8))
			{
				e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() + 1);
			}
		}
	}

	@Override
	public void preLoad()
	{
		GameType.setGameType(GameType.SPLEEF);
	}

	@Override
	public void unLoad()
	{
		unRegister();
	}

	@EventHandler
	public void playerDamage(EntityDamageEvent e)
	{
		if (GameState.getGameState() == GameState.ENDING)
		{
			e.setCancelled(true);
			return;
		}

		if (e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();

			if (e.getCause() == DamageCause.FALL)
			{
				e.setDamage(1);
			}
			else if (e.getCause() == DamageCause.VOID)
			{

				if (_engine.manager.getAlive().size() == 3)
				{
					_engine.manager.setPosition(3, player);

				}
				else if (_engine.manager.getAlive().size() == 2)
				{
					_engine.manager.setPosition(2, player);

					for (Player alive : _engine.manager.getAlive())
					{
						if (alive != player)
						{
							_engine.manager.setPosition(1, alive);
							_engine.manager.preFinishGame();
						}
					}
				}

				_engine.manager.removeAlive(player);
				_engine.manager.addSpectator(player);
			}
		}
	}
}