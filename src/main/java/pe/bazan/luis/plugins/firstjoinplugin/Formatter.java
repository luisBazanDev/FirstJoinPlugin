package pe.bazan.luis.plugins.firstjoinplugin;

import org.bukkit.ChatColor;

public class Formatter {
  public static String format(String msg) {
    return ChatColor.translateAlternateColorCodes('&', msg);
  }
}
