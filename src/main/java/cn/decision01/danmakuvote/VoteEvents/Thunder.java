package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;
import cn.decision01.danmakuvote.Utils.RandomChooser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Thunder extends VoteEvent {
    public Thunder(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
    }

    public void effect() {
        Player player = RandomChooser.RandomPlayer(worldName);
        Location location = player.getLocation();
        World world = Bukkit.getWorld(worldName);

        world.strikeLightning(location);
    }
}
