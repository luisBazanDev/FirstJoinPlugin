package pe.bazan.luis.plugins.firstjoinplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pe.bazan.luis.plugins.firstjoinplugin.FirstJoinPlugin;
import pe.bazan.luis.plugins.firstjoinplugin.PlayerJoined;

public class PlayerJoin implements Listener {
  private FirstJoinPlugin plugin;

  public PlayerJoin(FirstJoinPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    e.setJoinMessage(plugin.getCustConf().getConfigField("msg-join"));
    final PlayerJoined playerJoined = PlayerJoined.getInstance(plugin.getDb(), e.getPlayer().getName());
    if(!playerJoined.isFirstJoin()) {
      playerJoined.saveLast(plugin.getDb());
      return;
    }
    playerJoined.saveFirst(plugin.getDb());
    plugin.getServer().getPluginManager().callEvent(new PlayerFirstJoin.Event(playerJoined));
  }
}
