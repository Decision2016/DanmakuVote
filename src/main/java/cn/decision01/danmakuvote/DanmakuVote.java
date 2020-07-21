package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Commands.*;
import cn.decision01.danmakuvote.utils.DanmakuTaskMonitor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class DanmakuVote extends JavaPlugin {
    private boolean pluginSwitch;
    private int threadCount;
    private FileConfiguration config;

    private void Initialization() {
        this.saveDefaultConfig();
        this.reloadConfig();
        this.config = getConfig();
        this.threadCount = 0;
        this.pluginSwitch = config.getBoolean("Setting.switch");

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
        Bukkit.getPluginCommand("showthreadinfo").setExecutor(new VoteDebugExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void switchPlugin() {
        this.pluginSwitch = !this.pluginSwitch;
    }

    public boolean getSwitchStatus() {
        return this.pluginSwitch;
    }

    public boolean addThreadCount() {
        if (threadCount >= 1) return false;
        threadCount ++;
        return true;
    }

    public void threadFinished() {
        threadCount --;
    }

    public int getThreadCount() {
        return threadCount;
    }
}
