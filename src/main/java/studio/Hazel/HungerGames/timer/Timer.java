package studio.Hazel.HungerGames.timer;

import java.util.Date;

import org.bukkit.Bukkit;

import studio.Hazel.HungerGames.HungerGames;
import studio.Hazel.HungerGames.Settings;
import studio.Hazel.HungerGames.game.Game;
import studio.Hazel.HungerGames.game.GameStatus;

public class Timer implements Runnable
{
    private Game game;
    
    public Timer( Game game) {
        this.game = game;
    }
    
    @Override
    public void run() {
    
            switch (game.getStatus()) {
                case lobby: {
                    if (this.game.isTimerChange()) {
                        this.game.setTimerChange(false);
                        Bukkit.broadcastMessage(Settings.Messages.getTimerHasbeenCancelled());
                        break;
                    }
                    break;
                }
                case starting: {
                    if (this.game.isTimerChange()) {
                        this.game.setTimerChange(false);
                        Bukkit.broadcastMessage(Settings.Messages.getTimerStarting().replaceAll("%time_till_game_start%", (HungerGames.getPlugin().getSettings().getInteger("countdown-timer") == 1 ?  HungerGames.getPlugin().getSettings().getInteger("countdown-timer") + " minute": HungerGames.getPlugin().getSettings().getInteger("countdown-timer") + " minutes")));
                        this.game.countDown();
                    }
                    switch (this.game.getCount()) {
                        case 30: {
                             String message = Settings.Messages.getCountDown();
                            message = message.replaceAll("%time_till_game%", "30 seconds");
                            Bukkit.broadcastMessage(message);
                            this.game.countDown();
                            break;
                        }
                        case 15: {
                             String message = Settings.Messages.getCountDown();
                            message = message.replaceAll("%time_till_game%", "15 seconds");
                            Bukkit.broadcastMessage(message);
                            this.game.countDown();
                            break;
                        }
                        case 10: {
                             String message = Settings.Messages.getCountDown();
                            message = message.replaceAll("%time_till_game%", "10 seconds");
                            Bukkit.broadcastMessage(message);
                            this.game.countDown();
                            break;
                        }
                        case 5: {
                             String message = Settings.Messages.getCountDown();
                             message =  message.replaceAll("%time_till_game%", "5 seconds");
                            Bukkit.broadcastMessage(message);
                            this.game.countDown();
                            break;
                        }
                        case 4: {
                             String message = Settings.Messages.getCountDown();
                             message =  message.replaceAll("%time_till_game%", "4 seconds");
                            Bukkit.broadcastMessage(message);
                            this.game.countDown();
                            break;
                        }
                        case 3: {
                             String message = Settings.Messages.getCountDown();
                             message =  message.replaceAll("%time_till_game%", "3 seconds");
                            Bukkit.broadcastMessage(message);
                            this.game.countDown();
                            break;
                        }
                        case 2: {
                             String message = Settings.Messages.getCountDown();
                            message = message.replaceAll("%time_till_game%", "2 seconds");
                            Bukkit.broadcastMessage(message);
                            this.game.countDown();
                            break;
                        }
                        case 1: {
                             String message = Settings.Messages.getCountDown();
                            message = message.replaceAll("%time_till_game%", "1 second");
                            this.game.countDown();
                            Bukkit.broadcastMessage(message);
                            break;
                        }
                        case 0: {
                        	this.game.setCount(15);
                        	this.game.setStatus(GameStatus.pregame);
                            this.game.teleportPlayers();
                            break;
                        }
                        default: {
                        	this.game.countDown();
                            break;	
                            }
                    }
                  break;
                }
                case pregame: {
                if(game.isTimerChange()) {
                	Bukkit.broadcastMessage(Settings.Messages.getReleaseCage());
                	game.countDown();
                	game.setTimerChange(false);
                	break;
                }	
                switch(game.getCount()) {
                case 10: {
                	String message = Settings.Messages.getReleaseCountdown();
                	message = message.replaceAll("%time_till_release%", "10 seconds");
                	Bukkit.broadcastMessage(message);
                	game.countDown();   
                	break;	
                }
                case 5: {
                	String message = Settings.Messages.getReleaseCountdown();
                	message = message.replaceAll("%time_till_release%", "5 seconds");
                	Bukkit.broadcastMessage(message);
                	game.countDown();   
                	break;		
                }
                case 4:{
                	String message = Settings.Messages.getReleaseCountdown();
                	message = message.replaceAll("%time_till_release%", "4 seconds");
                	Bukkit.broadcastMessage(message);
                	game.countDown();                  	
                	break;		
                }
                case 3: {
                	String message = Settings.Messages.getReleaseCountdown();
                	message = message.replaceAll("%time_till_release%", "3 seconds");
                	Bukkit.broadcastMessage(message);
                	game.countDown();        	
                	break;		
                }
                case 2: {
                	String message = Settings.Messages.getReleaseCountdown();
                	message = message.replaceAll("%time_till_release%", "2 seconds");
                	Bukkit.broadcastMessage(message);
                	game.countDown();     	
                	break;		
                }
                case 1: {
                	String message = Settings.Messages.getReleaseCountdown();
                	message = message.replaceAll("%time_till_release%", "1 second");
                	Bukkit.broadcastMessage(message);
                	game.countDown();           
                	break;		
                }
                case 0: {
                	game.setGameStarted(new Date());
                	game.setStatus(GameStatus.game);
                	
                	break;
                }
               default: {
            	game.countDown();   
               	}
                }
                }
			default:
				break;
            }
        
    }
    
    public Game getGame() {
        return this.game;
    }
}
