package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.utils.DanmakuTaskMonitor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteSwitchExecutor implements CommandExecutor {
    private DanmakuVote plugin;

    public VoteSwitchExecutor(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 || !(sender instanceof Player)) {
            return false;
        }

        plugin.switchPlugin();
        DanmakuTaskMonitor monitor = new DanmakuTaskMonitor(plugin);
        monitor.addLaterTask();
        sender.sendMessage("DanmakuVote插件的状态已经设置为：" + (plugin.getSwitchStatus() ? "开启" : "关闭"));
        return true;
    }
}
