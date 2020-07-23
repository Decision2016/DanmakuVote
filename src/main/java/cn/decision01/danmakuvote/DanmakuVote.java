package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Commands.*;
import cn.decision01.danmakuvote.Utlis.DanmakuTaskMonitor;
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
        // todo: 配置文件不存在的情况下进行异常处理
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Initialization();
        Bukkit.getPluginCommand("setid").setExecutor(new VoteIdSetExecutor(this));
        Bukkit.getPluginCommand("setworld").setExecutor(new VoteWorldSetExecutor(this));
        Bukkit.getPluginCommand("time").setExecutor(new VoteTimeSetExecutor(this));
        Bukkit.getPluginCommand("voteswitch").setExecutor(new VoteSwitchExecutor(this));
        // Bukkit.getPluginCommand("showthreadinfo").setExecutor(new VoteDebugExecutor(this));
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
        Bukkit.getLogger().info("add thread");
        return true;
    }

    public void threadFinished() {
        Bukkit.getLogger().info("thread finished");
        threadCount --;
    }

    public int getThreadCount() {
        return threadCount;
    }
}
