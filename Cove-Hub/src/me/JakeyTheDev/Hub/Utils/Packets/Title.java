package me.JakeyTheDev.Hub.Utils.Packets;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Title {
	
	public static void sendTitle(Player p, String title, String subTitle) {
		IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		IChatBaseComponent icbc2 = ChatSerializer.a("{\"text\": \"" + subTitle + "\"}");
		
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, icbc);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, icbc2);
		PacketPlayOutTitle timingPackage = new PacketPlayOutTitle(10, 50, 10);
		
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(titlePacket);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitlePacket);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(timingPackage);
	}
}
