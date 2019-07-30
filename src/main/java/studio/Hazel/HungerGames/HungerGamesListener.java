package studio.Hazel.HungerGames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import studio.Hazel.HungerGames.game.GameStatus;

public class HungerGamesListener implements Listener
{
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        event.setJoinMessage((String)null);
        event.getPlayer().teleport(Bukkit.getWorld("Game").getSpawnLocation());

        if (HungerGames.getPlugin().getRunningGame().getStatus() == GameStatus.lobby || HungerGames.getPlugin().getRunningGame().getStatus() == GameStatus.starting) {
            if (HungerGames.getPlugin().getRunningGame().getStatus() != GameStatus.starting && Bukkit.getOnlinePlayers().size() >= HungerGames.getPlugin().getSettings().getInteger("player-countdown-minimum")) {
                HungerGames.getPlugin().getRunningGame().setStatus(GameStatus.starting);
                HungerGames.getPlugin().getRunningGame().setCount(HungerGames.getPlugin().getSettings().getInteger("countdown-timer") * 60);
                HungerGames.getPlugin().getRunningGame().setTimerChange(true);
            }
            return;
        }
        if (!event.getPlayer().hasPermission(HungerGames.getPlugin().getSettings().getString("bypass-login-permission"))) {
            event.getPlayer().kickPlayer(ChatColor.RED + "This game has already started.");
        }
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
        if (HungerGames.getPlugin().getRunningGame().getStatus() == GameStatus.starting) {
            if (Bukkit.getOnlinePlayers().size() < HungerGames.getPlugin().getSettings().getInteger("player-countdown-minimum")) {
                Bukkit.broadcastMessage(Settings.Messages.getTimerHasbeenCancelled());
                HungerGames.getPlugin().getRunningGame().setStatus(GameStatus.lobby);
                HungerGames.getPlugin().getRunningGame().setTimerChange(true);
                HungerGames.getPlugin().getRunningGame().setCount(20);
                return;
            }
            if (HungerGames.getPlugin().getRunningGame().getStatus() != GameStatus.endgame) {
                String eleminate = Settings.Messages.getEleminatedByQuiting();
                eleminate = eleminate.replaceAll("%player%", event.getPlayer().getName()).replaceAll("%players_alive%", new StringBuilder(String.valueOf(HungerGames.getPlugin().getRunningGame().getPlayers().size())).toString()).replaceAll("%players_maximum%", new StringBuilder(String.valueOf(HungerGames.getPlugin().getRunningGame().getPeopleinMatch())).toString());
                Bukkit.broadcastMessage(eleminate);
                //TODO: Eleminate player
            }
        }    
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
   	if(HungerGames.getPlugin().getRunningGame().getStatus() == GameStatus.pregame) {
   	if(event.getTo().getBlockX() != event.getFrom().getBlockX() || event.getTo().getBlockZ() != event.getFrom().getBlockZ() || (event.getTo().getBlockY() != event.getFrom().getBlockY())) {
   	   	event.setCancelled(true);	
   	   	return;
   	}
   	}
    }
    
}
