package com.hongshikaikai.greenname.listener;
import com.hongshikaikai.greenname.Greenname;
import com.hongshikaikai.greenname.manager.ConfigManager;
import com.hongshikaikai.greenname.manager.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
public class GreenNameListener implements Listener {
    private final Greenname plugin;
    private final DataManager dataManager;
    private final ConfigManager configManager;
    public GreenNameListener(Greenname plugin) {
        this.plugin = plugin;
        this.dataManager = plugin.getDataManager();
        this.configManager = plugin.getConfigManager();
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        dataManager.ensureToggleExists(p);
        dataManager.removeIfExpired(p);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (dataManager.hasGreenPermission(player)) {
            // 从配置中读取聊天格式，并应用颜色
            String format = configManager.getChatFormat();
            format = ChatColor.translateAlternateColorCodes('&', format);
            event.setFormat(format);
        }
    }
}