package cn.decision01.danmakuvote.Tasks;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class VoteEventTask extends BukkitRunnable {
    private final DanmakuVote plugin;

    public VoteEventTask(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        BukkitTask task = new DanmakuListenTask(plugin).runTaskAsynchronously(plugin);
        Bukkit.getLogger().info("Event async thread started.");
    }
}
