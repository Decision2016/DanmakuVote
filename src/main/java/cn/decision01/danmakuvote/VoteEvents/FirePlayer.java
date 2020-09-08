package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;
import cn.decision01.danmakuvote.Utils.FileUtil;
import cn.decision01.danmakuvote.Utils.RandomChooser;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FirePlayer extends VoteEvent {
    public FirePlayer(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
    }

    public final void effect() {
        Player player = RandomChooser.RandomPlayer(worldName);
        FileConfiguration config = FileUtil.getFileConfig(plugin, configFileName);
        player.setFireTicks(config.getInt(String.format("%s.ticks", configName)));
    }
}
