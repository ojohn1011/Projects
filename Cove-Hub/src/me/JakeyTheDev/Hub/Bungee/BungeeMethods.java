package me.JakeyTheDev.Hub.Bungee;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.entity.Player;

import me.JakeyTheDev.Hub.Core;
import me.JakeyTheDev.Hub.Utils.ChatUtil;

public class BungeeMethods
{
	
	public static void sendToServer(Player player, String Server) 
	
	{
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(stream);
		
		try 
		{
			
			output.writeUTF("Connect");
			output.writeUTF(Server);
			
		} catch(Exception e)
		{
			ChatUtil.sendMessage(player, ChatUtil.WARNING, "This server does not exist.");
			System.out.println("ERROR VIA BUNGEE SEND TO SERVER METHOD");
		}
		player.sendPluginMessage(Core.getInstance(), "BungeeCord", stream.toByteArray());
	}
}
