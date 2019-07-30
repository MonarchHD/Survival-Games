package studio.Hazel.HungerGames;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.zaxxer.hikari.HikariDataSource;

import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import lombok.Getter;
import studio.Hazel.HungerGames.Map.MapManager;
import studio.Hazel.HungerGames.game.Game;
import studio.Hazel.HungerGames.kit.KitManager;

public class HungerGames extends JavaPlugin{
private static @Getter HungerGames plugin;
private @Getter Game runningGame;
private @Getter Settings settings;
private @Getter HikariDataSource hikariDataSource;
private @Getter KitManager kitManager;
private @Getter MapManager mapManager;

@Override
	public void onEnable() {
	plugin = this;
	registerListeners();
	registerCommands();
	this.settings = new Settings(this);
	  try {
		this.runningGame = new Game(this);
          loadHikariDataSource();
      } catch (Exception e) {
          System.out.print("Could not connect with the database.");
          e.printStackTrace();
          return;
      }
	  registerManagers();
	  setupScoreboard();
	}
private void setupScoreboard() {
Assemble assemble = new Assemble(this, new ScoreboardAdapter());
	
	//Set Interval (Tip: 20 ticks = 1 second)
	assemble.setTicks(1);
	//Set Style (Tip: Viper Style starts at -1 and goes down)
	assemble.setAssembleStyle(AssembleStyle.MODERN);	
}
private void registerManagers() {
this.kitManager = new KitManager(this);
this.mapManager = new MapManager(this);
}
private void registerCommands() {
	// TODO Auto-generated method stub
	
}
private void registerListeners() {
Bukkit.getPluginManager().registerEvents(new HungerGamesListener(), this);	
}
@Override
	public void onDisable() {
	}
private void loadHikariDataSource() throws IOException, SQLException {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(settings.getString("database.url" + "/" + settings.getString("database.name")));
    dataSource.setUsername(settings.getString("database.user"));
    dataSource.setPassword(settings.getString("database.pass"));
    dataSource.setMaximumPoolSize(settings.getInteger("database.threads"));
    dataSource.setThreadFactory(new com.google.common.util.concurrent.ThreadFactoryBuilder().setDaemon(true)
            .setNameFormat("hikari-sql-pool-%d").build());
    this.hikariDataSource = dataSource;
}

}
