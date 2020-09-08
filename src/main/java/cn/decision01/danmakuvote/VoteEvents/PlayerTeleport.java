package cn.decision01.danmakuvote.VoteEvents;

import cn.decision01.danmakuvote.DanmakuVote;
import cn.decision01.danmakuvote.Enums.EventEnum;
import cn.decision01.danmakuvote.Events.VoteEvent;
import cn.decision01.danmakuvote.Tasks.PlayerTeleportTask;
import cn.decision01.danmakuvote.Utils.RandomChooser;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class PlayerTeleport extends VoteEvent {


    public PlayerTeleport(String _worldName, String _configName, EventEnum _type, DanmakuVote _plugin) {
        super(_worldName, _configName, _type, _plugin);
    }

    private double randomValue(double origin, boolean isHeightPos) {
        Random random = new Random();
        double multiple = 1000.0;
        if (isHeightPos) multiple /= 100.0;
        boolean positive = random.nextBoolean();
        if (positive) {
            return origin + random.nextDouble() * multiple;
        }
        else {
            return origin - random.nextDouble() * multiple;
        }
    }

    private Location randomChoseLocation(Player player) {
        Location nowLocation = player.getLocation();
        nowLocation.setX(randomValue(nowLocation.getX(), false));
        nowLocation.setY(randomValue(nowLocation.getY(), true));
        nowLocation.setZ(randomValue(nowLocation.getZ(), false));
        return nowLocation;
    }

    public final void effect() {
        // todo: 完善事件生效
        Player player = RandomChooser.RandomPlayer(worldName);
        Location destination = randomChoseLocation(player);
        player.sendMessage(String.format("恭喜您被选为幸运玩家，5秒后将会传送到坐标(%d, %d, %d)，祝您一路顺风。", destination.getBlockX(), destination.getBlockY(), destination.getBlockZ()));
        PlayerTeleportTask task = new PlayerTeleportTask(player, destination);
        task.runTaskLater(plugin, 20 * 5);
    }
}
