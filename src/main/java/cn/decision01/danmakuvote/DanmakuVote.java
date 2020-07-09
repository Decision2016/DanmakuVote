package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Commands.VoteDebugExecutor;
import cn.decision01.danmakuvote.Commands.VoteIdSetExecutor;
import cn.decision01.danmakuvote.Commands.VoteWorldSetExecutor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class DanmakuVote extends JavaPlugin {
    private boolean pluginSwitch;
    private FileConfiguration config;

    private void Initialization() {
        this.saveDefaultConfig();
        this.reloadConfig();
        this.config = getConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Initialization();
        Bukkit.getPluginCommand("setid").setExecutor(new VoteIdSetExecutor(this));
        Bukkit.getPluginCommand("setworld").setExecutor(new VoteWorldSetExecutor(this));
        Bukkit.getPluginCommand("debugvote").setExecutor(new VoteDebugExecutor(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
