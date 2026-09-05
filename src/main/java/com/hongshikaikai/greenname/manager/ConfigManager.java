package com.hongshikaikai.greenname.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
public class ConfigManager {
    private final JavaPlugin plugin;
    private String language;
    private String chatFormat;
    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig(); // 释放 config.yml
        reloadConfig();
    }
    public void reloadConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        language = config.getString("language", "zh");
        chatFormat = config.getString("chat-format", "&a<%s>&d %s");
    }
    public String getLanguage() {
        return language;
    }
    public String getChatFormat() {
        return chatFormat;
    }
}