package me.JakeyTheDev.Hub.DoubleJump;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DoubleJumpHandler extends JavaPlugin implements Listener {
	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if(player.getGameMode() == GameMode.CREATIVE)
			return;
		event.setCancelled(true);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.setVelocity(player.getLocation().getDirection().multiply(1.5)
		 .setY(1));
	}
	
	@EventHandler
	public void doubleJump(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if ((player.getGameMode() != GameMode.CREATIVE)
			&& (player.getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR) && (!player.isFlying())) {
			
			player.setAllowFlight(true);
			
			
		}
			
			
		
	}
}
