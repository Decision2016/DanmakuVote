package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;
import cn.decision01.danmakuvote.Utils.RandomChooser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class MonsterPut extends VoteEvent {

    public MonsterPut(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
    }

    Player randomChosePlayer() {
        List<Player> players = Bukkit.getWorld(worldName).getPlayers();
        Collections.shuffle(players);
        return players.get(0);
    }

    public final void effect() {
        Player player = randomChosePlayer();
        Location location = player.getLocation();
        Bukkit.getWorld(worldName).spawnEntity(location, RandomChooser.RandomMonsterChoose(super.plugin));
    }
}
