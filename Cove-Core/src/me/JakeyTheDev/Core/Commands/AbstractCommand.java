package me.JakeyTheDev.Core.Commands;

import org.bukkit.command.CommandExecutor;

import me.JakeyTheDev.Core.Core;
import me.JakeyTheDev.Core.Utils.ChatUtil;


public abstract class AbstractCommand implements CommandExecutor {

	protected Core core;
	protected ChatUtil chat;
	protected int rank;
	
	public AbstractCommand(int rank) {
		this.core = Core.getInstance();
		this.chat = this.core.getChatManager();
		this.rank = rank;
	}
}