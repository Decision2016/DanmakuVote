package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;
import cn.decision01.danmakuvote.Tasks.InventoryClearTask;
import cn.decision01.danmakuvote.Utils.RandomChooser;
import org.bukkit.entity.Player;

public class InventoryClear extends VoteEvent {

    public InventoryClear(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
    }

    public final void effect() {
        Player player = RandomChooser.RandomPlayer(worldName);
        player.sendMessage("你的背包将在30秒后被清空，清注意保留背包物品到其他地方");
        InventoryClearTask task = new InventoryClearTask(player);
        task.runTaskLater(plugin, 20 * 30);
    }
}
