package com.hongshikaikai.greenname.command;
import com.hongshikaikai.greenname.Greenname;
import com.hongshikaikai.greenname.manager.DataManager;
import com.hongshikaikai.greenname.manager.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class GreenNameCommand implements CommandExecutor {
    private final Greenname plugin;
    private final DataManager dataManager;
    private final MessageManager messageManager;
    public GreenNameCommand(Greenname plugin) {
        this.plugin = plugin;
        this.dataManager = plugin.getDataManager();
        this.messageManager = plugin.getMessageManager();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String prefix = messageManager.getMessage("prefix");

        if (cmd.getName().equalsIgnoreCase("setgreen")) {
            return handleSetGreen(sender, args, prefix);
        } else if (cmd.getName().equalsIgnoreCase("greenname")) {
            return handleGreenName(sender, args, prefix);
        }
        return false;
    }
    private boolean handleSetGreen(CommandSender sender, String[] args, String prefix) {
        if (!sender.hasPermission("greennames.admin")) {
            sender.sendMessage(messageManager.getMessage("admin.set.no-permission").replace("%prefix%", prefix));
            return true;
        }
        if (args.length != 2) {
            sender.sendMessage(messageManager.getMessage("admin.set.usage").replace("%prefix%", prefix));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(messageManager.getMessage("admin.set.player-not-online").replace("%prefix%", prefix));
            return true;
        }
        int days;
        try {
            days = Integer.parseInt(args[1]);
            if (days <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            sender.sendMessage(messageManager.getMessage("admin.set.invalid-days").replace("%prefix%", prefix));
            return true;
        }
        dataManager.setExpiry(target, days);
        String successMsg = messageManager.getMessage("admin.set.success", "player", target.getName(), "days", String.valueOf(days));
        String finalMsg = successMsg.replace("%prefix%", prefix);
        sender.sendMessage(finalMsg);
        target.sendMessage(finalMsg);
        return true;
    }
    private boolean handleGreenName(CommandSender sender, String[] args, String prefix) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage(messageManager.getMessage("player.toggle.usage").replace("%prefix%", prefix));
            return true;
        }
        String option = args[0].toLowerCase();
        if (!option.equals("on") && !option.equals("off")) {
            player.sendMessage(messageManager.getMessage("player.toggle.invalid-arg").replace("%prefix%", prefix));
            return true;
        }
        if (dataManager.isExpired(player)) {
            player.sendMessage(messageManager.getMessage("player.toggle.expired").replace("%prefix%", prefix));
            return true;
        }
        boolean newState = option.equals("on");
        dataManager.setToggle(player, newState);
        if (newState) {
            player.sendMessage(messageManager.getMessage("player.toggle.on").replace("%prefix%", prefix));
        } else {
            player.sendMessage(messageManager.getMessage("player.toggle.off").replace("%prefix%", prefix));
        }
        return true;
    }
}