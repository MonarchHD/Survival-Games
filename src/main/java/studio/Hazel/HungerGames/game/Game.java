package studio.Hazel.HungerGames.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import lombok.Getter;
import lombok.Setter;
import studio.Hazel.HungerGames.HungerGames;
import studio.Hazel.HungerGames.player.HungerPlayer;
import studio.Hazel.HungerGames.timer.Timer;
import studio.Hazel.HungerGames.util.UnzipUtility;

public class Game
{
    private GameStatus status = GameStatus.lobby;
    private long progress;
    private int peopleinMatch;
    private Collection<HungerPlayer> players;
    private Collection<HungerPlayer> spectators;
    private boolean finished;
    private boolean timerChange;
    @Nullable
    private HungerPlayer winner;
    private HungerGames plugin;
    private BukkitTask task;
    private Timer timer;
    private World world;
    private @Setter @Getter Date gameStarted;
    private Integer count;
    
    public Game(final HungerGames plugin) {
        this.progress = 0L;
        this.peopleinMatch = 0;
        this.players = Collections.emptyList();
        this.spectators = Collections.emptyList();
        this.finished = false;
        this.winner = null;
        this.plugin = plugin;
        this.status = GameStatus.lobby;
        this.count = plugin.getSettings().getInteger("countdown-timer") * 60;
        this.timer = new Timer(this);
        this.task = Bukkit.getScheduler().runTaskTimer((Plugin)plugin, (Runnable)this.timer, 0L, 20L);

        final File file = new File("");
        System.out.println(file.getAbsoluteFile());
        if (!new File("Game.zip").exists()) {
        	
           Bukkit.getLogger().log(Level.SEVERE,"Game.zip was not found, shutting down the server.");
            Bukkit.shutdown();
            return;
        }
        if(new File("Game").exists()) {
        new File("Game").delete();	
        }
        try {
			new UnzipUtility().unzip("Game.zip", "Game");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Bukkit.getServer().createWorld(new WorldCreator("Game"));
    }
    
    public void unzip(final String zipFilePath, final String destDir) {
        final File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        final byte[] buffer = new byte[1024];
        try {
            final FileInputStream fis = new FileInputStream(zipFilePath);
            final ZipInputStream zis = new ZipInputStream(fis);
            for (ZipEntry ze = zis.getNextEntry(); ze != null; ze = zis.getNextEntry()) {
                final String fileName = ze.getName();
                final File newFile = new File(String.valueOf(destDir) + File.separator + fileName);
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                new File(newFile.getParent()).mkdirs();
                final FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
            }
            zis.closeEntry();
            zis.close();
            fis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void eleminate(final HungerPlayer victim, @Nullable final HungerPlayer killer) {
        this.players.remove(victim);
        killer.addKill();
    }
    
    public void countDown() {
        --this.count;
    }
    
	public void teleportPlayers() {
    	int i = 0;
    for(Player p : Bukkit.getOnlinePlayers()) {
    	p.setAllowFlight(false);
    	p.teleport(HungerGames.getPlugin().getMapManager().getCurrentMap().getSpawnLocations().get(i));
    	i+=1;
    }
    }
    
    public GameStatus getStatus() {
        return this.status;
    }
    
    public void setStatus(final GameStatus status) {
    	this.setTimerChange(true);
        this.status = status;
    }
    
    public long getProgress() {
        return this.progress;
    }
    
    public void setProgress(final long progress) {
        this.progress = progress;
    }
    
    public int getPeopleinMatch() {
        return this.peopleinMatch;
    }
    
    public void setPeopleinMatch(final int peopleinMatch) {
        this.peopleinMatch = peopleinMatch;
    }
    
    public Collection<HungerPlayer> getPlayers() {
        return this.players;
    }
    
    public Collection<HungerPlayer> getSpectators() {
        return this.spectators;
    }
    
    public boolean isFinished() {
        return this.finished;
    }
    
    public void setFinished(final boolean finished) {
        this.finished = finished;
    }
    
    public void setTimerChange(final boolean timerChange) {
        this.timerChange = timerChange;
    }
    
    public boolean isTimerChange() {
        return this.timerChange;
    }
    
    @Nullable
    public HungerPlayer getWinner() {
        return this.winner;
    }
    
    public HungerGames getPlugin() {
        return this.plugin;
    }
    
    public BukkitTask getTask() {
        return this.task;
    }
    
    public Timer getTimer() {
        return timer;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(final Integer count) {
        this.count = count;
    }
    
    
}
