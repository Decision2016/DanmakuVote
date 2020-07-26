package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoteCommands implements TabExecutor {
    private DanmakuVote plugin;
    private String[] subCommands = {"setid", "setworld", "switch", "time", "status"};

    public VoteCommands(DanmakuVote plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "setid": {
                VoteIdSetExecutor idSetExecutor = new VoteIdSetExecutor(plugin);
                return idSetExecutor.onCommand(sender, command, label, args);
            }
            case "setworld": {
                VoteWorldSetExecutor worldSetExecutor = new VoteWorldSetExecutor(plugin);
                return worldSetExecutor.onCommand(sender, command, label, args);
            }
            case "switch": {
                VoteSwitchExecutor switchExecutor = new VoteSwitchExecutor(plugin);
                return switchExecutor.onCommand(sender, command, label, args);
            }
            case "time": {
                VoteTimeSetExecutor timeSetExecutor = new VoteTimeSetExecutor(plugin);
                return timeSetExecutor.onCommand(sender, command, label, args);
            }
            case "status": {
                VoteStatusExecutor statusExecutor = new VoteStatusExecutor(plugin);
                return statusExecutor.onCommand(sender, command, label, args);
            }
            default: {
                sender.sendMessage(ChatColor.RED + "BVote command argument error");
            }
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Arrays.asList(subCommands);
        }
        else {
            return new ArrayList<>();
        }
    }
}
