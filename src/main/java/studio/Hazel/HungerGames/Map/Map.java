package studio.Hazel.HungerGames.Map;

import java.util.HashMap;

import org.bukkit.Location;

import lombok.Getter;

public class Map {
private @Getter String name;
private @Getter HashMap<Integer,Location> spawnLocations = new HashMap<>();
private @Getter Location middlePoint;
public Map(String name, HashMap<Integer,Location> spawnLocations, Location middlePoint) {
this.name =name;
this.spawnLocations = spawnLocations;
this.middlePoint = middlePoint;
}
}
