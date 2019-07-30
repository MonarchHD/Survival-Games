package studio.Hazel.HungerGames;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;

public class Settings
{
    private Config config;
    
    public Settings(final HungerGames plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        if (!new File("config.yml").exists()) {
            plugin.saveDefaultConfig();
        }
        this.config = new Config(plugin, "config.yml");
        new Messages(this);

    }
    
    public void load() {
    }
    
    public void unload() {
    }
    
    public Boolean contains(final String arg0) {
        return this.config.contains(arg0);
    }
    
    public Boolean getBoolean(final String arg0) {
        return this.config.getBoolean(arg0);
    }
    
    public Double getDouble(final String arg0) {
        return this.config.getDouble(arg0);
    }
    
    public ItemStack getItemStack(final String arg0) {
        return this.config.getItemStack(arg0);
    }
    
    public Integer getInteger(final String arg0) {
        return this.config.getInt(arg0);
    }
    
    public Long getLong(final String arg0) {
        return this.config.getLong(arg0);
    }
    
    public String getString(final String arg0) {
        return this.config.getString(arg0);
    }
    
    public List<String> getStringList(final String arg0) {
        return (List<String>)this.config.getStringList(arg0);
    }
    
    public void reload() {
        (this.config = new Config(HungerGames.getPlugin(), "config")).save();
    }
    
    public void save() {
        this.config.save();
    }
    
    public void set(final String arg0, final Object arg1) {
        this.config.set(arg0, arg1);
    }
    
    public Config getConfig() {
        return this.config;
    }
    
    public static class Messages
    {
        private static String eleminatedByQuiting;
        private static String timerHasbeenCancelled;
        private static String timerStarting;
        private static String countDown;
        private static String playerJoinedLobby;
        private static String gamehasAlreadyStarted;
        private static @Getter String releaseCage;
        private static @Getter String releaseCountdown;
        public Messages(final Settings settings) {
            Messages.eleminatedByQuiting = ChatColor.translateAlternateColorCodes('&', settings.getString("messages.eleminated_By_Leaving"));
            Messages.timerHasbeenCancelled = ChatColor.translateAlternateColorCodes('&', settings.getString("messages.timer_cancelled"));
            Messages.timerStarting = ChatColor.translateAlternateColorCodes('&', settings.getString("messages.timer_starting"));
            Messages.playerJoinedLobby = ChatColor.translateAlternateColorCodes('&', settings.getString("messages.player_joins_lobby"));
            Messages.gamehasAlreadyStarted = ChatColor.translateAlternateColorCodes('&', "messages.game-already-started");
            Messages.countDown = ChatColor.translateAlternateColorCodes('&', settings.getString("messages.timer_countdown"));
            Messages.releaseCage = ChatColor.translateAlternateColorCodes('&', settings.getString("messages.release_cage_message"));
            Messages.releaseCountdown = ChatColor.translateAlternateColorCodes('&', settings.getString("messages.release_countdown"));
        }
        
        public static String getEleminatedByQuiting() {
            return Messages.eleminatedByQuiting;
        }
        
        public static String getTimerHasbeenCancelled() {
            return Messages.timerHasbeenCancelled;
        }
        
        public static String getTimerStarting() {
            return Messages.timerStarting;
        }
        
        public static String getCountDown() {
            return Messages.countDown;
        }
        
        public static String getPlayerJoinedLobby() {
            return Messages.playerJoinedLobby;
        }
        
        public static String getGamehasAlreadyStarted() {
            return Messages.gamehasAlreadyStarted;
        }
    }
}
