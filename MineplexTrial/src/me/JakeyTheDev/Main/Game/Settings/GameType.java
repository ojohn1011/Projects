package me.JakeyTheDev.Main.Game.Settings;

public enum GameType 
{
	NONE(),
    SPLEEF(),
    RUNNER();

    private static GameType _gameType;

    public static GameType getGameType()
    {
        return _gameType;
    }
    public static void setGameType(GameType type)
    {
    	_gameType = type;
    }
    public static boolean isGameType(GameState type) 
    {
    	return GameState.getGameState() == type;
    }
}