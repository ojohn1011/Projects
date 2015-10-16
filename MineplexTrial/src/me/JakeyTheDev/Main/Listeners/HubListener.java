package me.JakeyTheDev.Main.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.JakeyTheDev.Main.Engine;
import me.JakeyTheDev.Main.Game.Settings.GameState;

public class HubListener implements Listener
{
	private Engine _engine;
	
	public HubListener(Engine engine)
	{
		this._engine = engine;
	}
	
	@EventHandler
	public void onFoodLoss(FoodLevelChangeEvent e) 
	{
		if(e.getFoodLevel() < 20) 
		{
			e.setFoodLevel(20);
		} 
	}
	 @EventHandler
	    public void playerDamage(EntityDamageByEntityEvent event)
	    {
	        if(GameState.getGameState() != GameState.IN_PROGRESS)
	        {
	            event.setCancelled(true);
	        }

	        if(_engine.manager.getSpectators().contains(event.getDamager()))
	        {
	            event.setCancelled(true);
	        }
	    }

	    @EventHandler
	    public void dropItem(PlayerDropItemEvent event)
	    {
	        event.setCancelled(true);
	    }

	    @EventHandler
	    public void blockBreak(BlockBreakEvent event)
	    {
	        event.setCancelled(true);
	    }
}
