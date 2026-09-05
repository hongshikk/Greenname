package com.hongshikaikai.greenname.manager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
public class DataManager {
    private final JavaPlugin plugin;
    private final Map<String, Long> expiryMap = new HashMap<>();   // 玩家名小写 -> 到期时间戳
    private final Map<String, Boolean> toggleMap = new HashMap<>(); // 玩家名小写 -> 开关状态
    private File dataFile;
    private FileConfiguration dataConfig;
    public DataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        dataFile = new File(plugin.getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            try { dataFile.createNewFile(); } catch (Exception ignored) {}
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
        loadData();
    }
    private void loadData() {
        expiryMap.clear();
        toggleMap.clear();
        if (dataConfig.contains("expiry")) {
            for (String name : dataConfig.getConfigurationSection("expiry").getKeys(false)) {
                long time = dataConfig.getLong("expiry." + name);
                expiryMap.put(name.toLowerCase(), time);
            }
        }
        if (dataConfig.contains("toggle")) {
            for (String name : dataConfig.getConfigurationSection("toggle").getKeys(false)) {
                boolean enabled = dataConfig.getBoolean("toggle." + name);
                toggleMap.put(name.toLowerCase(), enabled);
            }
        }
    }
    public void saveData() {
        dataConfig.set("expiry", null);
        dataConfig.set("toggle", null);
        for (Map.Entry<String, Long> entry : expiryMap.entrySet()) {
            dataConfig.set("expiry." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Boolean> entry : toggleMap.entrySet()) {
            dataConfig.set("toggle." + entry.getKey(), entry.getValue());
        }
        try { dataConfig.save(dataFile); } catch (Exception e) { e.printStackTrace(); }
    }
    private String getKey(Player p) {
        return p.getName().toLowerCase();
    }
    public boolean hasGreenPermission(Player p) {
        String key = getKey(p);
        Long expiry = expiryMap.get(key);
        if (expiry == null || System.currentTimeMillis() >= expiry) {
            return false;
        }
        Boolean toggle = toggleMap.get(key);
        return toggle != null && toggle;
    }
    public boolean isExpired(Player p) {
        String key = getKey(p);
        Long expiry = expiryMap.get(key);
        return expiry == null || System.currentTimeMillis() >= expiry;
    }
    public void setExpiry(Player p, int days) {
        String key = getKey(p);
        long expiry = System.currentTimeMillis() + (days * 86400000L);
        expiryMap.put(key, expiry);
        toggleMap.put(key, true);
        saveData();
    }
    public void setToggle(Player p, boolean enabled) {
        String key = getKey(p);
        toggleMap.put(key, enabled);
        saveData();
    }
    public void ensureToggleExists(Player p) {
        String key = getKey(p);
        if (!toggleMap.containsKey(key)) {
            toggleMap.put(key, false);
            saveData();
        }
    }
    public void removeIfExpired(Player p) {
        String key = getKey(p);
        Long expiry = expiryMap.get(key);
        if (expiry != null && System.currentTimeMillis() >= expiry) {
            expiryMap.remove(key);
            saveData();
        }
    }
}