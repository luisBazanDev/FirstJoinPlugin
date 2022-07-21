package pe.bazan.luis.plugins.firstjoinplugin;

import org.bukkit.plugin.java.JavaPlugin;
import pe.bazan.luis.plugins.firstjoinplugin.commands.FirstJoin;
import pe.bazan.luis.plugins.firstjoinplugin.listeners.PlayerFirstJoin;
import pe.bazan.luis.plugins.firstjoinplugin.listeners.PlayerJoin;

public final class FirstJoinPlugin extends JavaPlugin {
  private CustomYML db;
  private CustomYML customConfig;

  @Override
  public void onEnable() {
    // Plugin startup logic
    getLogger().info("Enabling plugin...");
    reloadFiles();
    new PlayerJoin(this);
    new PlayerFirstJoin(this);
    new FirstJoin(this);
    getLogger().info("Enabled plugin!");
  }

  public void reloadFiles() {
    this.customConfig = null;
    this.db = null;
    this.customConfig = new CustomYML("config", this);
    this.db = new CustomYML("db", this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public CustomYML getCustConf() {
    return customConfig;
  }

  public CustomYML getDb() {
    return db;
  }
}
