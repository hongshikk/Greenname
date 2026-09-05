package com.hongshikaikai.greenname;

import com.hongshikaikai.greenname.command.GreenNameCommand;
import com.hongshikaikai.greenname.listener.GreenNameListener;
import com.hongshikaikai.greenname.manager.ConfigManager;
import com.hongshikaikai.greenname.manager.DataManager;
import com.hongshikaikai.greenname.manager.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Greenname extends JavaPlugin {

    private static Greenname instance;
    private ConfigManager configManager;
    private DataManager dataManager;
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        instance = this;

        // 1. 初始化配置管理器（自动加载 config.yml）
        configManager = new ConfigManager(this);

        // 2. 初始化语言管理器（从 language/ 文件夹加载）
        messageManager = new MessageManager(this, configManager.getLanguage());

        // 3. 初始化数据管理器（加载 data.yml）
        dataManager = new DataManager(this);

        // 4. 注册事件监听器
        getServer().getPluginManager().registerEvents(new GreenNameListener(this), this);

        // 5. 注册命令
        getCommand("setgreen").setExecutor(new GreenNameCommand(this));
        getCommand("greenname").setExecutor(new GreenNameCommand(this));

        getLogger().info("绿色名称插件已启动 (语言: " + configManager.getLanguage() + ")");
    }

    @Override
    public void onDisable() {
        if (dataManager != null) {
            dataManager.saveData();
        }
        getLogger().info("绿色名称插件已关闭");
    }

    public static Greenname getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}