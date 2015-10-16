package me.JakeyTheDev.Hub.CommandBlocker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.JakeyTheDev.Hub.Utils.ChatUtil;

public class PluginBlocker implements Listener {

	@EventHandler
	public void blockPlugin(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().contains("/pl") || e.getMessage().contains("/plugin")|| e.getMessage().contains("/?")) {
			if(e.getPlayer().isOp()) {

			} else {
				ChatUtil.sendMessage(e.getPlayer(), ChatUtil.FABULOUS, "This command could of been fabulous! But erm no.");
				e.setCancelled(true);
			}
		}
	}
}
