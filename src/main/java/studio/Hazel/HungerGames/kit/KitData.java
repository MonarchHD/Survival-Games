package studio.Hazel.HungerGames.kit;

import java.io.File;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import studio.Hazel.HungerGames.Config;
import studio.Hazel.HungerGames.HungerGames;

public class KitData
{
    private Config config;
    
    public KitData(final HungerGames plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        if (!new File("kits.yml").exists()) {
            plugin.saveDefaultConfig();
        }
        this.config = new Config(plugin, "kits.yml");
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
}
