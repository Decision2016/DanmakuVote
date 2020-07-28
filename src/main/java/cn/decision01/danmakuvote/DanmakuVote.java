package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Commands.*;
import cn.decision01.danmakuvote.Utils.DanmakuTaskMonitor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class DanmakuVote extends JavaPlugin {
    private int threadCount;
    private FileConfiguration config;

    private void Initialization() {
        this.saveDefaultConfig();
        this.reloadConfig();
        this.config = getConfig();
        this.threadCount = 0;

        DanmakuTaskMonitor monitor = new DanmakuTaskMonitor(this);
        monitor.addLaterTask();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Initialization();
        Bukkit.getPluginCommand("bvote").setExecutor(new VoteCommands(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void switchPlugin() {
        boolean switchStatus = config.getBoolean("Setting.switch");
        config.set("Setting.switch", ! switchStatus);
        this.saveConfig();
    }

    public boolean getSwitchStatus() {
        return config.getBoolean("Setting.switch");
    }

    public boolean addThreadCount() {
        if (threadCount >= 1) return false;
        threadCount ++;
        return true;
    }

    void threadFinished() {
        threadCount --;
    }

    public int getThreadCount() {
        return threadCount;
    }
}
