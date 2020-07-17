package cn.decision01.danmakuvote.utils;

import org.bukkit.Bukkit;

public class ServerCheckUtil {
    static boolean hasOnlinePlayer() {
        return Bukkit.getOnlinePlayers().isEmpty();
    }
}
