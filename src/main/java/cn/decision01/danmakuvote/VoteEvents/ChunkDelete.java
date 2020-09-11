package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;
import cn.decision01.danmakuvote.Utils.RandomChooser;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ChunkDelete extends VoteEvent {
    public ChunkDelete(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
    }

    @Override
    public void effect() {
        Player player = RandomChooser.RandomPlayer(worldName);

        Chunk chunk = RandomChooser.RandomChunkChoose(player);

        chunk.unload(true);
    }
}
