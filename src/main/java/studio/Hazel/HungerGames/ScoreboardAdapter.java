package studio.Hazel.HungerGames;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import io.github.thatkawaiisam.assemble.AssembleAdapter;
import studio.Hazel.HungerGames.game.GameStatus;

public class ScoreboardAdapter implements AssembleAdapter {

	@Override
	public String getTitle(Player player) {
		// TODO Auto-generated method stub
		return ChatColor.translateAlternateColorCodes('&',HungerGames.getPlugin().getSettings().getString("scoreboard.title"));
	}

	@Override
	public List<String> getLines(Player player) {
		Settings settings = HungerGames.getPlugin().getSettings();
		ArrayList<String> lines = new ArrayList<>();
		if(HungerGames.getPlugin().getRunningGame().getStatus() == GameStatus.lobby || HungerGames.getPlugin().getRunningGame().getStatus() == GameStatus.starting) {
		for(String key : settings.getConfig().getConfigurationSection("scoreboard.lobby").getKeys(false)) {
		String message = ChatColor.translateAlternateColorCodes('&', settings.getString("scoreboard.lobby." + key));
		message = message.replaceAll("%players_online%", Bukkit.getOnlinePlayers().size() + "")
				.replaceAll("%players_maximum%", Bukkit.getMaxPlayers() + "");
		lines.add(message);
		}
		}else if(HungerGames.getPlugin().getRunningGame().getStatus() != GameStatus.pregame){
				for(String key : settings.getConfig().getConfigurationSection("scoreboard.game").getKeys(false)) {
				String message = ChatColor.translateAlternateColorCodes('&', settings.getString("scoreboard.game." + key));
				long time = (new Date().getTime() - HungerGames.getPlugin().getRunningGame().getGameStarted().getTime())/1000;
				message = message.replaceAll("%players_online%", Bukkit.getOnlinePlayers().size() + "")
						.replaceAll("%players_maximum%", Bukkit.getMaxPlayers() + "")
						.replaceAll("%kills%", "") //TODO
						.replaceAll("%border%", "")   //TODO
						.replaceAll("%time-playing%", (HungerGames.getPlugin().getRunningGame().getGameStarted() != null ? calc((int) time ): ""));
				lines.add(message);
				}	
		}
		
		switch(HungerGames.getPlugin().getRunningGame().getStatus()) {
		case starting:{
		String message = ChatColor.translateAlternateColorCodes('&',settings.getString("scoreboard.timers.game-start"));
		message = message.replaceAll("%time%", calc(HungerGames.getPlugin().getRunningGame().getCount()));
		lines.add(message);
		break;
		}
		case pregame: {
			String message = ChatColor.translateAlternateColorCodes('&',settings.getString("scoreboard.timers.cage-release"));
			message = message.replaceAll("%time%", calc(HungerGames.getPlugin().getRunningGame().getCount()));
			lines.add(message);	
			break;
		}
		case game: {
			String message = ChatColor.translateAlternateColorCodes('&',settings.getString("scoreboard.timers.feast"));
			message = message.replaceAll("%time%", calc(HungerGames.getPlugin().getRunningGame().getCount()));
			lines.add(message);	
			break;
		}
		case feast: {
			String message = ChatColor.translateAlternateColorCodes('&',settings.getString("scoreboard.timers.deathmatch"));
			message = message.replaceAll("%time%", calc(HungerGames.getPlugin().getRunningGame().getCount()));
			lines.add(message);	
			break;
		}
		default:
			break;			
		}
		lines.add(0,ChatColor.translateAlternateColorCodes('&', settings.getString("scoreboard.footer")));
		lines.add(ChatColor.translateAlternateColorCodes('&', settings.getString("scoreboard.header")));
		return lines;
	}
	public static String calc(int i2) {
		
		  long seconds = i2;
		    long absSeconds = Math.abs(seconds);
		    String positive = String.format(
		        "%d:%02d:%02d",
		        absSeconds / 3600,
		        (absSeconds % 3600) / 60,
		        absSeconds % 60);
		    return seconds < 0 ? "-" + positive : positive;	
		    }
}
