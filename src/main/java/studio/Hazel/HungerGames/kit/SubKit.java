package studio.Hazel.HungerGames.kit;

import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import java.util.*;

public class SubKit
{
    private int level;
    private String kit;
    private int unlockCoins;
    private HashMap<Integer, ItemStack> items;
    private Collection<PotionEffect> effects;
    
    public SubKit(final int level, final String kit, final int unlockCoins, final HashMap<Integer, ItemStack> items, final Collection<PotionEffect> effects) {
        this.unlockCoins = 0;
        this.items = new HashMap<Integer, ItemStack>();
        this.effects = new ArrayList<PotionEffect>();
        this.level = level;
        this.kit = kit;
        this.unlockCoins = unlockCoins;
        this.items = items;
        this.effects = effects;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public String getKit() {
        return this.kit;
    }
    
    public int getUnlockCoins() {
        return this.unlockCoins;
    }
    
    public HashMap<Integer, ItemStack> getItems() {
        return this.items;
    }
    
    public Collection<PotionEffect> getEffects() {
        return this.effects;
    }
}
