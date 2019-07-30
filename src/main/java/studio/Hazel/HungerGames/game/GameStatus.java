package studio.Hazel.HungerGames.game;

public enum GameStatus
{
    lobby("lobby", 0), 
    starting("starting", 1), 
    pregame("pregame", 2), 
    game("game", 3), 
    feast("feast", 4), 
    deathmatch("deathmatch", 5), 
    endgame("endgame", 6);
    
    private GameStatus(final String s, final int n) {
    }
}
