package pe.bazan.luis.plugins.firstjoinplugin.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.executors.ExecutorType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;
import pe.bazan.luis.plugins.firstjoinplugin.FirstJoinPlugin;
import pe.bazan.luis.plugins.firstjoinplugin.Formatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class FirstJoin {
  private FirstJoinPlugin plugin;
  CommandAPICommand reload = new CommandAPICommand("reload")
          .withPermission("firstjoin.reload")
          .executes((sender, args) -> {
            sender.sendMessage(Formatter.format("&aReloading config..."));
            plugin.reloadFiles();
            sender.sendMessage(Formatter.format("&aConfiguration reloaded"));
          }, ExecutorType.ALL);

  CommandAPICommand itemsAdd = new CommandAPICommand("add")
          .withPermission("firstjoin.items.add")
          .withArguments(new IntegerArgument("slot", 0, 40))
          .executesPlayer((sender, args) -> {
            ItemStack itemStack = sender.getInventory().getItemInMainHand();
            if(itemStack == null || itemStack.getType().equals(Material.AIR)) {
              sender.sendMessage(Formatter.formatPlaceHolders(sender, "&cItem undefined, please place item in your main hand."));
              return;
            }
            String encodeObject;
            try {
              ByteArrayOutputStream io = new ByteArrayOutputStream();
              BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
              os.writeObject(itemStack);
              os.flush();

              byte[] serializedObject = io.toByteArray();
              encodeObject = Base64.getEncoder().encodeToString(serializedObject);
            } catch (IOException e) {
              sender.sendMessage(Formatter.formatPlaceHolders(sender, "&cError serializing this item"));
              e.printStackTrace();
              return;
            }
            plugin.getCustConf().setField("items." + args.get(0), encodeObject);
            sender.sendMessage(Formatter.formatPlaceHolders(sender, "&aSuccessfully, save this item."));
          });

  CommandAPICommand items = new CommandAPICommand("items")
          .withPermission("firstjoin.items")
          .withSubcommand(itemsAdd)
          .executes((sender, args) -> {
            sender.sendMessage(Formatter.format("&a================ &6&lFirst Join Plugin&a ================"));
            sender.sendMessage(Formatter.format("&e/firstjoin items add&7 - add item from your hand."));
            sender.sendMessage(Formatter.format("&e/firstjoin items view&7 - open inventory for view items."));
            sender.sendMessage(Formatter.format("&a===================================================="));
          }, ExecutorType.ALL);

  CommandAPICommand firstjoin = new CommandAPICommand("firstjoin")
          .withPermission("firstjoin.info")
          .withSubcommand(reload)
          .withSubcommand(items)
          .executes((sender, args) -> {
            sender.sendMessage(Formatter.format("&a================ &6&lFirst Join Plugin&a ================"));
            sender.sendMessage(Formatter.format("&aBy:&f Luis Bazán"));
            sender.sendMessage(Formatter.format("&aVersion:&f %version%".replaceAll("%version%", plugin.getDescription().getVersion())));
            sender.sendMessage(Formatter.format("&8GitHub:&f https://github.com/luisBazanDev/FirstJoinPlugin"));
            sender.sendMessage(Formatter.format("&a===================================================="));
          }, ExecutorType.ALL);

  public FirstJoin(FirstJoinPlugin plugin) {
    this.plugin = plugin;

    firstjoin.register();
  }
}
