package studio.Hazel.HungerGames.kit;

import org.bukkit.inventory.*;
import java.util.*;

public class Kit
{
    private String name;
    private int unlockAmount;
    private ItemStack icon;
    private HashMap<Integer, SubKit> subKits;
    
    public Kit(final String name, final int unlockAmount, final HashMap<Integer, SubKit> subKits, final ItemStack icon) {
        this.unlockAmount = 0;
        this.name = name;
        this.unlockAmount = unlockAmount;
        this.subKits = subKits;
        this.icon = icon;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getUnlockAmount() {
        return this.unlockAmount;
    }
    
    public ItemStack getIcon() {
        return this.icon;
    }
    
    public HashMap<Integer, SubKit> getSubKits() {
        return this.subKits;
    }
}
