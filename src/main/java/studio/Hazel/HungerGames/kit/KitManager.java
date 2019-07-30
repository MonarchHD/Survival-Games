package studio.Hazel.HungerGames.kit;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import studio.Hazel.HungerGames.HungerGames;

public class KitManager
{
    private HungerGames plugin;
    private KitData kits;
    private Collection<Kit> kit;
    
    public KitManager(final HungerGames plugin) {
        this.kit = Collections.emptyList();
        this.plugin = plugin;
        this.kits = new KitData(plugin);
        this.loadKits();
    }
    
    public void loadKits() {
    	if(kits.contains("kits") == false) {
    	Bukkit.broadcastMessage(ChatColor.YELLOW + "Don't forget to setup the kits!");
    	return;
    	}
        for (final String kit : this.kits.getConfig().getConfigurationSection("kits").getKeys(false)) {
            final String name = this.kits.getString("kits." + kit + ".name");
            final ItemStack icon = this.kits.getItemStack("kits." + kit + ".icon");
            final Integer unlockAmount = this.kits.getInteger("kits." + kit + ".unlockAmount");
            final HashMap<Integer, SubKit> subKits = new HashMap<Integer, SubKit>();
            for (final String subKit : this.kits.getConfig().getConfigurationSection("kits." + kit + ".subKit").getKeys(false)) {
                final HashMap<Integer, ItemStack> inventory = new HashMap<Integer, ItemStack>();
                final Collection<PotionEffect> effects = Collections.emptyList();
                final int level = this.kits.getInteger("kits." + kit + ".subKit." + subKit + ".level");
                final int unlockCoins = this.kits.getInteger("kits." + kit + ".subKit." + subKit + ".unlockCoins");
                for (final String i : this.kits.getConfig().getConfigurationSection("kits." + kit + ".subKit." + subKit + ".items").getKeys(false)) {
                    final Integer item = Integer.parseInt(i);
                    final ItemStack stack = this.kits.getItemStack("kits." + kit + ".subKit." + subKit + ".items." + item);
                    if (stack == null) {
                        continue;
                    }
                    inventory.put(item, stack);
                }
                if (this.kits.contains("kits." + kit + ".subKit." + subKit + ".effects")) {
                    for (final String j : this.kits.getConfig().getConfigurationSection("kits." + kit + ".subKit." + subKit + ".effects").getKeys(false)) {
                        final PotionEffectType type = PotionEffectType.getByName(this.kits.getConfig().getString("kits." + kit + ".subKit." + subKit + ".effects." + j + ".type"));
                        final int duration = this.kits.getInteger("kits." + kit + ".subKit." + subKit + ".effects." + j + ".duration");
                        final int amplifier = this.kits.getInteger("kits." + kit + ".subKit." + subKit + ".effects." + j + ".amplifier");
                        final boolean ambient = this.kits.getBoolean("kits." + kit + ".subKit." + subKit + ".effects." + j + ".ambient");
                        final boolean particles = this.kits.getBoolean("kits." + kit + ".subKit." + subKit + ".effects." + j + ".particles");
                        final PotionEffect effect = new PotionEffect(type, duration, amplifier, ambient, particles);
                        effects.add(effect);
                    }
                }
                final SubKit sub = new SubKit(level, name, unlockCoins, inventory, effects);
                subKits.put(sub.getLevel(), sub);
            }
            final Kit k = new Kit(name, unlockAmount, subKits, icon);
            this.kit.add(k);
        }
    }
    
    public void addKit(final String name, final int unlockAmount, final SubKit kit, final ItemStack icon) {
        final HashMap<Integer, SubKit> k = new HashMap<Integer, SubKit>();
        k.put(1, kit);
        final Kit ki = new Kit(name, unlockAmount, k, icon);
        this.kit.add(ki);
    }
    
    public Kit getKit(final String name) {
        for (final Kit kit : this.kit) {
            if (kit.getName().equalsIgnoreCase(name)) {
                return kit;
            }
        }
        return null;
    }
    
    public SubKit getSubKit(final String kitName, final int level) {
        final Kit kit = this.getKit(kitName);
        if (kit == null) {
            return null;
        }
        final SubKit subKit = kit.getSubKits().get(level);
        if (subKit == null) {
            return null;
        }
        return subKit;
    }
    
    public HungerGames getPlugin() {
        return this.plugin;
    }
    
    public KitData getKits() {
        return this.kits;
    }
    
    public Collection<Kit> getKit() {
        return this.kit;
    }
}
