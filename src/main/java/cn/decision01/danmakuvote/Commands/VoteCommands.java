package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VoteCommands implements CommandExecutor {
    private DanmakuVote plugin;

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
            case "debug": {
                VoteDebugExecutor debugExecutor = new VoteDebugExecutor(plugin);
                return debugExecutor.onCommand(sender, command, label, args);
            }
        }
        return false;
    }
}
