package pe.bazan.luis.plugins.firstjoinplugin.listeners;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.jetbrains.annotations.NotNull;
import pe.bazan.luis.plugins.firstjoinplugin.FirstJoinPlugin;
import pe.bazan.luis.plugins.firstjoinplugin.Formatter;
import pe.bazan.luis.plugins.firstjoinplugin.PlayerJoined;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class PlayerFirstJoin implements Listener {
  private FirstJoinPlugin plugin;

  public PlayerFirstJoin(FirstJoinPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onFirstJoin(Event e) {
    ConfigurationSection items = plugin.getCustConf().getConfigField("items");
    items.getKeys(false).forEach((slot) -> {
      String item = plugin.getCustConf().getConfigField("items."+slot);
      try {
        byte[] decode = Base64.getDecoder().decode(item);
        ByteArrayInputStream in = new ByteArrayInputStream(decode);
        BukkitObjectInputStream is = new BukkitObjectInputStream(in);
        ItemStack itemStack = (ItemStack) is.readObject();
        e.getPlayer().getInventory().setItem(Integer.valueOf(slot), itemStack);
      } catch (IOException | ClassNotFoundException ex) {
        ex.printStackTrace();
      }
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
      e.getPlayer().sendMessage(Formatter.formatPlaceHolders(e.getPlayer(), line));
    });
  }

  public static class Event extends org.bukkit.event.@NotNull Event {
    private static HandlerList handlerList = new HandlerList();
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
    public HandlerList getHandlers() {
      return handlerList;
    }
    public static HandlerList getHandlerList() {
      return handlerList;
    }
  }
}
