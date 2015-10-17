package me.JakeyTheDev.Hub.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.JakeyTheDev.Hub.Core;
import me.JakeyTheDev.Hub.Inventory.ServerSelectorInventory;
import me.JakeyTheDev.Hub.Utils.ChatUtil;
import me.JakeyTheDev.Hub.Utils.Packets.Title;

public class SendAd 
{
	
	public static int Time;
	static int Task;
	
	public static void sendAds(final Player player) 
	{
		
		Time = 5;
		
		Task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Core.getInstance(), new Runnable() 
		{
			
			@Override
			public void run() 
			{
				if(Time == 5)
				{
					ChatUtil.sendMessage(player, ChatUtil.HUB, "The server selector will open in 5 seconds..");
					ChatUtil.sendMessage(player, ChatUtil.NONE, "");
					ChatUtil.sendMessage(player, ChatUtil.AD, "Welcome to the server!");
					ChatUtil.sendMessage(player, ChatUtil.AD, "You can donate at our shop");
					ChatUtil.sendMessage(player, ChatUtil.AD, "By doing /Buy! Or visiting the fourms");
					ChatUtil.sendMessage(player, ChatUtil.AD, "To remove ads and wait times donate!");	
	
				} else if(Time >= 1 && Time <= 4)
				{
					Title.sendTitle(player, ChatColor.AQUA + "Opening in", "" + ChatColor.AQUA + Time);

				} else if(Time <= 0) 
				{

					Bukkit.getScheduler().cancelTask(Task);

				}
				Time--;
			}
		}, 0L, 20L);
	}

}
