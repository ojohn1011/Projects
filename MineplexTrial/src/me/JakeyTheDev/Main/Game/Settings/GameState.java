package me.JakeyTheDev.Main.Game.Settings;

public enum GameState 
{
	NOT_READY(),
    IN_PROGRESS(),
    COUNTDOWN(),
    ENDING();

    private static GameState _gamestate;

    public static GameState getGameState()
    {
        return _gamestate;
    }
    public static void setGameState(GameState gamestate)
    {
        _gamestate = gamestate;
    }
    public static boolean isGameState(GameState state) 
    {
    	return GameState.getGameState() == state;
    }
}
