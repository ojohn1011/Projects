
package me.JakeyTheDev.Core.Listeners;

import org.bukkit.Bukkit;

import me.JakeyTheDev.Core.Core;
import me.JakeyTheDev.Core.Chat.AsyncChatListener;

public class RegisterListener
{
	
	public static void register()
	{
		
		Bukkit.getPluginManager().registerEvents(new AsyncChatListener(), Core.getInstance());
		
	}
	
}
