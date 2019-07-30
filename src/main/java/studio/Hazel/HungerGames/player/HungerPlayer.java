package studio.Hazel.HungerGames.player;

import studio.Hazel.HungerGames.kit.*;

public class HungerPlayer
{
    private int kills;
    private int deaths;
    private SubKit selectedKit;
    private SubKit defaultKit;
    private int coins;
    
    public void addKill() {
        ++this.kills;
    }
    
    public int getKills() {
        return this.kills;
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public SubKit getSelectedKit() {
        return this.selectedKit;
    }
    
    public SubKit getDefaultKit() {
        return this.defaultKit;
    }
    
    public int getCoins() {
        return this.coins;
    }
}
