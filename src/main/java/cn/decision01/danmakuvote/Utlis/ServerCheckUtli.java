package cn.decision01.danmakuvote.Utlis;

import org.bukkit.Bukkit;

public class ServerCheckUtli {
    static boolean hasOnlinePlayer() {
        return Bukkit.getOnlinePlayers().isEmpty();
    }
}
