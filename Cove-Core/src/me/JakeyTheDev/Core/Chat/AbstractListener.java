package me.JakeyTheDev.Core.Chat;

import org.bukkit.event.Listener;

import me.JakeyTheDev.Core.Core;

public abstract class AbstractListener implements Listener {

	protected Core core;
	
	public AbstractListener() {
		this.core = Core.getInstance();
	}
}