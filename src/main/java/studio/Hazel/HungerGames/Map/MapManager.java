package studio.Hazel.HungerGames.Map;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import lombok.Getter;
import studio.Hazel.HungerGames.HungerGames;
import studio.Hazel.HungerGames.Settings;

public class MapManager {

private @Getter HungerGames plugin;
private @Getter Map currentMap;

public MapManager(HungerGames plugin) {
this.plugin = plugin;
loadMapInfo();	
}

private void loadMapInfo() {
Settings settings = HungerGames.getPlugin().getSettings();
String name = settings.getString("map.name");
HashMap<Integer,Location> spawnLocations =  new HashMap<>();
for(String key : settings.getConfig().getConfigurationSection("map.spawnLocations").getKeys(false)) {
int x = settings.getInteger("map.spawnLocations." + key + ".x");
int y = settings.getInteger("map.spawnLocations." + key + ".y");
int z= settings.getInteger("map.spawnLocations." + key + ".z");
float yaw= settings.getConfig().getInt("map.spawnLocations." + key + ".yaw");
float pitch= settings.getInteger("map.spawnLocations." + key + ".pitch");
World world  = Bukkit.getWorld(settings.getString("map.spawnLocations." + key +".world"));
Location loc =new Location(world, x, y, z, yaw, pitch);
Integer i = Integer.parseInt(key);
spawnLocations.put(i, loc);

}
int x=  settings.getInteger("map.middlePoint.x");
int y=  settings.getInteger("map.middlePoint.y");
int z=  settings.getInteger("map.middlePoint.z");
World world = Bukkit.getWorld(settings.getString("map.middlePoint.world"));
Location middlePoint = new Location(world,x,y,z);
currentMap =new Map(name, spawnLocations, middlePoint);
}
}
