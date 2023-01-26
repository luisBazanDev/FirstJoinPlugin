package pe.bazan.luis.plugins.firstjoinplugin;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Formatter {
  public static  String format(String msg) {
    return ChatColor.translateAlternateColorCodes('&', msg);
  }
  public static String formatPlaceHolders(Player player, String msg) {
    if (FirstJoinPlugin.getInstance().isPlaceHolderActive()) {
      return format(PlaceholderAPI.setPlaceholders(player, msg));
    }
    return format(msg);
  }
}
