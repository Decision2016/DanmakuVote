package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.WorldEvent;
import cn.decision01.danmakuvote.Utils.RandomChooser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class MonsterPut extends WorldEvent {

    public MonsterPut(String _worldName, String _eventName, String _description, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _eventName, _description, _type, _plugin);
    }

    Player randomChosePlayer() {
        // todo： 存在空指针问题,考虑放到父类中
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
