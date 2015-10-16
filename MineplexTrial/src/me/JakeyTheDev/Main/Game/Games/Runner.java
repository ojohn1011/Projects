package me.JakeyTheDev.Main.Game.Games;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

import me.JakeyTheDev.Main.Engine;
import me.JakeyTheDev.Main.Game.Game;
import me.JakeyTheDev.Main.Game.Settings.GameState;
import me.JakeyTheDev.Main.Game.Settings.GameType;

public class Runner extends Game
{
	private Engine _engine;

	public Runner(Engine engine) 
	{
		super("Runner", 0, Arrays.asList("Blocks fall from underneath you."
				, "Keep running to stay alive.", "Avoid falling blocks from above."));
		
		this._engine = engine;
	} 

	@Override
	public void Register()
	{
		Bukkit.getPluginManager().registerEvents(this, _engine);
	}
    @Override
    public void preLoad()
    {
        GameType.setGameType(GameType.RUNNER);
    }
	@Override
	public void unRegister() 
	{
		
		HandlerList.unregisterAll();
	}

	@Override
	public void unLoad()
	{
		unRegister();
	}
    @EventHandler
    public void playerDamage(EntityDamageEvent event)
    {
        if (GameState.getGameState() == GameState.ENDING)
        {
            event.setCancelled(true);
            return;
        }

        if (event.getEntity() instanceof Player)
        {
            Player player = (Player) event.getEntity();

            if (event.getCause() == EntityDamageEvent.DamageCause.FALL)
            {
                event.setCancelled(true);
                player.damage(1);

            } else if (event.getCause() == EntityDamageEvent.DamageCause.VOID)
            {
                event.setCancelled(true);
                player.setHealth(20);
                player.getInventory().clear();

                if (_engine.manager.getAlive().size() == 3)
                {
                    _engine.manager.setPosition(3, player);
                    System.out.println(player.getName() + " SET TO POSITION 3");

                } else if (_engine.manager.getAlive().size() == 2)
                {
                    _engine.manager.setPosition(2, player);
                    System.out.println(player.getName() + " SET TO POSITION 2");

                    for (Player alive : _engine.manager.getAlive())
                    {
                        if (alive != player)
                        {
                            _engine.manager.setPosition(1, alive);
                            System.out.println(player.getName() + " SET TO POSITION 1");
                        }
                    }
                }

                _engine.manager.removeAlive(player);
                _engine.manager.addSpectator(player);

                if (_engine.manager.getAlive().size() < 2)
                {
                	System.out.println("RAN DEBUG");
                    _engine.manager.preFinishGame();
                }
            }
        }
    }
}
