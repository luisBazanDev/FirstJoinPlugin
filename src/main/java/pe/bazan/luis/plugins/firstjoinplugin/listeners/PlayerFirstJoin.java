package pe.bazan.luis.plugins.firstjoinplugin.listeners;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import pe.bazan.luis.plugins.firstjoinplugin.FirstJoinPlugin;
import pe.bazan.luis.plugins.firstjoinplugin.PlayerJoined;

public class PlayerFirstJoin implements Listener {
  private FirstJoinPlugin plugin;

  public PlayerFirstJoin(FirstJoinPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  public static class Event extends org.bukkit.event.@NotNull Event {
    PlayerJoined playerJoined;
    public Event(PlayerJoined playerJoined) {
      this.playerJoined = playerJoined;
    }

    public PlayerJoined getPlayerJoined() {
      return playerJoined;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
      return null;
    }
  }
}
