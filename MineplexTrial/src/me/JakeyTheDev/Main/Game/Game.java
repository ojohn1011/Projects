package me.JakeyTheDev.Main.Game;

import java.util.List;

import org.bukkit.event.Listener;

public abstract class Game implements Listener
{
	
	private String _name;
	private int _id;
	private List<String> _description;
	
	public Game(String gameName, int gameID, List<String> list)
	{
		this._name = gameName;
		this._id = gameID;
		this._description = list;

	}
	
	public abstract void Register();
	public abstract void unRegister();
	public abstract void preLoad();
	public abstract void unLoad();
	
	public String getName() { return this._name; }
	public int getID() { return this._id; }
	public List<String> getDescription() { return this._description; }

}
