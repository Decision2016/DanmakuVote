package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteStatusExecutor implements CommandExecutor {
    private DanmakuVote plugin;

    public VoteStatusExecutor(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //todo: 对于命令的if判断考虑改为注解
        if (args.length != 1 || !(sender instanceof Player)) {
            return false;
        }

        long nextTimestamp = plugin.nextTimestamp;
        long space = nextTimestamp - System.currentTimeMillis() / 1000;
        String msg;
        if (nextTimestamp == -1) {
            msg = "目前暂无下一次投票任务";
        }
        else {
            if (space > 0)
                msg = String.format("距离下一次弹幕投票还有%d秒", space);
            else
                msg = "下一次弹幕投票即将进行";
        }
        sender.sendMessage(msg);

        return true;
    }
}
