package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class VoteIdSetExecutor implements CommandExecutor {
    private final DanmakuVote plugin;

    public VoteIdSetExecutor(DanmakuVote plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2 || !(sender instanceof Player)) {
            return false;
        }

        FileConfiguration config = plugin.getConfig();
        config.set("Bilibili.room_id", Integer.parseInt(args[1]));
        plugin.saveConfig();
        return true;
    }
}
