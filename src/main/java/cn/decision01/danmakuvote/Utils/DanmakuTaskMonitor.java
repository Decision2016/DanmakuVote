package cn.decision01.danmakuvote.Utils;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Tasks.DanmakuListenTask;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class DanmakuTaskMonitor {
    private DanmakuVote plugin;

    public DanmakuTaskMonitor(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    public void addLaterTask() {
        FileConfiguration config = plugin.getConfig();
        int timeSpace;
        if (config.getBoolean("Setting.random"))
            timeSpace = RandomChooser.RandomTimeChoose(config.getInt("Setting.floor"), config.getInt("Setting.ceil"));
        else
            timeSpace = config.getInt("Setting.space");

        if (! plugin.getSwitchStatus()) return ;

        if (plugin.addThreadCount()) {
            DanmakuListenTask task = new DanmakuListenTask(plugin);
            task.runTaskLaterAsynchronously(plugin, timeSpace * Constants.onSecond);
            Bukkit.getLogger().info(String.format("下一次投票任务定于%d秒后进行", timeSpace));
        }
    }
}
