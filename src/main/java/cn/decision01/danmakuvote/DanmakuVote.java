package cn.decision01.danmakuvote;

import cn.decision01.danmakuvote.Commands.VoteDebugExecutor;
import cn.decision01.danmakuvote.Commands.VoteIdSetExecutor;
import cn.decision01.danmakuvote.Commands.VoteSwitchExecutor;
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

        this.pluginSwitch = config.getBoolean("Setting.switch");
        // todo: 配置文件不存在的情况下进行异常处理
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Initialization();
        Bukkit.getPluginCommand("setid").setExecutor(new VoteIdSetExecutor(this));
        Bukkit.getPluginCommand("setworld").setExecutor(new VoteWorldSetExecutor(this));
        Bukkit.getPluginCommand("votedebug").setExecutor(new VoteDebugExecutor(this));
        Bukkit.getPluginCommand("voteswitch").setExecutor(new VoteSwitchExecutor(this));
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

}
