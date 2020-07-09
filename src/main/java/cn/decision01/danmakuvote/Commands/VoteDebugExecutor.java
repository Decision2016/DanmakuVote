package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Tasks.DanmakuListenTask;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class VoteDebugExecutor implements CommandExecutor {
    private final DanmakuVote plugin;

    public VoteDebugExecutor(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 || !(sender instanceof Player)) {
            return false;
        }

        BukkitTask task = new DanmakuListenTask(plugin).runTask(plugin);
        Bukkit.getLogger().info("任务启动");
        return true;
    }
}
