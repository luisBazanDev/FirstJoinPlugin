package pe.bazan.luis.plugins.firstjoinplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import pe.bazan.luis.plugins.firstjoinplugin.FirstJoinPlugin;
import pe.bazan.luis.plugins.firstjoinplugin.Formatter;
import pe.bazan.luis.plugins.firstjoinplugin.PlayerJoined;

import java.util.List;

public class PlayerFirstJoin implements Listener {
  private FirstJoinPlugin plugin;

  public PlayerFirstJoin(FirstJoinPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onFirstJoin(Event e) {
    List<String> items = plugin.getCustConf().getConfigField("messages");
    items.forEach((item) -> {

    });

    List<String> commands = plugin.getCustConf().getConfigField("commands");
    commands.forEach((line) -> {
      line = line.replaceAll("%player%", e.getPlayer().getName());
      line = line.replaceAll("%server%", plugin.getCustConf().getConfigField("server-name"));
      plugin.getServer().dispatchCommand(
              plugin.getServer().getConsoleSender(),
              line
      );
    });

    List<String> messages = plugin.getCustConf().getConfigField("messages");
    messages.forEach((line) -> {
      line = line.replaceAll("%player%", e.getPlayer().getName());
      line = line.replaceAll("%server%", plugin.getCustConf().getConfigField("server-name"));
      e.getPlayer().sendMessage(Formatter.format(line));
    });
  }

  public static class Event extends org.bukkit.event.@NotNull Event {
    PlayerJoined playerJoined;
    public Event(PlayerJoined playerJoined) {
      this.playerJoined = playerJoined;
    }

    public PlayerJoined getPlayerJoined() {
      return playerJoined;
    }

    public Player getPlayer() {
      return playerJoined.getPlayer();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
      return null;
    }
  }
}
