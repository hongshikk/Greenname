package com.hongshikaikai.greenname.manager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
public class MessageManager {
    private final JavaPlugin plugin;
    private YamlConfiguration messagesConfig;
    private final String language;
    private static final List<String> DEFAULT_LANGUAGES = Arrays.asList(
            "messages_zh.yml",      // 简体中文
            "messages_zh_tw.yml",   // 繁体中文
            "messages_en.yml",      // 英语
            "messages_ru.yml",      // 俄语
            "messages_de.yml",      // 德语
            "messages_fr.yml",      // 法语
            "messages_es.yml",      // 西班牙语
            "messages_pt.yml",      // 葡萄牙语
            "messages_ja.yml",      // 日语
            "messages_ko.yml",      // 韩语
            "messages_id.yml",      // 印尼语
            "messages_ar.yml"       // 阿拉伯语
    );
    public MessageManager(JavaPlugin plugin, String language) {
        this.plugin = plugin;
        this.language = language;
        loadMessages();
    }
    private void loadMessages() {
        File langDir = new File(plugin.getDataFolder(), "language");
        if (!langDir.exists()) {
            langDir.mkdirs();
        }
        for (String fileName : DEFAULT_LANGUAGES) {
            releaseLanguageFile(fileName);
        }
        String fileName = "messages_" + language + ".yml";
        File langFile = new File(langDir, fileName);
        if (!langFile.exists()) {
            File zhFile = new File(langDir, "messages_zh.yml");
            if (zhFile.exists()) {
                try {
                    Files.copy(zhFile.toPath(), langFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception ignored) {}
            }
        }
        messagesConfig = YamlConfiguration.loadConfiguration(langFile);
    }
    private void releaseLanguageFile(String fileName) {
        File langDir = new File(plugin.getDataFolder(), "language");
        File target = new File(langDir, fileName);
        if (!target.exists()) {
            try (InputStream in = plugin.getResource("language/" + fileName)) {
                if (in != null) {
                    Files.copy(in, target.toPath());
                }
            } catch (Exception ignored) {}
        }
    }
    public String getMessage(String key) {
        String msg = messagesConfig.getString(key);
        if (msg == null) {
            return getDefaultMessage(key);
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
    public String getMessage(String key, String... replacements) {
        String msg = getMessage(key);
        for (int i = 0; i < replacements.length; i += 2) {
            if (i + 1 < replacements.length) {
                msg = msg.replace("%" + replacements[i] + "%", replacements[i + 1]);
            }
        }
        return msg;
    }
    private String getDefaultMessage(String key) {
        switch (key) {
            case "prefix": return "&a[GreenName]&r";
            case "admin.set.success": return "%prefix% &aSet &b%player% &agreen name for &e%days% &adays, auto-enabled.";
            case "admin.set.usage": return "%prefix% &cUsage: /setgreen <player> <days>";
            case "admin.set.no-permission": return "%prefix% &cYou don't have permission!";
            case "admin.set.player-not-online": return "%prefix% &cPlayer is not online!";
            case "admin.set.invalid-days": return "%prefix% &cDays must be a positive integer!";
            case "player.toggle.on": return "%prefix% &aYour green name is now enabled!";
            case "player.toggle.off": return "%prefix% &cYour green name is now disabled.";
            case "player.toggle.usage": return "%prefix% &eUsage: /greenname on or /greenname off";
            case "player.toggle.invalid-arg": return "%prefix% &cInvalid argument! Use on or off.";
            case "player.toggle.expired": return "%prefix% &cYour green name has expired, cannot enable! Contact admin.";
            default: return "&cUnknown message: " + key;
        }
    }
}