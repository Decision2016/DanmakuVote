package cn.decision01.danmakuvote.Tasks;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Vote;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class DanmakuListenTask extends BukkitRunnable {
    private final DanmakuVote plugin;

    public DanmakuListenTask(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        FileConfiguration config = plugin.getConfig();

        Vote vote = new Vote(config.getString("Setting.worldName"), config, plugin);
        try {
            vote.runVote();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
