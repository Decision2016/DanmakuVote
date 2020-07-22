package cn.decision01.danmakuvote.Commands;

import cn.decision01.danmakuvote.DanmakuVote;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class VoteTimeSetExecutor implements CommandExecutor {
    private DanmakuVote plugin;

    public VoteTimeSetExecutor(DanmakuVote plugin) {
        this.plugin = plugin;


    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "random": {
                if (args.length != 3) return false;
                FileConfiguration config = plugin.getConfig();
                int floor, ceil;
                try {
                    floor = Integer.parseInt(args[1]);
                    ceil = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Command error.");
                    return false;
                }
                if (! config.getBoolean("Setting.random"))
                    config.set("Setting.random", true);
                config.set("Setting.floor", floor);
                config.set("Setting.ceil", ceil);
                plugin.saveConfig();
                return true;
            }
            case "static": {
                if (args.length != 2) return false;
                FileConfiguration config = plugin.getConfig();
                int spacing;
                try {
                    spacing = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("Command error.");
                    return false;
                }
                if (config.getBoolean("Setting.random"))
                    config.set("Setting.random", false);
                config.set("Setting.space", spacing);
                plugin.saveConfig();
                return true;
            }
            default: {
                sender.sendMessage("Command error");
                return false;
            }
        }
    }
}
