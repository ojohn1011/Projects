package me.JakeyTheDev.Core.Utils;

public class MathUtils 
{
	
	public static boolean isNumeric(String str)
	{
		try 
		{
			Integer.parseInt(str);
		} catch (NumberFormatException e)
		{
			return false;
		}
		return true;
	}
}
