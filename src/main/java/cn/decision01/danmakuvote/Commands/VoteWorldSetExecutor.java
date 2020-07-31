package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class VoteWorldSetExecutor implements CommandExecutor {
    private final DanmakuVote plugin;

    public VoteWorldSetExecutor(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1 || !(sender instanceof Player)) {
            return false;
        }

        FileConfiguration config = plugin.getConfig();
        Player player = Bukkit.getPlayer(sender.getName());

        //todo: player exception error ?
        String worldName = player.getWorld().getName();
        config.set("Setting.worldName", worldName);
        sender.sendMessage("已将影响世界设置为当前所在世界.");
        plugin.saveConfig();
        return true;
    }
}
