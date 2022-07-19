package pe.bazan.luis.plugins.firstjoinplugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PlayerJoined {
  private String name;
  private Long firstJoin = null;
  private Long lastJoin = null;

  public PlayerJoined(String name, Long firt, Long last) {
    this.name = name;
    this.firstJoin = firt;
    this.lastJoin = last;
  }

  public Player getPlayer() {
    return Bukkit.getPlayer(name);
  }

  public boolean isFirstJoin() {
    return this.firstJoin == null;
  }

  public Long getFirstJoin() {
    return firstJoin;
  }

  public Long getLastJoin() {
    return lastJoin;
  }

  public void saveLast(CustomYML db) {
    db.setField(name + ".lastJoin", System.currentTimeMillis());
  }

  public static PlayerJoined getInstance(CustomYML db, String name) {
    return new PlayerJoined(
            name,
            db.getConfigField(name + ".firstJoin"),
            db.getConfigField(name + ".lastJoin")
    );
  }
}
