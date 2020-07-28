package cn.decision01.danmakuvote.Utils;

import org.bukkit.Bukkit;

public class ServerCheckUtil {
    static boolean hasOnlinePlayer() {
        return Bukkit.getOnlinePlayers().isEmpty();
    }
}
