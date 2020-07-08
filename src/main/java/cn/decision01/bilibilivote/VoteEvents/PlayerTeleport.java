package cn.decision01.bilibilivote.VoteEvents;

import cn.decision01.bilibilivote.Enums.EventEnum;
import cn.decision01.bilibilivote.event.PlayerEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerTeleport extends PlayerEvent {
    public PlayerTeleport(String _eventName, String _description, EventEnum _type) {
        super(_eventName, _description, _type);
    }

    private Player randomChosePlayer() {
        // todo: 随机选取玩家
        return null;
    }

    private Location randomChoseLocation() {
        // todo: 随机选取地点
        return null;
    }

    public void effect() {
        // todo: 完善事件生效
        Player player = randomChosePlayer();
        Location destination = randomChoseLocation();
        player.teleport(destination);
    }
}
